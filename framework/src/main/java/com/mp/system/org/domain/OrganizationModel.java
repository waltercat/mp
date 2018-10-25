
    /**  
    * @Title: OrganizationModel.java
    * @Package com.aas.system.org.domain
    * @Description: 机构实体
    * @author tony
    * @date 2018年10月18日
    * @version V1.0  
    */
    
package com.mp.system.org.domain;

import java.io.Serializable;

import com.mp.utils.CommonUtil;

/**
    * @ClassName: OrganizationModel
    * @Description: TODO(机构实体)
    * @author tony
    * @date 2018年10月18日
    *
    */

public class OrganizationModel implements Serializable {
	
	public OrganizationModel(long id, String orgCode, String orgName) {
		super();
		this.id = id;
		this.orgCode = orgCode;
		this.orgName = orgName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Override
	public String toString() {
		return "OrganizationModel [id=" + id + ", orgCode=" + orgCode + ", orgName=" + orgName + "]";
	}
	
	public boolean validateOK() {
		
		return !( CommonUtil.isEmpty(orgCode) || CommonUtil.isEmpty(orgName) ); 
	}

	private static final long serialVersionUID = -3220348544731375842L;

	//数据主键
	private long id;
	
	/**
	 * 组织机构编码
	 * 编码规则：2,2,4,4,4,4,4,4,4
	 * 实例：01,01,0001,0001,0001,0001,0001,0000,0000
	 * 组织机构根节点编码：com.aas.utils.Constants.ORG_ROOT_CODE
	 */
	private String orgCode;
	
	//组织机构名称
	private String orgName;
	
}
