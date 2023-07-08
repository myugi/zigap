package util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

	public static String numberFormat(int num) {
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(num);
	}
	
	public static String getToday(String format) {
		SimpleDateFormat format1 = new SimpleDateFormat (format);
				
		Date time = new Date();
				
		String dateStr = format1.format(time);
				

		return dateStr;
	}
	
	public static String getDateFormat(String date) {
		return date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6);
	}
	
	
	public static void main(String[] args) {
		System.out.println(getToday("yyyyMM"));
		
		System.out.println("201903".substring(0,4) + "/" + "201903".substring(4));
	
		System.out.println(getDateFormat("20190302"));
	}

}
