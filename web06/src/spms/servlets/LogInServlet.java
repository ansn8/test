package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.vo.Member;
import spms.dao.MemberDao;;
@WebServlet("/auth/logIn")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInForm.jsp");
//		rd.forward(request, response);
		System.out.println("logInServlet doGet실행");
		request.setAttribute("viewUrl", "/auth/LogInForm.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//			MemberDao memberDao = new MemberDao();	
			System.out.println("loginServlet doPost실행");
			ServletContext sc = this.getServletContext();
//			Connection connection = (Connection) sc.getAttribute("conn");
//			memberDao.setConnection(connection);
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			
				HttpSession session = request.getSession();
				try {
					session.setAttribute("loginss", memberDao.exist(request.getParameter("email"),request.getParameter("password")));
//					response.sendRedirect("../member/list");
					request.setAttribute("viewUrl", "redirect:../member/list.do");
				} catch (Exception e) {
//					RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInFail.jsp");
//					rd.forward(request, response);
//					e.printStackTrace();
					request.setAttribute("viewUrl", "/auth/LogInFail.jsp");
				}
			}
}
