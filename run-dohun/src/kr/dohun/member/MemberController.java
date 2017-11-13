package kr.dohun.member;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.dohun.common.JavaScript;
import kr.dohun.error.CustomGenericException;
import kr.dohun.session.SessionManager;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private SessionManager sessionManager;
	
	@RequestMapping(value = "/memberLoginForm.do")
	public ModelAndView memberLoginForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		if(sessionManager.userSessionCheck(request)){
			JavaScript.alert("접근할수 없습니다.", "/index.do").execute(response, request);
//			mv.setViewName("redirect:/index.do");
			return null;
		}
		
		mv.setViewName("member/memberLoginForm");
		return mv;
	}
	
	@RequestMapping(value = "/memberLogin.do")
	public ModelAndView memberLogin(MemberVO memberVo,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		String userId = request.getParameter("loginId");
		String userPwd = request.getParameter("loginPwd");
		
		memberVo.setUserName(userId);
		memberVo.setUserPassword(userPwd);
		
		//DB조회 1.사용자 유무확인
		if((userId.equals("") ||userId == null)){
			JavaScript.alert("사용자 아이디를 입력해주세요.").execute(response, request);
			return null;
		}

		MemberVO memberInfo = memberService.memberInfo(userId);
		
		if(memberInfo == memberService.memberInfo(userId)){
			//존재하지 않는 사용자 입니다.
			JavaScript.alert("존재하지 않는 사용자 입니다.").execute(response, request);
			throw new CustomGenericException("에러코드", memberVo.getUserId());
		}
		if(!userPwd.equals(memberInfo.getUserPassword())){
			//비밀번호가 틀렸습니다. 확인 후 다시 로그인 해주세요.
			JavaScript.alert("비밀번호가 틀렸습니다. 확인 후 다시 로그인 해주세요.").execute(response, request);
			return null;
		}
		//3.비번맞으면 세션 생성(SSO)?
		sessionManager.creatSessoin(memberVo, request, response);
		mv.setViewName("redirect:/index.do");
		
		return mv;
	}
	
	@RequestMapping(value = "/memberLogOut.do")
	public ModelAndView memberLogOut(MemberVO memberVo,HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	public ModelAndView memberJoin(MemberVO vo, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jsonView");
		try {
			memberService.memberInsertInfo(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	} 
	
	@RequestMapping(value = "/memberNameCheck.do")
	public ModelAndView memberNameCheck(MemberVO memberVo, HttpServletRequest request, HttpServletResponse response) {
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
	
	
	/**
	 * 네이버 API 콜백
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/naverLogin.do")
	public ModelAndView naverLogin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/naverLogin");
		return mv;
	}
	
	/**
	 * 네아로 사용자 로그인
	 * @param memberVo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/memberNaverLogin.do")
	public ModelAndView memberNaverLogin(MemberVO memberVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();

		String accessTokn = request.getParameter("accessTokn");
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String age = request.getParameter("age");
		String birthday = request.getParameter("birthday");
		String nickenc_idame = request.getParameter("nickenc_idame");
		String gender = request.getParameter("gender");
		String profile_image = request.getParameter("profile_image");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		
		System.out.println(accessTokn);
		System.out.println(email);
		System.out.println(age);
		System.out.println(birthday);
		System.out.println(nickenc_idame);
		System.out.println(gender);
		System.out.println(profile_image);
		System.out.println(name);
		System.out.println(id);
		memberVo.setUserId(email);
		
//		memberVo.setUserId(email);	//네이버 가입자는 email을 아이디로 가입시킴
		memberVo.setSnsId(id);
//		memberVo.setSnsName(nickname);
//		memberVo.setSnsProfile(profile_image);
//		memberVo.setSnsType(snsType);
		
		if(memberService.naverUserInfoSnsId(id) == null){
			mv.setViewName("member/memberNaverJoinForm");
		}else{
			memberService.naverMergeInfo(memberVo);
			sessionManager.creatSessoin(memberVo, request, response);
			mv.setViewName("redirect:/index.do");	
		}
		return mv;
	}
	
}
