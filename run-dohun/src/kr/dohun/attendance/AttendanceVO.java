package kr.dohun.attendance;

public class AttendanceVO {

	private int attendIdx;
	private String memberId;
	private String memberName;
	private String attentTimeStart;
	private String attentTimeEnd;
	private String attentStatus;
	private String createDate;
	
	public int getAttendIdx() {
		return attendIdx;
	}
	public void setAttendIdx(int attendIdx) {
		this.attendIdx = attendIdx;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getAttentTimeStart() {
		return attentTimeStart;
	}
	public void setAttentTimeStart(String attentTimeStart) {
		this.attentTimeStart = attentTimeStart;
	}
	public String getAttentTimeEnd() {
		return attentTimeEnd;
	}
	public void setAttentTimeEnd(String attentTimeEnd) {
		this.attentTimeEnd = attentTimeEnd;
	}
	public String getAttentStatus() {
		return attentStatus;
	}
	public void setAttentStatus(String attentStatus) {
		this.attentStatus = attentStatus;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
