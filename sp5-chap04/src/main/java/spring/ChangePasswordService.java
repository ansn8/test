package spring;

import org.springframework.beans.factory.annotation.Autowired;

import exception.MemberNotFoundException;
import vo.Member;

public class ChangePasswordService {
	@Autowired
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao; Autowired를 통해 의존객체를 주입했음으로 setter메서드는 사용하지 않음
		
	}
	
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}else {
			member.changePassword(oldPwd, newPwd);
			memberDao.update(member);
		}
	}
	
}
