package com.yyy.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyy.index.IndexUtils;
import com.yyy.model.Dis_details;
import com.yyy.model.Disease;
import com.yyy.model.Paging;
import com.yyy.service.IndexService;
import com.yyy.service.SQLService;
import com.yyy.service.impl.IndexServiceImpl;
import com.yyy.utils.ObjectUtils;
import com.yyy.utils.PageUtils;

@Controller
public class DiseaseAction {

	IndexService is = new IndexServiceImpl();
	
	@Autowired
	SQLService sqlService;
	
	@RequestMapping(value="initLucene")
	public String initLucene(){
		new IndexServiceImpl().creat(sqlService);
		return "system_settings.jsp";
		
	}
	
	/**
	 * 初始化页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="initDisease")
	public String initDisease(Model model,HttpServletRequest request){
		
		//new IndexServiceImpl().creat(sqlService);
		//HttpSession session = request.getSession();
		List<Disease> disList = sqlService.diseasePage(0, 10);
		disList = ObjectUtils.getCompleteList(disList);
		int count = sqlService.pageMaxNum();
		Paging page = PageUtils.initPage(count);
		model.addAttribute("PAGE", page);
		model.addAttribute("COUNT", count);
		//session.setAttribute("DISLIST", disList);
		model.addAttribute("DISLIST", disList);
		return "disease.jsp";
	}
	
	/**
	 * 分页数据以及页码跳转
	 * @param pageNum
	 * @param total
	 * @param totalPageNum
	 * @return
	 */
	@RequestMapping(value="turnPageDis")
	@ResponseBody
	public List<Disease> turnPageDis(int pageNum,int total,int totalPageNum){
		//Paging page = new Paging();
		
		int beginNum = (pageNum-1)*10;
		//page.setBeginNum(beginNum);
		//page.setTotalNum(totalPageNum);
		//if(pageNum<=page.getTotalNum()-1){
		//	page.setEndNum(beginNum+9);
		//}else{
		//	page.setEndNum(totalPageNum);
		//}
		List<Disease> disList = sqlService.diseasePage(beginNum, 10);//10表示一页10个数据
		disList = ObjectUtils.getCompleteList(disList);
		//page.setPageBeginNum(page.getBeginNum()/100+1);
		//page.setPageEndNum(PageUtils.getEndNum(page, pageNum));
		//disList.get(0).setPage(page);
		//model.addAttribute("PAGE", page);
		return disList;
		
	}
	
	/**
	 * 搜索功能
	 * 搜索结果占时不做分页如有需求再扩充
	 * @return
	 */
	
	
	@RequestMapping(value="searchOfDis") 
	@ResponseBody
	public List<Disease> searchOfDis(String field,String value){
		
		List<Disease> disList = null;
		field = IndexUtils.getDisField(field);
		Map<List<Disease>,Integer> map = null;
		int count = 0;
		try {
			if(field.equals("disAll")){
				
			}else{
				map = is.getListDiseaseByQP(1,20,field,value,0);
				for(List<Disease> list :map.keySet()){
					disList = list;
					count = map.get(list);
					break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Paging page = PageUtils.initPage(count);
		if(count>20){
			page.setCount(20);
		}
		if(disList.size()>0){
			disList.get(0).setPage(page);
		}
		return disList;
	}
	
	@RequestMapping(value="getDisDetails")
	public String getDisDetails(Model model,int did){
		Dis_details dis_details = sqlService.getDisDetails(did);
		dis_details = ObjectUtils.getCompleteDisDetails(dis_details);
		model.addAttribute("DISDETAILS", dis_details);
		return "disease_details.jsp";
	}
	
}
