package com.yyy.model;

public class Prescription {

	private int pid;//处方id
	private String pname;//处方名称
	private String pindications;//功能主治
	private String psource;//来源
	private String pusage;//用法
	private String psong;//歌诀
	private String pcomposition;//组成成分
	private String peffect;//功效
	private String psolution;//方解
	private String pclinical_application;//临床应用
	private String pban;//注意事项

	private Paging page;

	
	public String getPcomposition() {
		return pcomposition;
	}

	public void setPcomposition(String pcomposition) {
		this.pcomposition = pcomposition;
	}

	public String getPeffect() {
		return peffect;
	}

	public void setPeffect(String peffect) {
		this.peffect = peffect;
	}

	public String getPsolution() {
		return psolution;
	}

	public void setPsolution(String psolution) {
		this.psolution = psolution;
	}

	public String getPclinical_application() {
		return pclinical_application;
	}

	public void setPclinical_application(String pclinical_application) {
		this.pclinical_application = pclinical_application;
	}

	public String getPban() {
		return pban;
	}

	public void setPban(String pban) {
		this.pban = pban;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPindications() {
		return pindications;
	}

	public void setPindications(String pindications) {
		this.pindications = pindications;
	}

	public String getPsource() {
		return psource;
	}

	public void setPsource(String psource) {
		this.psource = psource;
	}

	public String getPusage() {
		return pusage;
	}

	public void setPusage(String pusage) {
		this.pusage = pusage;
	}

	public String getPsong() {
		return psong;
	}

	public void setPsong(String psong) {
		this.psong = psong;
	}

	public Paging getPage() {
		return page;
	}

	public void setPage(Paging page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "Prescription [pid=" + pid + ", pname=" + pname + ", pindications=" + pindications + ", psource="
				+ psource + ", pusage=" + pusage +  ", psong=" + psong
				+ ", pcomposition=" + pcomposition + ", peffect=" + peffect + ", psolution=" + psolution
				+ ", pclinical_application=" + pclinical_application + ", pban=" + pban + ", page=" + page + "]";
	}
}
