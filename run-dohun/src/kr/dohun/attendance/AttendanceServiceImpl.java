package kr.dohun.attendance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("AttendanceServiceImpl")
public class AttendanceServiceImpl implements AttendanceService {
	
	@Autowired
	private AttendanceDAO attendanceDAO;
	
	@Override
	public boolean attendanceInsert(AttendanceVO vo) throws Exception {
		if(vo.getAttentStatus().equals("S")){
			vo.setMemberId("guest");
			vo.setMemberName("게스트");
			attendanceDAO.attendanceInsert(vo);
		}else if(vo.getAttentStatus().equals("E")){
			attendanceDAO.attendanceUpdate(vo);
		}
		
		return true;
	}
	
	@Override
	public List<AttendanceVO> attendanceSelect(AttendanceVO vo) throws Exception {
		return attendanceDAO.attendanceSelect(vo);
	}

	@Override
	public int attendanceSelectCnt(AttendanceVO vo) throws Exception {
		return attendanceDAO.attendanceSelectCnt(vo);
	}

	@Override
	public void attendanceUpdate(AttendanceVO vo) throws Exception {
		attendanceDAO.attendanceUpdate(vo);
	}

	@Override
	public void attendanceDelete(AttendanceVO vo) throws Exception {
		attendanceDAO.attendanceDelete(vo);	
	}
}
