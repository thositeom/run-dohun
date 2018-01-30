package kr.dohun.inmanu.banner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InmanuBannerController {

	@RequestMapping(value = "/inmanuBannerForm.do")
	public ModelAndView inmanuBannerForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		try {
			
			
//			mv.addObject("inmanuBannerList", inmanuBannerList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.setViewName("inmanu/banner/inmanuBannerForm");
		return mv;
	}
}
