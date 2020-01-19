package spms.controls;

import java.util.Map;

import spms.dao.MemberDao;

public class MemberDeleteController implements Controller {

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		System.out.println("MemberDeleteController실행 (삭제요청 doGet)");
		MemberDao memberDao = (MemberDao) model.get("memberDao");
		int no = (int) model.get("no");
		memberDao.delete(no);
		return "redirect:list.do";
	}

}
