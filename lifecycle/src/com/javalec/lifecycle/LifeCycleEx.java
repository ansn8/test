package com.javalec.lifecycle;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifeCycleEx
 */
@WebServlet("/LifeCycleEx")
public class LifeCycleEx extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LifeCycleEx() {
        super();
    }

    @Override
    public void init() throws ServletException {
    	super.init();
    	System.out.println("init");
    	// doGet,doPost등이 실행되기전에 최초 한번만 실행
    }
    
    @Override
    public void destroy() {
    	super.destroy();
    	System.out.println("destroy");
    	// 종료될때 실행
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("service");
    	
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
	}
	
	//init메서드가 실행되기전, 실행시키는 메서드
	//메서드를 오버라이드하는 것이 아닌 어노테이션을 통해 @PostConstruct를 선언하여 사용 메서드명은 아무거나 가능
	//서블릿의 선처리과정이라고 함
	@PostConstruct 
	private void initPostConstruct() {
		System.out.println("initPostConstruct");
	}

	//destroy메서드가 실행된후(자원이 해제된 후) 실행시키는 메서드
	//메서드를 오버라이드하는 것이 아닌 어노테이션을 통해 @PreDestroy를 선언하여 사용 메서드명은 아무거나 가능
	//서블릿의 후처리과정이라고 함
	@PreDestroy
	private void destoryPreDestroy() {
		System.out.println("destoryPreDestroy");
		
	}
	
}
	/*
	 전체적인 과정
	 Servlet 객체생성
	 선처리과정 @PostConstruct 호출
	 Init() 호출
	 service(),doGet(),doPost() 호출
	 destroy() 호출
	 후처리과정 @PreDestroy 호출
	 */












