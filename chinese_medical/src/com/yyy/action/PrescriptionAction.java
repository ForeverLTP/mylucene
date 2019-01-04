package com.yyy.action;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyy.index.IndexUtils;
import com.yyy.model.Paging;
import com.yyy.model.Prescription;
import com.yyy.service.IndexService;
import com.yyy.service.SQLService;
import com.yyy.service.impl.IndexServiceImpl;
import com.yyy.utils.PageUtils;

@Controller
public class PrescriptionAction {

	@Autowired
	SQLService sqlService;
	
	/**
	 * 初始化页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="initPrescription")
	public String initPrescription(Model model){

		List<Prescription> preList = sqlService.prescriptionPage(0, 10);
		int count = sqlService.prePageMaxNum();
		Paging page = PageUtils.initPage(count);
		model.addAttribute("PAGE", page);
		model.addAttribute("COUNT", count);
		//session.setAttribute("DISLIST", disList);
		model.addAttribute("PRELIST", preList);
		return "prescription.jsp";
	}
	
	/**
	 * 分页数据以及页码跳转
	 * @param pageNum
	 * @param total
	 * @param totalPageNum
	 * @return
	 */
	@RequestMapping(value="turnPagePre")
	@ResponseBody
	public List<Prescription> turnPagePre(int pageNum,int total,int totalPageNum){
		
		int beginNum = (pageNum-1)*10;
		List<Prescription> preList = sqlService.prescriptionPage(beginNum, 10);//10表示一页10个数据
		
		return preList;
	}
	
	/**
	 * 搜索功能
	 * 搜索结果占时不做分页如有需求再扩充
	 * @return
	 */
	@RequestMapping(value="searchOfPre") 
	@ResponseBody
	public List<Prescription> searchOfPre(String field,String value){
		IndexService is = new IndexServiceImpl();
		List<Prescription> preList = null;
		field = IndexUtils.getPreField(field);
		Map<List<Prescription>,Integer> map = null;
		int count = 0;
		try {
			map = is.getListPreByQP(1,20,field,value);
			for(List<Prescription> list :map.keySet()){
				preList = list;
				count = map.get(list);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Paging page = PageUtils.initPage(count);
		if(count>20){
			page.setCount(20);
		}
		if(preList.size()>0){
			preList.get(0).setPage(page);
		}
		return preList;
	}
	
	@RequestMapping(value="getPreDetails")
	public String getPreDetails(Model model,int pid){
		Prescription pre_details = sqlService.getPreDetails(pid);
		model.addAttribute("PREDETAILS", pre_details);
		return "pre_details.jsp";
	}
}
