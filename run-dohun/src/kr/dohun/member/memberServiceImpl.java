package kr.dohun.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.dohun.member.memberVO;


@Service("memberServiceImpl")
public class memberServiceImpl implements memberService {

	@Autowired
	memberDAO memberDao;
	
	@Override
	public List memberList() throws Exception {
		// TODO Auto-generated method stub
		return memberDao.memberList();
	}

	@Override
	public memberVO memberInfo(memberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.memberInfo(vo);
	}

	@Override
	public int memberInsertInfo(memberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.memberInsertInfo(vo);
	}

	@Override
	public int memberUpdateInfo(memberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.memberUpdateInfo(vo);
	}

	@Override
	public int memberDeleteInfo(memberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return memberDao.memberDeleteInfo(vo);
	}

}
