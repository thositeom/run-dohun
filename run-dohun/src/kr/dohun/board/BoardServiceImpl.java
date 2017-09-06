package kr.dohun.board;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.dohun.common.CommonService;
import kr.dohun.session.SessionManager;

@Service("BoardServiceImpl")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public List boardList(BoardVO vo, HttpServletRequest request) throws Exception {
		return boardDao.boardList(boardPage(vo, request));
	}
	
	@Override
	public int boardListCnt(String vo) throws Exception {
		return boardDao.boardListCnt(vo);
	}

	@Override
	public int boardInsertInfo(BoardVO vo, HttpServletRequest request) throws Exception {
		if(vo.getBoardCreateUser() == null){
			vo.setBoardCreateUser("guest");
		}
		
		commonService.commonUpdateSeq("boardIdx"); //boardIdx 시퀀스증가
		vo.setBoardIdx(commonService.commonSeqCnt("boardIdx"));
		
		fileUpload(vo, request);
		
		return boardDao.boardInsertInfo(vo);
	}

	@Override
	public int boardDeleteInfo(Map boardCheck) throws Exception{
		return boardDao.boardDeleteInfo(boardCheck);
	}

	@Override
	public BoardVO boardDetailInfo(BoardVO vo) throws Exception{
		return boardDao.boardDetailInfo(vo);
	}

	@Override
	public int boardUpdateInfo(BoardVO vo) throws Exception{
		return boardDao.boardUpdateInfo(vo);
	}

	@Override
	public boolean boardTypeCheck(HttpServletRequest request, BoardVO vo) throws Exception {
		if(vo.getBoardType() == null){
			return false;
		}
		if(!vo.getBoardType().equals("PN")){ // P공개용 N일반게시판
			if(!SessionManager.userSessionCheck(request)){
				return false; 
			}
		}
		
		return true;
	}

	@Override
	public BoardVO boardPage(BoardVO vo, HttpServletRequest request) throws Exception {
		if(vo.getCurrentPage() != 1){
			int rows = vo.getRows();
			int endRow = vo.getCurrentPage()*rows;
			int startRow = endRow+1-rows;
			
			vo.setStartRow(startRow);
			vo.setEndRow(endRow);
		}
		return vo;
	}

	@Override
	public void fileUpload(BoardVO vo, HttpServletRequest request) throws Exception {
		MultipartFile mfile = null;
		String fieldName = "";
		String path = "D:\\dohun\\filedown";
		System.out.println("@1");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		System.out.println("@2");
		Iterator iter = multipartRequest.getFileNames();
		System.out.println("@3");
		
		while (iter.hasNext()) {

			fieldName = (String) iter.next(); // 내용을 가져와서
			mfile = multipartRequest.getFile(fieldName);
			
			
			if(mfile.isEmpty()){
				
				System.out.println("@@@@@@@@@@@@@@@@");
				
			}
			
			String origName = new String(mfile.getOriginalFilename().getBytes("utf-8"), "utf-8"); 
			// 파일명이 없다면
			if ("".equals(origName)) {
				continue;
			}
			long fileSize = mfile.getSize(); // 파일 사이즈
			// 확장자
			String extention = origName.substring(origName.lastIndexOf('.')+1);
			//UUID(Universally unique identifier)범용 고유 식별자 사용하여 파일명 변경.
			String saveFileName = UUID.randomUUID().toString().replaceAll("-", "") + extention;
			
			//DB저장
			vo.setFileIdx(saveFileName);
			vo.setFileName(origName);
			vo.setFilePath(path);
			vo.setFileSize(Long.toString(fileSize));
			vo.setFileExtention(extention);
			boardDao.boardInsertFile(vo);

			//파일 저장
			File serverFile = new File(path + File.separator + saveFileName );
			mfile.transferTo(serverFile);
			
			System.out.println("::::::::::::: "+origName);
			System.out.println("::::::::::::: "+fileSize);
			System.out.println("::::::::::::: "+extention);
			System.out.println("::::::::::::: "+saveFileName);
			
		}
	}
}
