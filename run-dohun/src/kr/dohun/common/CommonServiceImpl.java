package kr.dohun.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.dohun.common.CommonDao;
import kr.dohun.common.CommonService;

@Service("CommonServiceImpl")
public class CommonServiceImpl implements CommonService {

	@Autowired
	CommonDao commonDao;

	@Override
	public int commonSeqCnt(String seqId) throws Exception {
		return commonDao.commonSeqCnt(seqId);
	}
	
	@Override
	public int commonUpdateSeq(String seqId) throws Exception {
		return commonDao.commonUpdateSeq(seqId);
	}
}
