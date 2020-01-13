package spms.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;
//프런트 컨트롤러(was의 공통적인 컨트롤러를 담당함)

//어노테이션을 *.do로 지정하면 서블릿경로이름이 .do로 끝나는 경우는 DistpatcherServlet에서 처리하겠다 라는 의미
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DispatcherServlet() {
        super();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		String servletPath = request.getServletPath();
		try {
			String pageControllPath = null;
			if("/member/list.do".equals(servletPath)) {
				System.out.println("list.do 실행");
				pageControllPath = "/member/list";
			}else if("/member/add.do".equals(servletPath)){
				System.out.println("add.do 실행");
				pageControllPath = "/member/add";
				if(request.getParameter("email") != null) {//로그인을 해야 add가 가능함
					System.out.println("add.do 내부if문 작동");
					request.setAttribute("member", new Member().setEmail(request.getParameter("email"))
															   .setPassword(request.getParameter("password"))
															   .setName(request.getParameter("name"))
							);
				}
			}else if("/member/update.do".equals(servletPath)) {
				System.out.println("update.do작동");
				pageControllPath = "/member/update?no="+request.getParameter("no");
				if(request.getParameter("email") != null) {
					System.out.println("update.do 내부if문 작동");
					request.setAttribute("member", new Member().setNo(Integer.parseInt(request.getParameter("no")))
															   .setEmail(request.getParameter("email"))
															   .setName(request.getParameter("name"))
							);
				}
			}else if("/member/delete.do".equals(servletPath)) {
				System.out.println("delete.do작동");
				pageControllPath = "/member/delete";
			}else if("/auth/logIn.do".equals(servletPath)) {
				System.out.println("logIn.do작동");
				pageControllPath = "/auth/logIn";
			}else if("/auth/logout.do".equals(servletPath)) {
				pageControllPath = "/auth/logout";
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(pageControllPath);
			System.out.println("pageControllPath include 실행 전");
			rd.include(request, response);
			System.out.println("pageControllPath include 실행 후");
			String viewUrl = (String) request.getAttribute("viewUrl");
			if(viewUrl.startsWith("redirect:")){
				System.out.println("viewUrl redirect: 실행");
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				rd = request.getRequestDispatcher(viewUrl);
				System.out.println(viewUrl+" : viewUrl 실행");
				System.out.println("----------------------");
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}

}
