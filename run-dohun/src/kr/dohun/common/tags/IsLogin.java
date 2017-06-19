package kr.dohun.common.tags;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.beans.factory.annotation.Autowired;

import kr.dohun.session.SessionManager;

public class IsLogin extends BodyTagSupport{

	@Autowired
	private SessionManager sessionManager;
	
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
