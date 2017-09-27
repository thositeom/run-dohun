package kr.dohun.board;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.dohun.common.CommonService;
import kr.dohun.file.FileUpload;
import kr.dohun.session.SessionManager;

@Service("BoardServiceImpl")
public class BoardServiceImpl implements BoardService {

	final static String TEMP_PATH = "D://dohun/filedown/temp/"; //임시폴더 생성경로
	final static String REAL_PATH = "D://dohun/filedown/real/"; //실제폴더 생성경로
	
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
	public void boardInsertInfo(BoardVO vo, HttpServletRequest request) throws Exception {
		if(vo.getBoardCreateUser() == null){
			vo.setBoardCreateUser("guest");
		}
		
		commonService.commonUpdateSeq("BOARD_SEQ"); //boardIdx 시퀀스증가
		vo.setBoardIdx(commonService.commonSeqCnt("BOARD_SEQ")); 
		
		String folderName = Long.toString(System.currentTimeMillis());
		vo.setFilePath(REAL_PATH + folderName + "/");//실제폴더 생성경로

		if(vo.getFileUploadList() != null){
			for (int i = 0; i < vo.getFileUploadList().length; i++) {
				
				vo.setFileName(vo.getFileUploadList()[i].split("//")[0]);
				vo.setFileOrigName(vo.getFileUploadList()[i].split("//")[1]);
				vo.setFileSize(vo.getFileUploadList()[i].split("//")[2]);
				vo.setFileExtention(vo.getFileUploadList()[i].split("//")[3]);
				
				commonService.commonUpdateSeq("BOARD_FILE_SEQ"); //boardFileSeq 시퀀스증가
				vo.setFileIdx(commonService.commonSeqCnt("BOARD_FILE_SEQ"));//boardFileSeq를 fileIdex로 사용
				boardDao.boardInsertFile(vo);	//파일저장
				
				FileUpload.setMakeFolder(REAL_PATH, folderName);	//실제파일 저장될 폴더생성
				FileUpload.setMoveFolder(TEMP_PATH+request.getRequestedSessionId()+"/", vo.getFilePath(), vo.getFileName());	//임시폴더 -> 실제폴더 파일이동
				FileUpload.setRemoveFolder(TEMP_PATH, request.getRequestedSessionId());	//임시폴더삭제
			}	
		}
		
		 boardDao.boardInsertInfo(vo);	//게시물 저장
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
	public List fileUpload(BoardVO vo, HttpServletRequest request) throws Exception {
		String folderName = request.getRequestedSessionId(); //세션ID 임시폴더
		
		//임시폴더에 파일저장 후 파일명들 가져오기
		List fileList = FileUpload.getFileUpload(request, TEMP_PATH, folderName);
		
		for(int i = 0; i < fileList.size(); i++) {
			Map<String, String> fileMap = new HashMap<String, String>();
			fileMap = (Map<String, String>) fileList.get(i);
			
		}
		return fileList;
	}

	@Override
	public List boardFileList(BoardVO vo) throws Exception {
		return boardDao.boardFileList(vo);
	}

	@Override
	public BoardVO boardFileInfo(BoardVO vo) throws Exception {
		return boardDao.boardFileInfo(vo);
	}

	@Override
	public void fileDelete(BoardVO vo, HttpServletRequest request) throws Exception {
		String fileString = request.getParameter("fileString");
		String[] fileInfo= fileString.split("//");
		String folderName = request.getRequestedSessionId(); //세션ID 임시폴더
		
		FileUpload.setRemoveFile(TEMP_PATH, folderName, fileInfo[0]);
	}

}
