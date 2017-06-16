package kr.dohun.honey;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLEditorKit.Parser;

import org.apache.commons.collections.map.HashedMap;
import org.apache.tomcat.util.descriptor.web.InjectionTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.commonService;

import kr.dohun.member.memberService;
import kr.dohun.member.memberVO;

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
			list = (List) memberService.memberList();
			
			mv.addObject("result", list); // 실제 jqgrid에서 뿌려져야 할 데이터
		
			response.setContentType("text/xml; charset=UTF-8");
			PrintWriter out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(value = "/honeyJqgridMerge.do")
	public ModelAndView honeyJqgridMerge(HttpServletRequest request, memberVO vo) {
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
	public ModelAndView honeyJqgridSubMerge(HttpServletRequest request, honeyVO vo) {
		ModelAndView mv = new ModelAndView("jsonView");
		try {
			String oper = request.getParameter("oper");
			
			switch (oper){
			case "add":
					System.out.println(request.getParameter("subUserId"));
					if(request.getParameter("subUserId") != null){
						
						vo.setHoneyId(getCurrentDate());
						String cost = getUnitCost(vo.getHoneyOrderList());
						vo.setHoneyCost(cost);
						vo.setHoneyAmount(getAmount(vo.getHoneyQty(),cost));
						
						vo.setHoneyUserId(request.getParameter("subUserId"));
						
						honeyService.honeyInsertInfo(vo);
					}
				break;
			case "edit":
					String cost = getUnitCost(vo.getHoneyOrderList());
					vo.setHoneyCost(cost);
					vo.setHoneyAmount(getAmount(vo.getHoneyQty(),cost));
					honeyService.honeyUpdateInfo(vo);
				break;
			default :
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
	
	
	
	/**
	 * @return
	 * dohun 2017. 6. 15.
	 * 현재 날짜 1/1000 가져오기
	 */
	private String getCurrentDate() {
/*		String currentDate;
		Calendar cal = Calendar.getInstance();
*/		
		String currentDate = Long.toString(System.currentTimeMillis());
		/*String year = Integer.toString(cal.get(Calendar.YEAR));
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
	
	/**
	 * @return
	 * dohun 2017. 6. 15.
	 * 주문내역으로 단가 구하기  TODO 코드 값 변환해서 디비에서 가져오게 변환
	 */
	private String  getUnitCost(String orderList) {
		String cost="";
		switch (orderList) {
		case "잡화":
			cost = "40000";
			break;
		case "아카시아":
			cost = "40000";
			break;
		case "꽃화분":
			cost = "60000";
			break;
		default:
			cost = "80000";
			break;
		}
		return cost;
	}
	
	private String getAmount(String qty, String cost) {
		int numQty =  Integer.parseInt(qty);
		int numCost = Integer.parseInt(cost);

		return String.valueOf(numQty * numCost);
	}
	
}
