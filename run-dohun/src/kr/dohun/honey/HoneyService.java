package kr.dohun.honey;

import java.util.List;

public interface HoneyService {

	public List honeyList(String userId) throws Exception;
	public HoneyVO honeyInfo(HoneyVO vo) throws Exception;
	public int honeyInsertInfo(HoneyVO vo) throws Exception;
	public int honeyUpdateInfo(HoneyVO vo) throws Exception;
	public int honeyDeleteInfo(HoneyVO vo) throws Exception;

}
