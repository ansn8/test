package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	@Override
	public Object[] getDataBinders() {
		System.out.println("MemberDeleteContoller getDataBinders 실행");
		return new Object[] {
			"no", Integer.class	
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		System.out.println("MemberDeleteController실행 (삭제요청 doGet)");
//		MemberDao memberDao = (MemberDao) model.get("memberDao");
		int no = (int) model.get("no");
		memberDao.delete(no);
		return "redirect:list.do";
	}

}
