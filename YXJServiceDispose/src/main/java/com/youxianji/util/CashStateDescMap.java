package com.youxianji.util;

import java.util.HashMap;
import java.util.Map;

public class CashStateDescMap {

	
	//批次提现记录表：状态 0.初始 1.提交成功，待审核 2.冻结或回盘失败 3.审核成功已报盘 4.回盘成功 
	public static Map<String,String> upperStateMap =  new HashMap<>();
	static{
		upperStateMap.put("0", "初始");
		upperStateMap.put("1", "提交成功，待审核");
		upperStateMap.put("2", "冻结或回盘失败");
		upperStateMap.put("3", "审核成功已报盘");
		upperStateMap.put("4", "回盘成功");
		
	}
	//提现明细表：状态 0.初始 1.审核中 2.提现成功
    public static Map<String,String> detailStateMap =  new HashMap<>();
	static{
		detailStateMap.put("0", "初始");
		detailStateMap.put("1", "审核中");
		detailStateMap.put("2", "提现成功");

	}
	
	//报盘订单表：状态 1.报盘成功 2.回盘成功
    public static Map<String,String> offerStateMap =  new HashMap<>();
	static{
		offerStateMap.put("1", "报盘成功");
		offerStateMap.put("2", "回盘成功");
	}
	
	//1.待审核 2.报盘成功 3.回盘成功 4.回盘失败（提现记录表修改为2）
    public static Map<String,String> offerUpperRsStateMap =  new HashMap<>();
	static{
		offerUpperRsStateMap.put("1", "待审核");
		offerUpperRsStateMap.put("2", "报盘成功");
		offerUpperRsStateMap.put("3", "回盘成功");
		offerUpperRsStateMap.put("4", "回盘失败");
	}


}
