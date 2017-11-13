package kr.dohun.member;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MemberVO {
	
	private int startRow = 1;
	private int endRow = 11;
	
	private int userIdx;
	private String userId;
	private String userName;
	private String userEmail;
	private String userPhone;
	private String userAddress;
	private String userPassword;
	private String userEtc01; 
	private String userEtc02;
	private String userEtc03;
	private String userEtc04;
	private String userEtc05;
	
	private String snsId;
	private String snsType;
	private String snsName;
	private String snsProfile;
	private String snsConnectDate;
	
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public String getSnsId() {
		return snsId;
	}

	public void setSnsId(String snsId) {
		this.snsId = snsId;
	}

	public String getSnsType() {
		return snsType;
	}

	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}

	public String getSnsName() {
		return snsName;
	}

	public void setSnsName(String snsName) {
		this.snsName = snsName;
	}

	public String getSnsProfile() {
		return snsProfile;
	}

	public void setSnsProfile(String snsProfile) {
		this.snsProfile = snsProfile;
	}

	public String getSnsConnectDate() {
		return snsConnectDate;
	}

	public void setSnsConnectDate(String snsConnectDate) {
		this.snsConnectDate = snsConnectDate;
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
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEtc01() {
		return userEtc01;
	}
	public void setUserEtc01(String userEtc01) {
		this.userEtc01 = userEtc01;
	}
	public String getUserEtc02() {
		return userEtc02;
	}
	public void setUserEtc02(String userEtc02) {
		this.userEtc02 = userEtc02;
	}
	public String getUserEtc03() {
		return userEtc03;
	}
	public void setUserEtc03(String userEtc03) {
		this.userEtc03 = userEtc03;
	}
	public String getUserEtc04() {
		return userEtc04;
	}
	public void setUserEtc04(String userEtc04) {
		this.userEtc04 = userEtc04;
	}
	public String getUserEtc05() {
		return userEtc05;
	}
	public void setUserEtc05(String userEtc05) {
		this.userEtc05 = userEtc05;
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
	
}