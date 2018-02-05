package kr.dohun.inmanu.banner;

import java.util.List;

public interface InmanuBannerService {
	
	/** 배너등록 */
	public void inmanuBannerInert(InmanuBannerVO vo) throws Exception;
	/**	배너수정*/
	public void inmanuBannerUpdate(InmanuBannerVO vo) throws Exception;
	/**	배너삭제*/
	public void inmanuBannerDelete(InmanuBannerVO vo) throws Exception;
	/**	배너목록*/
	public List<InmanuBannerVO> inmanuBannerList() throws Exception;
	/**	배너목록Cnt*/
	public int inmanuBannerListCnt() throws Exception;
	
	
}
