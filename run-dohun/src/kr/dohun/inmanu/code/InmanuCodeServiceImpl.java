package kr.dohun.inmanu.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("InmanuCodeServiceImpl")
public class InmanuCodeServiceImpl implements InmanuCodeService {

	@Autowired
	InmanuCodeDAO inmanuCodeDao;
	
	@Override
	public void test001() throws Exception {
		inmanuCodeDao.test001();
	}

	@Override
	public List<InmanuCodeVO> InmanuCodeTopList() throws Exception {
		return inmanuCodeDao.InmanuCodeTopList();
	}

	@Override
	public List<InmanuCodeVO> InmanuCodeSubList(String topCode) throws Exception {
		return inmanuCodeDao.InmanuCodeSubList(topCode);
	}

	@Override
	public void inmanuTopCodeAdd(InmanuCodeVO vo) throws Exception {
		vo.setTopCode(vo.getTopCode().toUpperCase());
		inmanuCodeDao.inmanuTopCodeAdd(vo);
	}

	@Override
	public void inmanuTopCodeDelete(InmanuCodeVO vo) throws Exception {
		inmanuCodeDao.inmanuSubCodeDelete(vo);
		inmanuCodeDao.inmanuTopCodeDelete(vo);
	}
	
}
