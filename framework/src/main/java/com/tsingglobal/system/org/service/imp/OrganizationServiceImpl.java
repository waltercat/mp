package com.tsingglobal.system.org.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsingglobal.system.org.dao.OrganizationDao;
import com.tsingglobal.system.org.domain.OrganizationModel;
import com.tsingglobal.system.org.service.OrganizationService;
import com.tsingglobal.system.org.vo.OrganizationVO;
import com.tsingglobal.utils.CommonUtil;
import com.tsingglobal.utils.Constants;

@Service("orgService")
public class OrganizationServiceImpl implements OrganizationService {

	public OrganizationModel loadOrganization(final long id) {

		return orgDao.loadOrganization(id);
	}

	public List<OrganizationModel> queryOrganizationByRoot() {

		OrganizationModel org = new OrganizationModel();
		org.setOrgCode(Constants.ORG_ROOT_CODE.substring(0, 2));

		return orgDao.queryOrganizations(org);
	}

	public List<OrganizationModel> queryOrganizationByPage(final OrganizationModel org) {

		return orgDao.queryOrganizations(org);
	}

	@Transactional(rollbackFor = { Exception.class })
	public long saveOrganization(final OrganizationModel org) {

		if (org.validateOK()) {

			org.setId(CommonUtil.genarateID());

			this.orgDao.saveOrganization(org);

			return org.getId();
		}

		return 0l;
	}

	@Transactional(rollbackFor = { Exception.class })
	public long updateOrganization(final OrganizationModel org) {

		if (org.validateOK()) {

			this.orgDao.updateOrganization(org);

			// 更新当前机构下人员信息。

			return org.getId();
		}
		return 0l;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public long delOrganizations(final Long[] ids) {

		if (null == ids || 0 == ids.hashCode()) {

			return 0l;
		}

		// 删除机构下的全部人员。

		// 删除机构
		this.orgDao.delOrganization(ids);

		return ids.length;
	}
	
	@Transactional(rollbackFor = { Exception.class })
	public void delOrganizationByCode(String[] orgCodes) {
		
		// 删除机构下的全部人员。

		// 删除机构
		String[] strOrgCodes = this.getOrgCode(orgCodes);
		for( String code : strOrgCodes) {
			this.orgDao.delOrganizationByCode( code.split(",") );
		}
		
	}
	
	private String[] getOrgCode( final String[] orgCodes) {
		String[] codes = new String[orgCodes.length];
		
		for( int i = 0 ; i < orgCodes.length ; i++) {
			
			codes[i] = CommonUtil.getOrgCode(orgCodes[i]);
		}
		return codes;
	}

	@Override
	public List<OrganizationVO> queryOrgForTree() {

		OrganizationModel org = new OrganizationModel();

		org.setOrgCode(Constants.ORG_ROOT_CODE.substring(0, 2));

		return orgDao.queryOrganizationsForTree(org);
	}

	@Override
	public String generateOrgCode(final Long parentId, final int orgLevel) {

		String maxCode = orgDao.queryMaxCode(parentId);

		if (CommonUtil.isEmpty(maxCode)) {

			OrganizationModel org = orgDao.loadOrganization(parentId);
			
			maxCode = org.getOrgCode();
		}

		return this.generateMaxCode(maxCode, orgLevel);
	}

//	 * 组织机构编码
//	 * 编码规则：2,2,4,4,4,4,4,4,4
//	*  实例：01,01,0001,0001,0001,0001,0001,0000,0000
	private String generateMaxCode(final String maxCode, final int level) {

		int beginIndex = 0;
		int endIndex = 0;
		String generatedCode;

		switch (level) {
		case 0:
			beginIndex = 2;
			endIndex = beginIndex + 2;
			break;
		case 1:
			beginIndex = 4;
			endIndex = beginIndex + 4;
			break;
		case 2:
			beginIndex = 8;
			endIndex = beginIndex + 4;
			break;
		case 3:
			beginIndex = 12;
			endIndex = beginIndex + 4;
			break;
		case 4:
			beginIndex = 16;
			endIndex = beginIndex + 4;
			break;
		case 5:
			beginIndex = 20;
			endIndex = beginIndex + 4;
			break;
		case 6:
			beginIndex = 24;
			endIndex = beginIndex + 4;
			break;
		case 7:
			beginIndex = 28;
			endIndex = beginIndex + 4;
			break;
		}

		generatedCode = maxCode.substring(0, beginIndex) + this.getMaxCode(maxCode.substring(beginIndex, endIndex), level);

		for (int i = 32; i > endIndex; i--) {
			generatedCode += "0";
		}

		return generatedCode;
	}

	private String getMaxCode(final String generatedCode, final int level) {

		int maxCode = Integer.parseInt(generatedCode)+1;
		
		int zeroNum = 0;
		
		String strMaxCode = ""+maxCode;
		
		switch (level) {
		case 0:
			zeroNum = ( maxCode <10) ? 1:0;
			break;
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
			if( maxCode < 10 ) {
				zeroNum = 3;
			}else if( maxCode < 100 && maxCode > 9) {
				zeroNum = 2;
			}else if( maxCode < 1000 && maxCode > 99) {
				zeroNum = 1;
			}
		}
		
		for( int i = 0 ;  i < zeroNum ; i++) {
			
			strMaxCode = "0"+strMaxCode;
		}

		return strMaxCode;
	}

	@Autowired
	private OrganizationDao orgDao;
}
