package kr.dohun.attendance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository("AttendanceDAO")
public class AttendanceDAO {

	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate){
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
	
	/*근태 추가*/
	public void attendanceInsert(AttendanceVO vo) throws Exception{
		sqlMapClientTemplate.insert("attendanceSqlMap.attendanceInsert", vo);
	}
	/*근태 목록*/
	public List<AttendanceVO> attendanceSelect(AttendanceVO vo) throws Exception{
		return sqlMapClientTemplate.queryForList("attendanceSqlMap.attendanceSelect", vo);
	}
	/*근태 목록 Cnt*/
	public int attendanceSelectCnt(AttendanceVO vo) throws Exception{
		return (int) sqlMapClientTemplate.queryForObject("attendanceSqlMap.attendanceSelectCnt", vo);
	}
	/*근태 수정 */
	public void attendanceUpdate(AttendanceVO vo) throws Exception{
		sqlMapClientTemplate.update("attendanceSqlMap.attendanceUpdate", vo);
	}
	/*근태 삭제*/
	public void attendanceDelete(AttendanceVO vo) throws Exception{
		sqlMapClientTemplate.delete("attendanceSqlMap.attendanceDelete", vo);
	}
}
