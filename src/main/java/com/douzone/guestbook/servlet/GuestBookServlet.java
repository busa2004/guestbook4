package com.douzone.guestbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.guestbook.dao.GuestBookDao;
import com.douzone.guestbook.vo.GuestBookVo;
import com.douzone.guestbook.web.WebUtils;

@WebServlet("/gb")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GuestBookServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 요청 분리(식별)
		String action = request.getParameter("a");

		if ("deleteform".equals(action)) {
			WebUtils.forward(request, response, "/WEB-INF/views/deleteform.jsp");
		} else if ("add".equals(action)) {

			String firstName = request.getParameter("name");
			String lastName = request.getParameter("password");
			String email = request.getParameter("message");

			GuestBookVo vo = new GuestBookVo();
			vo.setName(firstName);
			vo.setPassword(lastName);
			vo.setMessage(email);

			new GuestBookDao().insert(vo);

			WebUtils.redirect(request, response, request.getContextPath() + "/gb");
		} else if ("delete".equals(action)) {
			request.setCharacterEncoding("utf-8");

			String no = request.getParameter("no");
			String password = request.getParameter("password");

			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVo> list = dao.getList();

			GuestBookVo vo = new GuestBookVo();
			vo.setNo(Long.parseLong(no));
			vo.setPassword(password);

			new GuestBookDao().delete(vo);
			WebUtils.redirect(request, response, request.getContextPath() + "/gb");

		} else {

			// default action
			// list
			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVo> list = dao.getList();

			// 데이터를 request 범위에 저장
			request.setAttribute("list", list);

			// forwarding
			WebUtils.forward(request, response, "/WEB-INF/views/index.jsp");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
