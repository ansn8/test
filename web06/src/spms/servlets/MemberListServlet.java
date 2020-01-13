package spms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;

//프런트 컨트롤러 적용
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("listServlet doGet실행");
		try {
			ServletContext sc = this.getServletContext();
			
			//프런트컨트롤러에서 설정했기때문에 contentType을 여기서 또 지정할 필요가 없음
			//response.setContentType("text/html; charset=UTF-8");
			
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			request.setAttribute("members", memberDao.selectList());
			
			//화면출력을 위한 jsp로의 위임도 프런트 컨트롤러에서 처리하기 때문에 필요없음
			//RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
			//rd.include(request, response);
			
			request.setAttribute("viewUrl", "/member/MemberList.jsp");
		} catch (Exception e) {
			//오류발생시 Error.jsp로 넘기는 작업은 프런트컨트롤러가 수행하기 때문에 삭제
			//e.printStackTrace();
			//request.setAttribute("error", e);
			//RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			//rd.forward(request, response);
		
			//대신 Dao실행시 오류가 발생하면 기존오류를 객체에 담아서 나오게함
			throw new ServletException(e);
			
		}
	}
}
