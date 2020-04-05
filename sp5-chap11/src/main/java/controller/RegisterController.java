package controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import exception.DuplicateMemberException;
import spring.MemberRegisterService;
import vo.RegisterRequest;

@Controller
public class RegisterController {
	@Autowired
	private MemberRegisterService memberRegisterService;
	
	
	@RequestMapping("/register/step1")
	public String handleStep() {
		return "register/step1";
	}
	@PostMapping("/register/step2")
	public String handleStep2(@RequestParam(value = "agree", defaultValue= "false") Boolean agree, Model model) {
		if(!agree) {
			return "register/step1";
		}	
		model.addAttribute("registerRequest", new RegisterRequest());
			return "register/step2";
	}
	@GetMapping("/register/step2") //step2를 get방식으로 접근시 step1로 redirect실행 
	public String handleStep2Get() {
		return "redirect:/register/step1";
	}
	
	
	@PostMapping("/register/step3")
	public String handleStep3(@Valid RegisterRequest regReq, Errors errors) { 
		if(errors.hasErrors()) {
			return "register/step2";
		}
		// RegisterRequest는 jsp에서 커맨드객체로 사용됨 @ModelAttribute를 통해 이름변경도 가능
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		} catch (DuplicateMemberException e) {
			errors.rejectValue("email", "duplicate");
			return "register/step2";
		}
	}
}
