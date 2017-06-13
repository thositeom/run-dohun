package kr.dohun.honey;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository("honeyDAO")
public class honeyDAO {

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
	public List honeyList() throws Exception{
		return (List) sqlMapClientTemplate.queryForList("honeySqlMap.honeyList");
	
	}
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 8.
	 */
	public honeyVO honeyInfo(honeyVO vo) throws Exception{
		return (honeyVO) sqlMapClientTemplate.queryForObject("honeySqlMap.honeyInfo",vo);
	
	}
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 8.
	 */
	public int honeyInsertInfo(honeyVO vo) throws Exception{
		return sqlMapClientTemplate.update("honeySqlMap.honeyInsertInfo", vo);
	}
	
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 8.
	 */
	public int honeyUpdateInfo(honeyVO vo) throws Exception{
		return sqlMapClientTemplate.update("honeySqlMap.honeyUpdateInfo", vo);
	}
	
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 8.
	 */
	public int honeyDeleteInfo(honeyVO vo) throws Exception{
		return sqlMapClientTemplate.delete("honeySqlMap.honeyDeleteInfo", vo);
	}
	
	/**
	 * @return
	 * @throws Exception
	 * dohun 2017. 6. 11.
	 */
	public List honeySubList(String userId) throws Exception{
		return sqlMapClientTemplate.queryForList("honeySqlMap.honeySubList", userId);
	
	}
}
