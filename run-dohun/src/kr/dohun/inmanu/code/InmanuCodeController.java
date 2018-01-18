package kr.dohun.inmanu.code;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InmanuCodeController {

	@Autowired
	private InmanuCodeService inmanuCodeService;
	
	@RequestMapping(value = "/inmanuCodeForm.do")
	public ModelAndView inmanuCodeMngForm(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		
		try {
			List<InmanuCodeVO> codeTopList = inmanuCodeService.InmanuCodeTopList();
			mv.addObject("codeTopList", codeTopList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.setViewName("inmanu/code/inmanuCodeForm");
		return mv;
	}
	
	@RequestMapping(value = "/inmanuSubCode.do")
	public ModelAndView inmanuSubCode(HttpServletRequest request, HttpServletResponse response, InmanuCodeVO inmanuCodeVO) {
		response.setHeader("Content-Type", "application/xml"); 
		response.setContentType("text/xml;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8"); 
		ModelAndView mv = new ModelAndView();
		try {
			List<InmanuCodeVO> codeSubList = inmanuCodeService.InmanuCodeSubList(inmanuCodeVO.getTopCode());
			mv.addObject("codeSubList", codeSubList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	 * 상위코드 등록팝업창
	 * @param request
	 * @param response
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/inmanuTopCodeAddPop.do")
	public ModelAndView inmanuTopCodeAddPop(HttpServletRequest request, HttpServletResponse response, InmanuCodeVO vo) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("inmanu/code/inmanuTopCodeAddPop");
		return mv;
	}
	
	/**
	 * 상위코드 등록
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/inmanuTopCodeAdd.do")
	public ModelAndView inmanuTopCodeAdd(HttpServletRequest request, HttpServletResponse response, InmanuCodeVO vo) throws Exception{
		response.setHeader("Content-Type", "application/xml"); 
		response.setContentType("text/xml;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		ModelAndView mv = new ModelAndView();
		try {
			inmanuCodeService.inmanuTopCodeAdd(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	 * 상위코드 삭제
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/inmanuTopCodeDelete.do")
	public ModelAndView inmanuTopCodeDelete(HttpServletRequest request, HttpServletResponse response, InmanuCodeVO vo) {
		response.setHeader("Content-Type", "application/xml"); 
		response.setContentType("text/xml;charset=UTF-8"); 
		response.setCharacterEncoding("utf-8");
		ModelAndView mv = new ModelAndView();
		try {
			inmanuCodeService.inmanuTopCodeDelete(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.setViewName("jsonView");
		return mv;
	}
}
