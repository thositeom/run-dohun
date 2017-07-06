package kr.dohun.board;

import java.util.List;

public interface BoardService {
	public List boardList(BoardVO vo) throws Exception;
	public int boardInsertInfo(BoardVO vo) throws Exception;
}
