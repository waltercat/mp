package com.tsingglobal.system.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsingglobal.system.user.domain.UserModel;
import com.tsingglobal.system.user.service.UserService;
import com.tsingglobal.utils.CommonUtil;

@RestController
@RequestMapping(value="/system/user")
public class UserController {

	/**
	 * 
	    * @Title: 增加用户
	    * @Description: TODO(增加用户)
	    * @return void    用户信息
	    * @author tony
	 */
	@GetMapping(value="/getNewUser")
	public void addUser() {}
	
	/**
	 * 
	    * @Title: editUser
	    * @Description: TODO(加载机构对象)
	    * @param @param id    机构ID
	    * @return void    返回类型
	    * @author tony
	    * @throws
	 */
	@GetMapping(value="/getUser/{id}")
	public void editUser(@PathVariable  final Long id) {
		
		UserModel user = this.userService.loadUser(id);
	}
	
	@PostMapping(value= "/putNewUser")
	public void saveUser(@RequestBody  final UserModel user, final HttpServletResponse response) {
		
		if( CommonUtil.isEmpty(user) || CommonUtil.isEmpty(user.getLoginName()) || CommonUtil.isEmpty(user.getPassword()) ) {
			
		}
		
		user.setPassword(CommonUtil.MD5(user.getPassword().toLowerCase().trim()));
		
		userService.saveUser(user);
	}
	
	@PostMapping(value= "/putUser")
	public void updateUser(@RequestBody  final UserModel user, final HttpServletResponse response) {
		
		userService.updateUser(user);
	}
	
	@GetMapping(value="/delUser/{id}")
	public void delUser(@PathVariable  final long id) {
		
		userService.delUser(id);
	}
	
	@GetMapping(value="/viewUser/{id}")
	public void viewUser( @PathVariable  final Long id) {
		
		UserModel user = this.userService.loadUser(id);
	}
	
	@GetMapping(value="/getUsers/{pageNum}&{pageSize}")
	public void listUser( @PathVariable final int pageNum, @PathVariable final int pageSize) {
		
		PageHelper.startPage(pageNum, pageSize);
		
		PageInfo<UserModel> pageInfo = new PageInfo<UserModel>( userService.queryUser(null) );
	}
	
	@PostMapping(value= "/getUserForLogin")
	public void login(@RequestBody  final UserModel user, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		
		UserModel curUser = ( null == request.getSession().getAttribute("curUser")) ? null : (UserModel)request.getSession().getAttribute("curUser");
		
		if(  null == curUser ) {
			
			if( CommonUtil.isEmpty(user) || CommonUtil.isEmpty(user.getLoginName()) || CommonUtil.isEmpty(user.getPassword()) ) {
				
				CommonUtil.error(response, "缺少登录用户信息！");
				
				return ;
			}
			
			user.setPassword(CommonUtil.MD5(user.getPassword().trim()));
			
			List<UserModel> users =  userService.queryUser(user);
			
			if( users == null || users.size() != 1) {
				
				CommonUtil.error(response, "缺少登录用户信息！");
				
				return ;
			}
			
			curUser =  users.get(0);
			
			request.getSession().setAttribute( "curUser", curUser );			
		}
		
		CommonUtil.sendJsonData(response, JSON.toJSONString(curUser));
	}
	
	@GetMapping(value= "/deleteUserForLogout")
	public void deleteUserForLogout(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		
		request.getSession().setAttribute( "curUser", null );	
		
		CommonUtil.sendJsonData(response, JSON.toJSONString("用户退出系统！"));
	}
		

	@Autowired
	@Qualifier("userService")
	private UserService userService;
}
