package hello.user.itemmanagement.exception;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errCode;
	
	private String errField; 
	
	private String errMsg;
	
	public BusinessException(){
		
	}
	
	public BusinessException(String errCode, String errField, String errMsg) {
		super();
		this.errCode = errCode;
		this.errField = errField;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getErrField() {
		return errField;
	}

	public void setErrField(String errField) {
		this.errField = errField;
	}
}
