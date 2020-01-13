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
    	// doGet,doPost���� ����Ǳ����� ���� �ѹ��� ����
    }
    
    @Override
    public void destroy() {
    	super.destroy();
    	System.out.println("destroy");
    	// ����ɶ� ����
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
	
	//init�޼��尡 ����Ǳ���, �����Ű�� �޼���
	//�޼��带 �������̵��ϴ� ���� �ƴ� ������̼��� ���� @PostConstruct�� �����Ͽ� ��� �޼������ �ƹ��ų� ����
	//������ ��ó�������̶�� ��
	@PostConstruct 
	private void initPostConstruct() {
		System.out.println("initPostConstruct");
	}

	//destroy�޼��尡 �������(�ڿ��� ������ ��) �����Ű�� �޼���
	//�޼��带 �������̵��ϴ� ���� �ƴ� ������̼��� ���� @PreDestroy�� �����Ͽ� ��� �޼������ �ƹ��ų� ����
	//������ ��ó�������̶�� ��
	@PreDestroy
	private void destoryPreDestroy() {
		System.out.println("destoryPreDestroy");
		
	}
	
}
	/*
	 ��ü���� ����
	 Servlet ��ü����
	 ��ó������ @PostConstruct ȣ��
	 Init() ȣ��
	 service(),doGet(),doPost() ȣ��
	 destroy() ȣ��
	 ��ó������ @PreDestroy ȣ��
	 */












