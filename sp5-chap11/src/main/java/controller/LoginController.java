package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import exception.WrongIdPasswordException;
import login.AuthInfo;
import login.LoginCommand;
import login.LoginCommandValidator;
import spring.AuthService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private AuthService authService;
	
	@GetMapping
	public String form(LoginCommand loginCommand, @CookieValue(value="REMEMBER", required= false) Cookie rCookie) {
		//@CookieValue로 쿠키 지정 value = 쿠키이름, 지정한 이름을 가진 쿠키가 존재하지않을 수도 있을경우 required= false
		if(rCookie != null) { //쿠키가 null이 아닐경우, 쿠키의 값을 로그인폼에 넣음
			loginCommand.setEmail(rCookie.getValue());
			loginCommand.setRememberEmail(true);
		}
		return "login/loginForm";
	}
	
	@PostMapping
	public String submit(LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse response) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors()) {
			return "login/loginForm";
		}
		try {
			AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			session.setAttribute("authInfo", authInfo); // 로그인정보를 저장해놓기위해 세션에 인증정보객체를 넣어둠
			
			Cookie rememberCookie = new Cookie("REMEMBER",loginCommand.getEmail()); // 로그인을 성공하면 Email을 쿠키에 담음
			rememberCookie.setPath("/");
			if(loginCommand.isRememberEmail()) { // 이메일기억하기 활성화되어있다면 생성되는 쿠키 30일간 유지
				rememberCookie.setMaxAge(60*60*24*30);
			} else {
				rememberCookie.setMaxAge(0); // 이메일기억하기 비활성화일경우 쿠키 삭제
			}
			response.addCookie(rememberCookie); //response에 쿠키추가
			return "login/loginSuccess";
		} catch (WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
	}
}
