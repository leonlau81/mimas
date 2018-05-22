
package com.vanseed.mimas.common.utils;

import java.text.ChoiceFormat;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public final class DateUtils {
    
    /**
     * MEDIUM_FORMAT.
     */
    //public static final DateFormat MEDIUM_FORMAT = DateFormat
    //        .getDateInstance(DateFormat.MEDIUM);

    /**
     * ISO8601-like pattern for date-time. It does not support timezone.
     * <tt>yyyy-MM-dd HH:mm:ss</tt>
     */
    public static final String ISO8601_DATETIME_NO_T_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * ISO8601-like pattern for date. <tt>yyyy-MM-dd</tt>
     */
    public static final String ISO8601_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * ISO8601-like pattern for time. <tt>HH:mm:ss</tt>
     */
    public static final String ISO8601_TIME_PATTERN = "HH:mm:ss";

    // code from Magesh moved from DefaultLogger and slightly modified
    private static final MessageFormat MINUTE_SECONDS = new MessageFormat(
            "{0}{1}");

    private static final double[] LIMITS = {0,1,2};

    private static final String[] MINUTES_PART = {"","1 minute ", "{0,number} minutes "};

    private static final String[] SECONDS_PART = {"0 seconds","1 second", "{1,number} seconds"};

    private static final ChoiceFormat MINUTES_FORMAT = new ChoiceFormat(LIMITS,
            MINUTES_PART);

    private static final ChoiceFormat SECONDS_FORMAT = new ChoiceFormat(LIMITS,
            SECONDS_PART);

   
	private static final int T60 = 60;

    private static final int T1000 = 1000;

	private DateUtils() {
	}

	/**
	 * newDateStartInstance.
     * @return Date
	 */
	public static java.util.Date newDateStartInstance() {

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * newDateEndInstance.
     * @return Date
	 */
	public static java.util.Date newDateEndInstance() {

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return cal.getTime();
	}
	
	public static java.util.Date getDateEndInstance(java.util.Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * newDateInstance.
	 * 
	 * @param year
	 *            int
	 * @param month
	 *            int
	 * @param date
	 *            int
     * @return Date          
	 */
	public static java.util.Date newDateInstance(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * newDateInstance.
	 * 
	 * @param year
	 *            int
	 * @param month
	 *            int
	 * @param date
	 *            int
	 * @param h
	 *            int
	 * @param m
	 *            int
	 * @param s
	 *            int
     * @return Date         
	 */
	public static java.util.Date newDateInstance(int year, int month, int date,
			int h, int m, int s) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date, h, m, s);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * parse.
	 * 
	 * @param datestr
	 *            String
	 * @return Date
	 */
	public static Date parse(String datestr) {
		try {
			DateFormat MEDIUM_FORMAT = DateFormat.getDateInstance(DateFormat.MEDIUM);
			return MEDIUM_FORMAT.parse(datestr);
		}
		catch(ParseException e) {
			throw new IllegalArgumentException("can't parse '" + datestr
					+ "' to a date ");
		}
	}

	/**
	 * format.
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String format(Date date) {
		DateFormat MEDIUM_FORMAT = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return MEDIUM_FORMAT.format(date);
	}

	/**
	 * isStartOfDay.
	 * 
	 * @param date
	 *            Date
	 * @return boolean
	 */
	public static boolean isStartOfDay(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(cal.get(Calendar.HOUR_OF_DAY) == 0 && cal.get(Calendar.MINUTE) == 0
				&& cal.get(Calendar.SECOND) == 0
				&& cal.get(Calendar.MILLISECOND) == 0) {
			return true;
		}

		return false;
	}

	/**
	 * isEndOfDay.
	 * 
	 * @param date
	 *            Date
	 * @return boolean
	 */
	public static boolean isEndOfDay(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(cal.get(Calendar.HOUR_OF_DAY) == 23
				&& cal.get(Calendar.MINUTE) == 59
				&& cal.get(Calendar.SECOND) == 59
				&& cal.get(Calendar.MILLISECOND) == 999) {
			return true;
		}
		return false;
	}

	/**
	 * getStartOfDate.
	 * 
	 * @param date
	 *            Date
	 * @return Date
	 */
	public static Date getStartOfDate(Date date) {
		if(date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * getEndOfDate.
	 * 
	 * @param date
	 *            Date
	 * @return Date
	 */
	public static Date getEndOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		if(date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * getEndOfDate.
	 * 
	 * @param date
	 *            Date
	 * @return Date
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfYear(Date date) {
		if(date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}
	
	/**
	 * �õ���ȷ���ֵ�ʱ��
	 * @param date
	 *            Date
	 * @return Date
	 */
	public static Date getDateAccurateToMinute(Date date) {
		if(date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}
	
	/**
	 * getOffsetOfDate.
	 * 
	 * @param date
	 *            Date
	 * @return Date
	 */
	public static Date getOffsetOfDate(Date date, Integer offset ) {
		if(date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, offset);  //设置偏移量，负数向前，正数向后

		return cal.getTime();
	}

	/**
	 * ת��java.util.Date�������ڵ�java.sql.Date��������.
	 * 
	 * @param oldD
	 *            Ҫת��������
	 * @return ת���������
	 */
	public static java.sql.Date convertUtilToSql(java.util.Date oldD) {
		java.sql.Date newD = null;
		if(oldD == null) {
			return null;
		}
		newD = new java.sql.Date(oldD.getTime());
		return newD;
	}

	/**
	 * ת��java.sql.Date�������ڵ�java.util.Date��������.
	 * 
	 * @param oldD
	 *            Ҫת��������
	 * @return ת���������
	 */
	public static java.util.Date convertSqlToUtil(java.sql.Date oldD) {
		java.util.Date newD = null;
		if(oldD == null) {
			return null;
		}
		newD = new java.sql.Date(oldD.getTime());
		return newD;
	}

	/**
	 * ת��java.util.Date�������ڵ�java.sql.Date��������.
	 * 
	 * @param oldD
	 *            Ҫת��������
	 * @return ת���������
	 */
	public static java.sql.Timestamp convertDateToTimestamp(java.util.Date oldD) {
		java.sql.Timestamp newD = null;
		if(oldD == null) {
			return null;
		}
		newD = new java.sql.Timestamp(oldD.getTime());
		return newD;
	}

	/**
	 * unjavadoced.
	 * 
	 * @author unknown
	 * @version 0.0
	 */
	static {
		MINUTE_SECONDS.setFormat(0, MINUTES_FORMAT);
		MINUTE_SECONDS.setFormat(1, SECONDS_FORMAT);
	}

	/**
	 * Format a date/time into a specific pattern.
	 * 
	 * @param date
	 *            the date to format expressed in milliseconds.
	 * @param pattern
	 *            the pattern to use to format the date.
	 * @return the formatted date.
	 */
	public static String format(long date, String pattern) {
		return format(new Date(date), pattern);
	}

	/**
	 * Format a date/time into a specific pattern.
	 * 
	 * @param date
	 *            the date to format expressed in milliseconds.
	 * @param pattern
	 *            the pattern to use to format the date.
	 * @return the formatted date.
	 */
	public static String format(Date date, String pattern) {
		DateFormat df = createDateFormat(pattern);
		return df.format(date);
	}

	/**
	 * Format an elapsed time into a plurialization correct string. It is
	 * limited only to report elapsed time in minutes and seconds and has the
	 * following behavior.
	 * <ul>
	 * <li>minutes are not displayed when 0. (ie: "45 seconds")</li>
	 * <li>seconds are always displayed in plural form (ie "0 seconds" or "10
	 * seconds") except for 1 (ie "1 second")</li>
	 * </ul>
	 * 
	 * @param millis
	 *            the elapsed time to report in milliseconds.
	 * @return the formatted text in minutes/seconds.
	 */
	public static String formatElapsedTime(long millis) {
		long seconds = millis / T1000;
		long minutes = seconds / T60;
		Object[] args = {new Long(minutes),new Long(seconds % T60)};
		return MINUTE_SECONDS.format(args);
	}

	/**
	 * return a lenient date format set to GMT time zone.
	 * 
	 * @param pattern
	 *            the pattern used for date/time formatting.
	 * @return the configured format for this pattern.
	 */
	private static DateFormat createDateFormat(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		//TimeZone gmt = TimeZone.getTimeZone("GMT");
		TimeZone gmt = TimeZone.getDefault();
		sdf.setTimeZone(gmt);
		sdf.setLenient(true);
		return sdf;
	}

	/**
	 * Calculate the phase of the moon for a given date.
	 * <p>
	 * Code heavily influenced by hacklib.c in <a
	 * href="http://www.nethack.org/">Nethack</a>
	 * </p>
	 * <p>
	 * The Algorithm:
	 * 
	 * <pre>
	 * 
	 *  
	 *   
	 *    moon period = 29.53058 days &tilde;= 30, year = 365.2422 days
	 *   
	 *    days moon phase advances on first day of year compared to preceding year
	 *     = 365.2422 - 12*29.53058 &tilde;= 11
	 *   
	 *    years in Metonic cycle (time until same phases fall on the same days of
	 *     the month) = 18.6 &tilde;= 19
	 *   
	 *    moon phase on first day of year (epact) &tilde;= (11*(year%19) + 18) % 30
	 *     (18 as initial condition for 1900)
	 *   
	 *    current phase in days = first day phase + days elapsed in year
	 *   
	 *    6 moons &tilde;= 177 days
	 *    177 &tilde;= 8 reported phases * 22
	 *    + 11/22 for rounding
	 *    
	 *   
	 *  
	 * </pre>
	 * @param cal cal
	 * @return The phase of the moon as a number between 0 and 7 with 0 meaning
	 *         new moon and 4 meaning full moon.
	 * @since 1.2, Ant 1.5
	 */
	public static int getPhaseOfMoon(Calendar cal) {
		int dayOfTheYear = cal.get(Calendar.DAY_OF_YEAR);
		int yearInMetonicCycle = ((cal.get(Calendar.YEAR) - 1900) % 19) + 1;
		int epact = (11 * yearInMetonicCycle + 18) % 30;
		if((epact == 25 && yearInMetonicCycle > 11) || epact == 24) {
			epact++;
		}
		return (((((dayOfTheYear + epact) * 6) + 11) % 177) / 22) & 7;
	}

	/**
	 * Parse a string as a datetime using the ISO8601_DATETIME format which is
	 * <code>yyyy-MM-dd HH:mm:ss</code>.
	 * 
	 * @param datestr
	 *            string to be parsed
	 * @return a java.util.Date object as parsed by the format.
	 */
	public static Date parseIso8601DateTimeNoT(String datestr) {
		try {
			return new SimpleDateFormat(ISO8601_DATETIME_NO_T_PATTERN)
					.parse(datestr);
		}
		catch(ParseException ex) {
			throw new IllegalArgumentException("ISO8601 date format is "
					+ ISO8601_DATE_PATTERN);
		}
	}

	/**
	 * Parse a string as a date using the ISO8601_DATE format which is
	 * <code>yyyy-MM-dd</code>.
	 * 
	 * @param datestr
	 *            string to be parsed
	 * @return a java.util.Date object as parsed by the format.
	 */
	public static Date parseIso8601Date(String datestr) {
		try {
			return new SimpleDateFormat(ISO8601_DATE_PATTERN).parse(datestr);
		}
		catch(ParseException ex) {
			throw new IllegalArgumentException("ISO8601 date format is "
					+ ISO8601_DATE_PATTERN);
		}
	}

	/**
	 * Parse a string as a date using the either the ISO8601_DATETIME or
	 * ISO8601_DATE formats.
	 * 
	 * @param datestr
	 *            string to be parsed
	 * @return a java.util.Date object as parsed by the formats.
	 */
	public static Date parseIso8601DateTimeOrDate(String datestr) {
		try {
			return parseIso8601DateTimeNoT(datestr);
		}
		catch(IllegalArgumentException px) {
			return parseIso8601Date(datestr);
		}
	}

}
