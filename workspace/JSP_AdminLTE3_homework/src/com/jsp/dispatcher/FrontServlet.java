package com.jsp.dispatcher;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class FrontServlet extends HttpServlet {
	
	private HandlerMapper handlerMapper;
	private ViewResolver viewResolver;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		String handlerMapperType=config.getInitParameter("handlerMapper");
		String viewResolverType =config.getInitParameter("viewResolver");
		
		/*try {
			Class<?> handlerMapper = Class.forName(handlerMapperType);
			Class<?> viewResolver = Class.forName(viewResolverType);
			
			this.handlerMapper = (HandlerMapper) handlerMapper.newInstance();
			System.out.println("[FrontServlet]" + handlerMapper + "가 준비되었습니다.");
			
			this.viewResolver = (ViewResolver) viewResolver.newInstance();
			System.out.println("[FrontServlet]" + viewResolver + "가 준비되었습니다.");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[FrontServlet]" + handlerMapper + "가 준비되지 않았습니다.");
		}*/
		try {
			this.handlerMapper=(HandlerMapper)injectionBean(handlerMapperType);
			System.out.println("[FrontServlet]" + handlerMapper + "가 준비되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[FrontServlet]" + handlerMapper + "가 준비되지 않았습니다.");
		}
		
		try {
			this.viewResolver=(ViewResolver)injectionBean(viewResolverType);
			System.out.println("[FrontServlet]" + viewResolver + "가 준비되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[FrontServlet]" + viewResolver + "가 준비되지 않았습니다.");
		}
		
		super.init(config); // 다른건 너가 하던데로 하라는 뜻.
	}

	// doGet, doPost 모두 요청을 받으면 requestPro()를 호출하고 끝.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	

	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자가요청하는 url 이 뭔지알아야함.
		
		// property key
		
		// 2. action받아서 실행(excute()) : view를 return.
		
		// 3. 화면 url(View)받아서 viewresolver준다.
		
		String command = request.getRequestURI(); // contextPath  포함.
		
		if(command.indexOf(request.getContextPath()) == 0) { // contextPath 삭제
			command = command.substring(request.getContextPath().length());
		}
		
		Action act = null;
		String view = null;
		
		act = handlerMapper.getAction(command);
		
		if(act == null) {
			System.out.println("!! not found : " + command);
			// throw new PageNotFoundException();
		} else {
			view = act.execute(request, response);
			
			if(view != null)
				viewResolver.view(request, response, view);
		}
	}
	
	private Object injectionBean(String classType) throws Exception{
		
			Class<?> cls = Class.forName(classType);
			return cls.newInstance();
			
	}
	
}
