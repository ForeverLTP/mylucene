package com.yyy.model;

public class Dis_details {
	private int did;//疾病id
	private String dintroduce;//疾病简介
	private String dknowlege;//疾病知识
	private String dcure;//疾病治疗
	private String title1;//疾病简介标题
	private String title2;//疾病知识标题
	private String title3;//疾病治疗标题
	
	
	public String getTitle1() {
		return title1;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getTitle3() {
		return title3;
	}
	public void setTitle3(String title3) {
		this.title3 = title3;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getDintroduce() {
		return dintroduce;
	}
	public void setDintroduce(String dintroduce) {
		this.dintroduce = dintroduce;
	}
	public String getDknowlege() {
		return dknowlege;
	}
	public void setDknowlege(String dknowlege) {
		this.dknowlege = dknowlege;
	}
	public String getDcure() {
		return dcure;
	}
	public void setDcure(String dcure) {
		this.dcure = dcure;
	}
	@Override
	public String toString() {
		return "Dis_details [did=" + did + ", dintroduce=" + dintroduce + ", dknowlege=" + dknowlege + ", dcure="
				+ dcure + ", title1=" + title1 + ", title2=" + title2 + ", title3=" + title3 + "]";
	}
}
