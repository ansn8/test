package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

public class LogInController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public LogInController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		System.out.println("MemberLogInContoller getDataBinders 실행");
		return new Object[] {
				"loginInfo", spms.vo.Member.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member loginInfo = (Member) model.get("loginInfo");
		if(loginInfo.getEmail() == null) {
			return "/auth/LogInForm.jsp";
		}else {
//			MemberDao memberDao = (MemberDao) model.get("memberDao");
			
			Member member = memberDao.exist(loginInfo.getEmail(), loginInfo.getPassword());
			
			if(member != null) { //로그인이 정상적으로 성공했을경우
				HttpSession session = (HttpSession) model.get("session");
				session.setAttribute("loginss", member);
				return "redirect:../member/list.do";
			}else {
				return "/auth/LogInFail.jsp";
			}
		}
	}
}
