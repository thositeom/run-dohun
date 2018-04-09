package kr.dohun.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.vo.testVO;

import kr.dohun.todoList.ToDoListService;

@Controller
public class MainController {
	
	@Autowired
	ToDoListService toDoListService;
	
	@RequestMapping(value = "/index.do")
	public ModelAndView index(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("todoListResult", toDoListService.todoList());
		mv.setViewName("main/main");
		return mv;
	} 
	
	@RequestMapping(value = "/mainForm.do")
	public ModelAndView mainForm(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main/mainForm");
		return mv;
	}
	
	@RequestMapping(value = "/springFormTest.do")
	public ModelAndView springFormTest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mv = new ModelAndView();
		testVO vo = new testVO();
		mv.addObject("testVO",vo);
		mv.setViewName("test/springFormTest");
		return mv;
	}
		
		
	
}
