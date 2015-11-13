package com.aglook.comapp.view;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Timestamp {
	private static SimpleDateFormat sf = null;
	
	

	/**
	 * 获取系统时间 ，格式为：yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		Date date = new Date();
		sf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sf.format(date);
	}

	/**
	 * 时间戳转换成日期yyyy-MM-dd
	 * 
	 * @param time
	 *            时间戳
	 * @return
	 */
	public static String getDateToString(String time) {
		long lcc = Long.valueOf(time);

		sf = new SimpleDateFormat("yyyy-MM-dd");

		String times = sf.format(new Date(lcc));
		return times;
	}
	
	/**
	 * 时间戳转换成日期yyyy.MM.dd
	 * 
	 * @param time
	 *            时间戳
	 * @return
	 */
	public static String getDateToDate(String time) {
		long lcc = Long.valueOf(time);

		sf = new SimpleDateFormat("yyyy.MM.dd");

		String times = sf.format(new Date(lcc));
		return times;
	}
	
	
	/**
	 * 时间戳转换成日期MM.dd
	 * 
	 * @param time
	 *            时间戳
	 * @return
	 */
	public static String getDateToMD(String time) {
		long lcc = Long.parseLong(time);

		sf = new SimpleDateFormat("MM.dd");

		String times = sf.format(new Date(lcc));
		return times;
	}
	/**
	 * 时间戳转换成日期MM-dd
	 * 
	 * @param time
	 *            时间戳
	 * @return
	 */
	public static String getDateToLine(String time) {
		long lcc = Long.parseLong(time);

		sf = new SimpleDateFormat("MM-dd");

		String times = sf.format(new Date(lcc));
		return times;
	}

	/**
	 * 时间戳转换成日期yyyy-MM-dd HH:mm
	 * 
	 * @param time
	 *            时间戳
	 * @return
	 */
	public static String getDateTo(String time) {
		long lcc = Long.valueOf(time);

		sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String times = sf.format(new Date(lcc));
		return times;
	}
	/**
	 * 时间戳转换成日期yyyyMMddHHmmss
	 * 
	 * @param time
	 *            时间戳
	 * @return
	 */
	public static String getServerDateTo(String time) {
		long lcc = Long.valueOf(time);
		
		sf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String times = sf.format(new Date(lcc));
		return times;
	}

	/**
	 * 时间戳转换成日期yyyy年MM月dd日
	 * 
	 * @param time
	 *            时间戳
	 * @return
	 */
	public static String getDate(String time) {
		long lcc = Long.valueOf(time);

		sf = new SimpleDateFormat("yyyy年MM月dd日");

		String times = sf.format(new Date(lcc));
		return times;
	}

	/**
	 * 将时间戳转换为星期
	 * 
	 * @param date
	 *            传入的日期
	 * @return
	 */
	public static String DateToWeek(String time) {
		sf = new SimpleDateFormat("yyyy-MM-dd");

		Long lcc = Long.valueOf(time);
		String times = sf.format(new Date(lcc));
		Date date = null;
		int mydate = 0;
		String week = null;

		try {
			date = sf.parse(times);
			Calendar cd = Calendar.getInstance();
			cd.setTime(date);
			mydate = cd.get(Calendar.DAY_OF_WEEK);
			// 获取指定日期转换成星期几

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (mydate == 1) {
			week = "星期日";
		} else if (mydate == 2) {
			week = "星期一";
		} else if (mydate == 3) {
			week = "星期二";
		} else if (mydate == 4) {
			week = "星期三";
		} else if (mydate == 5) {
			week = "星期四";
		} else if (mydate == 6) {
			week = "星期五";
		} else if (mydate == 7) {
			week = "星期六";
		}
		return week;

	}

	/**
	 * 掉此方法输入所要转换的时间输入例如（"2014年06月14日16时09分00秒"）返回时间戳
	 * 
	 * @param time
	 * @return
	 */
	public String data(String time) {
		sf = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
		Date date;
		String times = null;
		try {
			date = sf.parse(time);
			long l = date.getTime();
			String stf = String.valueOf(l);
			times = stf.substring(0, 10);
			Log.d("--444444---", times);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return times;
	}

	/**
	 * 掉此方法输入所要转换的时间输入例如（"2014年06月14日16时09分00秒"）返回时间戳
	 * 
	 * @param time
	 * @return
	 */
	public static Long dateToTime(String time) {
		sf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		Date date;
		String times = null;
		try {
			date = sf.parse(time);
			long l = date.getTime();
			String stf = String.valueOf(l);
			times = stf.substring(0, 10);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Long.parseLong(times);
	}

}
