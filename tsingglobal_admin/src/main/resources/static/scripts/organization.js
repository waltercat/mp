/**
 * 系统字典JavaScript脚本
 * wud
 * 20181027
 */

var setting = {
		view: {
			dblClickExpand: false,
			showLine: false,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				
				curOrg.selectedCode = treeNode.orgCode;
				
				curOrg.selectedName = treeNode.name;
				
				curOrg.selectedId = treeNode.id;
				
				curOrg.selectedLevel = treeNode.level;
				
				curOrg.queryOrgs();			
				
				return true;
			}
		}
	};

var curOrg = {
		
		orgCode : '',
		
		orgName : '',
		
		selectedCode : '',
		
		selectedLevel : -1,
		
		editOrgId : '',
		
		orgJSON : {},
		
		getOrgByPageURL : '/system/org/getOrgByPage',
		
		getOrgForTreeURL : '/system/org/getOrgForTree',
		
		deleteOrgURL : '/system/org/deleteOrg',
		
		deleteOrgByCodeURL : '/system/org/deleteOrgByCode',
		
		org_add : function(title,url,w,h){
			
			if( null == curOrg.selectedId ){
				
				$.Huimodalalert('请选择父级机构！',2000)	
				return ;
				
			}else{
				layer_show(title,url,w,h);
			}
			
			
		},
		
		org_edit : function(title,url,w,h,id){
			
			if( null == id ){
				
				$.Huimodalalert('请选择父级机构！',2000)	
				return ;
				
			}else{
				curOrg.editOrgId = id;
				layer_show(title,url,w,h);
			}
			
			
		},
		
		org_del : function( obj, orgID ){
			
			layer.confirm('确认要删除吗？',function(index){
					$.ajax({
						url : curOrg.deleteOrgURL+"/"+orgID,
						async : false,
						type:'DELETE',
						contentType:'application/json',
//						data : JSON.stringify(delJSON),
						dataType:'json',
						success : function( orgJSON ){
							$(obj).parents("tr").remove();
							layer.msg('已删除!',{icon:1,time:1000});
						},
						error : function( errorJSON ){}
				});
			});
		},
		
		//deleteOrgByCode
		delOrgByCode : function( obj, orgCode ){
			layer.confirm('确认要删除吗？',function(index){
					$.ajax({
						url : curOrg.deleteOrgByCodeURL,
						async : false,
						type:'DELETE',
						contentType:'application/json',
						data : orgCode,
						dataType:'json',
						success : function( orgJSON ){
							
							curOrg.delOrgView();
							
							layer.msg('已删除!',{icon:1,time:1000});
							
							curOrg.showOrgForTree();
						},
						error : function( errorJSON ){}
				});
			});
		},
		
		delOrgView : function(){
			$("[name=chkItem]:checkbox").each(function(){
				if( $(this).prop("checked")){
					
					$(this).parents("tr").remove();
				}
			});
		},
		
		getCheckedOrg : function(){
			var orgCodes = "";
			$("[name=chkItem]:checkbox").each(function(){
				if( $(this).prop("checked")){
					orgCodes+=$(this).attr("orgCode")+",";
				}
				
			});
			return orgCodes;
		},
		
		showOrgForTree : function(){
			$.ajax({
				url : curOrg.getOrgForTreeURL,
				async : false,
				type:'get',
				contentType:'application/json',
				dataType:'json',
				success : function( orgJSON ){
					
					if( orgJSON && orgJSON.length && orgJSON.length > 0){
						
						orgJSON[0].open=true;
						
						curOrg.orgJSON = orgJSON;
						
						$.fn.zTree.init( $("#treeDemo"), setting, orgJSON);
					}
				},
				error : function( errorJSON){
					
					window.location.href="/html/login.html";
				}
			});
		},
		
		checkAll : function( obj ){
			
			$("[name=chkItem]:checkbox").prop("checked",obj.checked);
		},
		queryOrgs : function(){
			
			$curTable.ajax.reload();
		}
};

var $curTable = $('#table_list_id').dataTable({
	"striped": true,
	"processing": true,
    "serverSide": true,
    "bStateSave": true,
    "aaSorting": [[ 1, "desc" ]],//默认第几个排序
    "sPaginationType": "full_numbers",
//    "ajax": curOrg.getOrgByPageURL,
    ajax : {
    	"url" : curOrg.getOrgByPageURL,
    	"type" : 'get',
    	"data" : function( d ){
    		d.orgCode = $('#search_orgCodeID').val();
    		d.orgName = $('#search_orgNameID').val();
    		d.parentCode = curOrg.selectedCode;
    		d.parentLevel = curOrg.selectedLevel;
    	}
    },
    "aLengthMenu": [[5, 10, 20, 100], [5, 10, 20, 100]],  
    "iDisplayLength":5 ,
    "columns" :[
    	{data:'id', "bSortable": false,render:function( data, type, full, meta ){ return '<input type="checkbox" name="chkItem" orgCode="'+full.orgCode+'" class="productCheckBox" name="productCheckBox" value="' + data + '" />'; }},
    	{data:'id',render:function( data, type, full, meta ){return data}},
    	{data:'orgCode',render:function( data, type, full, meta ){return data}},
    	{data:'orgName',render:function( data, type, full, meta ){return data}},
    	{data:'orgCode',render:function( data, type, full, meta ){
    		return "<a title=\'编辑\' href=\'javascript:;\' onclick=\'curOrg.org_edit(\"编辑机构\",\"html/organization/organization_edit.html\",\"500\",300,\""+full.id+"\")\' class=\'ml-5\' style=\'text-decoration:none\'><i class=\'Hui-iconfont\'>&#xe6df;</i></a>"
    		+"<a title=\'删除\' href=\'javascript:;\' onclick=\'curOrg.delOrgByCode(this,\""+data+"\")\' class=\'ml-5\' style=\'text-decoration:none\'><i class=\'Hui-iconfont\'>&#xe6e2;</i></a>";}}
    	
    ],
    "aoColumnDefs": [{ "bSearchable": false, "bVisible": false, "aTargets": [1] }]//隐藏ID列。 
}).api();//

curOrg.showOrgForTree();
