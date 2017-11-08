package kr.dohun.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import kr.dohun.member.MemberVO;

@Service("SessionManager")
public class SessionManager{
	
	public boolean userSessionCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("_USER_INFO_") == null){
			//null일때
			return false;
		}else{
			//null아닐때
			return true;
		}
		
	}
	
	public void creatSessoin(MemberVO memberVo,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		String userName = memberVo.getUserName();
		
		//세션 생성
		if(session.getAttribute("_USER_INFO_") == null){
			session.setAttribute("_USER_INFO_", memberVo);

		}else{
			//세션이 존재함
			MemberVO userInfo = (MemberVO) session.getAttribute("_USER_INFO_");
			System.out.println(userInfo.getUserName());
		}
		
	}
	
	public void removeSessoin(MemberVO memberVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		//세션 삭제
		if(!(session.getAttribute("_USER_INFO_") == null)){
			session.invalidate();
		}
	}


}
