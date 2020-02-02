package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;
import spms.dao.MemberDao;
// JSP 적용 
// - 변경폼 및 예외 처리
@SuppressWarnings("serial")
//@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
//	MemberDao memberDao = new MemberDao();
	ServletContext sc;
//	Connection connection = null;
	MemberDao memberDao;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("updateServlet doGet 실행");
		sc = this.getServletContext();
//		connection = (Connection) sc.getAttribute("conn");
		memberDao = (MemberDao) sc.getAttribute("memberDao");
		try {
//			memberDao.setConnection(connection);
			//memberDao.selectOne(Integer.parseInt(request.getParameter("no")));
			request.setAttribute("member", memberDao.selectOne(Integer.parseInt(request.getParameter("no"))));
			
			request.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");
			
//			if(request.getAttribute("member") != null) {
//				RequestDispatcher rd = request.getRequestDispatcher("/member/MemberUpdateForm.jsp");
//				rd.forward(request, response);
//			}else {
//				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
//			}
			
		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("error", e);
//			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
//			rd.forward(request, response);
			throw new ServletException(e);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("updateServlet doPost 실행");
		sc = this.getServletContext();
//		connection = (Connection) sc.getAttribute("conn");
		memberDao = (MemberDao) sc.getAttribute("memberDao");
		try {
//			memberDao.setConnection(connection);
			Member member = (Member) request.getAttribute("member");
//			memberDao.update(new Member().setEmail(request.getParameter("email"))
//										 .setName(request.getParameter("name"))
//										 .setNo(Integer.parseInt(request.getParameter("no")))
//										 );
//			response.sendRedirect("list");
			memberDao.update(member);
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
