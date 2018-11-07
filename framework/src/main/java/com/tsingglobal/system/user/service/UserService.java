package com.tsingglobal.system.user.service;

import java.util.List;

import com.tsingglobal.system.user.domain.UserModel;

public interface UserService {
	
	/**
	 * 
	    * @Title: loadUser
	    * @Description: TODO(按ID加载用户信息)
	    * @param @param id	用户ID
	    * @return UserModel    返回用户信息
	 */
	public UserModel loadUser( final long id);
	
	/**
	 * 
	    * @Title: queryUser
	    * @Description: TODO(按条件查询用户信息)
	    * @param @param user 查询条件
	    * @return List<UserModel>    用户信息
	 */
	public List<UserModel> queryUser( final UserModel user) ;
	
	
	/**
	 * 
	    * @Title: saveUser
	    * @Description: TODO(保存用户)
	    * @param @param user 用户
	    * @return long    返回类型 用户ID
	 */
	public long saveUser( final UserModel user ) ;
	
	/**
	 * 
	    * @Title: updateUser
	    * @Description: TODO(更新用户)
	    * @param @param user 用户
	    * @return long    返回类型 用户ID
	 */
	public long updateUser( final UserModel user ) ;
	
	/**
	 * 
	    * @Title: delUser
	    * @Description: TODO(删除用户)
	    * @param @param id 用户ID
	    * @return long    返回类型 用户ID
	 */
	public long delUser( final long id ) ;
	
	/**
	 * 
	    * @Title: delUserByOrg
	    * @Description: TODO(删除指定机构下的全部用户)
	    * @param @param id 机构编码
	    * @return long    返回类型 用户ID
	 */
	public long delUserByOrg( final String orgCode ) ;
}
