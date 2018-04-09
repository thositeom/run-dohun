package kr.dohun.todoList;

public class ToDoListVO {
	/** Page */
	private int startRow = 1;
	private int endRow = 10;
	private int currentPage=1;
	private int rows;
	
	/** ToDoListVO */
	private int userIdx;
	private String userId;
	private String userName;
	private int todoListIdx;
	private String todoLisTitle;
	private String todoLisContent;
	private String createDate;
	private String updateDate;

	
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getTodoListIdx() {
		return todoListIdx;
	}
	public void setTodoListIdx(int todoListIdx) {
		this.todoListIdx = todoListIdx;
	}
	public String getTodoLisTitle() {
		return todoLisTitle;
	}
	public void setTodoLisTitle(String todoLisTitle) {
		this.todoLisTitle = todoLisTitle;
	}
	public String getTodoLisContent() {
		return todoLisContent;
	}
	public void setTodoLisContent(String todoLisContent) {
		this.todoLisContent = todoLisContent;
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

	
}
