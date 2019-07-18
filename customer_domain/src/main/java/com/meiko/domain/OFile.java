package com.meiko.domain;

public class OFile {	
	private String id;
    private String fileName;
    private String url; 
    private int status;
    private float fileSize;
    private UserInfo user;
    
    public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public float getFileSize() {
		return fileSize;
	}
	public void setFileSize(float fileSize) {
		this.fileSize = fileSize;
	}
	public OFile(){

    }
	
    public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public OFile(String id,String fileName,String url){
    	this.id = id;
    	this.fileName = fileName;
    	this.url = url;
    }
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	} 
	
    public String toString1() {
	return "OFile{" +
		   "id='" + id + '\'' +
		    ", fileName='" + fileName + '\'' +
		    ", url='" + url + '\'' +
		                
		   '}';
    }
}
