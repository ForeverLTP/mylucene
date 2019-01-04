package com.yyy.model;

public class Sy_details {
	private int sid;//症状id
	private String ssymptom;//症状描述
	private String sreason;//产生原因
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSsymptom() {
		return ssymptom;
	}
	public void setSsymptom(String ssymptom) {
		this.ssymptom = ssymptom;
	}
	public String getSreason() {
		return sreason;
	}
	public void setSreason(String sreason) {
		this.sreason = sreason;
	}
	@Override
	public String toString() {
		return "Sy_details [sid=" + sid + ", ssymptom=" + ssymptom + ", sreason=" + sreason + "]";
	}
}
