package kr.dohun.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface BoardService {
	
	/** 게시판 목록 */
	public List boardList(BoardVO vo, HttpServletRequest request) throws Exception;
	/** 게시판 목록Count */
	public int boardListCnt(String vo) throws Exception;
	/** 게시판 등록 */
	public void boardInsertInfo(BoardVO vo, HttpServletRequest request) throws Exception;
	/** 게시판 삭제 */
	public int boardDeleteInfo(Map boardCheck) throws Exception;
	/** 게시판 상세보기 */
	public BoardVO boardDetailInfo(BoardVO vo) throws Exception;
	/** 게시판 수정 */
	public int boardUpdateInfo(BoardVO vo) throws Exception;
	/** 게시판 종류확인 */
	public boolean boardTypeCheck(HttpServletRequest request, BoardVO vo) throws Exception;
	/** 게시판 페이징 */
	public BoardVO boardPage(BoardVO vo, HttpServletRequest request) throws Exception;
	/** 게시판 파일업로드 */
	public List fileUpload(BoardVO vo, HttpServletRequest request) throws Exception;
	/** 게시판 파일목록 */
	public List boardFileList(BoardVO vo) throws Exception;
	
	
//	public void boardInsertFile(BoardVO vo) throws Exception;
}
