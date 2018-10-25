
    /**  
    * @Title: OrganizationModel.java
    * @Package com.aas.system.org.domain
    * @Description: 机构实体
    * @author tony
    * @date 2018年10月18日
    * @version V1.0  
    */
    
package com.mp.system.user.domain;

import java.io.Serializable;

import com.mp.utils.CommonUtil;

/**
    * @ClassName: UserModel
    * @Description: TODO(用户实体)
    * @author tony
    * @date 2018年10月21日
    *
    */

public class UserModel implements Serializable {


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLockedOK() {
		return lockedOK;
	}

	public void setLockedOK(int lockedOK) {
		this.lockedOK = lockedOK;
	}

	public long getOrgID() {
		return orgID;
	}

	public void setOrgID(long orgID) {
		this.orgID = orgID;
	}

	public boolean validateOK() {
		
		return !( CommonUtil.isEmpty(userCode) || CommonUtil.isEmpty(loginName) ); 
	}

	private static final long serialVersionUID = -4997501622359192938L;
	
	//数据主键
	private long id;
	
	/**
	 * 用户编码
	 */
	private String userCode;
	
	//用户名称
	private String userName;
	
	//登录名称
	private String loginName;
	
	//登录密码
	private String password;
	
	//用户是否锁定 1=已锁定 0=未锁定 ，默认为0
	private int lockedOK;
	
	//组织机构ID
	private long orgID;
}
