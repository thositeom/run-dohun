package kr.dohun.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.dohun.common.CommonService;


@Service("MemberServiceImpl")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public List memberList() throws Exception {
		// TODO Auto-generated method stub
		return memberDao.memberList();
	}

	@Override
	public MemberVO memberInfo(String vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.memberInfo(vo);
	}

	@Override
	public int memberInsertInfo(MemberVO vo) throws Exception {
		commonService.commonUpdateSeq("MEMBER_SEQ");
		vo.setUserIdx(commonService.commonSeqCnt("MEMBER_SEQ")); 
		return memberDao.memberInsertInfo(vo);
	}

	@Override
	public int memberUpdateInfo(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.memberUpdateInfo(vo);
	}

	@Override
	public int memberDeleteInfo(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.memberDeleteInfo(vo);
	}

	@Override
	public List memberHoneyList(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.memberHoneyList(vo);
	}

	@Override
	public int memberHoneyListCnt(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.memberHoneyListCnt(vo);
	}

	@Override
	public int naverMergeInfo(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.naverMergeInfo(vo);
	}

	@Override
	public MemberVO naverUserInfoUserId(String vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.naverUserInfoUserId(vo);
	}

	@Override
	public MemberVO naverUserInfoSnsId(String vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.naverUserInfoSnsId(vo);
	}

}
