package com.tsingglobal.system.tpermission.dto;

import java.io.Serializable;

/**
* 描述：质量问题DTO
* @author Ay
* @date Thu Nov 29 00:37:12 CST 2018
*/
public class TPermissionDTO implements Serializable{
	
	    /**
	    * @Fields field:field:{todo}(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = -2912106870620289864L;


	private long id;
    
	
	private String f_parentID;
    
	
	private String f_permission_code;
    
	
	private String f_permission_name;
    
	
	private String f_permission_url;
    
	
	private String f_permission_type;
    
	
	private int f_permission_status;
    
	
	private int f_permission_icon;
    
	
	private int f_permission_order;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getF_parentID() {
		return f_parentID;
	}


	public void setF_parentID(String f_parentID) {
		this.f_parentID = f_parentID;
	}


	public String getF_permission_code() {
		return f_permission_code;
	}


	public void setF_permission_code(String f_permission_code) {
		this.f_permission_code = f_permission_code;
	}


	public String getF_permission_name() {
		return f_permission_name;
	}


	public void setF_permission_name(String f_permission_name) {
		this.f_permission_name = f_permission_name;
	}


	public String getF_permission_url() {
		return f_permission_url;
	}


	public void setF_permission_url(String f_permission_url) {
		this.f_permission_url = f_permission_url;
	}


	public String getF_permission_type() {
		return f_permission_type;
	}


	public void setF_permission_type(String f_permission_type) {
		this.f_permission_type = f_permission_type;
	}


	public int getF_permission_status() {
		return f_permission_status;
	}


	public void setF_permission_status(int f_permission_status) {
		this.f_permission_status = f_permission_status;
	}


	public int getF_permission_icon() {
		return f_permission_icon;
	}


	public void setF_permission_icon(int f_permission_icon) {
		this.f_permission_icon = f_permission_icon;
	}


	public int getF_permission_order() {
		return f_permission_order;
	}


	public void setF_permission_order(int f_permission_order) {
		this.f_permission_order = f_permission_order;
	}
	
	
	
    
}
