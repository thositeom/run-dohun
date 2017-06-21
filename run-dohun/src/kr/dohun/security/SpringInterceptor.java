package kr.dohun.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.dohun.common.JavaScript;

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
			reqUrl.equals(context+"memberLoginForm.do")||
			reqUrl.equals(context+"memberJoin.do")||
			reqUrl.equals(context+"memberLogin.do")
				))
		{	
			//session 체크
			HttpSession session = request.getSession();
			if(session.getAttribute("_USER_INFO_") != null){
				//TODO 권한체크
				
			}else{//로그인필요
				System.out.println("::::::::::::::Session null LoginPage go!!");
				JavaScript.alertToUrl("로그인후 사용 가능합니다.", "/memberLoginForm.do").execute(response, request);
				return false;
			}
		}
		return true;
	}
	
}
