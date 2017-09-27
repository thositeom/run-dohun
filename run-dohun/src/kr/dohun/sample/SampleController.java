package kr.dohun.sample;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {
	
	@RequestMapping(value = "/sampleForm.do")
	public ModelAndView sampleForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sample/sampleForm");
		return mv;
	}
	
	/**
	 * Naver lucyXss 필터
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/lucyXssTest.do")
	public ModelAndView lucyXssTest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		String lucyXss = request.getParameter("lucyXss");
		mv.addObject("lucyXss",lucyXss);
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	 * 데이터변환 bytes -> KB,MB,GB,TB,EB
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/dataConversion.do")
	public ModelAndView dataConversion(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		String value = request.getParameter("bytes");
		
		long bytes = Long.parseLong(value);
		boolean si = Boolean.valueOf(request.getParameter("si")).booleanValue(); //International System of Units(SI)
		
		int unit = si ? 1000 : 1024; //SI단위로 할지 Data단위로 계산할지 여부
	    if (bytes < unit) {
	    	mv.addObject("dataConversion", bytes + " B");
	    	System.out.println(bytes + " B");
	    }else{
	    	int exp = (int) (Math.log(bytes) / Math.log(unit));//Math.log 밑이 2인 로그함수
		    String pre = (si ? "KMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
		    String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
			System.out.println(String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre));
			mv.addObject("dataConversion", String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre));
	    }
		
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	
}
