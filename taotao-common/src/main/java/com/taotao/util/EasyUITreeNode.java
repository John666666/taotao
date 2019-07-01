package com.taotao.util;

import java.io.Serializable;
/**
 *  返回值是EasyUITreeNode的集合，EasyUITreeNode类的内容如下所示。
 *  id是树节点的id，text是节点的名称，state是指树形是闭合的还是打开的，
 *  如果当前节点还有子节点，那么state的值是"closed"，
 *  如果当前节点已经是叶子节点了，那么state的值是"open"。
 * @author admin
 *
 */
public class EasyUITreeNode implements Serializable{
	private Long id;
	private String text;
	private String state;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
