package kr.dohun.inmanu.code;

import java.util.List;

public interface InmanuCodeService {
	public List<InmanuCodeVO> InmanuCodeTopList() throws Exception;
	public List<InmanuCodeVO> InmanuCodeSubList(String topCode) throws Exception;
	
	public void inmanuTopCodeAdd(InmanuCodeVO vo) throws Exception;
	public void inmanuTopCodeDelete(InmanuCodeVO vo) throws Exception;
	
	
}
