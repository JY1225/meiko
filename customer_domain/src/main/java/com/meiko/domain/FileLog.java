package com.meiko.domain;

import java.util.Date;

public class FileLog {
	private  int recid;
	private int  cust_jccjs_list_id;
	private String login_name;
	private String file_name;
	private String type;
	private int previews;
	private int down_loads;
	private Date loadTime;
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public int getCust_jccjs_list_id() {
		return cust_jccjs_list_id;
	}
	public void setCust_jccjs_list_id(int cust_jccjs_list_id) {
		this.cust_jccjs_list_id = cust_jccjs_list_id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPreviews() {
		return previews;
	}
	public void setPreviews(int previews) {
		this.previews = previews;
	}
	public int getDown_loads() {
		return down_loads;
	}
	public void setDown_loads(int down_loads) {
		this.down_loads = down_loads;
	}
	public Date getLoadTime() {
		return loadTime;
	}
	public void setLoadTime(Date loadTime) {
		this.loadTime = loadTime;
	}
	
	
	

}
