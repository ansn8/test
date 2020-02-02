package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.dao.MemberDao;
@Component("/member/list.do")
public class MemberListController implements Controller {
	MemberDao memberDao;
	public MemberListController setMemberDao(MemberDao memberDao) {
		//외부에서 MemberDao를 직접적으로 주입받음
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		System.out.println("MemberListContoller 실행");
		//model에는 프런트컨트롤러가 넘겨준 MemberDao객체가 들어갈 예정
		//MemberDao memberdao = (MemberDao) model.get("memberDao");
		//이제 외부에서 MemberDao객체를 주입해주기 때문에 Map에서 MemberDao를 꺼내올 필요가 없기때문에 제거
		
		//페이지컨트롤러가 작업한 결과를 다시 Map객체에 담아둠
		model.put("members", memberDao.selectList());
		
		return "/member/MemberList.jsp";
	}

}
