package spms.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.controls.*;
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
			ServletContext sc = this.getServletContext();
			HashMap<String,Object> model = new HashMap<String,Object>();
			model.put("memberDao",sc.getAttribute("memberDao"));
			model.put("session", request.getSession());
			Controller pageController = null;
			if("/member/list.do".equals(servletPath)) {
				System.out.println("list.do 실행");
				//pageControllPath = "/member/list";
				pageController = new MemberListController();
			}else if("/member/add.do".equals(servletPath)){
				System.out.println("add.do 실행");
				//pageControllPath = "/member/add";
				pageController = new MemberAddController();
				if(request.getParameter("email") != null) {//로그인을 해야 add가 가능함
					System.out.println("add.do 내부if문 작동");
//					request.setAttribute("member", new Member().setEmail(request.getParameter("email"))
//															   .setPassword(request.getParameter("password"))
//															   .setName(request.getParameter("name"))
//							);
					model.put("member",new Member().setEmail(request.getParameter("email"))
												   .setPassword(request.getParameter("password"))
												   .setName(request.getParameter("name"))
							);
				}
			}else if("/member/update.do".equals(servletPath)) {
				System.out.println("update.do작동");
				//pageControllPath = "/member/update?no="+request.getParameter("no");
				model.put("no",Integer.parseInt(request.getParameter("no")));
				pageController = new MemberUpdateController();
				if(request.getParameter("email") != null) {
					System.out.println("update.do 내부if문 작동");
//					request.setAttribute("member", new Member().setNo(Integer.parseInt(request.getParameter("no")))
//															   .setEmail(request.getParameter("email"))
//															   .setName(request.getParameter("name"))
//							);
					model.put("member",new Member().setNo(Integer.parseInt(request.getParameter("no")))
												   .setEmail(request.getParameter("email"))
												   .setName(request.getParameter("name"))
							);
				}
			}else if("/member/delete.do".equals(servletPath)) {
				System.out.println("delete.do작동");
				//pageControllPath = "/member/delete";
				model.put("no", Integer.parseInt(request.getParameter("no")));
				pageController = new MemberDeleteController();
			}else if("/auth/logIn.do".equals(servletPath)) {
				System.out.println("logIn.do작동");
				pageController = new LogInController();
				if(request.getParameter("email") !=null) {
					model.put("loginInfo", new Member().setEmail(request.getParameter("email"))
													   .setPassword(request.getParameter("password"))
							);
				}
			}else if("/auth/logout.do".equals(servletPath)) {
				pageController = new LogOutController();
			}
			
			//RequestDispatcher rd = request.getRequestDispatcher(pageControllPath);
			//System.out.println("pageControllPath include 실행 전");
			//rd.include(request, response);
			//System.out.println("pageControllPath include 실행 후");
			
			//String viewUrl = (String) request.getAttribute("viewUrl");
			String viewUrl = pageController.execute(model);
			for(String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
//				System.out.println(key);
//				System.out.println(model.get(key));
//				System.out.println("-----");
			}
			
			if(viewUrl.startsWith("redirect:")){
				System.out.println("viewUrl redirect: 실행");
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				//rd = request.getRequestDispatcher(viewUrl);
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
