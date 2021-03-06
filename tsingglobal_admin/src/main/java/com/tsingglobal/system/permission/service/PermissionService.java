package com.tsingglobal.system.permission.service;

import java.util.List;

import com.tsingglobal.system.permission.domain.PermissionModel;

public interface PermissionService {
	
	public PermissionModel loadPermission( final long id);
	
	public List<PermissionModel> queryPermissionByRoot() ;
	
	public List<PermissionModel> queryPermissionByPage(final PermissionModel permission) ;
	
	public List<PermissionModel> queryPermissionForTree() ;
	
	public long savePermission( final PermissionModel org ) ;
	
	public long updatePermission( final PermissionModel org ) ;
	
	public long delPermissions( final long[] ids ) ;
	
	public void delPermissionByCode(String[] permissionCodes);
}
