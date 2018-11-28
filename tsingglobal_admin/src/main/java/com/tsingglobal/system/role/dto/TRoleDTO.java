package com.tsingglobal.system.role.dto;

import java.io.Serializable;

/**
* 描述：质量问题DTO
* @author Ay
* @date Thu Nov 29 00:34:17 CST 2018
*/
public class TRoleDTO implements Serializable{
	
	    /**
	    * @Fields field:field:{todo}(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = 7803295486680818105L;


	private long id;
    
	
	private String f_role_code;
    
	
	private String f_role_name;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getF_role_code() {
		return f_role_code;
	}


	public void setF_role_code(String f_role_code) {
		this.f_role_code = f_role_code;
	}


	public String getF_role_name() {
		return f_role_name;
	}


	public void setF_role_name(String f_role_name) {
		this.f_role_name = f_role_name;
	}
	
    
}
