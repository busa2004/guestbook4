/*package com.douzone.guestbook.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.guestbook.dao.GuestBookDao;
import com.douzone.guestbook.vo.GuestBookVo;

import net.sf.json.JSONObject;

@Controller("/ajax")
public class AjaxController {

	@Autowired // 자동으로 DI 하는 변수
	private GuestBookDao guestBookDao;
	
	@ResponseBody
	@RequestMapping("")
	public String list(String p) {
		
		if("".equals(p)) {
			p="1";
		}
		
		if(p.matches("\\d*")==false) {
			p="1";
		}
		
		int page = Integer.parseInt(p);
		
		GuestBookDao dao = new GuestBookDao();
		List<GuestBookVo> list = dao.getList(page);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data",list);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		//response.getWriter().print(jsonObject.toString());
		return jsonObject.toString();
		
		
	}
	@RequestMapping("/add")
	public void add(GuestBookVo guestBookVo) {
		guestBookDao.insert(guestBookVo);
		
	}
	@RequestMapping("/delete")
	public void delete(GuestBookVo guestBookVo) {
		System.out.println(guestBookVo);
		guestBookDao.delete(guestBookVo);
		
	}
}*/
