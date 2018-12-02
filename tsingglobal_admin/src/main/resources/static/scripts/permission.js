/**
 * 系统菜单JavaScript脚本
 * wud
 * 20181021
 */
var setting = {
		view: {
			dblClickExpand: false,
			showLine: false,
			selectedMulti: false
		},
		data: {
			key: {
				name: "permissionName"
			},
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "parentID",
				rootPId: ""
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				
				if( treeNode.permissionCode != curPermission.selectedCode ){
					
					curPermission.selectedCode = treeNode.permissionCode;
					
					curPermission.selectedId = treeNode.id;
					
					$('#search_permissionCodeID').val("");
					
		    		$('#search_permissionNameID').val("");
				}
				
				curPermission.queryPermissions();			
				
				return true;
			}
		}
	};

var curPermission = {
		
		getPermissionsForTreeURL : '/system/permission/listPermissionForTree',
		
		getPermissionsForZTreeURL : '/system/permission/listPermissionForZTree',
		
		getPermissionsForPage : '/system/permission/getPermissionByPage', 
		
		deletePermissionURL : '/system/permission/delPermissionByCode',
		deletePermissionByCodeURL : '/system/permission/delPermissionByCode',
		
		permissionJSON : [],
		
		selectedCode : null,
		
		selectedId : '',
		
		editPermissionID : '',
		
		permission_add : function(title,url,w,h){
			
			if( null == curPermission.selectedCode ){
				
				$.Huimodalalert('请选择父级菜单！',2000)	
				return ;
				
			}else{
				layer_show(title,url,w,h);
			}
			
			
		},
		
		permission_edit : function(title,url,w,h,id){
			
			if( null == id ){
				
				$.Huimodalalert('请选择父级菜单！',2000)	
				return ;
				
			}else{
				curPermission.editPermissionID = id;
				layer_show(title,url,w,h);
			}
			
			
		},
		
		permission_del : function( obj, permissionCode ){
			
			layer.confirm('确认要删除吗？',function(index){
					$.ajax({
						url : curPermission.deletePermissionURL,
						async : false,
						type:'DELETE',
						contentType:'application/json',
						data : permissionCode,
						dataType:'json',
						success : function( permissionJSON ){
							$(obj).parents("tr").remove();
							layer.msg('已删除!',{icon:1,time:1000});
							curPermission.showPermissionForTree();
						},
						error : function( errorJSON ){}
				});
			});
		},
		
		delPermissionByCode : function( obj, permissionCode ){
			layer.confirm('确认要删除吗？',function(index){
					$.ajax({
						url : curPermission.deletePermissionByCodeURL,
						async : false,
						type:'DELETE',
						contentType:'application/json',
						data : permissionCode,
						dataType:'json',
						success : function( permissionJSON ){
							curPermission.delPermissionView();
							
							layer.msg('已删除!',{icon:1,time:1000});
							
							curPermission.showPermissionForTree();
						},
						error : function( errorJSON ){}
				});
			});
		},
		
		delPermissionView : function(){
			$("[name=chkItem]:checkbox").each(function(){
				if( $(this).prop("checked")){
					
					$(this).parents("tr").remove();
				}
			});
		},
		
		getCheckedPermission : function(){
			var permissionCodes = "";
			$("[name=chkItem]:checkbox").each(function(){
				if( $(this).prop("checked")){
					permissionCodes+=$(this).attr("permissionCode")+",";
				}
				
			});
			return permissionCodes;
		},
		
		showPermission : function(){
			
			var permission = $('#permissionID');
			
			//listPermissionForTree
			
			$.ajax({
				url : curPermission.getPermissionsForTreeURL,
				async : false,
				type:'get',
				contentType:'application/json',
				dataType:'json',
				success : function( permissionJSON ){
					
					curPermission.permissionJSON = permissionJSON;
					
					for( i = 0 ; i < curPermission.permissionJSON.children.length ; i++ ){
						
						permission.append( curPermission.createPermission( curPermission.permissionJSON.children[i] ) );
					}
					
				},
				error : function( errorJSON){
					
					window.location.href="/html/login.html";
				}
			});
		},
		
		createPermission : function( permission){
			
			var value = "<dl>";
			value += " <dt><i class='Hui-iconfont'>"+permission.icon+"</i> "+permission.name;
			value +=(permission.children.length > 0) ? "<i class='Hui-iconfont menu_dropdown-arrow'>&#xe6d5;</i></dt>" : "</dt>";
			
			if( permission.children.length > 0 ){
				value +="<dd><ul>";
				for( m = 0 ; m < permission.children.length ; m++){
					
					value += "<li><a href='#' onclick=\"javascript:curPermission.loadPage('"+permission.children[m].name+"','"+permission.children[m].url+"')\" title='"+permission.children[m].name+"'>"+permission.children[m].name+"</a></li> ";
				}
				value +="</ul></dd>";
			}
			
			value +="</dl>";
			
			return value;
		},
		
		loadPage : function( pName,pUrl ){
			
			var path = "../../html/";
			
			$('#navNameID').text(pName);
			
			$('#containerID').load(path+pUrl);
		},
		
		showPermissionForTree : function(){
			$.ajax({
				url : curPermission.getPermissionsForZTreeURL,
				async : false,
				type:'get',
				contentType:'application/json',
				dataType:'json',
				success : function( permissionJSON ){
					if( permissionJSON && permissionJSON.length && permissionJSON.length > 0){
						
						permissionJSON[0].open=true;
						
						curPermission.permissionJSON = permissionJSON;
						
						$.fn.zTree.init( $("#treeDemo"), setting, permissionJSON);
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
		
		queryPermissions : function(){
			
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
    ajax : {
    	"url" : curPermission.getPermissionsForPage,
    	"type" : 'get',
    	"data" : function( d ){
    		d.permissionCode = $('#search_permissionCodeID').val();
    		d.permissionName = $('#search_permissionNameID').val();
    		d.parentCode = curPermission.selectedCode;
    	}
    },
    "aLengthMenu": [[5, 10, 20, 100], [5, 10, 20, 100]],  
    "iDisplayLength":5 ,
    "columns" :[
    	{data:'id', "bSortable": false,render:function( data, type, full, meta ){ return '<input type="checkbox" name="chkItem" permissionCode="'+full.permissionCode+'" class="productCheckBox" name="productCheckBox" value="' + data + '" />'; }},
    	{data:'id',render:function( data, type, full, meta ){return data}},
    	{data:'permissionCode',render:function( data, type, full, meta ){return data}},
    	{data:'permissionName',render:function( data, type, full, meta ){return data}},
    	{data:'permissionURL',render:function( data, type, full, meta ){return data}},
    	{data:'permissionType',render:function( data, type, full, meta ){return (data == '1')?"<span>菜单权限</span>":"<span>按钮权限</span>"}},
    	{data:'permissionStatus',render:function( data, type, full, meta ){return (data == '1')?"<span>已启用</span>":"<span>已停用</span>"}},
    	{data:'permissionIcon',render:function( data, type, full, meta ){return "<i class='Hui-iconfont'>"+(( data && data.length < 4) ? '缺少图标' : data)+"</i>"}},
    	{data:'permissionCode',render:function( data, type, full, meta ){
    		return "<a title=\'编辑\' href=\'javascript:;\' onclick=\'curPermission.permission_edit(\"编辑权限\",\"html/permission/permission_edit.html\",\"400\",450,\""+full.id+"\")\' class=\'ml-5\' style=\'text-decoration:none\'><i class=\'Hui-iconfont\'>&#xe6df;</i></a>"
    		+"<a title=\'删除\' href=\'javascript:;\' onclick=\'curPermission.permission_del(this,\""+data+"\")\' class=\'ml-5\' style=\'text-decoration:none\'><i class=\'Hui-iconfont\'>&#xe6e2;</i></a>"}
    	}
    ],
    "aoColumnDefs": [{ "bSearchable": false, "bVisible": false, "aTargets": [1] }]//隐藏ID列。 
}).api();//

curPermission.showPermissionForTree();
