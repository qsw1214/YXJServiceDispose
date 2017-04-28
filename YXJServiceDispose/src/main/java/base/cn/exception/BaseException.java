package base.cn.exception;

import com.youxianji.web.util.ErrorEnum;

public class BaseException extends RuntimeException {

	private ErrorEnum error = null;
	
	public BaseException() {
	}
	
	public BaseException(ErrorEnum error) {
		this.error = error;
	}
	
	public BaseException(String message) {
		super(message);
	}

	public ErrorEnum getError() {
		return error;
	}

	public void setError(ErrorEnum error) {
		this.error = error;
	}
}
