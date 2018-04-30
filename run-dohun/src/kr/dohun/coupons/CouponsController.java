package kr.dohun.coupons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CouponsController {

	@Autowired
//	private BoardService boardService;
	
	@RequestMapping(value = "/couponsForm.do")
	public ModelAndView boardForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mv = new ModelAndView();
			
		mv.setViewName("coupons/couponsForm");
		return mv;
	} 
	
}
