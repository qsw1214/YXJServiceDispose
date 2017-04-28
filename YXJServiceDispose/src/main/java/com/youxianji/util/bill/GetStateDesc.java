package com.youxianji.util.bill;

public class GetStateDesc {
	public static ReturnMsg setRtrunBean(String returnStr){
		ReturnMsg returnMsg = new ReturnMsg();
		
		if(returnStr!=null && !"".equals(returnStr)){
			String[] temp = returnStr.split("&");
			if(temp.length>1){
				String paramCodeStr = temp[0].split("=")[1].trim();
				returnMsg.setReturnCode(paramCodeStr);
				String paramMessageStr = temp[1].split("=")[1].trim();
				returnMsg.setMessage(paramMessageStr);
			}else{
				String paramCodeStr = temp[0];
				returnMsg.setReturnCode(paramCodeStr);
				returnMsg.setMessage("");
			}
		}
		
		return returnMsg;
	}
}
