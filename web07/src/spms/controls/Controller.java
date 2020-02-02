package spms.controls;

import java.util.Map;

public interface Controller {
	
	String execute(Map<String, Object> model) throws Exception;
	//execute()는 프런트컨트롤러가 페이지컨트롤러에게 넘기기 위해 호출하는 메서드
	//프런트컨트롤러가 execute를 호출하려면 Map객체를 넘겨줘야함
}
