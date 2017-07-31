package kr.dohun.board;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView boardForm(HttpServletRequest request, HttpServletResponse response, BoardVO vo){
		ModelAndView mv = new ModelAndView();
		try {

			/*if(!currentPage.equals("1")){
				int startRow = (Integer.parseInt(currentPage)*10)+1-Integer.parseInt(rows);
				int endRow = startRow+Integer.parseInt(rows);
				vo.setStartRow(startRow);
				vo.setEndRow(endRow);
			}
			*/
			
			if(vo.getStartRow() != 1){
				String currentPage = request.getParameter("currentPage");
				int rows = 10;
				int startRow = (Integer.parseInt(currentPage)*10)+1-rows;
				int endRow = startRow+rows;
				
				vo.setStartRow(startRow);
				vo.setEndRow(endRow);
//				mv.addObject("startRow",startRow);
//				mv.addObject("endRow",endRow);
				mv.addObject("currentPage",currentPage);
			}else{
				int currentPage = 1;				
				mv.addObject("currentPage",currentPage);
//				mv.addObject("startRow",1);
//				mv.addObject("endRow",10);
			}
			
			mv.addObject("boardList", boardService.boardList(vo));
			mv.addObject("boardListCnt", boardService.boardListCnt(""));
			//mv.addObject("totalPage",totalPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response, BoardVO vo){
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
	public ModelAndView boardWriteFrom(HttpServletRequest request, HttpServletResponse response, BoardVO vo){
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
	public ModelAndView boardWrite(HttpServletRequest request, HttpServletResponse response, BoardVO vo){
		ModelAndView mv = new ModelAndView();
		try {
			
			vo.setBoardId("A"); //A:일반게시판
			boardService.boardInsertInfo(vo);
			
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
	public ModelAndView boardDelete(HttpServletRequest request, HttpServletResponse response, BoardVO vo){
		ModelAndView mv = new ModelAndView();
		try {
			
			String[] boardCheck = request.getParameterValues("boardCheck");
			
			Map hm = new HashMap();
			hm.put("boardCheck", boardCheck);
			
//			HashMap mapIdx = new HashMap();
//			mapIdx.put("boardCheck", boardCheck);
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
	public ModelAndView boardDetailForm(HttpServletRequest request, HttpServletResponse response, BoardVO vo){
		ModelAndView mv = new ModelAndView();
		try {
			BoardVO boardVo = boardService.boardDetailInfo(vo);
//			boardVo.setBoardContent(boardVo.getBoardContent().replaceAll("& lt;", "<").replaceAll("& gt;", ">"));
			mv.addObject("boardVo", boardVo);
			System.out.println(":::::::::::::::"+boardVo.getBoardContent());
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
	public ModelAndView boardUpdateList(HttpServletRequest request, HttpServletResponse response, BoardVO vo){
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
	public ModelAndView boardUpdate(HttpServletRequest request, HttpServletResponse response, BoardVO vo){
		ModelAndView mv = new ModelAndView();
		try {
			System.out.println("@@@@@@@@@@@@@@@1");
			System.out.println("@@@@@@@@@@@@@@@"+ vo.getBoardIdx());
			
			
			boardService.boardUpdateInfo(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("redirect:/boardForm.do");
		return mv;
	}
}
