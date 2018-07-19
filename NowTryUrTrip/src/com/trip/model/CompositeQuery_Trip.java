/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package com.trip.model;

import java.util.*;

public class CompositeQuery_Trip {

	public static String get_aCondition_For_Oracle(String key, String value) {

		String aCondition = null;

		if ("trip_keyword".equals(key))
			aCondition = " (tripName" + " like '%" + value + "%' or tripDesc" + " like '%" + value + "%') ";
		else if ("from".equals(key)||"to".equals(key))                          // 用於Oracle的date
			aCondition = " to_date('" + value + "','YYYY-MM-DD HH24:MI') ";
		else if ("trip_type".equals(key))
			aCondition = " tripType= " + value;
		else if ("sorting".equals(key)&&value.equals("byRate"))
			aCondition = " tripRate ";
		else if ("sorting".equals(key)&&value.equals("byNo"))
			aCondition = " tripNo ";
		else if ("sorting".equals(key)&&value.equals("byDate"))
			aCondition = " departTime ";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		String[] sqlArr = new String[6];
		int typeCount=1;
		String type = null;
		for (String key : keys) {
			for(int k =0;k<map.get(key).length;k++){
				String value = map.get(key)[k];
				System.out.println(value);
				if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
					String aCondition = get_aCondition_For_Oracle(key, value.trim());
					System.out.println(value);
					
					
					if(key.equals("trip_keyword")){
						sqlArr[0] = aCondition;
					}else if(key.equals("rating")){
						sqlArr[1] = " tripRate > "+value;
					}else if(key.equals("from")){
						sqlArr[2] = aCondition;
					}else if(key.equals("to")){
						sqlArr[3] = aCondition;
					}else if(key.equals("trip_type")&&(value.equals("0")||value.equals("1")||value.equals("2"))){
						if(typeCount==1){
							type =  aCondition;
							typeCount++;
						}else{
							type = type + " or " + aCondition;
							typeCount++;
						}
						sqlArr[4] = type;
					}else if(key.equals("sorting")){
						sqlArr[5] = aCondition;
					}
						
				}
			}
		}
		
		whereCondition = whereCondition.append(" where tripIspublic > 0 ");
		for(int i=0;i<6;i++){
			if(sqlArr[i]!=null){
				
				if(i==0){
					whereCondition = whereCondition.append(" and " + sqlArr[i]);
				}else if(i==1){
					whereCondition = whereCondition.append(" and " + sqlArr[i]);
				}else if(i==2){
					whereCondition = whereCondition.append(" and (departTime between " + sqlArr[i]);
				}else if(i==3){
					whereCondition = whereCondition.append(" and " + sqlArr[i] + " ) ");
				}else if(i==4){
					whereCondition = whereCondition.append(" and (" + sqlArr[i] + ")");
				}else if(i==5){
					whereCondition = whereCondition.append(" order by " + sqlArr[i]);
				}
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("trip_keyword", new String[] { "拉拉山" });
		map.put("rating", new String[] { "4" });
		map.put("from", new String[] { "2017-01-01" });
		map.put("to", new String[] { "2017-01-30" });
		map.put("trip_type", new String[] { "0","1" });
		map.put("sorting", new String[] { "byRate" });
		map.put("action", new String[] { "" });

		String finalSQL = "select * from trip "
				          + CompositeQuery_Trip.get_WhereCondition(map);
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
