package kr.dohun.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.dohun.common.CommonService;

@Controller
public class BoardController {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/boardForm.do")
	public ModelAndView boardForm(HttpServletRequest request, HttpServletResponse response, BoardVO vo){
		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("boardList", boardService.boardList(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("board/boardForm");
		return mv;
	} 

	
	@RequestMapping(value = "/boardWriteForm.do")
	public ModelAndView boardWriteFrom(HttpServletRequest request, HttpServletResponse response, BoardVO vo){
		ModelAndView mv = new ModelAndView();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("board/boardWriteForm");		
		return mv;
	}
	
	@RequestMapping(value = "/boardWrite.do")
	public ModelAndView boardWrite(HttpServletRequest request, HttpServletResponse response, BoardVO vo){
		ModelAndView mv = new ModelAndView();
		try {
			
//			vo.setBoardIdx(commonService.commonSeqCnt("boardIdx"));
			
			vo.setBoardIdx(commonService.commonUpdateSeq("boardIdx"));
			vo.setBoardId("A"); //A:일반게시판
			vo.setBoardCreateUser("userId");
			vo.setBoardUpdateUser("userId");
			boardService.boardInsertInfo(vo);
			
//			int bbb = commonService.commonUpdateSeq("boardIdx");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("redirect:/boardForm.do");		
		return mv;
	}
	
	@RequestMapping(value = "/boardUpdateForm.do")
	public ModelAndView boardUpdateList(HttpServletRequest request, HttpServletResponse response, BoardVO vo){
		ModelAndView mv = new ModelAndView();
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("board/boardUpdateForm");
		return mv;
	}
	
	@RequestMapping(value = "/boardUpdate.do")
	public ModelAndView boardUpdate(HttpServletRequest request, HttpServletResponse response, BoardVO vo){
		ModelAndView mv = new ModelAndView();
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("redirect:/boardForm.do");
		return mv;
	}
	
	@RequestMapping(value = "/boardDelete.do")
	public ModelAndView boardDelete(HttpServletRequest request, HttpServletResponse response, BoardVO vo){
		ModelAndView mv = new ModelAndView();
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("redirect:/boardForm.do");
		return mv;
	}
	
	@RequestMapping(value = "/boardList.do")
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response, BoardVO vo){
		ModelAndView mv = new ModelAndView();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("redirect:/boardForm.do");
		return mv;
	}
	
	@RequestMapping(value = "/boardTest.do")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("jsonView");
		return mv;
	}
}
