
    /**  
    * @Title: OrganizationModel.java
    * @Package com.aas.system.org.domain
    * @Description: 机构实体
    * @author tony
    * @date 2018年10月18日
    * @version V1.0  
    */
    
package com.mp.system.org.vo;

import java.io.Serializable;

import com.mp.utils.CommonUtil;

/**
    * @ClassName: OrganizationModel
    * @Description: TODO(机构实体)
    * @author tony
    * @date 2018年10月18日
    *
    */

public class OrganizationVO implements Serializable {
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public long getpId() {
		return pId;
	}

	public void setpId(long pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}



		/**
	    * @Fields field:field:{todo}(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = -1827864881929259094L;

	//数据主键
	private long id;
	
	private long pId;
	
	//组织机构名称
	private String name;
	
	//组织机构编码
	private String orgCode;
	
}
