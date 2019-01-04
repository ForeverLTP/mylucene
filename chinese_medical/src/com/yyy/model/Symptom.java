package com.yyy.model;

import java.util.List;

public class Symptom {
	
	private int sid;//症状id
	private String sname;//症状名称
	private String salias;//症状别名
	private String sdescription;//症状描述
	private int did;//疾病id待定
	private String sdisease;
	private Paging page;//页码
	
	private List<Disease> disList;
	
	
	
	public Paging getPage() {
		return page;
	}
	public void setPage(Paging page) {
		this.page = page;
	}
	public String getSdisease() {
		return sdisease;
	}
	public void setSdisease(String sdisease) {
		this.sdisease = sdisease;
	}
	public List<Disease> getDisList() {
		return disList;
	}
	public void setDisList(List<Disease> disList) {
		this.disList = disList;
	}
	public String getSalias() {
		return salias;
	}
	public void setSalias(String salias) {
		this.salias = salias;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSdescription() {
		return sdescription;
	}
	public void setSdescription(String sdescription) {
		this.sdescription = sdescription;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public Symptom() {
		super();
	}
	@Override
	public String toString() {
		return "Symptom [sid=" + sid + ", sname=" + sname + ", salias=" + salias + ", sdescription=" + sdescription
				+ ", did=" + did + ", sdisease=" + sdisease + ", page=" + page + ", disList=" + disList + "]";
	}
}
