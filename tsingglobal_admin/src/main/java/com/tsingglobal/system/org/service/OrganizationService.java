package com.tsingglobal.system.org.service;

import java.util.List;

import com.tsingglobal.system.org.domain.OrganizationModel;

public interface OrganizationService {
	
	public OrganizationModel loadOrganization( final long id);
	
	public List<OrganizationModel> queryOrganizationByRoot() ;
	
	public List<OrganizationModel> queryOrganizationByPage( final OrganizationModel org ) ;
	
	public long saveOrganization( final OrganizationModel org ) ;
	
	public long updateOrganization( final OrganizationModel org ) ;
	
	public long delOrganizations( final Long[] ids ) ;
	
	public List<OrganizationModel> queryOrgForTree();

	/**
	 * 
	    * @Title: generateOrgCode
	    * @Description: TODO(生成指定级别机构的最大编码)
	    * @param @param parentId	指定级别机构
	    * @param @param orgLevel	机构级别
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	public String generateOrgCode( final Long parentId, final int orgLevel);

	/**
	 * 
	    * @Title: delOrganizationByCode
	    * @Description: TODO(按机构编码删除本级和下级机构)
	    * @param @param orgCodes    参数
	    * @return void    返回类型
	    * @throws
	 */
	public void delOrganizationByCode(String[] orgCodes);
}
