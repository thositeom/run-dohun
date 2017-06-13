package kr.dohun.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository("memberDAO")
public class memberDAO {

	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate){
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 13.
	 */
	public List memberList() throws Exception{
		return (List) sqlMapClientTemplate.queryForList("memberSqlMap.memberList");
	
	}
	
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 13.
	 */
	public memberVO memberInfo(memberVO vo) throws Exception{
		return (memberVO) sqlMapClientTemplate.queryForObject("memberSqlMap.memberInfo",vo);
	
	}
	
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 13.
	 */
	public int memberInsertInfo(memberVO vo) throws Exception{
		return sqlMapClientTemplate.update("memberSqlMap.memberInsertInfo", vo);
	}
	
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 13.
	 */
	public int memberUpdateInfo(memberVO vo) throws Exception{
		return sqlMapClientTemplate.update("memberSqlMap.memberUpdateInfo", vo);
	}
	
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 13.
	 */
	public int memberDeleteInfo(memberVO vo) throws Exception{
		return sqlMapClientTemplate.delete("memberSqlMap.memberDeleteInfo", vo);
	}
	
}
