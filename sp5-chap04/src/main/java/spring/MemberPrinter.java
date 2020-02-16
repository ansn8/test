package spring;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import vo.Member;

public class MemberPrinter {
// @Autowired에 required 속성을 false로 지정하면 매칭되는 빈객체가 없어도 익셉션을 발생시키지 않음
//	@Autowired
//	@Nullable
//  (required = false) 대신 @Nullable을 사용하면 익셉션을 발생시키지 않고 null값을 할당함
//  @Nullable을 사용하면 빈이 존재하지 않아도 메서드를 일단 호출하고 required를 사용하면 세터메서드를 호출하지 않음
	@Autowired(required = false)
	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 mm월 dd일");
	}
	public void print(Member member) {
		if(dateTimeFormatter == null) {
			System.out.printf("아이디 : %d, 이메일 : %s, 이름=%s, 등록일=%tF\n",
					member.getId(),
					member.getEmail(),
					member.getName(),
					member.getRegisterDateTime()
					);
		} else {
			System.out.printf("아이디 : %d, 이메일 : %s, 이름=%s, 등록일=%s\n",
					member.getId(),
					member.getEmail(),
					member.getName(),
					dateTimeFormatter.format(member.getRegisterDateTime())
					);
		}
	}
	
// 스프링5부터는 Autowired에 (required = false)속성을 사용하는 대신 의존주입대상의 Optional을 사용해도 됨
//	@Autowired
//	public void setDateFormatter(@Nullable Optional<DateTimeFormatter> formatterOpt) {
//		if(formatterOpt.isPresent()) { // .ispresent()는 변수에 값이 존재할 떄 true 없을떄 false를 반환함
//			this.dateTimeFormatter = formatterOpt.get();
//		} else {
//			this.dateTimeFormatter = null;
//		}
//	}
}
