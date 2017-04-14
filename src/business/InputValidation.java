package business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputValidation {
	public static Long validatePNC(String pnc){
		Long val = null;
		if(pnc.matches("\\d*")&& pnc.length() > 2)
			val = Long.parseLong(pnc);
		
		return val;
	}
	
	public static Integer validateNumber(String nr){
		Integer value = null;
		if(nr.matches("\\d*")&& nr.length() > 0)
		{
			value = Integer.parseInt(nr);
		}
		return value;
	}
	
	public static boolean validateDate(String date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
	   
	    formatter.setLenient(false);
	    Date parsedDate = null;
	    try {
	        parsedDate = formatter.parse(date);
	        return true;
	    } catch (ParseException e) {
	    }
	    return false;
	}
}
