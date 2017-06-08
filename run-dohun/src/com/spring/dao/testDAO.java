package com.spring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.spring.vo.testVO;

@Repository("testDAO")
public class testDAO {

	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate){
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
	
	/**
	 * dohun 
	 * @param vo
	 * @return
	 * @throws Exception
	 * 2015. 8. 10.오후 2:31:16
	 */
	public testVO testInfo(testVO vo) throws Exception{
		return (testVO) sqlMapClientTemplate.queryForObject("testMap.testInfo",vo);
	}
	
	/**
	 * 
	 * dohun 
	 * @param vo
	 * @return
	 * @throws Exception
	 * 2015. 8. 10.오후 2:34:21
	 */
	public List testInfoList() throws Exception{
		return (List) sqlMapClientTemplate.queryForList("testMap.testInfoList");
	}
	
	/**
	 * dohun 
	 * @param vo
	 * @return
	 * @throws Exception
	 * 2015. 8. 10.오후 2:32:44
	 */
	public int insertTestInfo(testVO vo) throws Exception{
		return sqlMapClientTemplate.update("testMap.instTestInfo",vo);
	}
	
	/**
	 * 
	 * dohun 
	 * @param vo
	 * @return
	 * @throws Exception
	 * 2015. 8. 10.오후 2:40:19
	 */
	public int updateTestInfo(testVO vo) throws Exception{
		return sqlMapClientTemplate.update("testMap.updateTestInfo", vo);
		
	}
	
	/**
	 * 
	 * dohun 
	 * @param vo
	 * @return
	 * @throws Exception
	 * 2015. 8. 10.오후 2:40:15
	 */
	public int deleteTestInfo(testVO vo) throws Exception{
		return sqlMapClientTemplate.delete("testMap.deleteTestInfo", vo);
	}
	
}
