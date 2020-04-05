package controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import survey.AnsweredDate;
import survey.Question;

@Controller
@RequestMapping("/survey")
public class SurveyContoller {
	
//	@GetMapping
//	public String form(Model model) {
//		List<Question> questions = createQuestions();
//		model.addAttribute("questions",questions);
//		return "survey/surveyForm";
//	}
	
	@GetMapping //ModelAndView를 사용하면 view와 model을 한번에 처리가능함
	public ModelAndView form() {
		List<Question> questions = createQuestions();
		ModelAndView mav = new ModelAndView();
		mav.addObject("questions",questions);
		mav.setViewName("survey/surveyForm");
		return mav;
	}
	private List<Question> createQuestions() {
		Question q1 = new Question("당신의 역할은 ?",Arrays.asList("서버","프론트","풀스택"));
		Question q2 = new Question("많이 사용하는 개발도구는 ?",Arrays.asList("이클립스","인텔리J","서브라임"));
		Question q3 = new Question("하고픈말은 ?");
		return Arrays.asList(q1,q2,q3);
	}
	
	@PostMapping
	public String submit(@ModelAttribute("ansData")AnsweredDate data) {
		return "survey/submitted";
	}
	
}
