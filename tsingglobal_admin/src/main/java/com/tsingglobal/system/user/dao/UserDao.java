package com.tsingglobal.system.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tsingglobal.system.user.domain.UserModel;

@Mapper
public interface UserDao {
	
	/*
	 * 加载用户
	 * id 用户ID
	 * 返回用户信息
	 */
	public UserModel loadUser( final long id);
	
	/**
	 * 
	    * @Title: queryUsers
	    * @Description: TODO(按条件查询用户信息)
	    * @param @param user 查询条件
	    * @return List<UserModel>    返回用户信息
	 */
	public List<UserModel> queryUsers( final UserModel user);
	
	/**
	 * 
	    * @Title: saveUser
	    * @Description: TODO(保存用户)
	    * @param @param user    用户
	    * @return void    返回类型 用户ID
	 */
	public void saveUser( final UserModel user);

	/**
	 * 
	    * @Title: updateUser
	    * @Description: TODO(更新用户信息)
	    * @param @param user    参数 用户信息
	 */
	public void updateUser( final UserModel user);

	
	/**
	 * 
	    * @Title: delUser
	    * @Description: TODO(删除用户)
	    * @param @param id    用户ID
	 */
	public void delUser( final long id);

	/**
	 * 	
	    * @Title: delUserByOrg
	    * @Description: TODO(删除指定组织机构下的全部用户)
	    * @param @param orgCode    指定机构编码
	 */
	public void delUserByOrg( final String orgCode);
	
	/**
	 * 
	    * @Title: queryUsersByOrg
	    * @Description: TODO(按机构、用户信息查询用户)
	    * @param @param conditionMap	查询条件
	    * @param @return    参数
	    * @return List<UserModel>    返回类型	指定机构、用户编码、用户名称的用户信息
	    * @throws
	 */
	public List<UserModel> queryUsersByOrg( final Map<String,Object> conditionMap);

	/**
	 * 
	    * @Title: delUsers
	    * @Description: TODO(批量删除用户)
	    * @param @param ids    待删除用户ID
	    * @return void    返回类型
	    * @throws
	 */
	public void delUsers(String[] ids);
}
