package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import assembler.Assembler;
import config.AppConf1;
import config.AppConf2;
import config.AppCtx;
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;

public class MainForSpring {
	private static Assembler assembler = new Assembler();
	
	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppConf1.class);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println("명령어를 입력하세요");
			String command= reader.readLine();
			//equalsIgnoreCase는 비교를 대소문자 구분없이 하는 메서드
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다");
				break;
			}
			if(command.startsWith("new")) {
				processNewCommand(command.split(" "));
				continue;
			} else if(command.startsWith("change")) {
				processChangeCommand(command.split(" "));
				continue;
			} else if(command.startsWith("list")) {
				processListCommand();
				continue;
			} else if(command.startsWith("info")) {
				processInfoCommand(command.split(" "));
			}
			printHelp();
		}
	}

	private static void processInfoCommand(String[] split) {
		if(split.length !=2) {
			printHelp();
			return;
		}
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(split[1]);
	}

	private static void processListCommand() {
		MemberListPrinter listPrinter = ctx.getBean("listPrinter",MemberListPrinter.class);
		listPrinter.printAll();
	}

	private static void processNewCommand(String[] split) {
		if(split.length != 5) {
			printHelp();
			return;
		}
//		MemberRegisterService regSvc = assembler.getMemberRegisterService();
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(split[1]);
		req.setName(split[2]);
		req.setPassword(split[3]);
		req.setConfirmPassword(split[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록완료!");
		} catch (DuplicateMemberException e) {
			System.out.println("이미존재하는 메일입니다.");
		}
	}

	private static void processChangeCommand(String[] split) {
		if(split.length != 4) {
			printHelp();
			return;
		}
//		ChangePasswordService changePwdSvc = assembler.getChangePasswordService();
		ChangePasswordService changePwdSvc = ctx.getBean("changePwdSvc",ChangePasswordService.class);
		try {
			changePwdSvc.changePassword(split[1], split[2], split[3]);
			System.out.println("암호변경완료");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지않는 이메일입니다.");
		} catch(WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.");
		}
	}
	private static void printHelp() {
		System.out.println();
		System.out.println("다시입력하세요.");
		System.out.println("new 이메일 이름 암호 암호");
		System.out.println("change 이메일 현재비밀번호 변경할비밀번호");
		
	}
}
