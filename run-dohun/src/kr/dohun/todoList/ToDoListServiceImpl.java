package kr.dohun.todoList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ToDoListServiceImpl")
public class ToDoListServiceImpl implements ToDoListService {

	@Autowired
	ToDoListDao toDoListDao;

	@Override
	public int todoListMaxIndex() throws Exception {
		return toDoListDao.todoListMaxIndex();
	}
	
	@Override
	public void todoListWrite(ToDoListVO vo) throws Exception {
		toDoListDao.todoListWrite(vo);
	}

	@Override
	public void todoListDelete(ToDoListVO vo) throws Exception {
		toDoListDao.todoListDelete(vo);
	}

	@Override
	public List todoList() throws Exception {
		return toDoListDao.todoList();
	}

	@Override
	public int todoListCnt() throws Exception {
		return toDoListDao.todoListCnt();
	}


}
