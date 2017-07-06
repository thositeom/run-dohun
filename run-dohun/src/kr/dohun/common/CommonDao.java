package kr.dohun.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository("CommonDao")
public class CommonDao {

	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate){
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	/**
	 * @param seqId
	 * @throws Exception
	 * dohun 2017. 7. 5.
	 */
	public int commonSeqCnt(String seqId) throws Exception{
		return (int) sqlMapClientTemplate.queryForObject("commonSqlMap.commonSeqCnt",seqId);
	}
	
	/**
	 * @param vo
	 * @return
	 * @throws Exception
	 * dohun 2017. 7. 5.
	 */
	public int commonUpdateSeq(String seqId) throws Exception{
		return (int) sqlMapClientTemplate.queryForObject("commonSqlMap.commonUpdateSeq",seqId);
	}
	
	
}
