package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalEverySaturday {

	public static String calEverySaturday(String begintime,String endtime) throws ParseException{
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
		Date startDate = df.parse(begintime);
		startCalendar.setTime(startDate);
		Date endDate = df.parse(endtime);
		endCalendar.setTime(endDate);
		
		String result = "";
		while (true) {
			startCalendar.add(Calendar.DAY_OF_MONTH, 1);
			if (startCalendar.getTimeInMillis() <= endCalendar.getTimeInMillis()) {// TODO
																					// 转数组或是集合，楼主看着写吧

				DateFormat format1 = new SimpleDateFormat("yyyy-M-d");
				Date bdate = format1.parse(df.format(startCalendar.getTime()));
				Calendar cal = Calendar.getInstance();
				cal.setTime(bdate);
				if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
//					System.out.print(df.format(startCalendar.getTime()) + "   it's Saturday");
					result += df.format(startCalendar.getTime()) + "#";
//					System.out.println();
				}
			} else {
				break;
			}
		}
	
		return result;
		
	}
}
