package kr.dohun.honey;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.commonService;

import kr.dohun.member.memberService;

@Controller
public class honeyController {
	
	@Autowired
	private commonService commonService;
	@Autowired
	private honeyService honeyService;
	@Autowired
	private memberService memberService;
	
	
	@RequestMapping(value = "/honeyForm.do")
	public ModelAndView honeyFrom(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		System.out.println("::start::honeyForm.do");
		
//		mv.setViewName("jsonView");
		
		mv.setViewName("honey/honeyForm");
		return mv;
	}
	@RequestMapping(value = "/honeyJqgridList.do")
	public ModelAndView honeyJqgridList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		ModelAndView mv = new ModelAndView("jsonView");
		              
		mv.addObject("page",1);		// 현재 페이지   
		mv.addObject("total",10);	// 총 페이지 수 
		
		List list = new ArrayList();
		Map map = new HashedMap();
		
		try {
			list = (List) honeyService.honeyList();

			/*map.put("No", "001");
			map.put("col1", "value01");
			map.put("col2", "value02");
			map.put("col3", "value03");
			map.put("col4", "value04");
			map.put("col5", "value05");
			map.put("col6", "value06");
			map.put("col7", "value07");
			map.put("col8", "value08");
			map.put("col9", "value09");
			map.put("col10", "value10");
			map.put("col11", "value11");
			map.put("col12", "value12");
			
			list.add(map);
			list.add(map);
			list.add(map);
		*/	
			mv.addObject("result", list); // 실제 jqgrid에서 뿌려져야 할 데이터
		
			response.setContentType("text/xml; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/honeyJqgridMerge.do")
	public ModelAndView honeyJqgridMerge(HttpServletRequest request, honeyVO vo) {
		ModelAndView mv = new ModelAndView("jsonView");
		try {
			String oper = request.getParameter("oper");
			honeyVO honeyVo = honeyService.honeyInfo(vo);
			
			switch (oper){
			case "add":
				if(honeyVo == null){
//					honeyService.honeyInsertInfo(vo);
					memberService.mem
					
				}
				break;
			case "edit":
					honeyService.honeyUpdateInfo(vo);
				break;
			default ://del
				vo.setHoneyId(request.getParameter("no"));
				honeyService.honeyDeleteInfo(vo);
				break;
			}
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value = "/honeyJqgridSub.do")
	public ModelAndView honeyJqgridSub(HttpServletRequest request, honeyVO vo) {
		ModelAndView mv = new ModelAndView("jsonView");
		try {
			List list = new ArrayList();
			list = honeyService.honeySubList(vo);
			mv.addObject("resultSub",list);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
}
