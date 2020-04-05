package controller;

// 정상과 비정상 모두 JSON응답을 전송하기위해 ResponseEntity를 사용 
//에러상황일 떄, 응답으로 사용할 클래스 작성
public class ErrorResponse {
	private String message;
	
	public ErrorResponse(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
