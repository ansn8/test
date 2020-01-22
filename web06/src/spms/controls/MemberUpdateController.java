package spms.controls;

import java.util.Map;

import javax.servlet.ServletContext;

import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

public class MemberUpdateController implements Controller, DataBinding {
	MemberDao memberDao;
	
	public MemberUpdateController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	@Override
	public Object[] getDataBinders() {
		System.out.println("MemberUpdateContoller getDataBinders 실행");
		return new Object[] {
				"no", Integer.class,
				"member", spms.vo.Member.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
//		memberDao = (MemberDao) model.get("memberDao");
		Member member = (Member) model.get("member");
		if(member.getEmail() == null) {
			System.out.println("MemberUpdateController실행 (입력폼요청 doGet)");
			
			int no = (int) model.get("no");
			model.put("member", memberDao.selectOne(no));
			return "/member/MemberUpdateForm.jsp";
		}else {
			System.out.println("MemberUpdateController실행 (회원갱신요청 doPost)");
			//Member member = (Member) model.get("member");
			memberDao.update(member);
			return "redirect:list.do";
			
		}
		
	}

}
