package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import list.ListCommand;
import spring.MemberDao;
import vo.Member;

@Controller
public class MemberListController {
	@Autowired
	private MemberDao memberDao;

	@RequestMapping("/members")
	public String list(@ModelAttribute("cmd") ListCommand listCommand, Errors errors, Model model) {
		if(errors.hasErrors()) {
			// 지정한 형식과 맞지않을경우, typeMismatch 에러코드를 추가시킴
			return "member/memberList";
		}
		if(listCommand.getFrom() !=null && listCommand.getTo() !=null) {
			List<Member> members = memberDao.selectByRegdate(listCommand.getFrom(), listCommand.getTo());
			model.addAttribute("members",members);
		}
		return "member/memberList";
		
	}

}
