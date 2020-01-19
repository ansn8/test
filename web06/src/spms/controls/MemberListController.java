package spms.controls;

import java.util.Map;
import spms.dao.MemberDao;
public class MemberListController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		System.out.println("MemberListContoller 실행");
		//model에는 프런트컨트롤러가 넘겨준 MemberDao객체가 들어갈 예정
		MemberDao memberdao = (MemberDao) model.get("memberDao");
		
		//페이지컨트롤러가 작업한 결과를 다시 Map객체에 담아둠
		model.put("members", memberdao.selectList());
		
		return "/member/MemberList.jsp";
	}

}
