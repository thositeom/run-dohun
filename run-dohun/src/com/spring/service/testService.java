package com.spring.service;

import java.util.List;

import com.spring.vo.testVO;

public interface testService {
	public testVO testInfo(testVO vo) throws Exception;
	public List testInfoList() throws Exception;
	public int insertTestInfo(testVO vo) throws Exception;
	public int updateTestInfo(testVO vo) throws Exception;
	public int deleteTestInfo(testVO vo) throws Exception;
}
