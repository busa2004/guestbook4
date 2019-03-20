package com.douzone.guestbook.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.guestbook.dao.GuestBookDao;
import com.douzone.guestbook.vo.GuestBookVo;

@Controller
public class GuestBookController {
	
	@Autowired // 자동으로 DI 하는 변수
	private GuestBookDao guestBookDao;
	
	/*@RequestMapping("")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", emaillistDao.getList());
		mav.setViewName("/WEB-INF/views/list.jsp");
		return mav;
	}*/
	
	@RequestMapping("")
	public String list(Model model) {
		model.addAttribute("list",guestBookDao.getList());
		return "list";
	}
	
	@RequestMapping("/delete")
	public String form(GuestBookVo guestBookVo,Model model) {
		System.out.println(guestBookVo);
		model.addAttribute("no",guestBookVo.getNo());
		return "delete";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(GuestBookVo guestBookVo) {
		guestBookDao.insert(guestBookVo);
		return "redirect:/";
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(GuestBookVo guestBookVo) {
		System.out.println(guestBookVo);
		guestBookDao.delete(guestBookVo);
		return "redirect:/";
	}
	

}
