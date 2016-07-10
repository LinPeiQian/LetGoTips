package com.brianLin.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/** yyyy-MM-dd字符串 */
	public static final String DEFAULT_FORMAT_DATE = "yyyy-MM-dd";

	/** yyyy-MM-dd HH:mm:ss字符串 */
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

//	/**
//	 * 与当前日期计算时间差
//	 * @param time
//	 * @return
//	 */
//	public static String timeDiffByToday(String str){
//		long time = getCreateTimeLong(str);
//		long todayTime = new Date().getTime();
//		long diff = todayTime - time ;
//		long day = diff/(24*60*60*1000);
//		long hour = ( diff / (60*60*1000) - day * 24 );
//		long min = (( diff / ( 60 * 1000 )) - day * 24 * 60 - hour * 60 );
//		long s = ( diff / 1000-day*24*60*60-hour*60*60-min*60);
//		String result = "";
//		if( day > 0 ){
//			result += day +"天前"; 
//		}else {
//			if(hour > 0 ){
//				result += hour +"小时前";
//			}else{
//				if(min > 0 ){
//					result += min +"分钟前";
//				}else{
//					result += s +"秒前";
//				}
//			}
//		}
//		
//		return result;
//	}
	
	/**
	 * 与当前日期计算时间差
	 * @param time
	 * @return
	 */
	public static String timeDiffByToday(String timestamp){
		long time = Long.parseLong(timestamp);
		long todayTime = new Date().getTime();
		long diff = todayTime - time ;
		long day = diff/(24*60*60*1000);
		long hour = ( diff / (60*60*1000) - day * 24 );
		long min = (( diff / ( 60 * 1000 )) - day * 24 * 60 - hour * 60 );
		long s = ( diff / 1000-day*24*60*60-hour*60*60-min*60);
		String result = "";
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("mm:ss");
		if( day > 0 ){
			result += getTimeStr(time); 
		}else {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(time);
			if(hour > 0 ){
				result += getTimeStr(time); 
			}else{
				if(min > 0 ){
					result += min +"分钟前";
				}else{
					if(s == 0){
						result += "刚刚";
					}else{
						result += s +"秒前";
					}
				}
			}
		}
		return result;
	}
	
	public static String timeDiffByToday(int timeStamp){
		long between = timeStamp - getTodayTimestamp();
		int day = (int)(between/(24*3600));   
		if(day > 0){
			return day+"天后计划";
		}else{
			return day+"天前计划";
		}
	}
	
	
	
	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static long strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate.getTime();
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int day=c.get(Calendar.DAY_OF_WEEK);
		// day中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(long sdate) {
		// 再转换为时间
		Date date = new Date(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int day=c.get(Calendar.DAY_OF_WEEK);
		// day中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	public static String getWeekStr(String sdate) {
		String str = "";
		str = getWeek(sdate);
		if ("1".equals(str)) {
			str = "周日";
		} else if ("2".equals(str)) {
			str = "周一";
		} else if ("3".equals(str)) {
			str = "周二";
		} else if ("4".equals(str)) {
			str = "周三";
		} else if ("5".equals(str)) {
			str = "周四";
		} else if ("6".equals(str)) {
			str = "周五";
		} else if ("7".equals(str)) {
			str = "周六";
		}
		return str;
	}

	public static String getWeekStr(long sdate) {
		String str = "";
		str = getWeek(sdate);
		if ("1".equals(str)) {
			str = "周日";
		} else if ("2".equals(str)) {
			str = "周一";
		} else if ("3".equals(str)) {
			str = "周二";
		} else if ("4".equals(str)) {
			str = "周三";
		} else if ("5".equals(str)) {
			str = "周四";
		} else if ("6".equals(str)) {
			str = "周五";
		} else if ("7".equals(str)) {
			str = "周六";
		}
		return str;
	}

	/**
	 * 后台返回的数据是从周一开始计算的
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getShortWeekStr(String sdate) {
		if ("7".equals(sdate)) {
			sdate = "日";
		} else if ("1".equals(sdate)) {
			sdate = "一";
		} else if ("2".equals(sdate)) {
			sdate = "二";
		} else if ("3".equals(sdate)) {
			sdate = "三";
		} else if ("4".equals(sdate)) {
			sdate = "四";
		} else if ("5".equals(sdate)) {
			sdate = "五";
		} else if ("6".equals(sdate)) {
			sdate = "六";
		}
		return sdate;
	}

	/**
	 * 将时间装换成只显示月份
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getMonth(String sdate) {
		// 再转换为时间
		Date date = strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return new SimpleDateFormat("MM月dd日").format(c.getTime());
	}

	/**
	 * 将时间装换成只显示月份
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getMonth(long sdate) {
		// 再转换为时间
		Date date = new Date(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return new SimpleDateFormat("MM月dd日").format(c.getTime());
	}
	
	public static String getTimeStr(long timeStamp){
		Date date = new Date(timeStamp);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(c.getTime());
	}
	
	public static String getTimeStr(String timeStamp){
		long time = Long.parseLong(timeStamp);
		return getTimeStr(time);
	}
	
	
	
	/**
	 * 根据时间戳 返回运动计划的日期
	 * @param timeStamp
	 * @return
	 */
	public static String getPlanTitle(int timeStamp){
		if(timeStamp < 0){
			return "";
		}
		if(timeStamp == getTodayTimestamp()){
			return "今日计划";
		}else if(timeStamp == getDaysAfterTimestamp(-1)){
			return "昨天计划";
		}else if(timeStamp == getDaysAfterTimestamp(-2)){
			return "前天计划";
		}else if(timeStamp == getDaysAfterTimestamp(-3)){
			return "大前天计划";
		}else if(timeStamp == getDaysAfterTimestamp(1)){
			return "明日计划";
		}else if(timeStamp == getDaysAfterTimestamp(2)){
			return "后天计划";
		}else if(timeStamp == getDaysAfterTimestamp(3)){
			return "大后天计划";
		}else{
			return timeDiffByToday(timeStamp);
		}
	}

	public static String[] getWeekDayArray(String sdate) {
		if (sdate == null)
			return null;
		return sdate.split(",");
	}
	/**
	 * 
	 * @param time
	 * @return  yyyy-MM-dd HH:mm:ss
	 */
	public static String long2Time(long time) {

		// 再转换为时间
		Date date = new Date(time);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
	}
	
	/**
	 * 
	 * @param time
	 * @return  yyyy-MM-dd
	 */
	public static String long2Date(long time) {

		// 再转换为时间
		Date date = new Date(time);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	public static String long2DateByAchievement(long time){
		// 再转换为时间
		Date date = new Date(time);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return new SimpleDateFormat("yyyy年MM月dd日").format(c.getTime());
	}
	
	/**
	 * 将UNIX时间戳解析成通话记录时间，当天显示小时:分钟，昨天显示昨天，其他显示月:日
	 * @param time UNIX时间戳
	 * @return 通话记录时间
	 */
	public static String parseCallLogTime(long time) {
		Calendar c = Calendar.getInstance();
		
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		c.add(Calendar.DATE,-1);
		int yesterday = c.get(Calendar.DAY_OF_MONTH);
		
		Date date = new Date(time);
		c.setTime(date);
		
		if (c.get(Calendar.YEAR) == year && c.get(Calendar.MONTH) == month) {
			if (c.get(Calendar.DAY_OF_MONTH) == day) {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				return sdf.format(c.getTime());
			} else if (c.get(Calendar.DAY_OF_MONTH) == yesterday) {
				return "昨天";
			}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
		return sdf.format(c.getTime());
	}
	
	public static String getActivityTime(String timeStr){
		long time=getCreateTimeLong(timeStr);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(time);
	}
	
	public static long getCreateTimeLong(String timeStr){
		if(timeStr!=null){
			String time=timeStr.replace("T", " ");
			return strToDateLong(time);
		}
		return 0l;
	}
	
	public static String getLiveTime(String timeStr){
		long time=getCreateTimeLong(timeStr);
		long disTime= System.currentTimeMillis()-time;
		long nd = 1000*24*60*60;//一天的毫秒数
		long nh = 1000*60*60;//一小时的毫秒数
		long day = disTime/nd;//计算差多少天
		if(day>0){
			return day+"天前";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(time);
	}
	
    /**
     * 判断2个时间相差多少天、多少小时、多少分<br>
     * <br>
     * @param long 获得两个时间的毫秒时间差异<br>
     * @return String 时间相差<br>
     * @Exception 发生异常<br>
     */
	public static String dateDiff(long diff) {
	    String result = "";
        long nd = 1000*24*60*60;//一天的毫秒数
        long nh = 1000*60*60;//一小时的毫秒数
        long nm = 1000*60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数
        try {
            long day = diff/nd;//计算差多少天
            long hour = diff%nd/nh;//计算差多少小时
            long min = diff%nd%nh/nm;//计算差多少分钟
            long sec = diff%nd%nh%nm/ns;//计算差多少秒
            //输出结果
            if(day != 0){
                result += day+"天";
            }
            if(hour != 0){
                result += hour+"小时";
            }
            if(min != 0){
                result += min+"分钟";
            }
            if(sec != 0){
                result += sec+"秒";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}
	
    /**
     * 判断2个时间相差多少天、多少小时、多少分<br>
     * <br>
     * @param long 获得两个时间的毫秒时间差异<br>
     * @return String 时间相差<br>
     * @Exception 发生异常<br>
     */
    public static String dateDiffForHtml(long diff) {
        String result = "";
        long nd = 1000*24*60*60;//一天的毫秒数
        long nh = 1000*60*60;//一小时的毫秒数
        long nm = 1000*60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数
        try {
            long day = diff/nd;//计算差多少天
            long hour = diff%nd/nh;//计算差多少小时
            long min = diff%nd%nh/nm;//计算差多少分钟
            long sec = diff%nd%nh%nm/ns;//计算差多少秒
            //输出结果
            if(day != 0){
                result += ("<font color=#c95330>" + String.valueOf(day) + "</font>"
                        + "<font color=#656870>天</font>");
            }
            if(hour != 0){
                result += ("<font color=#c95330>" + String.valueOf(hour) + "</font>"
                        + "<font color=#656870>小时</font>");
            }
            if(min != 0){
                result += ("<font color=#c95330>" + String.valueOf(min) + "</font>"
                        + "<font color=#656870>分钟</font>");
            }
            if(sec != 0){
                result += ("<font color=#c95330>" + String.valueOf(sec) + "</font>"
                        + "<font color=#656870>秒</font>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static String[] getTime(long pre) {
		String time = "";
		String[] times = new String[2];
		try {
			String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
					"星期六" };
			String[] day_per_names = { "今天", "昨天", "前天" };
			// -2:前天 -1：昨天 0：今天 1：明天 2：后天， out：显示日期
			long now = System.currentTimeMillis();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			SimpleDateFormat ft_final = new SimpleDateFormat("yyyy年MM月dd日");
			String pre_d = ft.format(new Date(pre));
			String now_d = ft.format(new Date(now));
			Date d1 = ft.parse(pre_d);
			Date d2 = ft.parse(now_d);
			long range = d2.getTime() - d1.getTime();
			long time_one = 24 * 60 * 60 * 1000;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d1);
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			String week = dayNames[dayOfWeek - 1];
			int dis = (int) (range / time_one);
			if (dis < 3) {
				time = day_per_names[dis] + "#" + week;
			} else {
				time = ft_final.format(new Date(pre)) + "#" + week;
			}
			times[0] = ft_final.format(new Date(pre));
			times[1] = time;
			return times;
		} catch (ParseException e) {
			e.printStackTrace();
			return times;
		}
	}
    
    
    /**
     * 根据时间戳转换成string
     * @param timestamp
     * @return
     */
    public static String getStrTime(long timestamp){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");
    	return sdf.format(new Date(timestamp * 1000L));
    }
    
    /**
     * 获得今天0点0分0秒的时间戳
     */
    public static int getTodayTimestamp(){
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    	return (int)(cal.getTimeInMillis()/1000L); 
    }
    
    /**
     * 获得n天后的0点0分0秒的时间戳 \n
     * @return
     */
    public static int getDaysAfterTimestamp(int days){
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.HOUR_OF_DAY, 24 * days);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    	return (int)(cal.getTimeInMillis() / 1000L); 
    	
    }

	public static long Date2TimeStamp(String time) {
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
		try{
			Date date = format.parse(time);
			return date.getTime();
		}catch (Exception e){

		}
		return 0;
	}

	public static String getDateAndTime(long timeSecond) {
		Date date = new Date(timeSecond * 1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateStr = sdf.format(date);
		return dateStr;
	}

	/**
	 * 获得几天之前或者几天之后的日期
	 * @param diff 差值：正的往后推，负的往前推
	 * @return
	 */
	public static String getOtherDay(int diff) {
		Calendar mCalendar = Calendar.getInstance();
		mCalendar.add(Calendar.DATE, diff);
		return getDateFormat(mCalendar.getTime());
	}

	/**
	 * 将date转成yyyy-MM-dd字符串<br>
	 * @param date Date对象
	 * @return yyyy-MM-dd
	 */
	public static String getDateFormat(Date date) {
		return dateSimpleFormat(date, defaultDateFormat.get());
	}

	/**
	 * 将date转成字符串
	 * @param date Date
	 * @param format SimpleDateFormat
	 * <br>
	 * 注： SimpleDateFormat为空时，采用默认的yyyy-MM-dd HH:mm:ss格式
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String dateSimpleFormat(Date date, SimpleDateFormat format) {
		if (format == null)
			format = defaultDateTimeFormat.get();
		return (date == null ? "" : format.format(date));
	}

	/** yyyy-MM-dd格式 */
	public static final ThreadLocal<SimpleDateFormat> defaultDateFormat = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DEFAULT_FORMAT_DATE);
		}

	};

	/** yyyy-MM-dd HH:mm:ss格式 */
	public static final ThreadLocal<SimpleDateFormat> defaultDateTimeFormat = new ThreadLocal<SimpleDateFormat>() {

		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
		}

	};

}
