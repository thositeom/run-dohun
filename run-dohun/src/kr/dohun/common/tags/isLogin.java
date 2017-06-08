package kr.dohun.common.tags;

import javax.mail.Session;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import kr.dohun.session.sessionManager;

import org.springframework.beans.factory.annotation.Autowired;

public class isLogin extends BodyTagSupport{

	@Autowired
	private sessionManager sessionManager;
	
	public int doStartTag() throws JspException{
		//		EVAL_BODY_INCLUDE 태그안 내용 포함 
		//		SKIP_BODY		  태그안 내용 불포함
		//		로그인 되어있음
		//		null일때 false

		HttpSession session = pageContext.getSession();
		if(session.getAttribute("_USER_INFO_") == null){
			return SKIP_BODY;
		}else {
			return EVAL_BODY_INCLUDE;
		}
	}
}
