package com.youxianji.util;


public class CalculateDistance {

	    
	    public static double getDistance(String long1, String lat1, String long2,  
	    		String lat2) {  
	        double a, b, R;  
	        R = 6378137; // 地球半径  
	        Double lat1Inner = Double.valueOf(lat1) * Math.PI / 180.0;  
	        Double lat2Inner = Double.valueOf(lat2) * Math.PI / 180.0;  
	        a = lat1Inner - lat2Inner;  
	        b = (Double.valueOf(long1) - Double.valueOf(long2)) * Math.PI / 180.0;  
	        double d;  
	        double sa2, sb2;  
	        sa2 = Math.sin(a / 2.0);  
	        sb2 = Math.sin(b / 2.0);  
	        d = 2  
	                * R  
	                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(Double.valueOf(lat1))  
	                        * Math.cos(Double.valueOf(lat2)) * sb2 * sb2));  
	        return d/1000;  
	    }  
	    
	     
	 /*   *//**
	     * 获取当前用户一定距离以内的经纬度值
	     * 单位米 return minLat
	     * 最小经度 minLng
	     * 最小纬度 maxLat
	     * 最大经度 maxLng
	     * 最大纬度 minLat
	     *//*
	    public static Map getAround(String latStr, String lngStr, String raidus) {
	        Map map = new HashMap();
	         
	        Double latitude = Double.parseDouble(latStr);// 传值给经度
	        Double longitude = Double.parseDouble(lngStr);// 传值给纬度
	 
	        Double degree = (24901 * 1609) / 360.0; // 获取每度
	        double raidusMile = Double.parseDouble(raidus);
	         
	        Double mpdLng = Double.parseDouble((degree * Math.cos(latitude * (Math.PI / 180))+"").replace("-", ""));
	        Double dpmLng = 1 / mpdLng;
	        Double radiusLng = dpmLng * raidusMile;
	        //获取最小经度
	        Double minLat = longitude - radiusLng;
	        // 获取最大经度
	        Double maxLat = longitude + radiusLng;
	         
	        Double dpmLat = 1 / degree;
	        Double radiusLat = dpmLat * raidusMile;
	        // 获取最小纬度
	        Double minLng = latitude - radiusLat;
	        // 获取最大纬度
	        Double maxLng = latitude + radiusLat;
	         
	        map.put("minLat", minLat+"");
	        map.put("maxLat", maxLat+"");
	        map.put("minLng", minLng+"");
	        map.put("maxLng", maxLng+"");
	         
	        return map;
	    }*/
	     
	    public static void main(String[] args) {
	        //济南国际会展中心经纬度：117.11811  36.68484
	        //趵突泉：117.00999000000002  36.66123
	        System.out.println(getDistance("116.3722","39.94883","116.372022","39.818833"));
	        System.out.println(getDistance("116.3722","39.94883","116.472961","39.935057"));
	        //System.out.println(getAround("117.11811", "36.68484", "13000"));
	        //117.01028712333508(Double), 117.22593287666493(Double),
	        //36.44829619896034(Double), 36.92138380103966(Double)
	         
	    }
}
