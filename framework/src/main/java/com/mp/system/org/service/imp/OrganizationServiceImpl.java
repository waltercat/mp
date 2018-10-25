package com.mp.system.org.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mp.system.org.dao.OrganizationDao;
import com.mp.system.org.domain.OrganizationModel;
import com.mp.system.org.service.OrganizationService;
import com.mp.utils.CommonUtil;
import com.mp.utils.Constants;

@Service("orgService")
public class OrganizationServiceImpl implements OrganizationService {

	public OrganizationModel loadOrganization( final long id) {
		
		return orgDao.loadOrganization(id);
	}
	
	public List<OrganizationModel> queryOrganizationByRoot() {
		
		OrganizationModel org = new OrganizationModel(0l,Constants.ORG_ROOT_CODE,"组织机构");
		
		return orgDao.queryOrganizations(org);
	}
	
	public List<OrganizationModel> queryOrganizationByPage() {
		
		return orgDao.queryOrganizations( null );
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public long saveOrganization( final OrganizationModel org ) {
		
		if( org.validateOK() ) {
			
			org.setId(CommonUtil.genarateID());
			
			this.orgDao.saveOrganization(org);
			
			return org.getId();
		}
		
		return 0l;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public long updateOrganization( final OrganizationModel org ) {
		
		if( org.validateOK() ) {
			
			this.orgDao.updateOrganization(org);
			
			//更新当前机构下人员信息。
			
			return org.getId();
		}
		return 0l;
	}
	
	public long delOrganizations( final long[] ids ) {
		
		if( null == ids || 0 == ids.hashCode() ) {
			
			return 0l;
		}
		
		//删除机构下的全部人员。
		
		//删除机构
		this.orgDao.delOrganization(ids);
		
		return ids.length;
	}
	
	@Autowired
	private OrganizationDao orgDao;
}
