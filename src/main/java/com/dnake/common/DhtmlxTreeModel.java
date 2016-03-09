package com.dnake.common;

import java.util.List;

public class DhtmlxTreeModel {

	private String id;		//id of the node;
	private String text;	//label of the node
	private String child;	//specifies whether a node has child items (1) or not (0);
	private String im0;		//image for a node without child items (tree will get images from the path specified in setImagePath() method);
	private String im1;		//image for an expanded node with child items
	private String im2;		//image for a collapsed node with child items;
	private String open;	//show a node opened (any value);
	private String select;	//select a node on load (any value);
	private List<DhtmlxTreeModel> item;
	
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
	public String getChild() {
		return child;
	}
	public void setChild(String child) {
		this.child = child;
	}
	public String getIm0() {
		return im0;
	}
	public void setIm0(String im0) {
		this.im0 = im0;
	}
	public String getIm1() {
		return im1;
	}
	public void setIm1(String im1) {
		this.im1 = im1;
	}
	public String getIm2() {
		return im2;
	}
	public void setIm2(String im2) {
		this.im2 = im2;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public List<DhtmlxTreeModel> getItem() {
		return item;
	}
	public void setItem(List<DhtmlxTreeModel> item) {
		this.item = item;
	}
}
