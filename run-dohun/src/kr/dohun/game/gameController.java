package kr.dohun.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.commonService;

@Controller
public class gameController {
	
	@Autowired
	private commonService commonService;

	@RequestMapping(value = "/baseballGameFrom.do")
	public ModelAndView basballGameFrom(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("game/baseballGameForm");
		System.out.println("::start::baseballGameFrom.do");
//		mv.setViewName("jsonView");
		mv.setViewName("game/baseballGameForm");
		return mv;
	}
	@RequestMapping(value = "/baseballGameStart.do")
	public ModelAndView basballGameStart(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("::start::baseballGameStart.do");
		ModelAndView mav = new ModelAndView();
		Map resultMap = new HashedMap();
		int strake = 0;
		int ball = 0;

		String startCheck = request.getParameter("startCheck");
		String userNum = request.getParameter("userNum");
		String rNum = request.getParameter("rNum");
		
		try{
			if(startCheck.equals("true")){
				//1. 입력받은값 list에 String로 추가
				List iList = new ArrayList<String>();
				iList = commonService.stringOfList(userNum);
			
				//2. 랜덤 숫자 4자리 생성(중복제거)
				List rList = new ArrayList<String>();
				rList = commonService.randomDeduplication();
				
				String randomNum = "";
				for (int i=0; i<4; i++) {//값출력
					System.out.print(rList.get(i)+" ");
					randomNum += rList.get(i);
				}
				
				System.out.println("");
				
				//3. 입력받은값 생성값 비교
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						if(iList.get(i) == rList.get(j)){
							if(i == j){
								strake++;
							}else{
								ball++;
							}
						}
					}
				}
				resultMap.put("rNum", randomNum);
				
			}else{
				//입력값 list에 String로 추가 
				List iList = new ArrayList<String>();
				iList = commonService.stringOfList(userNum);
				
				//랜덤변수 list에 String로 추가
				List jList = new ArrayList<String>();
				jList = commonService.stringOfList(rNum);
				
				//입력값 랜덤변수 비교
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						if(iList.get(i) == jList.get(j)){
							if(i == j){
								strake++;
							}else{
								ball++;
							}
						}
					}
				}  
				resultMap.put("rNum", rNum);
			}
			 
			System.out.println("스트라이크:"+strake+" 볼:"+ball);
			resultMap.put("userNum", userNum);
			resultMap.put("strake", strake);
			resultMap.put("ball", ball);
			resultMap.put("startCheck", "false");
			mav.addAllObjects(resultMap);
			mav.setViewName("jsonView");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return mav;
	}
}
