package kr.dohun.member;

import java.util.List;

import org.springframework.stereotype.Service;

public interface MemberService {
	
	public List memberList() throws Exception;
	public MemberVO memberInfo(String vo) throws Exception;
	public int memberInsertInfo(MemberVO vo) throws Exception;
	public int memberUpdateInfo(MemberVO vo) throws Exception;
	public int memberDeleteInfo(MemberVO vo) throws Exception;
	public List memberHoneyList(MemberVO vo) throws Exception;
	public int memberHoneyListCnt(MemberVO vo) throws Exception;
	
	//NAVER(네아로) 사용자 정보 등록
	public int naverMergeInfo(MemberVO vo) throws Exception;
	//NAVER(네아로) MEMBER_ID로 사용자 정보 조회
	public MemberVO naverUserInfoUserId(String vo) throws Exception;
	//NAVER(네아로) SNS_ID로 사용자 정보 조회
	public MemberVO naverUserInfoSnsId(String vo) throws Exception;
	
}
