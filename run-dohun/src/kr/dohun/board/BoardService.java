package kr.dohun.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface BoardService {
	public List boardList(BoardVO vo, HttpServletRequest request) throws Exception;
	public int boardListCnt(String vo) throws Exception;
	public int boardInsertInfo(BoardVO vo, HttpServletRequest request) throws Exception;
	public int boardDeleteInfo(Map boardCheck) throws Exception;
	public BoardVO boardDetailInfo(BoardVO vo) throws Exception;
	public int boardUpdateInfo(BoardVO vo) throws Exception;
	
	public boolean boardTypeCheck(HttpServletRequest request, BoardVO vo) throws Exception;
	public BoardVO boardPage(BoardVO vo, HttpServletRequest request) throws Exception;
	public BoardVO fileUpload(BoardVO vo, HttpServletRequest request) throws Exception;
}
