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

import kr.dohun.common.commonUtil;
import kr.dohun.member.MemberService;
import kr.dohun.member.MemberVO;

@Controller
public class HoneyController {
	
	@Autowired
	private HoneyService honeyService;
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/honeyForm.do")
	public ModelAndView honeyFrom(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("honey/honeyForm");
		return mv;
	}
	@RequestMapping(value = "/honeyJqgridList.do")
	public ModelAndView honeyJqgridList(HttpServletRequest request, HttpServletResponse response, ModelMap model, MemberVO vo) {
		ModelAndView mv = new ModelAndView("jsonView");
		              
		List list = new ArrayList();
		Map map = new HashedMap();
		
		String currentPage = request.getParameter("page"); //현재페이지
		String rows = request.getParameter("rows"); //보여줄 건수
		
		if(!currentPage.equals("1")){
			int startRow = (Integer.parseInt(currentPage)*10)+1-Integer.parseInt(rows);
			int endRow = startRow+Integer.parseInt(rows);
			vo.setStartRow(startRow);
			vo.setEndRow(endRow);
		}
		
		try {
			list = (List) memberService.memberHoneyList(vo);
			mv.addObject("result", list); // 실제 jqgrid에서 뿌려져야 할 데이터
			mv.addObject("page",currentPage);		// 현재 페이지
			mv.addObject("total",commonUtil.getTotalPage(memberService.memberHoneyListCnt(vo), Integer.parseInt(rows)));	// 총 페이지 수
		
			response.setContentType("text/xml; charset=UTF-8");
			PrintWriter out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value = "/honeyJqgridMerge.do")
	public ModelAndView honeyJqgridMerge(HttpServletRequest request, MemberVO vo) {
		ModelAndView mv = new ModelAndView("jsonView");
		try {
			String oper = request.getParameter("oper");
			switch (oper){
			case "add":
					vo.setUserId(getCurrentDate());
					vo.setUserPassword("createAuto"); //자동가입시 createAuto
					vo.setUserEtc02("A"); //A:자동가입
//					cal.set(Calendar.MILLISECOND, 0);
					memberService.memberInsertInfo(vo);
				break;
			case "edit":
					memberService.memberUpdateInfo(vo);
				break;
			default ://del
				vo.setUserId(request.getParameter("userId"));
				memberService.memberDeleteInfo(vo);
				break;
			}
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value = "/honeyJqgridSubList.do")
	public ModelAndView honeyJqgridSub(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("jsonView");
		try {
			List list = new ArrayList();
			list = honeyService.honeyList(request.getParameter("userId"));
			mv.addObject("subUserId",request.getParameter("userId"));
			mv.addObject("resultSub",list);
			
			response.setContentType("text/xml; charset=UTF-8");
			PrintWriter out = response.getWriter();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value = "/honeyJqgridSubMerge.do")
	public ModelAndView honeyJqgridSubMerge(HttpServletRequest request, HoneyVO vo) {
		ModelAndView mv = new ModelAndView("jsonView");
		try {
			String oper = request.getParameter("oper");
			
			switch (oper){
			case "add":
					if(request.getParameter("subUserId") != null){
						vo.setHoneyUserId(request.getParameter("subUserId"));
						vo.setHoneyId(getCurrentDate());
						honeyService.honeyInsertInfo(vo);
					}
				break;
			case "edit":
					vo.setHoneyId(request.getParameter("honeyId"));
					honeyService.honeyUpdateInfo(vo);
				break;
			default ://del
				vo.setHoneyId(request.getParameter("honeyId"));
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
	
	private String getCurrentDate() {
		String currentDate = Long.toString(System.currentTimeMillis());
		/*String currentDate;
		Calendar cal = Calendar.getInstance();
		String year = Integer.toString(cal.get(Calendar.YEAR));
		String month = Integer.toString(cal.get(Calendar.MONTH));
		String date = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		String hour = Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
		String min = Integer.toString(cal.get(Calendar.MINUTE));
		String sec = Integer.toString(cal.get(Calendar.SECOND));
		currentDate = year+month+date+hour+min+sec; 
		System.out.println("createId: "+ currentDate);
		*/
		return currentDate;
	}
	
}
