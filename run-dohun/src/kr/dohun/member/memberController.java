package kr.dohun.member;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.dohun.session.sessionManager;

@Controller
public class memberController {
	
	@Autowired
	private memberService memberService;
	
	@Autowired
	private sessionManager sessionManager;
	
	@RequestMapping(value = "/memberLoginForm.do")
	public ModelAndView memberLoginForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberLoginForm");
		return mv;
	}
	
	@RequestMapping(value = "/memberLogin.do")
	public ModelAndView memberLogin(memberVO memberVo,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		String userName = request.getParameter("loginId");
		String userPwd = request.getParameter("loginPwd");
		
		memberVo.setUserName(userName);
		memberVo.setUserPassword(userPwd);
		
		mv.setViewName("redirect:/index.do");
	
		//DB조회 1.사용자 유무확인
		if((userName.equals("") ||userName == null)){
			System.out.println(":::null");
			return null;
		}
		
		if(!userName.equals("thositeom")){
			//존재하지 않는 사용자 입니다. 메시지 출력
			System.out.println("::::: 존재하지 않는 사용자 입니다. ");
		}else{
			System.out.println(":::thositeom");
			//DB조회 2.사용자 비번확인
			//3.비번맞으면 세션 생성(SSO)?
			sessionManager.creatSessoin(memberVo, request, response);
//			memberService.creatSessoin(memberVo, request, response);
			
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/memberLogOut.do")
	public ModelAndView memberLogOut(memberVO memberVo,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		sessionManager.removeSessoin(memberVo, request, response);
//		memberService.removeSessoin(memberVo, request, response);
		
		mv.setViewName("redirect:/index.do");
		return mv;
	}
	
	@RequestMapping(value = "/memberJoinForm.do")
	public ModelAndView memberJoinForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberJoinForm");
		return mv;
	}
	
	@RequestMapping(value = "/memberJoin.do")
	public ModelAndView memberJoin(memberVO vo, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jsonView");
		
		try {
			
			
//			vo.setUserId("");
//			memberService.memberInsertInfo(vo);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	} 
	
	@RequestMapping(value = "/memberNameCheck.do")
	public ModelAndView memberNameCheck(memberVO memberVo, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		String tmepId = "thositeom";
		System.out.println("::::"+memberVo.getUserName());
		if(memberVo.getUserName().equals("thositeom")){
			System.out.println("::::사용불가");
			mv.addObject("status", "true");
		}else{
			System.out.println("::::사용가능");
			mv.addObject("status", "false");
		}
		
		mv.setViewName("jsonView");
		return mv;
	}
	
}
