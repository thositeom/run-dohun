package kr.dohun.member;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {
	
	public List memberList() throws Exception;
	public MemberVO memberInfo(String vo) throws Exception;
	public int memberInsertInfo(MemberVO vo) throws Exception;
	public int memberUpdateInfo(MemberVO vo) throws Exception;
	public int memberDeleteInfo(MemberVO vo) throws Exception;
	public List memberHoneyList(MemberVO vo) throws Exception;
	public int memberHoneyListCnt(MemberVO vo) throws Exception;
}
