package com.yyy.model;

import java.util.Map;

public class ChineseMedicine {
	
	private int mid;//中药id
	private String mname;//中药名称
	private String mtype;//药品类型
	private String mpinyin;//中药名称拼音
	private String malias;//中药名称别名
	private String msource;//中药来源
	private String mtraits;//性状
	private String msexuality;//性味归经
	private String mfunction;//主治功能
	private String mclinical_application;//临床用药
	private String mchemical_composition;//化学成分
	private String mban;//禁忌
	private String mprescription;//相关处方
	
	private Map<String,String> map;
	
	private Paging page;//页码对象

	
	
	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMpinyin() {
		return mpinyin;
	}

	public void setMpinyin(String mpinyin) {
		this.mpinyin = mpinyin;
	}

	public String getMalias() {
		return malias;
	}

	public void setMalias(String malias) {
		this.malias = malias;
	}

	public String getMsource() {
		return msource;
	}

	public void setMsource(String msource) {
		this.msource = msource;
	}

	public String getMtraits() {
		return mtraits;
	}

	public void setMtraits(String mtraits) {
		this.mtraits = mtraits;
	}

	public String getMsexuality() {
		return msexuality;
	}

	public void setMsexuality(String msexuality) {
		this.msexuality = msexuality;
	}

	public String getMfunction() {
		return mfunction;
	}

	public void setMfunction(String mfunction) {
		this.mfunction = mfunction;
	}

	public String getMclinical_application() {
		return mclinical_application;
	}

	public void setMclinical_application(String mclinical_application) {
		this.mclinical_application = mclinical_application;
	}

	public String getMchemical_composition() {
		return mchemical_composition;
	}

	public void setMchemical_composition(String mchemical_composition) {
		this.mchemical_composition = mchemical_composition;
	}

	public String getMban() {
		return mban;
	}

	public void setMban(String mban) {
		this.mban = mban;
	}

	public String getMprescription() {
		return mprescription;
	}

	public void setMprescription(String mprescription) {
		this.mprescription = mprescription;
	}

	public Paging getPage() {
		return page;
	}

	public void setPage(Paging page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "ChineseMedicine [mid=" + mid + ", mname=" + mname + ", mtype=" + mtype + ", mpinyin=" + mpinyin
				+ ", malias=" + malias + ", msource=" + msource + ", mtraits=" + mtraits + ", msexuality=" + msexuality
				+ ", mfunction=" + mfunction + ", mclinical_application=" + mclinical_application
				+ ", mchemical_composition=" + mchemical_composition + ", mban=" + mban + ", mprescription="
				+ mprescription + ", map=" + map + ", page=" + page + "]";
	}


}
