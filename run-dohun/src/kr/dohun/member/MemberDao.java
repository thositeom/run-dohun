package kr.dohun.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository("MemberDao")
public class MemberDao {

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
	public MemberVO memberInfo(String userId) throws Exception{
		return (MemberVO) sqlMapClientTemplate.queryForObject("memberSqlMap.memberInfo",userId);
	
	}
	
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 13.
	 */
	public int memberInsertInfo(MemberVO vo) throws Exception{
		return sqlMapClientTemplate.update("memberSqlMap.memberInsertInfo", vo);
	}
	
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 13.
	 */
	public int memberUpdateInfo(MemberVO vo) throws Exception{
		return sqlMapClientTemplate.update("memberSqlMap.memberUpdateInfo", vo);
	}
	
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 13.
	 */
	public int memberDeleteInfo(MemberVO vo) throws Exception{
		return sqlMapClientTemplate.delete("memberSqlMap.memberDeleteInfo", vo);
	}
	
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 21.
	 */
	public List memberHoneyList(MemberVO vo) throws Exception{
		return (List) sqlMapClientTemplate.queryForList("memberSqlMap.memberHoneyList", vo);
	}
	
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 22.
	 */
	public int memberHoneyListCnt(MemberVO vo) throws Exception{
		return (int) sqlMapClientTemplate.queryForObject("memberSqlMap.memberHoneyListCnt", vo);
	}
	
	
	public int naverMergeInfo(MemberVO vo) throws Exception{
		return sqlMapClientTemplate.update("memberSqlMap.naverMergeInfo", vo);
	}
	public MemberVO naverUserInfoUserId(String vo) throws Exception{
		return (MemberVO) sqlMapClientTemplate.queryForObject("memberSqlMap.naverUserInfoUserId",vo);
	}	
	public MemberVO naverUserInfoSnsId(String vo) throws Exception{
		return (MemberVO) sqlMapClientTemplate.queryForObject("memberSqlMap.naverUserInfoSnsId",vo);
	}
}
