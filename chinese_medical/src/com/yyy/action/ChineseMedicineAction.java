package com.yyy.action;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyy.index.IndexUtils;
import com.yyy.model.ChineseMedicine;
import com.yyy.model.Paging;
import com.yyy.service.IndexService;
import com.yyy.service.SQLService;
import com.yyy.service.impl.IndexServiceImpl;
import com.yyy.utils.ObjectUtils;
import com.yyy.utils.PageUtils;

@Controller
public class ChineseMedicineAction {

	@Autowired
	SQLService sqlService;
	
	/**
	 * 初始化页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="initChineseMedicine")
	public String initChineseMedicine(Model model){

		List<ChineseMedicine> cmList = sqlService.chineseMedicinePage(0, 10);
		cmList = ObjectUtils.getCompleteCM(cmList);
		int count = sqlService.cmPageMaxNum();
		Paging page = PageUtils.initPage(count);
		model.addAttribute("PAGE", page);
		model.addAttribute("COUNT", count);
		//session.setAttribute("DISLIST", disList);
		model.addAttribute("CMLIST", cmList);
		return "chinesemedicine.jsp";
	}
	
	/**
	 * 分页数据以及页码跳转
	 * @param pageNum
	 * @param total
	 * @param totalPageNum
	 * @return
	 */
	@RequestMapping(value="turnPageCM")
	@ResponseBody
	public List<ChineseMedicine> turnPageCM(int pageNum,int total,int totalPageNum){
		
		int beginNum = (pageNum-1)*10;
		List<ChineseMedicine> cmList = sqlService.chineseMedicinePage(beginNum, 10);//10表示一页10个数据
		cmList = ObjectUtils.getCompleteCM(cmList);
		
		return cmList;
	}
	
	/**
	 * 搜索功能
	 * 搜索结果占时不做分页如有需求再扩充
	 * @return
	 */
	@RequestMapping(value="searchOfCM") 
	@ResponseBody
	public List<ChineseMedicine> searchOfCM(String field,String value){
		IndexService is = new IndexServiceImpl();
		List<ChineseMedicine> cmList = null;
		field = IndexUtils.getCMField(field);
		Map<List<ChineseMedicine>,Integer> map = null;
		int count = 0;
		try {
			map = is.getListCMByQP(1,20,field,value);
			for(List<ChineseMedicine> list :map.keySet()){
				cmList = list;
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
		if(cmList.size()>0){
			cmList.get(0).setPage(page);
		}
		return cmList;
	}
	
	@RequestMapping(value="getCMDetails")
	public String getDisDetails(Model model,int mid){
		ChineseMedicine cm_details = sqlService.getCMDetails(mid);
		model.addAttribute("CMDETAILS", cm_details);
		return "cm_details.jsp";
	}
	
	
	
}
