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
	
	@RequestMapping(value = "/naverLogin.do")
	public ModelAndView naverLogin(HttpServletRequest request) {
		System.out.println("naverLogin");
		ModelAndView mv = new ModelAndView();
		/*String apiURL = "";
		try{
			String clientId = "vZbX7ZW_zMbNccbH_O_1";//애플리케이션 클라이언트 아이디값";
		    String redirectURI = URLEncoder.encode("http://127.0.0.1/memberNaverLogin.do", "UTF-8");
		    SecureRandom random = new SecureRandom();
		    String state = new BigInteger(130, random).toString();
		    apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		    apiURL += "&client_id=" + clientId;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&state=" + state;
		    
		    System.out.println(":::apiURL생성::::" + apiURL);

		    //CSRF방지위해 세션저장 로그인 후 비교
		    createSession(request, "_state_", state);
		    
		}catch(Exception e){
			 System.out.println(e);
		}
		mv.addObject("apiURL", apiURL);*/
		
		
		 
		/*String clientid="YOUR_CLIENT_ID"; 
		String clientsecret="YOUR_CLIENT_SECRET"; 
		String code = request.getParameter("code");
		String state= request.getParameter("state"); 
		String redirecturi=URLEncoder.encode("YOUR_CALLBACK_URL","utf-8");
		String apiurl = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&"; 
		apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
		String clientsecret = ""; 
		String redirecturi = ""; 
		String code = ""; 
		String state = ""; 
		String access_token = ""; 
		String refresh_token = ""; 
		System.out.println("apiurl="+apiURL);
	    
		
		
		try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod(" get");="" int="" responsecode="con.getResponseCode();" bufferedreader="" br;="" system.out.print("responsecode="+responseCode);
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode==200) {
	        out.println(res.toString());
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }
	  %>*/
		
		
		
		
		mv.setViewName("member/naverLogin");
		return mv;
	}
	
	public String naverLoginInit(HttpServletRequest request) {
		String apiURL = "";
		try{
			String clientId = "vZbX7ZW_zMbNccbH_O_1";//애플리케이션 클라이언트 아이디값";
		    String redirectURI = URLEncoder.encode("http://127.0.0.1/naverLogin.do", "UTF-8");
		    SecureRandom random = new SecureRandom();
		    String state = new BigInteger(130, random).toString();
		    apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		    apiURL += "&client_id=" + clientId;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&state=" + state;
		    
		}catch(Exception e){
			 System.out.println(e);
		}
		return apiURL;
	}
	
	//여기로 콜백
	@RequestMapping(value = "/memberNaverLogin.do")
	public ModelAndView memberNaverLogin(MemberVO memberVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		/*String code = request.getParameter("code");//로그인 후 생성된 인증토큰
		String state = request.getParameter("state");//상태토큰 -> 세션과비교해서 CSRF방지
*/		
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		String accessTokn = request.getParameter("accessTokn");
		
		System.out.println(accessTokn);
		System.out.println(nickname);
		memberVo.setUserId(email);
		sessionManager.creatSessoin(memberVo, request, response);
		
		mv.setViewName("redirect:/index.do");
		return mv;
	}
	
}
