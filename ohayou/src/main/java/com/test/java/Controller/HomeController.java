package com.test.java.Controller;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.java.DAO.PopitDAO;
import com.test.java.Service.PopitService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Resource(name="com.test.java.Service.PopitServiceImpl") // 해당 서비스가 리소스임을 표시합니다.
	private PopitService popitServiceimpl;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
               // XML -> Mapper(DAO) -> Service -> ServiceImpl -> Controller에 해당 함수 실행  
		List<PopitDAO> popitmapper = popitServiceimpl.selectlistService();
		// View에서 어떤 이름으로 값을 가져와서 뿌릴 건지 정하는 것입니다.
                // 여기서는 popitMapper 객체를 popitlist로 JSP(View)에 표시할 것입니다. 
		model.addAttribute("popitlist",popitmapper);
		return "home";
	}
}
