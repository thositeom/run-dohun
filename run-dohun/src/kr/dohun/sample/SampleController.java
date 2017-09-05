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
	
	@RequestMapping(value = "/lucyXssTest.do")
	public ModelAndView lucyXssTest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		String lucyXss = request.getParameter("lucyXss");
		mv.addObject("lucyXss",lucyXss);
		mv.setViewName("jsonView");
		return mv;
	}
}
