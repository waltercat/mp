package com.mp.system.permission.domain;

import java.io.Serializable;

public class PermissionModel implements Serializable {
	    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	private static final long serialVersionUID = -3515465085225832272L;
	
	private int id;
	
	//权限编码，采用2,2,2,2,2,2，规则。
	private String permissionCode;
	
	private String permissionName;
	
	private String permissionURL;
	
	private String icon;
	
	//权限类型，0=菜单权限 1=按钮权限 2=数据权限（暂不实现）
	private int permissionType;
	
	//权限状态，0=不可用，1=可用
	private int permissionStatus;
	
	//排序。只对菜单权限有效。
	private int order;

}
