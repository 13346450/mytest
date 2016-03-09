package com.dnake.common;

import java.util.List;

public class EasyUiTreeModel {

	private String id;
	private String text;
	private String state;
	private String iconCls;
	private String attributes;
	private List<EasyUiTreeModel> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public List<EasyUiTreeModel> getChildren() {
		return children;
	}
	public void setChildren(List<EasyUiTreeModel> children) {
		this.children = children;
	}
}
