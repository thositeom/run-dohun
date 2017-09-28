package kr.dohun.board;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {

	private int startRow = 1;
	private int endRow = 10;
	private int currentPage=1;
	private int rows;
	
	private int userIdx;
	private String userName;

	private int boardIdx;//boardIdx
	private String boardId;//게시판Id
	private int boardParentIdx;//부모Idx
	private String boardType;//게시판 타입
	private String boardTitle;//제목
	private String boardContent;//내용
	private String boardNotice;//공지
	private String boardSecret;//비밀글
	private String boardCreateDate;
	private String boardCreateUser;
	private String boardUpdateDate;
	private String boardUpdateUser;
	private String boardBest;//추천수
	private String boardWost;//신고수
	private String boardCategory01;
	private String boardCategory02;
	private String boardCategory03;
	private String boardCount;//조회수
	private String boardAnswerCnt;//답변수
	private String createUser;
	private String updateUser;
	
	private MultipartFile fileUpload;
	private String fileUploadList[];
	private int fileIdx;	//파일idx
	private String fileName;	//파일명(변경된파일명)
	private String fileOrigName;	//파일명(변경된파일명)
	private String filePath;
	private String fileSize;
	private String fileExtention;
	private String createDate;
	private String updateDate;
	
	private int boardRecommendedIdx;	//추천여부Idx
	private String boardRecommendedType;	//추천여부 Type 추천:B 신고:W
	
	
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public int getBoardParentIdx() {
		return boardParentIdx;
	}
	public void setBoardParentIdx(int boardParentIdx) {
		this.boardParentIdx = boardParentIdx;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardNotice() {
		return boardNotice;
	}
	public void setBoardNotice(String boardNotice) {
		this.boardNotice = boardNotice;
	}
	public String getBoardSecret() {
		return boardSecret;
	}
	public void setBoardSecret(String boardSecret) {
		this.boardSecret = boardSecret;
	}
	public String getBoardCreateDate() {
		return boardCreateDate;
	}
	public void setBoardCreateDate(String boardCreateDate) {
		this.boardCreateDate = boardCreateDate;
	}
	public String getBoardCreateUser() {
		return boardCreateUser;
	}
	public void setBoardCreateUser(String boardCreateUser) {
		this.boardCreateUser = boardCreateUser;
	}
	public String getBoardUpdateDate() {
		return boardUpdateDate;
	}
	public void setBoardUpdateDate(String boardUpdateDate) {
		this.boardUpdateDate = boardUpdateDate;
	}
	public String getBoardUpdateUser() {
		return boardUpdateUser;
	}
	public void setBoardUpdateUser(String boardUpdateUser) {
		this.boardUpdateUser = boardUpdateUser;
	}
	public String getBoardBest() {
		return boardBest;
	}
	public void setBoardBest(String boardBest) {
		this.boardBest = boardBest;
	}
	public String getBoardWost() {
		return boardWost;
	}
	public void setBoardWost(String boardWost) {
		this.boardWost = boardWost;
	}
	public String getBoardCategory01() {
		return boardCategory01;
	}
	public void setBoardCategory01(String boardCategory01) {
		this.boardCategory01 = boardCategory01;
	}
	public String getBoardCategory02() {
		return boardCategory02;
	}
	public void setBoardCategory02(String boardCategory02) {
		this.boardCategory02 = boardCategory02;
	}
	public String getBoardCategory03() {
		return boardCategory03;
	}
	public void setBoardCategory03(String boardCategory03) {
		this.boardCategory03 = boardCategory03;
	}
	public String getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(String boardCount) {
		this.boardCount = boardCount;
	}
	public String getBoardAnswerCnt() {
		return boardAnswerCnt;
	}
	public void setBoardAnswerCnt(String boardAnswerCnt) {
		this.boardAnswerCnt = boardAnswerCnt;
	}
	public MultipartFile getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(MultipartFile fileUpload) {
		this.fileUpload = fileUpload;
	}
	public String[] getFileUploadList() {
		return fileUploadList;
	}
	public void setFileUploadList(String[] fileUploadList) {
		this.fileUploadList = fileUploadList;
	}
	public int getFileIdx() {
		return fileIdx;
	}
	public void setFileIdx(int fileIdx) {
		this.fileIdx = fileIdx;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileOrigName() {
		return fileOrigName;
	}
	public void setFileOrigName(String fileOrigName) {
		this.fileOrigName = fileOrigName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileExtention() {
		return fileExtention;
	}
	public void setFileExtention(String fileExtention) {
		this.fileExtention = fileExtention;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public int getBoardRecommendedIdx() {
		return boardRecommendedIdx;
	}
	public void setBoardRecommendedIdx(int boardRecommendedIdx) {
		this.boardRecommendedIdx = boardRecommendedIdx;
	}
	public String getBoardRecommendedType() {
		return boardRecommendedType;
	}
	public void setBoardRecommendedType(String boardRecommendedType) {
		this.boardRecommendedType = boardRecommendedType;
	}
	
	
}
