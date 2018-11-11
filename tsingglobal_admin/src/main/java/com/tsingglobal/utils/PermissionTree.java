package com.tsingglobal.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.tsingglobal.system.permission.domain.PermissionModel;

public class PermissionTree extends MultipleTree {
	
	public PermissionTree(List<?> datas) {
		super(datas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<Long, Node> getData() {

		if( null == datas || 0 == datas.size() ) {
			
			return null;
		}
		
		Map<Long, Node> nodeMap = new HashMap<Long, Node>();
		Iterator<PermissionModel> it = (Iterator<PermissionModel>) datas.iterator();
		while( it.hasNext() ) {
			
			PermissionModel permission = it.next();
			
			nodeMap.put( permission.getId(), new PermissionNode( permission.getId() , permission.getParentID(), 
										permission.getPermissionName(), permission.getPermissionURL(), permission.getIcon() ) );
		}
		
		return nodeMap;
	}
	
	private class PermissionNode extends Node{

		public PermissionNode(long id, long parentId, String text, String url , String icon) {
			super(id, parentId, text);
			this.icon = icon;
			this.url = url;
		}
		
		public String toString() {
			
			String result = "{" + "id : '" + id + "'" + ", name : '" + text + "'" + ",icon:'"+((CommonUtil.isEmpty(icon)) ? "" : icon)+"', url:'"+url+"'";

			if (children != null && children.getSize() != 0) {
				result += ", children : " + children.toString();
			} else {
				result += ", leaf : true";
			}

			return result + "}";
		}
		
		public JSONObject toJSON() {
			
			JSONObject nodeJSON = new JSONObject();
			nodeJSON.put("id", id);
			nodeJSON.put("name", text);
			nodeJSON.put("icon", ((CommonUtil.isEmpty(icon)) ? "" : icon));
			nodeJSON.put("url", url);
			nodeJSON.put("children", children.toJSONArray());
			
			return nodeJSON;
		}
		
		private String url;
		
		private String icon;
		
	}
}
