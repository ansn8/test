package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import exception.MemberNotFoundException;
import vo.Member;

//@Component
public class ChangePasswordService {
	@Autowired
	private MemberDao memberDao;
	
//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;// Autowired를 통해 의존객체를 주입했음으로 setter메서드는 사용하지 않음
//		
//	}
	// 트랜잭션을 지정하고 싶은 메서드에 @Transactional을 붙이면 됨 
	// 이렇게 하면 여기서 실행되는 memberDao.selectByEmail과 member.changePassword는 하나의 트랜잭션으로 묶이게 됨 
	@Transactional
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
