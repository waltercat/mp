/**
 * 当前用户JavaScript脚本
 * wud
 * 20181021
 */

var curUser ={
		
		//用户信息
		userInfo:{},
		
		loginURL : '/system/user/getUserForLogin',
		
		logoutURL : '/system/user/deleteUserForLogout',
		
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
		
		loadUserFromSession : function(){
			
			curUser.doLogin( '', '');
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
