package kr.dohun.board;

public class BoardExcelVO {

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
	private String excelView;
	private int boardRecommendedIdx;	//추천여부Idx
	private String boardRecommendedType;	//추천여부 Type 추천:B 신고:W
	
	
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
	public String getExcelView() {
		return excelView;
	}
	public void setExcelView(String excelView) {
		this.excelView = excelView;
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
