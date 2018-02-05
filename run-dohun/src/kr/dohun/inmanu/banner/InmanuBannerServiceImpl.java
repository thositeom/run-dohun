package kr.dohun.inmanu.banner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.dohun.common.CommonService;

@Service("InmanuBannerServiceImpl")
public class InmanuBannerServiceImpl implements InmanuBannerService {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	InmanuBannerDAO inmanuBannerDao;
	
	/** 배너등록 */
	@Override
	public void inmanuBannerInert(InmanuBannerVO vo) throws Exception {
		commonService.commonUpdateSeq("BANNER_SEQ"); //boardIdx 시퀀스증가
		vo.setBannerIdx(commonService.commonSeqCnt("BANNER_SEQ")); 
		
		inmanuBannerDao.inmanuBannerInsert(vo);
	}

	/**	배너수정*/
	@Override
	public void inmanuBannerUpdate(InmanuBannerVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**	배너삭제*/
	@Override
	public void inmanuBannerDelete(InmanuBannerVO vo) throws Exception {
		inmanuBannerDao.inmanuBannerDelete(vo);
	}

	/**	배너목록*/
	@Override
	public List<InmanuBannerVO> inmanuBannerList() throws Exception {
		return inmanuBannerDao.inmanuBannerList();
	}

	/**	배너목록 Cnt */
	@Override
	public int inmanuBannerListCnt() throws Exception {
		return inmanuBannerDao.inmanuBannerListCnt();
	}

}
