package kr.dohun.member;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.dohun.honey.honeyVO;

@Service
public interface memberService {
	
	public List honeyList() throws Exception;
	public honeyVO honeyInfo(honeyVO vo) throws Exception;
	public int honeyInsertInfo(honeyVO vo) throws Exception;
	public int honeyUpdateInfo(honeyVO vo) throws Exception;
	public int honeyDeleteInfo(honeyVO vo) throws Exception;

	
}
