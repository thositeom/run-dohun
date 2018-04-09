package kr.dohun.todoList;

import java.util.List;

public interface ToDoListService {
	
	/* ToDoList 조회 */
	public int todoListMaxIndex() throws Exception;
	
	/* ToDoList 조회 */
	public void todoListWrite(ToDoListVO vo) throws Exception;
	
	/* ToDoList 삭제 */
	public void todoListDelete(ToDoListVO vo) throws Exception;
	
	/* ToDoList 목록 */
	public List todoList() throws Exception;
		
	/* ToDoList 목록Cnt */
	public int todoListCnt() throws Exception;
}
