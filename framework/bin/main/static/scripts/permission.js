/**
 * 系统菜单JavaScript脚本
 * wud
 * 20181021
 */
var curPermission = {
		
		permissionJSON : [{id:'01',name:'资讯管理',icon:'&#xe616',url:'',children:[]},
			{id:'01',name:'产品管理',icon:'&#xe616',url:'',children:[{id:'0101',name:'品牌管理',icon:'',url:'article-list.html'},{id:'0101',name:'品牌管理',icon:'',url:'article-list.html'},{id:'0101',name:'品牌管理',icon:'',url:'article-list.html'}]},
			{id:'01',name:'资讯管理',icon:'&#xe616',url:'',children:[{id:'0101',name:'资讯管理',icon:'',url:'article-list.html'}]},
			{id:'01',name:'资讯管理',icon:'&#xe616',url:'',children:[{id:'0101',name:'资讯管理',icon:'',url:'article-list.html'}]}],
		
		showPermission : function(){
			
			var permission = $('#permissionID');
			
			for( i = 0 ; i < curPermission.permissionJSON.length ; i++ ){
				
				permission.append( curPermission.createPermission( curPermission.permissionJSON[i] ) );
			}
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
