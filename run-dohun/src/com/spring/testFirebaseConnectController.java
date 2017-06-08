package com.spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.spring.service.testService;
import com.spring.vo.testVO;

@Controller
public class testFirebaseConnectController {

	@Autowired
	private testService testService;

	@RequestMapping(value = "/index2.do")
	public ModelAndView testInit(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		testVO vo = new testVO();
		List listResult = null;

		vo.setId(request.getParameter("id"));
		vo.setPwd(request.getParameter("pwd"));
		int insert_result;

		try {
			if(request.getParameter("id") != null && request.getParameter("id") !=""){
				insert_result = testService.insertTestInfo(vo);
			}
			listResult = (List) testService.testInfoList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		mav.addObject("listResult", listResult);
		mav.setViewName("test/result");
		return mav;
	}

	
	@RequestMapping(value = "/udTest.do")
	public ModelAndView udTest(HttpServletRequest request) {
		int result;
		ModelAndView mav = new ModelAndView();
		testVO vo = new testVO();

		vo.setId(request.getParameter("udVal"));
		try {
			result = testService.deleteTestInfo(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RedirectView rv = new RedirectView("/index.do");
		rv.setExposeModelAttributes(false);
		mav.setView(rv);
		
		return mav;
	}
	
}
