package kr.dohun.error;

public class CustomGenericException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private String errCode;
	private String errMsg;

	//getter and setter methods
	public CustomGenericException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
}
