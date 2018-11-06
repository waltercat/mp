package com.mp.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.mp.system.org.domain.OrganizationModel;
import com.mp.system.org.vo.OrganizationVO;

public class OrgTree extends MultipleTree {

	public OrgTree(List<?> datas) {
		super(datas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<Long, Node> getData() {

		if( null == datas || 0 == datas.size() ) {
			
			return null;
		}
		
		Map<Long, Node> nodeMap = new HashMap<Long, Node>();
		Iterator<OrganizationVO> it = (Iterator<OrganizationVO>) datas.iterator();
		while( it.hasNext() ) {
			
			OrganizationVO org = it.next();
			
			nodeMap.put( org.getId(), new OrgNode( org.getId() , org.getpId(), org.getName(), org.getOrgCode() ) );
			
		}
		
		return nodeMap;
	}
	
	private class OrgNode extends Node{

		public OrgNode(long id, long parentId, String text, String orgCode ) {
			super(id, parentId, text);
			this.orgCode = orgCode;
		}
		
		public String toString() {
			
			String result = "{" + "id : " + id +  ", name : '" + text + "'" + ",pId:"+parentId+", orgCode:'"+orgCode+"'";

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
			nodeJSON.put("pId", parentId);
			nodeJSON.put("name", text);
			nodeJSON.put("orgCode", orgCode);
			nodeJSON.put("children", children.toJSONArray());
			
			return nodeJSON;
		}
		
		private String orgCode;
	}
}
