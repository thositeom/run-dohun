package kr.dohun.board;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.dohun.common.JavaScript;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	/**
	 * @param request
	 * @param response
	 * @param vo
	 * 통합게시판 리스트폼
	 * @return
	 */
	@RequestMapping(value = "/boardForm.do")
	public ModelAndView boardForm(HttpServletRequest request, HttpServletResponse response, BoardVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		
//			if(!boardService.boardTypeCheck(request, vo)){
//				JavaScript.alert("로그인 후 이용이 가능합니다.", "/memberLoginForm.do").execute(response, request);
//				return null;
//			}
			mv.addObject("boardList", boardService.boardList(vo, request));
			mv.addObject("boardListCnt", boardService.boardListCnt(""));
			mv.addObject("currentPage",vo.getCurrentPage());
			mv.setViewName("board/boardForm");
		return mv;
	} 
	
	/**
	 * @param request
	 * @param response
	 * @param vo
	 * 통합게시판 리스트
	 * @return
	 */
	@RequestMapping(value = "/boardList.do")
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response, BoardVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("redirect:/boardForm.do");
		return mv;
	}
	
	/**
	 * @param request
	 * @param response
	 * @param vo
	 * 통합게시판 쓰기폼
	 * @return
	 */
	@RequestMapping(value = "/boardWriteForm.do")
	public ModelAndView boardWriteFrom(HttpServletRequest request, HttpServletResponse response, BoardVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("board/boardWriteForm");		
		return mv;
	}
	
	/**
	 * @param request
	 * @param response
	 * @param vo
	 * 통합게시판 쓰기
	 * @return
	 */
	@RequestMapping(value = "/boardWrite.do")
	public ModelAndView boardWrite(HttpServletRequest request, HttpServletResponse response, BoardVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		try {
			
			vo.setBoardId("A"); //A:일반게시판
			boardService.boardInsertInfo(vo, request);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("redirect:/boardForm.do");		
		return mv;
	}
	
	/**
	 * @param request
	 * @param response
	 * @param vo
	 * 통합게시판 삭제
	 * @return
	 */
	@RequestMapping(value = "/boardDelete.do")
	public ModelAndView boardDelete(HttpServletRequest request, HttpServletResponse response, BoardVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		try {
			
			String[] boardCheck = request.getParameterValues("boardCheck");
			
			Map hm = new HashMap();
			hm.put("boardCheck", boardCheck);
			
			if(0 < boardService.boardDeleteInfo(hm)){
				System.out.println("삭제완료");
			};
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("redirect:/boardForm.do");
		return mv;
	}
	
	/**
	 * @param request
	 * @param response
	 * @param vo
	 * 통합게시판 상세보기 폼
	 * @return
	 */
	@RequestMapping(value = "/boardDetailForm.do")
	public ModelAndView boardDetailForm(HttpServletRequest request, HttpServletResponse response, BoardVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		try {
			BoardVO boardVo = boardService.boardDetailInfo(vo);
			mv.addObject("boardVo", boardVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("board/boardDetailForm");
		return mv;
	}
	
	/**
	 * @param request
	 * @param response
	 * @param vo
	 * 통합게시판 수정폼
	 * @return
	 */
	@RequestMapping(value = "/boardUpdateForm.do")
	public ModelAndView boardUpdateList(HttpServletRequest request, HttpServletResponse response, BoardVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("board/boardUpdateForm");
		return mv;
	}
	
	/**
	 * @param request
	 * @param response
	 * @param vo
	 * 통합게시판 수정
	 * @return
	 */
	@RequestMapping(value = "/boardUpdate.do")
	public ModelAndView boardUpdate(HttpServletRequest request, HttpServletResponse response, BoardVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		try {
			boardService.boardUpdateInfo(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("redirect:/boardForm.do");
		return mv;
	}
}
