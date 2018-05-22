package com.vanseed.mimas.common.utils;

import java.math.BigDecimal;
import java.util.Date;

public class ParamUtils {

	public static String convertString(Object o){
		String s = o==null?"":o.toString().trim();
		if("".equals(s)){
			return null;
		}
		return s;
	}
	
	public static Integer convertInteger(Object o){
		if(o==null){
			return -1;
		}
		if(o instanceof String){
			if(((String)o).length()>0) return Integer.valueOf((String)o);
			else return -1;
		}
		return ((Number)o).intValue();
	}
	
	public static Long convertLong(Object o){
		if(o==null){
			return -1l;
		}
		if(o instanceof String){
			if(((String)o).length()>0) return Long.valueOf((String)o);
			else return -1l;
		}
		return ((Number)o).longValue();
	}
	
	public static Double convertDouble(Object o){
		if(o==null){
			return 0.0;
		}
		if(o instanceof String){
			if(((String)o).length()>0) return Double.valueOf((String)o);
			else return 0.0;
		}
		return ((Number)o).doubleValue();
	}
	
	public static BigDecimal convertBigDecimal(Object o){
		if(o==null){
			return new BigDecimal(0.0);
		}
		if(o instanceof String){
			if(((String)o).length()>0) 
				return new BigDecimal((String)o);
			
		}
			
		return new BigDecimal(0.0);
	}
	
	public static Float converyFloat(Object o){
		if(o==null){
			return 0.0f;
		}
		if(o instanceof String){
			if(((String)o).length()>0) return Float.valueOf((String)o);
			else return 0.0f;
		}
		return ((Number)o).floatValue();
	}
	
	public static Date converyDate(Object o){
		if(o==null || !(o instanceof String) ){
			return null;
		}
		return DateUtils.parse((String)o);
	}
}
