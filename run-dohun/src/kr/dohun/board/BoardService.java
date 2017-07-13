package kr.dohun.board;

import java.util.List;
import java.util.Map;

public interface BoardService {
	public List boardList(BoardVO vo) throws Exception;
	public int boardListCnt(String vo) throws Exception;
	public int boardInsertInfo(BoardVO vo) throws Exception;
	public int boardDeleteInfo(Map boardCheck);
}
