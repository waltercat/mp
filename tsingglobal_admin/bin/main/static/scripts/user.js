/**
 * 当前用户JavaScript脚本
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
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				curUser.selectedId = treeNode.id;
				curUser.selectedCode = treeNode.orgCode;
				curUser.queryUsersByOrg();
				return true;
			}
		}

	};

var curUser ={
		
		//用户信息
		userInfo:{},
		
		loginURL : '/system/user/getUserForLogin',
		
		logoutURL : '/system/user/deleteUserForLogout',
		
		getOrgForTreeURL : '/system/org/getOrgForTree',
		
		getUserForPage : '/system/user/getUsers',
		
		deleteUserURL : '/system/user/delUser',
		
		deleteUsersURL : '/system/user/delUsers',		
		
		curUserID : '',
		
		selectedId : '',
		
		selectedCode : '',
		
		selectedLevel : '',
		
		queryUsersByOrg : function(){
			$curTable.ajax.reload();
		},
		
		checkAll : function( obj ){
			
			$("[name=chkItem]:checkbox").prop("checked",obj.checked);
		},
		
		/*用户-停用*/
		stopUser : function(obj,id,orgId){
			layer.confirm('确认要停用吗？',function(index){
				
				curUser.lockUser(orgId,id,1);
				
				$(obj).parents("tr").find("#a"+id).parent().append('<a id=\'a'+id+'\' class=\'ml-5\' style=\"text-decoration:none\" onClick=\"curUser.startUser(this,\''+id+"\',\'"+orgId+'\')\" href=\"javascript:;\" title=\"启用\"><i class=\"Hui-iconfont\">&#xe6e1;</i></a>');
				$(obj).parents("tr").find("#label"+id).parent().html('<span id=\'label'+id+'\' class=\'label label-defaunt radius\'>已停用</span>');
				$(obj).remove();
				layer.msg('已停用!',{icon: 5,time:1000});
			});
		},

		/*用户-启用*/
		startUser : function(obj,id,orgId){
			layer.confirm('确认要启用吗？',function(index){
				curUser.lockUser(orgId,id,0);
				$(obj).parents("tr").find("#a"+id).parent().append('<a id=\'a'+id+'\' class=\'ml-5\' style=\"text-decoration:none\" onClick=\"curUser.stopUser(this,\''+id+"\',\'"+orgId+'\')\" href=\"javascript:;\" title=\"停用\"><i class=\"Hui-iconfont\">&#xe631;</i></a>');
				$(obj).parents("tr").find("#label"+id).parent().html('<span id=\'label'+id+'\' class=\'label label-success radius\'>已启用</span>');
				$(obj).remove();
				layer.msg('已启用!',{icon: 6,time:1000});
			});
		},
		
		doLogin : function( loginName, pwd, toURL){
			$.ajax({
				url : curUser.loginURL,
				async : false,
				type:'post',
				contentType:'application/json',
				data : JSON.stringify({'loginName':loginName, 'password':pwd}),
				dataType:'json',
				success : function( userJSON ){
					
					if( userJSON.code && userJSON.code=='404'){
						
						window.location.href="/html/login.html";
						
						return ;
					}
					
					curUser.userInfo = userJSON;	
					
					if( toURL  && toURL !='' ){
						
						window.location.href=toURL;
					}
				},
				error : function( errorJSON){
					
					window.location.href="/html/login.html";
				}
			});
		},
		
		showOrgForTree : function(){
			$.ajax({
				url : curUser.getOrgForTreeURL,
				async : false,
				type:'get',
				contentType:'application/json',
				dataType:'json',
				success : function( orgJSON ){
					
					if( orgJSON && orgJSON.length && orgJSON.length > 0){
						
						orgJSON[0].open=true;
						
						$.fn.zTree.init( $("#treeDemo"), setting, orgJSON);
					}
				},
				error : function( errorJSON){
					
					window.location.href="/html/login.html";
				}
			});
		},
		
		loadUserFromSession : function(){
			
			curUser.doLogin( '', '');
		},
		
		editUser : function(title,url,w,h,id){
			
			if( null == id ){
				
				$.Huimodalalert('请选择用户！',2000);
				return ;
				
			}else{
				curUser.editUserId = id;
				layer_show(title,url,w,h);
			}
			
			
		},
		
		delUser : function( obj, userId ){
			
			layer.confirm('确认要删除吗？',function(index){
					$.ajax({
						url : curUser.deleteUserURL+"/"+userId,
						async : false,
						type:'DELETE',
						contentType:'application/json',
						dataType:'json',
						success : function( orgJSON ){
							$(obj).parents("tr").remove();
							layer.msg('已删除!',{icon:1,time:1000});
						},
						error : function( errorJSON ){}
				});
			});
		},
		
		delUsers : function( obj, userId ){
			
			layer.confirm('确认要删除吗？',function(index){
					$.ajax({
						url : curUser.deleteUsersURL+"/"+userId,
						async : false,
						type:'DELETE',
						contentType:'application/json',
						dataType:'json',
						success : function( resultJSON ){
							curUser.delUsersView();
							layer.msg('已删除!',{icon:1,time:1000});
						},
						error : function( errorJSON ){}
				});
			});
		},
		
		delUsersView : function(){
			$("[name=chkItem]:checkbox").each(function(){
				if( $(this).prop("checked")){
					
					$(this).parents("tr").remove();
				}
			});
		},
		
		getCheckedUser : function(){
			var userIds = "";
			$("[name=chkItem]:checkbox").each(function(){
				if( $(this).prop("checked")){
					userIds+=$(this).attr("userId")+",";
				}
			});
			return userIds;
		},
		
		login: function( name, pwd, remenberOK ){
			
			if( !curUser.validateLoginUserOK( name, pwd ) ){				
				//写错误信息！
				$('#loginErrorID').show().text('账号、密码错误，请核对后重新输入！');
				$('#userLoginID').focus();
				return ;
			}else{
				$('#loginErrorID').hide().text('');
			} 
			
			curUser.doLogin( name, pwd, '/index.html' );
		},
		
		logout: function(){
			$.ajax({
				url : curUser.logoutURL,
				async : false,
				dataType:'json',
				success : function(){
					
					curUser.userInfo = {};
					
					window.location.href="/html/login.html";
				}
			});
		},
		
		loadUser: function(){},
		
		getUserInfo : function(){
			
			return curUser.userInfo;
		},
		
		user_add : function(title,url,w,h){
			
			if( '' == curUser.selectedId ){
				
				$.Huimodalalert('请选择组织机构！',2000)	
				return ;
				
			}else{
				layer_show(title,url,w,h);
			}
			
			
		},
		
		lockUser : function( orgId, userId , lockedOK){
			$.ajax({
				url : '/system/user/putUserForLock',
				async : false,
				type:'POST',
				contentType:'application/json',
				data : '{\"orgID\":'+orgId
				+',\"id\":'+userId	
				+',\"lockedOK\":\"'+lockedOK+'\"}',
				dataType:'json',
				success : function( msg ){},
				error : function( errorJSON){
					parent.location.href="/html/login.html";
				}
			});
		},
		
		validateLoginUserOK : function( name, pwd ){
			
			hasError = false;
			
			if( name ){
				
				if( null == name || '' == name ){
					hasError = true;
				}
			}else{
				hasError = true;
			}
			
			if( pwd ){
				
				if( null == pwd || '' == pwd ){
					hasError = true;
				}
			}else{
				
				hasError = true;
			}
			
			return !hasError;
		}
}

