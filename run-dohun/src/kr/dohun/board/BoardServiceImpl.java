package kr.dohun.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.dohun.common.CommonService;

@Service("BoardServiceImpl")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public List boardList(BoardVO vo) throws Exception {
		return boardDao.boardList(vo);
	}
	
	@Override
	public int boardListCnt(String vo) throws Exception {
		return boardDao.boardListCnt(vo);
	}

	@Override
	public int boardInsertInfo(BoardVO vo) throws Exception {
		
		if(vo.getBoardCreateUser() == null){
			vo.setBoardCreateUser("guest");
		}
		commonService.commonUpdateSeq("boardIdx"); //boardIdx 시퀀스증가
		vo.setBoardIdx(commonService.commonSeqCnt("boardIdx"));
		
		return boardDao.boardInsertInfo(vo);
	}

	@Override
	public int boardDeleteInfo(Map boardCheck) {
		return boardDao.boardDeleteInfo(boardCheck);
	}

	@Override
	public BoardVO boardDetailInfo(BoardVO vo) {
		return boardDao.boardDetailInfo(vo);
	}
}
