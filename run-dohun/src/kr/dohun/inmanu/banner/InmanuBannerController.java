package kr.dohun.inmanu.banner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InmanuBannerController {
	
	@Autowired
	private InmanuBannerService inmanuBannerService; 

	@RequestMapping(value = "/inmanuBannerForm.do")
	public ModelAndView inmanuBannerForm(HttpServletRequest request, HttpServletResponse response, InmanuBannerVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		try {

			//목록조회
			mv.addObject("bannerList", inmanuBannerService.inmanuBannerList());
			mv.addObject("bannerListCnt", inmanuBannerService.inmanuBannerListCnt());
			mv.addObject("currentPage",vo.getCurrentPage());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.setViewName("inmanu/banner/inmanuBannerForm");
		return mv;
	}
	
	@RequestMapping(value = "/inmanuBannerWriteForm.do")
	public ModelAndView inmanuBannerWriteForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mv = new ModelAndView();
	
		mv.setViewName("inmanu/banner/inmanuBannerWriteForm");
		return mv;
	}
	
	@RequestMapping(value = "/inmanuBannerWrite.do")
	public ModelAndView inmanuBannerWrite(HttpServletRequest request, HttpServletResponse response, InmanuBannerVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		System.out.println("vo.getBannerIdx()  : "+ vo.getBannerIdx());
		System.out.println("vo.getBannerName() : "+ vo.getBannerName());
		System.out.println("vo.getBannerLink() : "+ vo.getBannerUrl());
		System.out.println("vo.getBannerType() : "+ vo.getBannerType());
		System.out.println("vo.getBannerDesc() : "+ vo.getBannerDesc());
		
		inmanuBannerService.inmanuBannerInert(vo);
		
		mv.setViewName("redirect:/inmanuBannerForm.do");
		return mv;
	}
	
	@RequestMapping(value = "/inmanuBannerDelete.do")
	public ModelAndView inmanuBannerDelete(HttpServletRequest request, HttpServletResponse response, InmanuBannerVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		System.out.println("vo.getBannerIdx()  : "+ vo.getBannerIdx());
		
		inmanuBannerService.inmanuBannerDelete(vo);
		mv.setViewName("redirect:/inmanuBannerForm.do");
		return mv;
	}
	
}
