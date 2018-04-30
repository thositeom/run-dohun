package kr.dohun.attendance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	
	@RequestMapping(value = "/attendanceForm.do")
	public ModelAndView attendanceForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mv = new ModelAndView("attendance/attendanceForm");
		
		return mv;
	}
	
	@RequestMapping(value = "/attendanceInsert.do")
	public ModelAndView attendanceInsert(HttpServletRequest request, HttpServletResponse response, AttendanceVO vo) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		
		
		attendanceService.attendanceSelect(vo)
		
		//등록
		if(attendanceService.attendanceInsert(vo)){
			mv.addObject("success", "Y");
		}else{
			mv.addObject("success", "N");
		}
		
		return mv;
	}
	
}
