package base.cn.web.facade.bean;

import java.util.ArrayList;
import java.util.List;

public class BaseResponse {

	public String success="true";
	public String returncode="0000";
	public String returnmessage="交易成功";
	public String sign = "";
	public List<Object> paramdata = new ArrayList<Object>();
	
	public List<Object> getParamdata() {
		return paramdata;
	}
	public void setParamdata(List<Object> paramdata) {
		this.paramdata = paramdata;
	}
	public String getReturncode() {
		return returncode;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}
	public String getReturnmessage() {
		return returnmessage;
	}
	public void setReturnmessage(String returnmessage) {
		this.returnmessage = returnmessage;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	
	
}
