package com.tsingglobal.system.org.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsingglobal.system.org.domain.OrganizationModel;
import com.tsingglobal.system.org.service.OrganizationService;
import com.tsingglobal.utils.CommonUtil;
import com.tsingglobal.utils.IntegerValueFilter;


@RestController
@RequestMapping(value="/system/org")
public class OrganizationController {

	/**
	 * 
	    * @Title: 增加机构
	    * @Description: TODO(增加机构)
	    * @return void    将机构信息
	    * @author tony
	 * @throws Exception 
	 */
	@GetMapping(value="/getNewOrg")
	public void addOrganization(  final Long parentId,  final int orgLevel, final HttpServletResponse response ) throws Exception {
		//生成组织机构编码
		String maxCode = orgServcie.generateOrgCode( parentId, orgLevel );
		CommonUtil.success(response, maxCode);
	}
	
	/**
	 * @throws Exception 
	 * 
	    * @Title: editOrganization
	    * @Description: TODO(加载机构对象)
	    * @param @param id    机构ID
	    * @return void    返回类型
	    * @throws
	 */
	@GetMapping(value="/getOrg/{id}")
	public void editOrganization(@PathVariable  final Long id, final HttpServletResponse response) throws Exception {
		
		OrganizationModel org = this.orgServcie.loadOrganization(id);
		
		IntegerValueFilter intFilter = new IntegerValueFilter();
		
		CommonUtil.sendJsonData(response, JSON.toJSONString(org,intFilter));
	}
	
	@PostMapping(value= "/putNewOrg")
	public void saveOrganization(@RequestBody  final OrganizationModel org, final HttpServletResponse response) throws Exception {
		
		orgServcie.saveOrganization(org);
		
		CommonUtil.success(response, org.getOrgCode());
	}
	
	@PostMapping(value= "/putOrg")
	public void updateOrganization(@RequestBody  final OrganizationModel org, final HttpServletResponse response) throws Exception {
		
		orgServcie.updateOrganization(org);
		
		CommonUtil.success(response, org.getOrgCode());
	}
	
	@DeleteMapping(value="/deleteOrg/{id}")
	public void delOrganization( @PathVariable final Long id, final HttpServletResponse response) throws Exception {
		
		Long[] ids = new Long[1];
		
		ids[0] = id;
		
		orgServcie.delOrganizations(ids);
		
		CommonUtil.success(response, ""+id);
	}
	
	@DeleteMapping(value="/deleteOrgByCode")
	public void delOrganizationByCode( @RequestBody   String orgCodes , final HttpServletResponse response) throws Exception {
		
		orgCodes = (orgCodes.endsWith(",")) ? orgCodes.substring(0, orgCodes.length()-1) : orgCodes;
		
		orgServcie.delOrganizationByCode( orgCodes.split(",") );
		
		CommonUtil.success(response, ""+orgCodes);
	}
	
	@GetMapping(value="/viewOrg/{id}")
	public void viewOrganization( @PathVariable  final Long id) {
		
		OrganizationModel org = this.orgServcie.loadOrganization(id);
	}
	
	@GetMapping(value="/getOrgByPage")
	public void listOrganization( final int start, final int length, final int draw) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		
		PageHelper.startPage(start/length+1, length);
		
		
		final OrganizationModel org = new OrganizationModel();//new OrganizationModel();
		org.setOrgCode( request.getParameter("orgCode") );
		org.setOrgName( request.getParameter("orgName") );		
		org.setParentCode(  CommonUtil.getParentOrgCode( request.getParameter("parentCode"), request.getParameter("parentLevel")));
		
		PageInfo<OrganizationModel> pageInfo = new PageInfo<OrganizationModel>( orgServcie.queryOrganizationByPage( org ) );
		
		IntegerValueFilter intFilter = new IntegerValueFilter();
		JSONObject obj = new JSONObject();
		obj.put("draw", draw);
		obj.put("recordsTotal", pageInfo.getTotal());
		obj.put("recordsFiltered", pageInfo.getTotal());
		obj.put("data", JSONArray.parseArray( JSON.toJSONString(pageInfo.getList(),intFilter)));
		
		CommonUtil.sendJsonData(response, obj.toJSONString());
	}
	
	@GetMapping(value="/getOrgForTree")
	public void listPermissionForTree( final HttpServletResponse response) throws Exception {
		IntegerValueFilter intFilter = new IntegerValueFilter();
		CommonUtil.sendJsonData(response, JSON.toJSONString(orgServcie.queryOrgForTree(), intFilter));
	}
	
	public void importOrganization() {}
	
	public void exportOrganization() {}
	
	@Autowired
	@Qualifier("orgService")
	private OrganizationService orgServcie;
	
}
