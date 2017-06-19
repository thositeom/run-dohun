package kr.dohun.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("MemberServiceImpl")
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO memberDao;
	
	@Override
	public List memberList() throws Exception {
		// TODO Auto-generated method stub
		return memberDao.memberList();
	}

	@Override
	public MemberVO memberInfo(MemberVO vo) throws Exception {
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

}