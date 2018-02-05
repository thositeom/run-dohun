package kr.dohun.inmanu.code;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.dohun.member.MemberService;

@Controller
public class InmanuCodeManager {

	@Autowired
	private InmanuCodeService inmanuCodeService;
	
	@RequestMapping(value = "/inmanuCodeMngForm.do")
	public ModelAndView inmanuCodeMngForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		try {
			inmanuCodeService.test001();
			List<InmanuCodeVO> codeTopList = inmanuCodeService.InmanuCodeTopList();
//			List<InmanuCodeVO> codeSubList = inmanuCodeService.InmanuCodeSubList();
			
			mv.addObject("codeTopList", codeTopList);
//			mv.addObject("codeSubList", codeSubList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.setViewName("inmanu/inmanuCodeMngForm");
		return mv;
	}
	
}
