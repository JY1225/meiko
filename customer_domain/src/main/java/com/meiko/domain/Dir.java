package com.meiko.domain;

public class Dir {	
	private String id;
    private String editUser;
    private String url; 
    private int status;
    private String statuStr;
    
    public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEditUser() {
		return editUser;
	}
	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}
	public String getStatuStr() {
		if(status == 1) {
			statuStr = "开启";
		}else if(status == 0) {
			statuStr = "关闭";
		}
		return statuStr;
	}
	public void setStatuStr(String statuStr) {
		this.statuStr = statuStr;
	} 
	@Override
    public String toString() {
        return "Dir{" +
                "id='" + id + '\'' +
                ", editUser='" + editUser + '\'' +
                ", url='" + url + '\'' +                
                ", status=" + status +
                ", statuStr='" + statuStr + 
                '}';
    }
}
