package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import exception.DuplicateMemberException;
import spring.MemberDao;
import spring.MemberRegisterService;
import vo.Member;
import vo.RegisterRequest;

// json형식을 적용을 위해 @RestController 사용 자바객체 -> json형식으로 변환 
// 출력하는 값들을 전부 json형식으로 출력하게 함
// 출력을 원하지 않는 값들은 @JsonIgnore을 붙임
@RestController
public class RestMemberController {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberRegisterService memberRegisterService;
	
	@GetMapping("/api/members")
	public List<Member> members(){
		return memberDao.selectAll();
	}
	@GetMapping("/api/members/{id}")
	//ResponseEntity를 사용해서 정상,비정상 모두 Json응답을 전송가능하게함
	public ResponseEntity<Object> member(@PathVariable Long id, HttpServletResponse response) throws IOException {
		Member member = memberDao.selectById(id);
		if(member == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
//			response.sendError(HttpServletResponse.SC_NOT_FOUND);
//			return null;
		}
//		return member;
		return ResponseEntity.status(HttpStatus.OK).body(member);
	}
	@PostMapping("/api/members")
	public void newMember(@RequestBody @Valid RegisterRequest regReq, HttpServletResponse response) throws IOException {
		 try {
			Long newMemberId = memberRegisterService.regist(regReq);
			response.setHeader("Location", "/api/members/"+newMemberId);
			response.setStatus(HttpServletResponse.SC_CREATED);
		} catch (DuplicateMemberException dupEx) {
			response.sendError(HttpServletResponse.SC_CONFLICT);
		}
	}
}
