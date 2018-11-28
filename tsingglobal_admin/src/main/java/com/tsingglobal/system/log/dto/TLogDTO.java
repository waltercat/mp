package com.tsingglobal.system.log.dto;

import java.io.Serializable;
import java.sql.Time;

/**
* 描述：质量问题DTO
* @author Ay
* @date Thu Nov 29 00:39:27 CST 2018
*/
public class TLogDTO implements Serializable{
	
	
	    /**
	    * @Fields field:field:{todo}(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = -7259387477149485827L;


	private long id;
    
	
	private long f_org_id;
    
	
	private String f_org_name;
    
	
	private long f_user_id;
    
	
	private String f_user_name;
    
	
	private Time f_log_time;
    
	
	private String f_log_name;
    
	
	private String f_log_before;
    
	
	private String f_log_after;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getF_org_id() {
		return f_org_id;
	}


	public void setF_org_id(long f_org_id) {
		this.f_org_id = f_org_id;
	}


	public String getF_org_name() {
		return f_org_name;
	}


	public void setF_org_name(String f_org_name) {
		this.f_org_name = f_org_name;
	}


	public long getF_user_id() {
		return f_user_id;
	}


	public void setF_user_id(long f_user_id) {
		this.f_user_id = f_user_id;
	}


	public String getF_user_name() {
		return f_user_name;
	}


	public void setF_user_name(String f_user_name) {
		this.f_user_name = f_user_name;
	}


	public Time getF_log_time() {
		return f_log_time;
	}


	public void setF_log_time(Time f_log_time) {
		this.f_log_time = f_log_time;
	}


	public String getF_log_name() {
		return f_log_name;
	}


	public void setF_log_name(String f_log_name) {
		this.f_log_name = f_log_name;
	}


	public String getF_log_before() {
		return f_log_before;
	}


	public void setF_log_before(String f_log_before) {
		this.f_log_before = f_log_before;
	}


	public String getF_log_after() {
		return f_log_after;
	}


	public void setF_log_after(String f_log_after) {
		this.f_log_after = f_log_after;
	}
	
	
    
}
