package kr.dohun.honey;

import java.util.List;

public interface honeyService {
	public List honeyList() throws Exception;
	public honeyVO honeyInfo(honeyVO vo) throws Exception;
	public int honeyInsertInfo(honeyVO vo) throws Exception;
	public int honeyUpdateInfo(honeyVO vo) throws Exception;
	public int honeyDeleteInfo(honeyVO vo) throws Exception;
	
	
}
