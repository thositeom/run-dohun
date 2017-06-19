package kr.dohun.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SpringInterceptor extends HandlerInterceptorAdapter{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String reqUrl = request.getRequestURL().toString();
		System.out.println("::::::::::springInterceptor "+ reqUrl);
		/*.indexOf("/index.do")*/ 
		
		//로그인 여부확인
		/*
		if(!(reqUrl.equals("http://localhost/index.do")||
			reqUrl.equals("http://localhost/mainForm.do")||
			reqUrl.equals("http://localhost/memberLoginForm.do")))
		{	
			System.out.println("::::::::::check noAuth "+ reqUrl);
			ScriptEngineManager scriptEngineMgr = new ScriptEngineManager();
			ScriptEngine jsEngine = scriptEngineMgr.getEngineByName("JavaScript");
			String aaa = "alert"+"("+"asdasdsa"+")";
			jsEngine.eval(aaa);

			return false;
		}
		*/
		return true;
	}
	
}
