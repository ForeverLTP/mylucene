package com.yyy.utils;

import com.yyy.model.Paging;

public class PageUtils {
	
	/**
	 * 获取页码结束数
	 * @param page
	 * @param num
	 * @return
	 */
	public static int  getEndNum(Paging page,int num){
		int endNum = 0;
		if(page.getTotalNum()>10){
			int flag = (num-1)/10+1;
			if(flag < page.getTotalNum()/10){
				endNum = page.getPageBeginNum() + 9;
			}else{
				endNum = page.getTotalNum();
			}
		}else{
			endNum = page.getTotalNum();
		}
		
		return endNum;
	}
	/**
	 * 初始化页码
	 * @param count
	 * @return
	 */
	public static Paging initPage(int count){
		
		int totalNum = 0;
		
		Paging page = new Paging();
		page.setBeginNum(1);
		if(count >=10){
			if(0 == count%10){
				totalNum = count/10;
			}else{
				totalNum = count/10 + 1;
			}
			page.setEndNum(10-1);
		}else{
			totalNum = 1;//size小于10则只有一页
			page.setEndNum(count);
		}
		page.setTotalNum(totalNum);
		page.setPageBeginNum(page.getBeginNum()/100+1);
		page.setPageEndNum(getEndNum(page, 1));
		page.setCount(count);
		return page;
	}

}
