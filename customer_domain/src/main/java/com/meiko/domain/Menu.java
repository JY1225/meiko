package com.meiko.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @description:菜单
 */

public class Menu {
	

	private int id;

	private String name; // 菜单名称
	
	private String url; // 访问路径
	
	private Integer level; // 优先级
	
	private String remarks; // 描述
	
	private Integer state; //状态
	
	private int parentId; //父菜单id

	
	private Set<Role> roles = new HashSet<Role>(0);

	
	private Set<Menu> childrenMenus = new HashSet<Menu>();

	
	private Menu parentMenu;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Integer getLevel() {
		return level;
	}


	public void setLevel(Integer level) {
		this.level = level;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public int getParentId() {
		return parentId;
	}


	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public Set<Menu> getChildrenMenus() {
		return childrenMenus;
	}


	public void setChildrenMenus(Set<Menu> childrenMenus) {
		this.childrenMenus = childrenMenus;
	}


	public Menu getParentMenu() {
		return parentMenu;
	}


	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	
}
