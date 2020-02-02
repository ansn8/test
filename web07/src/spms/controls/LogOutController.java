package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.annotation.Component;
@Component("/auth/logout.do")
public class LogOutController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		System.out.println("logoutController execute실행");
		HttpSession session = (HttpSession) model.get("session");
		session.invalidate();
		return "redirect:../member/list.do";
	}

}
