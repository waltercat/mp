/**
 * 系统菜单JavaScript脚本
 * wud
 * 20181021
 */
var curPermission = {
		
		getPermissionsForTreeURL : '/system/permission/listPermissionForTree',
		
		permissionJSON : [],
		
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
		}

};
