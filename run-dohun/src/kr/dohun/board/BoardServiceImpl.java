package kr.dohun.board;

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
		
		
		System.out.println("::::::::::::::::::::: "+vo.getFileName());
		
		
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
	public List fileUpload(BoardVO vo, HttpServletRequest request) throws Exception {
		String path = "D:\\dohun\\filedown\\real\\"; //실제폴더 생성경로 
//		String path = "D:\\dohun\\filedown\\temp\\"; //임시폴더 생성경로
		String folderName = request.getRequestedSessionId(); //세션ID 임시폴더경로
		
		//임시폴더에 파일저장 후 파일명들 가져오기
		List fileList = FileUpload.getFileUpload(request, path, folderName);
		
		for(int i = 0; i < fileList.size(); i++) {
			Map<String, String> fileMap = new HashMap<String, String>();
			fileMap = (Map<String, String>) fileList.get(i);
			
/*			vo.setFileIdx(fileMap.get("saveFileName"));
			vo.setFileName(fileMap.get("origName"));
			vo.setFilePath(path);
			vo.setFileSize(fileMap.get("fileSize"));
			vo.setFileExtention(fileMap.get("extention"));
			boardDao.boardInsertFile(vo);//DB저장
*/
			
		}
		return fileList;
	}

	@Override
	public BoardVO fileTempUpload(BoardVO vo, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
