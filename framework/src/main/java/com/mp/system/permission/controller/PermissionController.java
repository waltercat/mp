package com.mp.system.permission.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mp.system.permission.domain.PermissionModel;
import com.mp.system.permission.service.PermissionService;
import com.mp.utils.CommonUtil;
import com.mp.utils.PermissionTree;

@RestController
@RequestMapping(value="/system/permission")
public class PermissionController {

	/**
	 * 
	    * @Title: 增加权限
	    * @Description: TODO(增加权限)
	    * @return void    权限信息
	    * @author tony
	 */
	@GetMapping(value="/getNewPermission")
	public void addPermission() {}
	
	/**
	 * @throws Exception 
	 * 
	    * @Title: editPermission
	    * @Description: TODO(加载权限对象)
	    * @param @param id    权限ID
	    * @return void    返回类型
	    * @throws
	 */
	@GetMapping(value="/getPermission/{id}")
	public void editPermission(@PathVariable  final Long id, final HttpServletResponse response) throws Exception {
		
		if( 0l == id ) {
			
			CommonUtil.error(response, "缺少权限ID，无法编辑权限信息！");
			
			return ;
		}
		
		PermissionModel permission = this.permissionServcie.loadPermission(id) ;
		
		if( permission.validateOK() && !( 0l == permission.getId() )) {
			
			CommonUtil.success( response, JSON.toJSONString( permission ) );
			
			return ;
		}
		
		CommonUtil.error( response, "权限信息不完整，无法完成编辑操作" );
	}
	
	@PostMapping(value= "/putNewPermission")
	public void savePermission(@RequestBody  final PermissionModel permission, final HttpServletResponse response) throws Exception {
		
		if( permission.validateOK() ) {
		
			permissionServcie.savePermission(permission);
			
			CommonUtil.success( response, JSON.toJSONString(permission) );
			
			return ;
		}
		
		CommonUtil.error(response, "权限信息不完整，无法保存！");
	}
	
	@PostMapping(value= "/putPermission")
	public void updatePermission(@RequestBody  final PermissionModel permission, final HttpServletResponse response) throws Exception {
		
		if( permission.validateOK() &&  (permission.getId() != 0l) ) {
			
			permissionServcie.updatePermission(permission);
			
			CommonUtil.success( response, JSON.toJSONString(permission) );		
			
			return ;
		}
		
		CommonUtil.error(response, "权限信息不完整，无法更新！");
	}
	
	@GetMapping(value="/delPermission/{id}")
	public void delPermission(@PathVariable  final long[] ids) {
		
		permissionServcie.delPermissions(ids);
	}
	
	@GetMapping(value="/viewPermission/{id}")
	public void viewPermission( @PathVariable  final Long id, final HttpServletResponse response) throws Exception {
		
		if( 0l == id) {
			
			CommonUtil.error(response, "缺少权限ID，无法完成查看操作！");
			
			return ;
		}
		
		PermissionModel permission = this.permissionServcie.loadPermission(id);
		
		if( permission.validateOK() &&  (permission.getId() != 0l) ) {
			
			CommonUtil.success( response, JSON.toJSONString(permission) );
			
			return ;
		}
		
		CommonUtil.error(response, "查看权限信息操作失败！");
	}
	
	@GetMapping(value="/listPermissionForTree")
	public void listPermissionForTree( final HttpServletResponse response) throws Exception {
		
		PermissionTree tree = new PermissionTree( permissionServcie.queryPermissionForTree() );
		
		CommonUtil.sendJsonData(response, tree.getTreeJSON());
	}
	
	public void listPermissionByPage( @PathVariable final int pageNum, @PathVariable final int pageSize, final HttpServletResponse response) throws Exception {
		
		PageHelper.startPage(pageNum, pageSize);
		
		PageInfo<PermissionModel> pageInfo = new PageInfo<PermissionModel>( permissionServcie.queryPermissionByPage() );
		
		CommonUtil.sendJsonData(response, JSON.toJSONString(pageInfo));
	}
	
	public void importPermission() {}
	
	public void exportPermission() {}
	
	@Autowired
	@Qualifier("permissionService")
	private PermissionService permissionServcie;
}
