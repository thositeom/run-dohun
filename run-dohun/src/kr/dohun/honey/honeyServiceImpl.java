package kr.dohun.honey;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("honeyServiceImp")
public class honeyServiceImpl implements honeyService {

	@Autowired
	honeyDAO honeyDao;
	
	@Override
	public List honeyList() throws Exception {
		return (List) honeyDao.honeyList();
	}
	
	@Override
	public int honeyInsertInfo(honeyVO vo) throws Exception {
		return honeyDao.honeyInsertInfo(vo);
	}

	@Override
	public honeyVO honeyInfo(honeyVO vo) throws Exception {
		return honeyDao.honeyInfo(vo);
	}

	@Override
	public int honeyUpdateInfo(honeyVO vo) throws Exception {
		return honeyDao.honeyUpdateInfo(vo);
	}

	@Override
	public int honeyDeleteInfo(honeyVO vo) throws Exception {
		return honeyDao.honeyDeleteInfo(vo);
	}
}
