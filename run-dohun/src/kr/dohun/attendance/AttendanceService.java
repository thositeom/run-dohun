package kr.dohun.attendance;

import java.util.List;

public interface AttendanceService {

	/**근태 추가*/
	public boolean attendanceInsert(AttendanceVO vo) throws Exception;
	/**근태 목록*/
	public List<AttendanceVO> attendanceSelect(AttendanceVO vo) throws Exception;
	/**근태 목록 Cnt*/
	public int attendanceSelectCnt(AttendanceVO vo) throws Exception;
	/**근태 수정 */
	public void attendanceUpdate(AttendanceVO vo) throws Exception;
	/**근태 삭제*/
	public void attendanceDelete(AttendanceVO vo) throws Exception;
	
}
