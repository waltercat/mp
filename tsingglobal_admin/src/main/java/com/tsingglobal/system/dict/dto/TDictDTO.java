package com.tsingglobal.system.dict.dto;

import java.io.Serializable;

/**
* 描述：数据字典DTO
* @author Ay
* @date Thu Nov 29 00:41:57 CST 2018
*/
public class TDictDTO implements Serializable{
	
	    /**
	    * @Fields field:field:{todo}(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = -9106369249167527258L;


	private long id;
    
	
	private String f_dict_code;
    
	
	private String f_dict_name;
    
	
	private int f_dict_type;
    
	
	private String f_dict_value;
    
	
	private int f_dict_system;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getF_dict_code() {
		return f_dict_code;
	}


	public void setF_dict_code(String f_dict_code) {
		this.f_dict_code = f_dict_code;
	}


	public String getF_dict_name() {
		return f_dict_name;
	}


	public void setF_dict_name(String f_dict_name) {
		this.f_dict_name = f_dict_name;
	}


	public int getF_dict_type() {
		return f_dict_type;
	}


	public void setF_dict_type(int f_dict_type) {
		this.f_dict_type = f_dict_type;
	}


	public String getF_dict_value() {
		return f_dict_value;
	}


	public void setF_dict_value(String f_dict_value) {
		this.f_dict_value = f_dict_value;
	}


	public int getF_dict_system() {
		return f_dict_system;
	}


	public void setF_dict_system(int f_dict_system) {
		this.f_dict_system = f_dict_system;
	}
	
	
    
}
