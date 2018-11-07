package com.tsingglobal.system.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tsingglobal.system.permission.domain.PermissionModel;

@Mapper
public interface PermissionDao {

	public PermissionModel loadPermission( final long id);
	
	public List<PermissionModel> queryPermissions( final PermissionModel permission);
	
	public void savePermission( final PermissionModel permission);
	
	public void updatePermission( final PermissionModel permission);
	
	public void delPermission( final long[] ids);
}
