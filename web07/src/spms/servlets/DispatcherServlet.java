package spms.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.bind.DataBinding;
import spms.bind.ServletRequestDataBinder;
import spms.context.ApplicationContext;
import spms.controls.Controller;
import spms.listeners.ContextLoaderListener;

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
			//ServletContext sc = this.getServletContext();
			ApplicationContext ctx = ContextLoaderListener.getApplicationContext();
			
			HashMap<String,Object> model = new HashMap<String,Object>();
			//이제 DB연결을 위한 MemberDao객체를 Map에 담을 필요가 없음
			//model.put("memberDao",sc.getAttribute("memberDao"));
			model.put("session", request.getSession());
			
			//Controller pageController = (Controller) sc.getAttribute(servletPath);
			Controller pageController = (Controller) ctx.getBean(servletPath);
			System.out.println("PageC"+pageController);
			if(pageController == null) {
				//주소를 잘못입력했을 경우, 발생하는 Exception
				throw new Exception("요청한 페이지를 찾을 수 없습니다");
			}
			
			if(pageController instanceof DataBinding) {
				//매개변수 값이 필요한 페이지컨트롤러에 DataBinding인터페이스를 구현하기로 했으므로 
				//pageController가 DataBinding을 부모객체로 가지는지(구현)했는지 확인
				//구현한 경우에만 prepareRequestData()를 호출하여 페이지컨트롤러를 위한 데이터 준비
				prepareRequestData(request, model, (DataBinding) pageController);
			}
			
			String viewUrl = pageController.execute(model);
			for(String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
			
			if(viewUrl.startsWith("redirect:")){
				System.out.println("viewUrl redirect: 실행");
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
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
		private void prepareRequestData(HttpServletRequest request, HashMap<String, Object> model, DataBinding dataBinding) 
				throws Exception {
			//페이지컨트롤러에서 필요한 데이터를 준비함
			System.out.println("prepareRequestData 실행");
			System.out.println(dataBinding+"(해당 페이지컨트롤러)에서 필요한 데이터가 뭔지 가져와 dataBinders에 담음");
				Object[] dataBinders = dataBinding.getDataBinders();
				String dataName = null;
				Class<?> dataType = null;
				Object dataObj = null;
				for(int i=0;i<dataBinders.length;i+=2) {
					dataName = (String) dataBinders[i]; //필요한 데이터이름 
					dataType = (Class<?>) dataBinders[i+1]; //필요한 데이터타입 
					dataObj = ServletRequestDataBinder.bind(request , dataType, dataName);
					//ServletRequestDataBinder.bind()메서드는 dataName과 일치하는 요청 매개변수를 찾고 dataType을 통해 해당 클래스의 인스턴스를 생성함
					model.put(dataName, dataObj);
					//반환한 데이터객체를 Map에 넣음 Map에 넣은 값이 페이지컨트롤러가 사용할 데이터가 되는 것
				}
			}
			//------------------------------------------
//			if("/member/list.do".equals(servletPath)) {
//				System.out.println("list.do 실행");
//				//pageControllPath = "/member/list";
//				//pageController = new MemberListController();
//			}else if("/member/add.do".equals(servletPath)){
//				System.out.println("add.do 실행");
//				//pageControllPath = "/member/add";
//				//pageController = new MemberAddController();
//				if(request.getParameter("email") != null) {//로그인을 해야 add가 가능함
//					System.out.println("add.do 내부if문 작동");
////					request.setAttribute("member", new Member().setEmail(request.getParameter("email"))
////															   .setPassword(request.getParameter("password"))
////															   .setName(request.getParameter("name"))
////							);
//					model.put("member",new Member().setEmail(request.getParameter("email"))
//												   .setPassword(request.getParameter("password"))
//												   .setName(request.getParameter("name"))
//							);
//				}
//			}else if("/member/update.do".equals(servletPath)) {
//				System.out.println("update.do작동");
//				//pageControllPath = "/member/update?no="+request.getParameter("no");
//				model.put("no",new Integer(request.getParameter("no")));
//				//pageController = new MemberUpdateController();
//				if(request.getParameter("email") != null) {
//					System.out.println("update.do 내부if문 작동");
////					request.setAttribute("member", new Member().setNo(Integer.parseInt(request.getParameter("no")))
////															   .setEmail(request.getParameter("email"))
////															   .setName(request.getParameter("name"))
////							);
//					model.put("member",new Member().setNo(Integer.parseInt(request.getParameter("no")))
//												   .setEmail(request.getParameter("email"))
//												   .setName(request.getParameter("name"))
//							);
//				}
//			}else if("/member/delete.do".equals(servletPath)) {
//				System.out.println("delete.do작동");
//				//pageControllPath = "/member/delete";
//				model.put("no", Integer.parseInt(request.getParameter("no")));
//				//pageController = new MemberDeleteController();
//			}else if("/auth/logIn.do".equals(servletPath)) {
//				System.out.println("logIn.do작동");
//				//pageController = new LogInController();
//				if(request.getParameter("email") !=null) {
//					model.put("loginInfo", new Member().setEmail(request.getParameter("email"))
//													   .setPassword(request.getParameter("password"))
//							);
//				}
//			}else if("/auth/logout.do".equals(servletPath)) {
//				System.out.println("logout.do작동");
//				//pageController = new LogOutController();
//			}
			
			//RequestDispatcher rd = request.getRequestDispatcher(pageControllPath);
			//System.out.println("pageControllPath include 실행 전");
			//rd.include(request, response);
			//System.out.println("pageControllPath include 실행 후");
			
			//String viewUrl = (String) request.getAttribute("viewUrl");
//			String viewUrl = pageController.execute(model);
//			for(String key : model.keySet()) {
//				request.setAttribute(key, model.get(key));
////				System.out.println(key);
////				System.out.println(model.get(key));
////				System.out.println("-----");
//			}
//			
//			if(viewUrl.startsWith("redirect:")){
//				System.out.println("viewUrl redirect: 실행");
//				response.sendRedirect(viewUrl.substring(9));
//				return;
//			} else {
//				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
//				//rd = request.getRequestDispatcher(viewUrl);
//				System.out.println(viewUrl+" : viewUrl 실행");
//				System.out.println("----------------------");
//				rd.include(request, response);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("error", e);
//			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
//			rd.forward(request, response);
//		}
	

	

}
