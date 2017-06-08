package kr.dohun.mail;


public class mailVO {

	private String mailId;
	private String mailPwd;
	private String mailTo;
	private String mailFrom;
	
	private mailVO mailVo;
	
	/**
	 * 생성자
	 */
	public mailVO(){
		this.mailVo = new mailVO();
	}
	
	/**
	 * TO,FROM메일주소 설정하기 
	 * @param String mailTo
	 * @param String mailFrom
	 */
	public void setMailAress(String mailTo, String mailFrom){
		this.mailTo = mailTo;
		this.mailFrom = mailFrom;
	}
	
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getMailPwd() {
		return mailPwd;
	}
	public void setMailPwd(String mailPwd) {
		this.mailPwd = mailPwd;
	}
	public String getMailTo() {
		return mailTo;
	}
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	public String getMailFrom() {
		return mailFrom;
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

}
