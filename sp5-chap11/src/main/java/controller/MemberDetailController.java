package controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import exception.MemberNotFoundException;
import spring.MemberDao;
import vo.Member;

@Controller
public class MemberDetailController {
	@Autowired
	private MemberDao memberDao;

	@GetMapping("/members/{id}") // @PathVariable을 통해 가변적으로 입력한 값을 받아서 사용가능 
	public String detail(@PathVariable("id") Long memId, Model model) {
		Member member = memberDao.selectById(memId);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		model.addAttribute("member",member);
		return "member/memberDetail";
	}
	
	//해당하는 익셉션 발생시 에러응답대신 지정한 뷰를 리턴함
	@ExceptionHandler(TypeMismatchException.class)
	public String handleTypeMismatchException() {
		return "member/invalidId";
	}
	@ExceptionHandler(MemberNotFoundException.class)
	public String handleNotFoundException() {
		return "member/noMember";
	}
}
