package kr.dohun.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import kr.dohun.honey.HoneyVO;

@Repository("BoardDao")
public class BoardDao {

	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate){
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
	
	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List boardList(BoardVO vo) throws Exception{
		return (List) sqlMapClientTemplate.queryForList("boardSqlMap.boardList",vo);
	}
	
	public int boardListCnt(String vo) throws Exception{
		return (int) sqlMapClientTemplate.queryForObject("boardSqlMap.boardListCnt",vo);
	}
	
	public int boardInsertInfo(BoardVO vo) throws Exception{
		return (int) sqlMapClientTemplate.update("boardSqlMap.boardInsertInfo",vo);
	}
	
	public int boardDeleteInfo(Map boardCheck) throws Exception{
        return sqlMapClientTemplate.delete("boardSqlMap.boardDeleteInfo", boardCheck);
    }
	
	public BoardVO boardDetailInfo(BoardVO vo) throws Exception{
		return (BoardVO) sqlMapClientTemplate.queryForObject("boardSqlMap.boardDetailInfo", vo);
	}
	
	public int boardUpdateInfo(BoardVO vo) throws Exception{
		return  (int) sqlMapClientTemplate.update("boardSqlMap.boardUpdateInfo", vo);
	}
	
	public int boardInsertFile(BoardVO vo) throws Exception{
		return  (int) sqlMapClientTemplate.update("boardSqlMap.boardInsertFile", vo);
	}
	
}
