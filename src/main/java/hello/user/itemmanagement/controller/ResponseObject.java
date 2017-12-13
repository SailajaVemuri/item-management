package hello.user.itemmanagement.controller;

import java.util.ArrayList;
import java.util.List;


public class ResponseObject {
	private String resMsg;
	
	private String userId;
	
	private List<ValError> valErrors = new ArrayList<ValError>();
	
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	public List<ValError> getValErrors() {
		return valErrors;
	}
	public void setValErrors(List<ValError> valErrors) {
		this.valErrors = valErrors;
	}
	
	public void addValError(ValError valErr){
		valErrors.add(valErr);
	}
	
}
