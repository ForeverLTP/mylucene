package com.yyy.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Disease {
	
	private int did;//疾病id
	private String dname;//疾病名称
	private String dalias;//疾病别名
	private String ddescription;//疾病描述
	private String dsympton;//相关症状
	
	private double probability;//疾病自查概率
	private List<Symptom> symList;
	private Paging page;//用于分页
	private Map<String,Integer> map;//用于疾病自查
	private Set<String> strSet;//相关症状
	
	
	public Set<String> getStrSet() {
		return strSet;
	}
	public void setStrSet(Set<String> strSet) {
		this.strSet = strSet;
	}
	public double getProbability() {
		return probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}
	public Map<String, Integer> getMap() {
		return map;
	}
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
	public Paging getPage() {
		return page;
	}
	public void setPage(Paging page) {
		this.page = page;
	}
	public String getDsympton() {
		return dsympton;
	}
	public void setDsympton(String dsympton) {
		this.dsympton = dsympton;
	}
	public List<Symptom> getSymList() {
		return symList;
	}
	public void setSymList(List<Symptom> symList) {
		this.symList = symList;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDalias() {
		return dalias;
	}
	public void setDalias(String dalias) {
		this.dalias = dalias;
	}
	public String getDdescription() {
		return ddescription;
	}
	public void setDdescription(String ddescription) {
		this.ddescription = ddescription;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public Disease() {
		super();
	}
	@Override
	public String toString() {
		return "Disease [did=" + did + ", dname=" + dname + ", dalias=" + dalias + ", ddescription=" + ddescription
				+ ", dsympton=" + dsympton + ", symList=" + symList + ", page=" + page + "]";
	}
}
