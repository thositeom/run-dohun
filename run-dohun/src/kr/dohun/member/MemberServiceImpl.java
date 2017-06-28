package kr.dohun.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("MemberServiceImpl")
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao memberDao;
	
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
		// TODO Auto-generated method stub
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

}
