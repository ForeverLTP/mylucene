package com.yyy.model;

public class User {
	private int uid;//用户id唯一
	private String uname;//用户名
	private String uaccountnum;//账号
	private String upassword;//用户密码
	private String uemail;//用户邮箱
	private String uphone;//用户电话
	private int uflag;//用户权限（0为超级管理员其他为普通用户）
	
	
	
	public String getUaccountnum() {
		return uaccountnum;
	}
	public void setUaccountnum(String uaccountnum) {
		this.uaccountnum = uaccountnum;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public int getUflag() {
		return uflag;
	}
	public void setUflag(int uflag) {
		this.uflag = uflag;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", uaccountnum=" + uaccountnum + ", upassword=" + upassword
				+ ", uemail=" + uemail + ", uphone=" + uphone + ", uflag=" + uflag + "]";
	}
}
