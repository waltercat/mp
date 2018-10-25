package com.mp.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collections;

/**
 * 多叉树类
 */
public class MultipleTree {
	public static void main(String[] args) {
		// 读取层次数据结果集列表
		List<HashMap<String, String>> dataList = VirtualDataGenerator.getVirtualResult();

		// 节点列表（散列表，用于临时存储节点对象）
		HashMap<Long, Node> nodeList = new HashMap<Long, Node>();
		// 根节点
		Node root = null;
		// 根据结果集构造节点列表（存入散列表）
		for (Iterator<HashMap<String, String>> it = dataList.iterator(); it.hasNext();) {
			Map<String, String> dataRecord = (HashMap<String, String>) it.next();
			Node node = new Node();
			node.id = Integer.parseInt( dataRecord.get("id"));
			node.text = (String) dataRecord.get("text");
			node.parentId = Integer.parseInt( dataRecord.get("parentId") );
			nodeList.put(node.id, node);
		}
		// 构造无序的多叉树
		Set<Map.Entry<Long, Node>> entrySet = nodeList.entrySet();
		for (Iterator<Map.Entry<Long, Node>> it = entrySet.iterator(); it.hasNext();) {
			Node node = it.next().getValue();
			if (node.parentId  == -1l) {
				root = node;
			} else {
				((Node) nodeList.get(node.parentId)).addChild(node);
			}
		}
		// 输出无序的树形菜单的JSON字符串
		System.out.println(root.toString());
		// 对多叉树进行横向排序
		root.sortChildren();
		// 输出有序的树形菜单的JSON字符串
		System.out.println(root.toString());

	}

}

/**
 * 节点类
 */
class Node {
	/**
	 * 节点编号
	 */
	public long id;
	/**
	 * 节点内容
	 */
	public String text;
	/**
	 * 父节点编号
	 */
	public long parentId;
	/**
	 * 孩子节点列表
	 */
	private Children children = new Children();

// 先序遍历，拼接JSON字符串
	public String toString() {
		String result = "{" + "id : '" + id + "'" + ", text : '" + text + "'";

		if (children != null && children.getSize() != 0) {
			result += ", children : " + children.toString();
		} else {
			result += ", leaf : true";
		}

		return result + "}";
	}

// 兄弟节点横向排序
	public void sortChildren() {
		if (children != null && children.getSize() != 0) {
			children.sortChildren();
		}
	}

// 添加孩子节点
	public void addChild(Node node) {
		this.children.addChild(node);
	}
}

/**
 * 孩子列表类
 */
class Children {
	private List<Node> list = new ArrayList<Node>();

	public int getSize() {
		return list.size();
	}

	public void addChild(Node node) {
		list.add(node);
	}

// 拼接孩子节点的JSON字符串
	public String toString() {
		String result = "[";
		for (Iterator<Node> it = list.iterator(); it.hasNext();) {
			result += ((Node) it.next()).toString();
			result += ",";
		}
		result = result.substring(0, result.length() - 1);
		result += "]";
		return result;
	}

// 孩子节点排序
	public void sortChildren() {
// 对本层节点进行排序
// 可根据不同的排序属性，传入不同的比较器，这里传入ID比较器
		Collections.sort(list, new NodeIDComparator());
// 对每个节点的下一层节点进行排序
		for (Iterator<Node> it = list.iterator(); it.hasNext();) {
			((Node) it.next()).sortChildren();
		}
	}
}

/**
 * 节点比较器
 */
class NodeIDComparator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		
		return (o1.id < o2.id ? -1 : (o1.id == o2.id ? 0 : 1));
	}

}

/**
 * 构造虚拟的层次数据
 */
class VirtualDataGenerator {
// 构造无序的结果集列表，实际应用中，该数据应该从数据库中查询获得；
	public static List<HashMap<String, String>> getVirtualResult() {
		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> dataRecord1 = new HashMap<String, String>();
		dataRecord1.put("id", "112000");
		dataRecord1.put("text", "廊坊银行解放道支行");
		dataRecord1.put("parentId", "110000");

		HashMap<String, String> dataRecord2 = new HashMap<String, String>();
		dataRecord2.put("id", "112200");
		dataRecord2.put("text", "廊坊银行三大街支行");
		dataRecord2.put("parentId", "112000");

		HashMap<String, String> dataRecord3 = new HashMap<String, String>();
		dataRecord3.put("id", "112100");
		dataRecord3.put("text", "廊坊银行广阳道支行");
		dataRecord3.put("parentId", "112000");

		HashMap<String, String> dataRecord4 = new HashMap<String, String>();
		dataRecord4.put("id", "113000");
		dataRecord4.put("text", "廊坊银行开发区支行");
		dataRecord4.put("parentId", "110000");

		HashMap<String, String> dataRecord5 = new HashMap<String, String>();
		dataRecord5.put("id", "100000");
		dataRecord5.put("text", "廊坊银行总行");
		dataRecord5.put("parentId", "-1");

		HashMap<String, String> dataRecord6 = new HashMap<String, String>();
		dataRecord6.put("id", "110000");
		dataRecord6.put("text", "廊坊分行");
		dataRecord6.put("parentId", "100000");

		HashMap<String, String> dataRecord7 = new HashMap<String, String>();
		dataRecord7.put("id", "111000");
		dataRecord7.put("text", "廊坊银行金光道支行");
		dataRecord7.put("parentId", "110000");

		dataList.add(dataRecord1);
		dataList.add(dataRecord2);
		dataList.add(dataRecord3);
		dataList.add(dataRecord4);
		dataList.add(dataRecord5);
		dataList.add(dataRecord6);
		dataList.add(dataRecord7);

		return dataList;
	}
}
