package kr.dohun.honey;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository("HoneyDAO")
public class HoneyDAO {

	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate){
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 8.
	 * 
	 */
	public List honeyList(String userId) throws Exception{
		return (List) sqlMapClientTemplate.queryForList("honeySqlMap.honeyList",userId);
	
	}
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 8.
	 */
	public HoneyVO honeyInfo(HoneyVO vo) throws Exception{
		return (HoneyVO) sqlMapClientTemplate.queryForObject("honeySqlMap.honeyInfo",vo);
	
	}
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 8.
	 */
	public int honeyInsertInfo(HoneyVO vo) throws Exception{
		return sqlMapClientTemplate.update("honeySqlMap.honeyInsertInfo", vo);
	}
	
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 8.
	 */
	public int honeyUpdateInfo(HoneyVO vo) throws Exception{
		return sqlMapClientTemplate.update("honeySqlMap.honeyUpdateInfo", vo);
	}
	
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 8.
	 */
	public int honeyDeleteInfo(HoneyVO vo) throws Exception{
		return sqlMapClientTemplate.delete("honeySqlMap.honeyDeleteInfo", vo);
	}
	
}
