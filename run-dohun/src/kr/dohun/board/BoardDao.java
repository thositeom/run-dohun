package kr.dohun.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

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
	
	
}
