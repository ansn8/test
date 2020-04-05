package vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;

import exception.WrongIdPasswordException;

public class Member {
	private Long id;
	private String email;
	@JsonIgnore //Json에 출력할 때 제외할 내용을 지정하는 Annotation
	private String password;
	private String name;
//	@JsonFormat(pattern = "yyyyMMddHHmmss") // Json에 출력할 때 형식을 원하는대로 변환해서 출력
	private LocalDateTime registerDateTime;
	
	public Member(String email, String password,String name, LocalDateTime regDateTime) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDateTime = regDateTime;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
	public String getPassword() {
		return password;
	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	public String getName() {
		return name;
	}
//	public void setName(String name) {
//		this.name = name;
//	}
	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}
//	public void setRegisterDateTime(LocalDateTime registerDateTime) {
//		this.registerDateTime = registerDateTime;
//	}
	
	public void changePassword(String oldPassword, String newPassword) {
		if(!password.equals(oldPassword)) {
			throw new WrongIdPasswordException();
		}else {
			this.password = newPassword;
		}
	}
	public boolean matchPassword(String password) {
		//암호 일치여부를 확인하는 메서드 
		return this.password.equals(password);
	}
	
}
