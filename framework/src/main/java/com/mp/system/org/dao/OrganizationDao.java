package com.mp.system.org.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mp.system.org.domain.OrganizationModel;

@Mapper
public interface OrganizationDao {

	public OrganizationModel loadOrganization( final long id);
	
	public List<OrganizationModel> queryOrganizations( final OrganizationModel org);
	
	public void saveOrganization( final OrganizationModel org);
	
	public void updateOrganization( final OrganizationModel org);
	
	public void delOrganization( final long[] ids);
}
