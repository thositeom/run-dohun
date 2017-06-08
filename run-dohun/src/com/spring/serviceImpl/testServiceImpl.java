package com.spring.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.testDAO;
import com.spring.service.testService;
import com.spring.vo.testVO;

@Service("testServiceImp")
public class testServiceImpl implements testService{

	@Autowired
	testDAO testDao;

	public void setTestDAO(testDAO testDao){
		this.testDao = testDao;
	}
	
	@Override
	public testVO testInfo(testVO vo) throws Exception {
		return testDao.testInfo(vo);
	}

	@Override
	public List testInfoList() throws Exception {
		return testDao.testInfoList();
	}

	@Override
	public int insertTestInfo(testVO vo) throws Exception {
		return testDao.insertTestInfo(vo);
	}

	@Override
	public int updateTestInfo(testVO vo) throws Exception {
		return testDao.updateTestInfo(vo);
	}

	@Override
	public int deleteTestInfo(testVO vo) throws Exception {
		return testDao.deleteTestInfo(vo);
	}
	
	
}

