package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LunarToSolar {

	public static String LunarToSolar(String lunarString){
		
		String[] lunarMonth = {"正月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","腊月"};
		String[] lunarDay = {"初一","初二","初三","初四","初五","初六","初七","初八","初九","初十","十一","十二","十三",
			    "十四","十五","十六", "十七", "十八","十九","廿十","廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","卅十"};
		
		//传过来的参数进行切割 第一个参数是年份，第二个是月份，第三个是日子
		String[] lunarStringSplitBySymbol = lunarString.split("#");
		int month = 0,day = 0,year = Integer.valueOf(lunarStringSplitBySymbol[0]);
		
		for(int i = 0; i < lunarMonth.length;i ++){
			if(lunarMonth[i].equals(lunarStringSplitBySymbol[1])){
				month = i + 1;
				break;
			}
		}
		int max_lunarDay = LunarCalendar.daysInMonth(year, month);
		
		for(int i = 0; i < max_lunarDay;i ++){
			if(lunarDay[i].equals(lunarStringSplitBySymbol[2])){
				day = i + 1;
				break;
			}
		}
		if(day == 0){
			day = max_lunarDay;
		}
		
		int leapMonth = LunarCalendar.leapMonth(year);
		boolean leap = false;
		if(month == leapMonth){
			leap = true;
		}
		
		int[] a = LunarCalendar.lunarToSolar(year, month, day, leap);
		String solarString = "";
		for (int x : a) {
			solarString += String.valueOf(x) + "-";
		}
		solarString = solarString.substring(0, solarString.length() - 1);
		
		String dateStr = solarString; 
		String[ ]  dateDivide = dateStr.split("-"); 
		if (dateDivide.length == 3) {
			int year1 = Integer.parseInt(dateDivide[0].trim());// 去掉空格
			int month1 = Integer.parseInt(dateDivide[1].trim());
			int day1 = Integer.parseInt(dateDivide[2].trim());
			Calendar c = Calendar.getInstance();// 获取一个日历实例
			c.set(year1, month1 - 1, day1);// 设定日历的日期
			System.out.println(c.getTime());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			simpleDateFormat.format(c.getTime());
			return simpleDateFormat.format(c.getTime());
		}
		
		return null;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String lunarString = "2016#五月#初一";
		String solarString = LunarToSolar.LunarToSolar(lunarString);
		System.out.println("农历 ：   "  + lunarString.replace('#', ' '));
		System.out.println("公历 ：   "  + solarString);
	}

}
