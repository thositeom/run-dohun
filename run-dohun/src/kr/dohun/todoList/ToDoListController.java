package kr.dohun.todoList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ToDoListController {

	@Autowired
	private ToDoListService toDoListService;
	
	@RequestMapping(value = "/todoListWrite.do")
	public ModelAndView todoListWrite(HttpServletRequest request, HttpServletResponse response, ToDoListVO vo) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		vo.setTodoListIdx(toDoListService.todoListMaxIndex() + 1);
		toDoListService.todoListWrite(vo);
		return mv;
	}
	
	@RequestMapping(value = "/todoListDelete.do")
	public ModelAndView todoListDelete(HttpServletRequest request, HttpServletResponse response, ToDoListVO vo) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		System.out.println(vo.getTodoListIdx());
		toDoListService.todoListDelete(vo);
		return mv;
	}
}
