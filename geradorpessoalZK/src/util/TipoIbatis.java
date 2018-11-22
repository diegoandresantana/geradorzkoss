package util;

import java.lang.reflect.Field;



public class TipoIbatis {

	public static String field(Field f){
		if(f.getType().getSimpleName().equals("BigDecimal") || f.getType().getSimpleName().equals("Long") 
				|| f.getType().getSimpleName().equals("Integer")|| 
				f.getType().getSimpleName().equals("Double") ||
				f.getType().getSimpleName().equals("Float") ){
			return "NUMBER";
		}else if(f.getType().getSimpleName().equals("String")){
			return "VARCHAR";
		}
		else if(f.getType().getSimpleName().equals("Date")){
					return "TIMESTAMP";
		}
		return "";
	}
}
