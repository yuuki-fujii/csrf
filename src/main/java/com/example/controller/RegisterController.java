package com.example.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private HttpSession session;
	
	/**
	 * 正常なリクエストページと偽造されたリクエストページの分岐.
	 * 
	 * @return 分岐画面
	 */
	@RequestMapping("/choice")
	public String choice() {
		return "choice";
	}
	
	
	@RequestMapping("/input")
	public String input(){
		// トークンをセッションに保存		
		session.setAttribute("token", UUID.randomUUID().toString());
		return "input";
	}
	
	@RequestMapping("/bad-input")
	public String badInput(){
		session.setAttribute("token", UUID.randomUUID().toString());
		return "bad-input";
	}
	
	
	@RequestMapping("/to-result")
	public String toResult(String name, String requestToken, Model model)  {
		String sessionToken = (String)session.getAttribute("token");
		
		if (sessionToken == null || !(sessionToken.equals(requestToken))) {
			return "not-success";
		} else {
			model.addAttribute("name", name);
		}
		session.invalidate();
		return "success";
	}
	
	
	
	@RequestMapping("/to-success")
	public String toSuccess() {
		return "success";
	}
}
