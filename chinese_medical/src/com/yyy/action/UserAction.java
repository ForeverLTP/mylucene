package com.yyy.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yyy.model.User;
import com.yyy.service.SQLService;

@Controller
public class UserAction {

	@Autowired
	SQLService sqlService;
	
	@RequestMapping(value="initUser")
	@ResponseBody
	public User initUser(HttpServletRequest request,int uaccountnum,String upassword){
		User user = sqlService.login(uaccountnum, upassword);
		HttpSession session = request.getSession();
		session.setAttribute("USER", user);
		return user;
	}
	
	@RequestMapping(value="updateUser")
	@ResponseBody
	public int updateUser(User user){
		
		int count = sqlService.update(user);		
		return count;
	}
	
	@RequestMapping(value="register")
	public String register(User user){
		
		sqlService.register(user);
		return "index.jsp";
		
	}
	@RequestMapping("exit")
	public String exit(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("USER");
		return "initIndex.action";
		
	}

}
