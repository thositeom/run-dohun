package kr.dohun.projects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjectsController {
	
	@RequestMapping(value = "/projectsForm.do")
	public ModelAndView honeyFrom(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("projects/projectsForm");
		return mv;
	}
}
