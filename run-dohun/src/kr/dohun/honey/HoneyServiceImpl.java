package kr.dohun.honey;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("HoneyServiceImp")
public class HoneyServiceImpl implements HoneyService {

	@Autowired
	HoneyDAO honeyDao;
	
	@Override
	public List honeyList(HoneyVO vo) throws Exception {
		return (List) honeyDao.honeyList(vo);
	}
	
	@Override
	public int honeyListCnt(String userId) throws Exception {
		return honeyDao.honeyListCnt(userId);
	}
	
	@Override
	public int honeyInsertInfo(HoneyVO vo) throws Exception {
		return honeyDao.honeyInsertInfo(vo);
	}

	@Override
	public HoneyVO honeyInfo(HoneyVO vo) throws Exception {
		return honeyDao.honeyInfo(vo);
	}

	@Override
	public int honeyUpdateInfo(HoneyVO vo) throws Exception {
		return honeyDao.honeyUpdateInfo(vo);
	}

	@Override
	public int honeyDeleteInfo(HoneyVO vo) throws Exception {
		return honeyDao.honeyDeleteInfo(vo);
	}

}
