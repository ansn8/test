package spring;

import org.springframework.beans.factory.annotation.Autowired;

import exception.WrongIdPasswordException;
import login.AuthInfo;
import vo.Member;

public class AuthService { //이메일과 비밀번호가 일치하는 확인해서 AuthInfo객체를 생성하는 클래스
	@Autowired
	private MemberDao memberDao;
	
	public AuthInfo authenticate(String email, String password) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new WrongIdPasswordException();
		}
		if(!member.matchPassword(password)) {
			throw new WrongIdPasswordException();
		}
		return new AuthInfo(member.getId(),member.getEmail(),member.getName());
	}

}
