package kr.dohun.security;

import javax.script.ScriptEngine;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.dohun.common.JavaScript;
import kr.dohun.common.ScriptEngineManager;

public class SpringInterceptor extends HandlerInterceptorAdapter{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String reqUrl = request.getRequestURL().toString();
		int pathLength = request.getContextPath() == null?0:request.getContextPath().length();
		String context = reqUrl.substring(pathLength, reqUrl.lastIndexOf("/")+1);
		System.out.println("::::::::::springInterceptor "+ reqUrl);
		

		//비 로그인 사용자
		if(!(reqUrl.equals(context+"index.do")||
			reqUrl.equals(context+"mainForm.do")||
			reqUrl.equals(context+"baseballGameFrom.do")||
			reqUrl.equals(context+"aboutForm.do")||
			reqUrl.equals(context+"contactForm.do")||
			reqUrl.equals(context+"projectsForm.do")||
			reqUrl.equals(context+"memberLoginForm.do")))
		{	
			System.out.println("::::::::::::::비로그인 사용자!!");
			//session 체크
			HttpSession session = request.getSession();
			if(session.getAttribute("_USER_INFO_") == null){
				System.out.println("::::::::::::::Session null LoginPage go!!");

				JavaScript aaa = new JavaScript();
				String abb = "alert"+"("+"asdasdsa"+");";
				aaa.eval(abb);
				return false;
			}
			
		}
		return true;
	}
	
}
