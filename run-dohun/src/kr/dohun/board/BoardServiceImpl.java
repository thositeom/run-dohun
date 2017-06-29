package kr.dohun.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("BoardServiceImp")
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;
	
	@Override
	public List boardList(BoardVO vo) throws Exception {
		return boardDao.boardList(vo);
	}

}
