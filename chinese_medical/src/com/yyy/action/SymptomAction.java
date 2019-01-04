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
import com.yyy.model.Sy_details;
import com.yyy.model.Symptom;
import com.yyy.service.IndexService;
import com.yyy.service.SQLService;
import com.yyy.service.impl.IndexServiceImpl;
import com.yyy.utils.ObjectUtils;
import com.yyy.utils.PageUtils;

@Controller
public class SymptomAction {

	@Autowired
	SQLService sqlService;
	
	@RequestMapping(value="initSymptom")
	public String initSymptom(Model model){
		List<Symptom> syList = sqlService.symptomPage(0, 10);
		syList = ObjectUtils.getCompleteListSy(syList);
		int count = sqlService.symptomPageMaxNum();
		Paging page = PageUtils.initPage(count);
		
		model.addAttribute("PAGE", page);
		model.addAttribute("COUNT", count);
		model.addAttribute("SYLIST", syList);
		return "symptom.jsp";
	}
	
	@RequestMapping(value="turnPageSy")
	@ResponseBody
	public List<Symptom> turnPageDis(int pageNum,int total,int totalPageNum){
		
		int beginNum = (pageNum-1)*10;
		List<Symptom> syList = sqlService.symptomPage(beginNum, 10);//10表示一页10个数据
		syList = ObjectUtils.getCompleteListSy(syList);
		return syList;
		
	}
	
	/**
	 * 搜索功能
	 * 搜索结果占时不做分页如有需求再扩充
	 * @return
	 */
	
	
	@RequestMapping(value="searchOfSy") 
	@ResponseBody
	public List<Symptom> searchOfSy(String field,String value){
		IndexService is = new IndexServiceImpl();
		List<Symptom> syList = null;
		field = IndexUtils.getSyField(field);
		Map<List<Symptom>,Integer> map = null;
		int count = 0;
		try {
			map = is.getListSymptomByQP(1,20,field,value);
			for(List<Symptom> list :map.keySet()){
				syList = list;
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
		if(syList.size()>0){
			syList.get(0).setPage(page);
		}
		return syList;
	}
	
	@RequestMapping(value="getSyDetails")
	public String  getSyDetails(Model model,int sid){
		
		Sy_details syDetails = sqlService.getSyDetails(sid);
		model.addAttribute("SYDETAILS", syDetails);
		
		return "symptom_details.jsp";
		
	}
}
