package com.mp.system.org.service;

import java.util.List;

import com.mp.system.org.domain.OrganizationModel;

public interface OrganizationService {
	
	public OrganizationModel loadOrganization( final long id);
	
	public List<OrganizationModel> queryOrganizationByRoot() ;
	
	public List<OrganizationModel> queryOrganizationByPage() ;
	
	public long saveOrganization( final OrganizationModel org ) ;
	
	public long updateOrganization( final OrganizationModel org ) ;
	
	public long delOrganizations( final long[] ids ) ;
}
