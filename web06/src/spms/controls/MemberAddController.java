package spms.controls;

import java.util.Map;
import spms.dao.MemberDao;
import spms.vo.Member;

public class MemberAddController implements Controller {
	//기존의 MemberAddServlet은 Get요청과 Post요청을 구분하여 처리가 가능했지만 
	//일반클래스에서는 불가능하기 때문에 값객체 Member에 값이 있으면 Post요청으로 처리되게하고
	//없으면 Get요청으로 처리될 수 있게 함
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		if(model.get("member") == null) { // 입력폼을 요청 (doGet)
			System.out.println("MemberAddController실행 (입력폼요청 doGet)");
			return "/member/MemberForm.jsp";
			
		}else { // 회원등록을 요청(doPost)
			System.out.println("MemberAddController실행 (회원등록요청 doPost)");
			MemberDao memberDao = (MemberDao) model.get("memberDao");
			Member member = (Member) model.get("member");
			memberDao.insert(member);
			
			return "redirect:list.do";
		}
	}

}
