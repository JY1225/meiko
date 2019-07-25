package com.meiko.domain;

import java.sql.Date;

public class Cust_jccjs_list {

	private int recid;
	private String cust_addr_id;
	private String cust_code;
	private String cust_name;
	private String cust_part;
	private String shipping_addr;
	private Date shipping_dt;
	private int shipping_jccjs_ptr;
	private String shipping_jccjs_no;
	private String upload_filename;
	private Date upload_dt;
	private String upload_terminal;
	private String upload_user;
	
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public String getCust_addr_id() {
		return cust_addr_id;
	}
	public void setCust_addr_id(String cust_addr_id) {
		this.cust_addr_id = cust_addr_id;
	}
	public String getCust_code() {
		return cust_code;
	}
	public void setCust_code(String cust_code) {
		this.cust_code = cust_code;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_part() {
		return cust_part;
	}
	public void setCust_part(String cust_part) {
		this.cust_part = cust_part;
	}
	public String getShipping_addr() {
		return shipping_addr;
	}
	public void setShipping_addr(String shipping_addr) {
		this.shipping_addr = shipping_addr;
	}
	public Date getShipping_dt() {
		return shipping_dt;
	}
	public void setShipping_dt(Date shipping_dt) {
		this.shipping_dt = shipping_dt;
	}
	public int getShipping_jccjs_ptr() {
		return shipping_jccjs_ptr;
	}
	public void setShipping_jccjs_ptr(int shipping_jccjs_ptr) {
		this.shipping_jccjs_ptr = shipping_jccjs_ptr;
	}
	public String getShipping_jccjs_no() {
		return shipping_jccjs_no;
	}
	public void setShipping_jccjs_no(String shipping_jccjs_no) {
		this.shipping_jccjs_no = shipping_jccjs_no;
	}
	public String getUpload_filename() {
		return upload_filename;
	}
	public void setUpload_filename(String upload_filename) {
		this.upload_filename = upload_filename;
	}
	public Date getUpload_dt() {
		return upload_dt;
	}
	public void setUpload_dt(Date upload_dt) {
		this.upload_dt = upload_dt;
	}
	public String getUpload_terminal() {
		return upload_terminal;
	}
	public void setUpload_terminal(String upload_terminal) {
		this.upload_terminal = upload_terminal;
	}
	public String getUpload_user() {
		return upload_user;
	}
	public void setUpload_user(String upload_user) {
		this.upload_user = upload_user;
	}
	
	
}
