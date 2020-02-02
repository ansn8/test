package spms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/auth/logout")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("logoutServlet doGet실행");
		HttpSession session = request.getSession();
		session.invalidate();
		
//		response.sendRedirect("../member/list");
		request.setAttribute("viewUrl", "redirect:../member/list.do");
	}
}
