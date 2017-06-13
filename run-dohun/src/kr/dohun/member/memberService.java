package kr.dohun.member;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface memberService {
	
	public List memberList() throws Exception;
	public memberVO memberInfo(memberVO vo) throws Exception;
	public int memberInsertInfo(memberVO vo) throws Exception;
	public int memberUpdateInfo(memberVO vo) throws Exception;
	public int memberDeleteInfo(memberVO vo) throws Exception;
	
}
