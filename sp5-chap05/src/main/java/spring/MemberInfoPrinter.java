package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import vo.Member;

@Component("infoPrinter")
public class MemberInfoPrinter {
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	@Qualifier("infoPrinter")
	private MemberPrinter printer;
	
	public void printMemberInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			System.out.println("데이터가 없습니다.");
			return;
		}
		printer.print(member);
		System.out.println();
	}
	
	//메서드에 Autowired를 붙이면 해당메서드의 파라매터 타입에 해당하는 빈 객체를 찾아 인자로 주입함
//	@Autowired	
//	public void setMemberDao(MemberDao memberDao) {
//		this.memberDao = memberDao;
//	}
//	@Autowired
//	public void setPrinter(MemberPrinter printer) {
//		this.printer = printer;
//	}
}
