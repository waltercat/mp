package com.tsingglobal.system.permission.domain;

import java.io.Serializable;

import com.tsingglobal.utils.CommonUtil;

public class PermissionModel implements Serializable {
	    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentID() {
		return parentID;
	}

	public void setParentID(long parentID) {
		this.parentID = parentID;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionURL() {
		return permissionURL;
	}

	public void setPermissionURL(String permissionURL) {
		this.permissionURL = permissionURL;
	}


	public int getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(int permissionType) {
		this.permissionType = permissionType;
	}

	public int getPermissionStatus() {
		return permissionStatus;
	}

	public void setPermissionStatus(int permissionStatus) {
		this.permissionStatus = permissionStatus;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	public String getPermissionIcon() {
		return permissionIcon;
	}

	public void setPermissionIcon(String permissionIcon) {
		this.permissionIcon = permissionIcon;
	}

	public boolean validateOK() {
		
		return !( CommonUtil.isEmpty(permissionCode) || CommonUtil.isEmpty(permissionName) || CommonUtil.isEmpty(permissionURL) );
	}
	
	

	@Override
	public String toString() {
		return "PermissionModel [id=" + id + ", parentID=" + parentID + ", permissionCode=" + permissionCode
				+ ", permissionName=" + permissionName + ", permissionURL=" + permissionURL + ", permissionIcon=" + permissionIcon
				+ ", permissionType=" + permissionType + ", permissionStatus=" + permissionStatus + ", order=" + order
				+ "]";
	}



	private static final long serialVersionUID = -3515465085225832272L;
	
	private long id;
	
	private long parentID;
	
	//权限编码，采用2,2,2,2,2,2，规则。
	private String permissionCode;
	
	private String permissionName;
	
	private String permissionURL;
	
	private String permissionIcon;
	
	//权限类型，1=菜单权限 2=按钮权限 3=数据权限（暂不实现）
	private int permissionType;
	
	//权限状态，1=可用, 2=不可用
	private int permissionStatus;
	
	//排序。只对菜单权限有效。
	private int order;

}
