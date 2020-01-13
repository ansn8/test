package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
// 오류 처리 JSP 적용  
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	MemberDao memberDao = new MemberDao();
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("deleteServlet doGet실행");
		ServletContext sc = this.getServletContext();
//		Connection connection = (Connection) sc.getAttribute("conn");
//		memberDao.setConnection(connection);
		MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
		try {
			memberDao.delete(Integer.parseInt(request.getParameter("no")));
			
			request.setAttribute("viewUrl", "redirect:list.do");
//			response.sendRedirect("list");
		}catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("error", e);
//			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
//			rd.forward(request, response);
			throw new ServletException(e);
		}
	}
}
