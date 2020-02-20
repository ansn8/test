package spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import vo.Member;

// Component 에노테이션에 값을 주지 않을경우 클래스이름의 첫글자를 소문자로 바꾼 값을 빈이름으로 사용한다
// 그러므로 이 클래스의 빈이름은 memberDao가 된다 
// @Component("이름") 이런식으로 에노테이션에 값을 줄경우  "이름"을 빈이름으로 사용한다
@Component
public class MemberDao {
	private static long nextId = 0;
	
	private Map<String, Member> map = new HashMap<String, Member>();
	
	public Member selectByEmail(String email) {
		return map.get(email);
	}
	
	public void insert(Member member) {
		member.setId(++nextId);
		map.put(member.getEmail(), member);
		System.out.println("insert");
	}
	
	public void update(Member member) {
		map.put(member.getEmail(), member);
	}
	public Collection<Member> selectAll(){
		return map.values();
	}
}
