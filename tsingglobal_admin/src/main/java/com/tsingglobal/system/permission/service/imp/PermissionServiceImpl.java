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
		permission.setParentID(Constants.PERMISSION_ROOT_PARENTID);
		permission.setPermissionStatus(Constants.PERMISSION_STATUS_OK);
		
		List<PermissionModel> permissions = permissionDao.queryPermissions( permission );
		
		permission.setPermissionCode(Constants.PERMISSION_ROOT_CODE);
		permission.setId(Constants.PERMISSION_ROOT_ID);
		
		permission.setPermissionType(Constants.PERMISSION_TYPE_MENU);
		permission.setPermissionName("系统权限");
		
		permissions.add( permission );
		
		return permissions;
	}
	
	public List<PermissionModel> queryPermissionByPage( final PermissionModel permission) {
		
		return permissionDao.queryPermissions( permission );
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
		
		this.permissionDao.delPermission(ids);
		
		return ids.length;
	}

	@Override
	public List<PermissionModel> queryPermissionForTree() {
		
		return this.queryPermissionByRoot();
	}

	@Override
	public void delPermissionByCode(String[] permissionCodes) {

		String[] strPermissionCodes = this.getPermissionCode(permissionCodes);
		
		for( String code : strPermissionCodes) {
			
			this.permissionDao.delPermissionByCode( code.split(",") );
		}
	}
	
	private String[] getPermissionCode( final String[] permissionCodes) {
		String[] codes = new String[permissionCodes.length];
		
		for( int i = 0 ; i < permissionCodes.length ; i++) {
			
			codes[i] = CommonUtil.getPermissionCode(permissionCodes[i]);
		}
		return codes;
	}
	
	@Autowired
	private PermissionDao permissionDao;
}
