package spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import vo.Member;

@Component("listPrinter")
public class MemberListPrinter {
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	@Qualifier("listPrinter")
	private MemberPrinter printer;

//	public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
//		this.memberDao = memberDao;
//		this.printer = printer;
//	}
	
	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m -> printer.print(m));
		
		for(Member m: members){
			printer.print(m);
		}
	}
}
