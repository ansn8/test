package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthCheckInterceptor implements HandlerInterceptor { 
	// 다수의 컨트롤러에 동일한 기능을 적용해야할 때 사용하고 인터셉터라고 함
	// 현재 구현한 내용은 비밀번호변경에 로그인여부를 묻는 기능(로그인여부를 세션확인코드로 진행할시, 이곳저곳에서 확인하므로 많은 중복을 일으킴)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		if(session!=null) {
			Object authInfo = session.getAttribute("authInfo");
			if(authInfo !=null) {
				return true;
			}
		}
		response.sendRedirect(request.getContextPath()+"/login");
		//로그인이 되어있지 않을경우 비밀번호변경페이지에 접근시 로그인화면으로 리다이렉트되게 구현
		return false;
	}
	
}
