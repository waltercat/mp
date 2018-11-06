package com.mp.system.org.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mp.system.org.domain.OrganizationModel;
import com.mp.system.org.vo.OrganizationVO;

@Mapper
public interface OrganizationDao {

	public OrganizationModel loadOrganization( final long id);
	
	public List<OrganizationModel> queryOrganizations( final OrganizationModel org);
	
	public List<OrganizationVO> queryOrganizationsForTree( final OrganizationModel org);
	
	public void saveOrganization( final OrganizationModel org);
	
	public void updateOrganization( final OrganizationModel org);
	
	public void delOrganization( final Long[] ids);

	public String queryMaxCode(Long parentId);

	/**
	 * 
	    * @Title: delOrganizationByCode
	    * @Description: TODO(按机构编码删除机构及下级机构)
	    * @param @param orgCodes    参数
	    * @return void    返回类型
	    * @throws
	 */
	public void delOrganizationByCode(String[] orgCodes);
}
