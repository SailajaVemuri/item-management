package hello.user.itemmanagement.controller;

public class ValError {
	private String errCode;
	private String errField;
	private String errMsg;
	
	public ValError(){
		
	}
	
	public ValError(String errCode, String errField, String errMsg){
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
	public String getErrField() {
		return errField;
	}
	public void setErrField(String errField) {
		this.errField = errField;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}