var $curTable = $('#table_list_id').dataTable({
	"striped": true,
	"processing": true,
    "serverSide": true,
    "bStateSave": true,
    "aaSorting": [[ 1, "desc" ]],//默认第几个排序
    "sPaginationType": "full_numbers",
//    "ajax": curUser.getOrgByPageURL,
    ajax : {
    	"url" : curUser.getUserForPage,
    	"type" : 'get',
    	"data" : function( d ){
    		d.userCode = $('#search_userCodeID').val();
    		d.userName = $('#search_userNameID').val();
    		d.orgCode = curUser.selectedCode;
    	}
    },
    "aLengthMenu": [[5, 10, 20, 100], [5, 10, 20, 100]],  
    "iDisplayLength":5 ,
    "columns" :[
    	{data:'id', "bSortable": false,render:function( data, type, full, meta ){ return '<input type="checkbox" name="chkItem" userId="'+full.id+'" class="productCheckBox" name="productCheckBox" value="' + data + '" />'; }},
    	{data:'id',render:function( data, type, full, meta ){return data}},
    	{data:'userCode',render:function( data, type, full, meta ){return data}},
    	{data:'loginName',render:function( data, type, full, meta ){return data}},
    	{data:'userName',render:function( data, type, full, meta ){return data}},
    	{data:'lockedOK',render:function( data, type, full, meta ){
    		if( full.lockedOK == "0"){
    			return "<span id=\'label"+full.id+"\'class=\'label label-success radius\'>已启用</span>";
			}else{				
				return "<span id=\'label"+full.id+"\' class=\'label label-defaunt radius\'>已停用</span>";
			}    		
    	}},
    	{data:'id',render:function( data, type, full, meta ){
    		var op = "<a title=\'编辑\' href=\'javascript:;\' onclick=\'curUser.editUser(\"编辑用户\",\"html/user/user_edit.html\",\"500\",380,\""+full.id+"\")\' class=\'ml-5\' style=\'text-decoration:none\'><i class=\'Hui-iconfont\'>&#xe6df;</i></a>"
    		+"<a title=\'删除\' href=\'javascript:;\' onclick=\'curUser.delUser(this,\""+data+"\")\' class=\'ml-5\' style=\'text-decoration:none\'><i class=\'Hui-iconfont\'>&#xe6e2;</i></a>";
    		
	    	if( full.lockedOK == "0"){
				op+="<a id=\'a"+full.id+"\' class=\'ml-5\' style=\'text-decoration:none\' onClick=\'curUser.stopUser(this,\""+full.id+"\",\""+full.orgID+"\")\' href=\'javascript:;\' title=\'停用\'><i class=\'Hui-iconfont\'>&#xe631;</i></a>";
			}else{				
				op+="<a id=\'a"+full.id+"\' class=\'ml-5\' style=\'text-decoration:none\' onClick=\'curUser.startUser(this,\""+full.id+"\",\""+full.orgID+"\")\' href=\'javascript:;\' title=\'启用\'><i class=\'Hui-iconfont\'>&#xe6e1;</i></a>";
			}
		
		return op;}}
    ],
    "aoColumnDefs": [{ "bSearchable": false, "bVisible": false, "aTargets": [1] }]//隐藏ID列。 
}).api();//

curUser.showOrgForTree();
