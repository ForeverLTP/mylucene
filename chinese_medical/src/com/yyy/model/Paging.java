package com.yyy.model;

public class Paging {
	
	private int totalNum;//总页数
	private int beginNum;//内容开始数
	private int endNum;//内容结束数
	private int pageBeginNum;//页码开始数
	private int pageEndNum;//页码结束数
	private int count;//记录总数
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageBeginNum() {
		return pageBeginNum;
	}
	public void setPageBeginNum(int pageBeginNum) {
		this.pageBeginNum = pageBeginNum;
	}
	public int getPageEndNum() {
		return pageEndNum;
	}
	public void setPageEndNum(int pageEndNum) {
		this.pageEndNum = pageEndNum;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getBeginNum() {
		return beginNum;
	}
	public void setBeginNum(int beginNum) {
		this.beginNum = beginNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	@Override
	public String toString() {
		return "Paging [totalNum=" + totalNum + ", beginNum=" + beginNum + ", endNum=" + endNum + ", pageBeginNum="
				+ pageBeginNum + ", pageEndNum=" + pageEndNum + ", count=" + count + "]";
	}
}
