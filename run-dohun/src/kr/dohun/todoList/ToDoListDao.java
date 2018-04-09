package kr.dohun.todoList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository("ToDoListDao")
public class ToDoListDao {

	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate){
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	public int todoListMaxIndex() throws Exception{
		 return (int)sqlMapClientTemplate.queryForObject("todoListSqlMap.todoListMaxIndex");
	}
	
	public void todoListWrite(ToDoListVO vo) throws Exception{
		 sqlMapClientTemplate.insert("todoListSqlMap.todoListWrite", vo);
	}
	
	public void todoListDelete(ToDoListVO vo) throws Exception{
		 sqlMapClientTemplate.delete("todoListSqlMap.todoListDelete", vo);
	}
	
	public List todoList(){
		return (List)sqlMapClientTemplate.queryForList("todoListSqlMap.todoList");
	}
	
	public int todoListCnt(){
		return (int) sqlMapClientTemplate.queryForObject("todoListSqlMap.todoListCnt");
	}
	
}
