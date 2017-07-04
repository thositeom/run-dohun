package kr.dohun.board;

import java.util.List;

import org.springframework.stereotype.Service;

public interface BoardService {
	public List boardList(BoardVO vo) throws Exception;
	public int boardInsertInfo(BoardVO vo) throws Exception;
}
