package com.mp.system.org.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mp.system.org.domain.OrganizationModel;
import com.mp.system.org.service.OrganizationService;

@RestController
@RequestMapping(value="/system/org")
public class OrganizationController {

	/**
	 * 
	    * @Title: 增加机构
	    * @Description: TODO(增加机构)
	    * @return void    将机构信息
	    * @author tony
	 */
	@GetMapping(value="/getNewOrg")
	public void addOrganization() {}
	
	/**
	 * 
	    * @Title: editOrganization
	    * @Description: TODO(加载机构对象)
	    * @param @param id    机构ID
	    * @return void    返回类型
	    * @throws
	 */
	@GetMapping(value="/getOrg/{id}")
	public void editOrganization(@PathVariable  final Long id) {
		
		OrganizationModel org = this.orgServcie.loadOrganization(id);
	}
	
	@PostMapping(value= "/putNewOrg")
	public void saveOrganization(@RequestBody  final OrganizationModel org, final HttpServletResponse response) {
		
		orgServcie.saveOrganization(org);
	}
	
	@PostMapping(value= "/putOrg")
	public void updateOrganization(@RequestBody  final OrganizationModel org, final HttpServletResponse response) {
		
		orgServcie.updateOrganization(org);
	}
	
	@GetMapping(value="/delOrg/{id}")
	public void delOrganization(@PathVariable  final long[] ids) {
		
		orgServcie.delOrganizations(ids);
	}
	
	@GetMapping(value="/viewOrg/{id}")
	public void viewOrganization( @PathVariable  final Long id) {
		
		OrganizationModel org = this.orgServcie.loadOrganization(id);
	}
	
	public void listOrganization( @PathVariable final int pageNum, @PathVariable final int pageSize) {
		
		PageHelper.startPage(pageNum, pageSize);
		
		PageInfo<OrganizationModel> pageInfo = new PageInfo<OrganizationModel>( orgServcie.queryOrganizationByPage() );
	}
	
	public void importOrganization() {}
	
	public void exportOrganization() {}
	
	@Autowired
	@Qualifier("orgService")
	private OrganizationService orgServcie;
}
