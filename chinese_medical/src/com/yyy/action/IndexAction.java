package com.yyy.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yyy.service.SQLService;

@Controller
public class IndexAction {

	@Autowired
	SQLService sqlService;
	
	@RequestMapping(value="initIndex")
	public String initIndex(Model model){
		int jbCount = sqlService.pageMaxNum();
		int zzCount = sqlService.symptomPageMaxNum();
		int zyCount = sqlService.cmPageMaxNum();
		int yfCount = sqlService.prePageMaxNum();
		model.addAttribute("JBCOUNT", jbCount);
		model.addAttribute("ZZCOUNT", zzCount);
		model.addAttribute("ZYCOUNT", zyCount);
		model.addAttribute("YFCOUNT", yfCount);
		return "index.jsp";
	}
}
