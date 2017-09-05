package kr.dohun.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.dohun.common.JavaScript;

public class SpringInterceptor extends HandlerInterceptorAdapter{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		//Ajax 콜인지 아닌지 판단 
        if(isAjaxRequest(request)){
        	if(!isUrlCheck(request))//URL CHECK
    		{	
    			//session 체크
    			HttpSession session = request.getSession();
    			if(session.getAttribute("_USER_INFO_") != null){
    			
    			}else{//로그인필요
    				System.out.println("::::::::::::::Session null LoginPage go!!");
    				JavaScript.alert("로그인후 사용 가능합니다.", "/memberLoginForm.do").execute(response, request);
    				return false;
    			}
    		}	
        	return true;
        }else{
        	//Ajax 콜이 아닐경우 
        	//메인페이지, 로그인페이지만 허용
        	if(!isAjaxUrlCheck(request)){
        		JavaScript.alert("비정상적인 접근입니다.", "/index.do").execute(response, request);
        		return false;
        	}
        	return true;
        }
		
	}
	
	private boolean isUrlCheck(HttpServletRequest req){
		String reqUrl = req.getRequestURL().toString();
		int pathLength = req.getContextPath() == null?0:req.getContextPath().length();
		String context = reqUrl.substring(pathLength, reqUrl.lastIndexOf("/")+1);
		System.out.println("::::::::::springInterceptor "+ reqUrl);
		
		if(reqUrl.equals(context+"index.do")||
				reqUrl.equals(context+"mainForm.do")||
				reqUrl.equals(context+"baseballGameForm.do")||
				reqUrl.equals(context+"baseballGameStart.do")||
				reqUrl.equals(context+"aboutForm.do")||
				reqUrl.equals(context+"contactForm.do")||
				reqUrl.equals(context+"projectsForm.do")||
				reqUrl.equals(context+"memberLoginForm.do")||
				reqUrl.equals(context+"memberJoin.do")||
				reqUrl.equals(context+"toss.do")||
				reqUrl.equals(context+"boardForm.do")||
				reqUrl.equals(context+"boardWriteForm.do")||
				reqUrl.equals(context+"boardWrite.do")||
				reqUrl.equals(context+"boardUpdateForm.do")||
				reqUrl.equals(context+"boardUpdate.do")||
				reqUrl.equals(context+"boardDelete.do")||
				reqUrl.equals(context+"boardList.do")||
				reqUrl.equals(context+"boardDetailForm.do")||
				reqUrl.equals(context+"boardDetail.do")||
				reqUrl.equals(context+"sampleForm.do")||
				reqUrl.equals(context+"lucyXssTest.do")||
				reqUrl.equals(context+"formTest.do")||
				reqUrl.equals(context+"memberLogin.do")
		){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isAjaxRequest(HttpServletRequest req) {
		 String ajaxHeader = "AJAX";
		 return req.getHeader(ajaxHeader) != null && req.getHeader(ajaxHeader).equals(Boolean.TRUE.toString());
	}
	
	private boolean isAjaxUrlCheck(HttpServletRequest req){
		String reqUrl = req.getRequestURL().toString();
		int pathLength = req.getContextPath() == null?0:req.getContextPath().length();
		String context = reqUrl.substring(pathLength, reqUrl.lastIndexOf("/")+1);
		if(reqUrl.equals(context+"index.do")||reqUrl.equals(context+"memberLogOut.do")||
				reqUrl.equals(context+"memberLogin.do")||reqUrl.equals(context+"memberLoginForm.do")
				||reqUrl.equals(context+"honeyForm.do")
				||reqUrl.equals(context+"honeyJqgridList.do")
				||reqUrl.equals(context+"honeyJqgridMerge.do")
				||reqUrl.equals(context+"honeyJqgridSubList.do")
				||reqUrl.equals(context+"honeyJqgridSubMerge.do")
		){
			return true;
		}else{
			return false;
		}
	}
	
}
