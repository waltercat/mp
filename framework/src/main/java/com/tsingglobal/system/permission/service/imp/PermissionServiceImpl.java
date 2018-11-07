package com.tsingglobal.system.permission.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsingglobal.system.permission.dao.PermissionDao;
import com.tsingglobal.system.permission.domain.PermissionModel;
import com.tsingglobal.system.permission.service.PermissionService;
import com.tsingglobal.utils.CommonUtil;
import com.tsingglobal.utils.Constants;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	public PermissionModel loadPermission( final long id) {
		
		return permissionDao.loadPermission(id);
	}
	
	public List<PermissionModel> queryPermissionByRoot() {
		
		final PermissionModel permission = new PermissionModel();		
		permission.setPermissionCode(Constants.PERMISSION_ROOT_CODE.substring(0, 2));
		
		List<PermissionModel> permissions = permissionDao.queryPermissions( permission );
		
		permission.setId(Constants.PERMISSION_ROOT_ID);
		permission.setParentID(Constants.PERMISSION_ROOT_PARENTID);
		permission.setPermissionName("系统权限");
		
		permissions.add( permission );
		
		return permissions;
	}
	
	public List<PermissionModel> queryPermissionByPage() {
		
		return permissionDao.queryPermissions( null );
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public long savePermission( final PermissionModel permission ) {
		
		if( permission.validateOK() ) {
			
			permission.setId(CommonUtil.genarateID());
			
			this.permissionDao.savePermission(permission);
			
			return permission.getId();
		}
		
		return 0l;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public long updatePermission( final PermissionModel permission ) {
		
		if( permission.validateOK() ) {
			
			this.permissionDao.updatePermission(permission);
			
			//更新当前机构下人员信息。
			
			return permission.getId();
		}
		return 0l;
	}
	
	public long delPermissions( final long[] ids ) {
		
		if( null == ids || 0 == ids.hashCode() ) {
			
			return 0l;
		}
		
		//删除机构下的全部人员。
		
		//删除机构
		this.permissionDao.delPermission(ids);
		
		return ids.length;
	}
	
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<PermissionModel> queryPermissionForTree() {
		
		return this.queryPermissionByRoot();
	}
}
