package com.meiko.domain;

import java.util.List;

public class UserInfo {
    private int id;
    private String userName;
    private String password;
    private String mark;//备注
    private String company;//公司简称
    
    private int status;
    private String statusStr;
    
    private String role;
    private List<Role> roles;
    
    private List<Dir> files;
  

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return mark;
    }

    public void setPhoneNum(String phoneNum) {
        this.mark = phoneNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatusStr() {
        if(status==1){
            statusStr="开启";
        }else if (status==0){
            statusStr="关闭";
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Dir> getFiles() {
		return files;
	}

	public void setFiles(List<Dir> files) {
		this.files = files;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", company='" + company + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mark='" + mark + '\'' +
                ", status=" + status +
                ", statusStr='" + statusStr + '\'' +
                ", roles=" + roles +
                '}';
    }
}
