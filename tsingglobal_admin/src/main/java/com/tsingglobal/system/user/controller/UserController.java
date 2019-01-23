package com.tsingglobal.system.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsingglobal.system.org.domain.OrganizationModel;
import com.tsingglobal.system.user.domain.UserModel;
import com.tsingglobal.system.user.service.UserService;
import com.tsingglobal.utils.CommonUtil;
import com.tsingglobal.utils.IntegerValueFilter;

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
	 * @throws Exception 
	 * 
	    * @Title: editUser
	    * @Description: TODO(加载机构对象)
	    * @param @param id    机构ID
	    * @return void    返回类型
	    * @author tony
	    * @throws
	 */
	@GetMapping(value="/getUser/{id}")
	public void editUser(@PathVariable  final Long id) throws Exception {
		
		UserModel user = this.userService.loadUser(id);
		
		CommonUtil.sendJsonData(CommonUtil.getResponse(), JSON.toJSONString(user,new IntegerValueFilter()));
	}
	
	@PostMapping(value= "/putNewUser")
	public void saveUser(@RequestBody  final UserModel user, final HttpServletResponse response) throws Exception {
		
		if( CommonUtil.isEmpty(user) || CommonUtil.isEmpty(user.getLoginName()) || CommonUtil.isEmpty(user.getPassword()) ) {
			
		}
		
		user.setPassword(CommonUtil.MD5(user.getPassword().toLowerCase().trim()));
		
		userService.saveUser(user);
		
		CommonUtil.success(response, user.getUserCode());
	}
	
	@PostMapping(value= "/putUser")
	public void updateUser(@RequestBody  final UserModel user, final HttpServletResponse response) throws Exception {
		
		userService.updateUser(user);
		
		CommonUtil.success( CommonUtil.getResponse(), JSON.toJSONString(user));
	}
	
	@PostMapping(value= "/putUserForLock")
	public void lockUser(@RequestBody  final UserModel user, final HttpServletResponse response) throws Exception {
		
		userService.lockUser(user);
		
		CommonUtil.success( CommonUtil.getResponse(), JSON.toJSONString(user));
	}
	
	
	
	@DeleteMapping(value="/delUser/{id}")
	public void delUser(@PathVariable  final long id) throws Exception {
		
		userService.delUser(id);
		
		CommonUtil.success( CommonUtil.getResponse(), ""+id);
	}
	
	@DeleteMapping(value="/delUsers/{ids}")
	public void delUsers(@PathVariable  final String ids) throws Exception {
		
		userService.delUsers(ids);
		
		CommonUtil.success( CommonUtil.getResponse(), ""+ids);
	}
	
	@GetMapping(value="/viewUser/{id}")
	public void viewUser( @PathVariable  final Long id) {
		
		UserModel user = this.userService.loadUser(id);
	}
	
	
	
	@GetMapping(value="/getUsers")
	public void listUser( final int start, final int length, final int draw) throws Exception {
		PageHelper.startPage(start/length+1, length);
		
		final OrganizationModel org = new OrganizationModel();//new OrganizationModel();
		org.setOrgCode( CommonUtil.getRequest().getParameter("orgCode"));
		
		final UserModel user = new UserModel();
		user.setUserCode( CommonUtil.getRequest().getParameter("userCode") );
		user.setUserName(CommonUtil.getRequest().getParameter("userName"));
		
		PageInfo<UserModel> pageInfo = new PageInfo<UserModel>( userService.queryUserByOrg( org, user) );
		
		IntegerValueFilter intFilter = new IntegerValueFilter();
		JSONObject obj = new JSONObject();
		obj.put("draw", draw);
		obj.put("recordsTotal", pageInfo.getTotal());
		obj.put("recordsFiltered", pageInfo.getTotal());
		obj.put("data", JSONArray.parseArray( JSON.toJSONString(pageInfo.getList(),intFilter)));
		
		CommonUtil.sendJsonData(CommonUtil.getResponse(), obj.toJSONString());
	}
	
	@PostMapping(value= "/getUserForLogin")
	public void login(@RequestBody  final UserModel user, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		
		UserModel curUser = ( null == request.getSession().getAttribute("curUser")) ? null : (UserModel)request.getSession().getAttribute("curUser");
		
		if(  null == curUser ) {
			
			if( CommonUtil.isEmpty(user) || CommonUtil.isEmpty(user.getLoginName()) || CommonUtil.isEmpty(user.getPassword()) ) {
				
				CommonUtil.log("登录", "登录失败！",user.getLoginName());
				
				CommonUtil.error(response, "缺少登录用户信息！");
				
				return ;
			}
			
			user.setPassword(CommonUtil.MD5(user.getPassword().trim()));
			
			List<UserModel> users =  userService.queryUser(user);
			
			if( users == null || users.size() != 1) {
				
				CommonUtil.log("登录", "登录失败！缺少登录用户信息！",user.getLoginName());
				CommonUtil.error(response, "缺少登录用户信息！");
				
				return ;
			}
			
			curUser =  users.get(0);
			
			request.getSession().setAttribute( "curUser", curUser );			
			
			CommonUtil.log("登录", curUser.getUserName()+"登录成功！");
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
