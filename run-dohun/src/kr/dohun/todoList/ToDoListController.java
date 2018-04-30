package kr.dohun.todoList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ToDoListController {

	@Autowired
	private ToDoListService toDoListService;
	
	@RequestMapping(value = "/todoListWrite.do")
	public ModelAndView todoListWrite(HttpServletRequest request, HttpServletResponse response, ToDoListVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		vo.setTodoListIdx(toDoListService.todoListMaxIndex() + 1);
		toDoListService.todoListWrite(vo);
		
		RedirectView rv = new RedirectView("/index.do");
		rv.setExposeModelAttributes(false);
		mv.setView(rv);
		
		return mv;
	}
	
	@RequestMapping(value = "/todoListDelete.do")
	public ModelAndView todoListDelete(HttpServletRequest request, HttpServletResponse response, ToDoListVO vo) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
		toDoListService.todoListDelete(vo);
		return mv;
	}
}
