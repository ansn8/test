package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

// JSP 적용
// - 입력폼 및 오류 처리 
//@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher rd = request.getRequestDispatcher("/member/MemberForm.jsp");
//		rd.forward(request, response);
		System.out.println("addServlet doGet실행");
		request.setAttribute("viewUrl", "/member/MemberForm.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		MemberDao memberDao = new MemberDao();
		System.out.println("addServlet doPost실행");
		try {
			ServletContext sc = this.getServletContext();
//			Connection conn = (Connection) sc.getAttribute("conn");  
//			memberDao.setConnection(conn);
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
//			memberDao.insert(new Member().setEmail(request.getParameter("email"))
//					  					 .setPassword(request.getParameter("password"))
//					  					 .setName(request.getParameter("name"))
//										);
			Member member = (Member) request.getAttribute("member");
			memberDao.insert(member);
			
			//response.sendRedirect("list");
			request.setAttribute("viewUrl", "redirect:list.do");
		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("error", e);
//			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
//			rd.forward(request, response);
			throw new ServletException(e);
		}
	}
}
