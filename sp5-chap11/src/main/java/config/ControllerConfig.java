package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.ChangePwdController;
import controller.LoginController;
import controller.LogoutController;
import controller.MemberDetailController;
import controller.MemberListController;
import controller.RegisterController;
import controller.RestMemberController;
import controller.SurveyContoller;

@Configuration
public class ControllerConfig {
	@Bean
	public RegisterController registerController() {
		return new RegisterController();
	}
	@Bean
	public SurveyContoller surveyController() {
		return new SurveyContoller();
	}
	@Bean
	public LoginController loginController() {
		return new LoginController();
	}
	@Bean
	public LogoutController logoutController() {
		return new LogoutController();
	}
	@Bean
	public ChangePwdController changePwdController() {
		return new ChangePwdController();
	}
	@Bean
	public MemberListController memberListController() {
		return new MemberListController();
	}
	@Bean
	public MemberDetailController memberDetailController() {
		return new MemberDetailController();
	}
	@Bean
	public RestMemberController restMemberController() {
		return new RestMemberController();
	}
}
