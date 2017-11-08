/**
 * Copyright (c) 2010 Saltware, Inc.
 * 
 * http://www.saltware.co.kr
 * 
 * Kolon Science Valley Bldg 2th. 901, Guro-dong 811, Guro-gu,
 * Seoul, 152-878, South Korea.
 * All Rights Reserved.
 * 
 * This software is the Java based Enterprise Portal of Saltware, Inc.
 * Making any change or distributing this without permission from us is out of law.
 */
package com.saltware.enface.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * 날짜와 관련된 메서드를 정의한 공통 Util Class.
 * 
 * @author <${developer}> (${email})
 * @author Last changed by: ${developer}
 * @version 3.2.2
 * @since 1.0-SNAPSHOT
 */
public final class DateUtil {
    /**
     * 일요일
     */
    public final static int SUNDAY = 1;
    
    /**
     * 월요일
     */
    public final static int MONDAY = 2;
    
    /**
     * 화요일
     */
    public final static int TUESDAY = 3;
    
    /**
     * 수요일
     */
    public final static int WEDNESDAY = 4;
    
    /**
     * 목요일
     */
    public final static int THURSDAY = 5;
    
    /**
     * 금요일
     */
    public final static int FRIDAY = 6;

    /**
     * 토요일
     */
    public final static int SATURDAY = 7;

	private static String timeSeparator = ":";
	private static String dateSeparator = ".";
	
	/**
	 * yyyyMMdd 혹은 yyyyMMddHHmmss 형식의 날짜(시간) 문자열을 입력 받아 시간을 제외한
	 * 날짜만 '.'을 구분자로 삽입한 문자열을 반환한다.
	 * 
	 * @param date  yyyyMMdd 혹은 yyyyMMddHHmmss 형식의 날짜(시간) 문자열
	 * @return 변환된 날짜 문자열
	 */
	public static String convert(String date) {
		return convert(date, false);
	}
	
	/**
	 * yyyyMMdd 혹은 yyyyMMddHHmmss 형식의 날짜(시간) 문자열을 입력 받아 날짜는 
	 * '.', 시간은 ':'을 구분자로 삽입한 문자열을 반환한다.
	 * 
	 * @param date  yyyyMMdd 혹은 yyyyMMddHHmmss 형식의 날짜(시간) 문자열
	 * @param IS_TIME  시간이 포함된 문자열일 경우 시간을 제외할 지의 여부
	 * @return 변환된 날짜 문자열
	 */
	public static String convert(String date, boolean IS_TIME) {
		String result = null;

		if(date.length() == 8) {
			result = date.substring(0,4) + dateSeparator + date.substring(4,6) + dateSeparator + date.substring(6);				
		} else if(date.length() == 14) {
			if(IS_TIME) {
				result = date.substring(0,4) + dateSeparator + date.substring(4,6) + dateSeparator + date.substring(6,8)
					+ " " + date.substring(8,10) + timeSeparator + date.substring(10,12) + timeSeparator + date.substring(12);
			} else {
				result = date.substring(0,4) + dateSeparator + date.substring(4,6) + dateSeparator + date.substring(6,8);					
			}
		} 

		return result;		
	}

    /**
	 * 현재시간을 long형(milliseconds)으로 반환한다.
	 * 
	 * @return long
	 */
    public static long getNow() {
        return new Date().getTime();
    }

    /**
     * long형의 날짜(시간)을 입력받아 둘 사이의 차이를 구한다.
     * 
     * @param startTime 시작 시간
     * @param endTime 종료 시간
     * @return long
     */
    public static long getInterval(long startTime, long endTime) {
        return endTime - startTime;
    }

    /**
    * 주어진 날짜를 yyyyMMddHHmmss 형식으로 돌려줌 <BR>
    *
    * @param date Date 객체
    * @return yyyyMMddHHmmss 형식의 14자리 날짜
    */

    public static String getDate(Date date) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpledateformat.format(date);
    }

    /**
    * 주어진 날짜를 yyyyMMdd 형식으로 돌려줌 <BR>
    *
    * @param date Date 객체
    * @return yyyyMMdd 형식의 8자리 날짜
    */

    public static String getShortDate(Date date) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
        return simpledateformat.format(date);
    }

    /**
     * yyyyMMdd 형식의 날짜문자열을 원하는 캐릭터(ch)로 쪼개 돌려준다<br/>
    * <pre>
    * ex) 20030405, ch(.) -> 2003.04.05
    * ex) 200304, ch(.) -> 2003.04
    * ex) 20040101,ch(/) --> 2004/01/01 로 리턴
    * </pre>
    * 
    * @param date yyyyMMdd 형식의 날짜문자열
    * @param ch 구분자
    * @return 변환된 문자열
     */

    public static String formatDate(String date, String ch) {
    	String str = StringUtil.isNullTrim(date);
    	
    	if ( str.length() == 0 )
            return "";
        
        String yyyy = "";
        String mm = "";
        String dd = "";

        if (str.length() == 8) {
            yyyy = str.substring(0, 4);
            if (yyyy.equals("0000"))
                return "";

            mm = str.substring(4, 6);
            if (mm.equals("00"))
                return yyyy;

            dd = str.substring(6, 8);
            if (dd.equals("00"))
                return yyyy + ch + mm;

            return yyyy + ch + mm + ch + dd;
        } else if (str.length() == 6) {
            yyyy = str.substring(0, 4);
            if (yyyy.equals("0000"))
                return "";

            mm = str.substring(4, 6);
            if (mm.equals("00"))
                return yyyy;

            return yyyy + ch + mm;
        } else if (str.length() == 4) {
            yyyy = str.substring(0, 4);
            if (yyyy.equals("0000"))
                return "";
            else
                return yyyy;
        } else
            return "";
    }

    /**
    * yyyyMMdd 형식의 날짜문자열을 KR Format으로 돌려준다 <br>
    * <pre>
    *     ex) 20030405 -> 2003년 4월 5일
    * </pre>
    *
    * @param date yyyyMMdd 형식의 날짜문자열
    * @return 변환된 문자열
    */

    public static String formatDateKR(String date) {
    	
        String str = StringUtil.isNullTrim(date);
        
        if (str.length() == 0 || str.length() != 8)
            return "";
        
        String yyyy = "";
        String mm = "";
        String dd = "";

        yyyy = str.substring(0, 4);
        mm = str.substring(4, 6);
        dd = str.substring(6);

        mm = mm.startsWith("0") ? (" " + mm.substring(1)) : mm;
        dd = dd.startsWith("0") ? (" " + dd.substring(1)) : dd;

        return yyyy + "Year " + mm + "Month " + dd + "Day ";
    }

    /**
     * time(hhmmss) 문자열을 원하는 캐릭터(ch)로 쪼개 돌려줌 <br>
     * ex) 112233, ch(/) -> 11/22/33 <BR>
     * @param str
     * @param ch
     * @return 변환된 문자열
     */

    /**
    * HH24MISS 형식의 시간문자열을 원하는 캐릭터(ch)로 쪼개 돌려준다 <br>
    * <pre>
    *     ex) 151241, ch(/) -> 15/12/31
    * </pre>
    *
    * @param str HH24MISS 형식의 시간문자열
    * @param ch 구분자
    * @return 변환된 문자열
    */

    public static String formatTime(String str, String ch) {
        if (str == null || str.length() == 0)
            return "";
        if (str.length() == 6) {
            return str.substring(0, 2) + ch + str.substring(2, 4) + ch + str.substring(4, 6);
        } else
            return "";
    }

    /**
    * 주어진 날짜가 속한 주의 월요일/일요일 날짜를 구해 스트링배열로 리턴한다 <br>
    *
    * @param str yyyymmdd 형식의 날짜문자열
    * @param week 주어진날짜가 속한 주에서의 차이, 1 : 다음주, -1 : 전주
    * @return 월/일요일
    * @exception ParseException 문자열파싱시 발생
    */

    public static String[] getBothDayOfWeek(String str, int week) throws ParseException {
        return new String[] { getFirstDayOfWeek(str, week), getLastDayOfWeek(str, week)};
    }

    /**
    * 주어진 날짜가 속한 주의 월요일/일요일 날짜를 구해 스트링배열로 리턴한다 <br>
    *
    * @param date Date객체
    * @param week 주어진날짜가 속한 주에서의 차이, 1 : 다음주, -1 : 전주
    * @return 월/일요일
    * @exception ParseException 문자열파싱시 발생
    */

    public static String[] getBothDayOfWeek(Date date, int week) throws ParseException {
        return new String[] { getFirstDayOfWeek(date, week), getLastDayOfWeek(date, week)};
    }

    /**
    * 주어진 날짜가 속한 주의 월요일 날짜를 구한다 <br>
    *
    * @param str yyyymmdd 형식의 날짜문자열
    * @param week 주어진날짜가 속한 주에서의 차이, 1 : 다음주, -1 : 전주
    * @return 월요일을 나타내는 yyyyMMdd 형식의 날짜 문자열
    * @exception ParseException 잘못된 형식의 문자열 입력시 발생
    */
    public static String getFirstDayOfWeek(String str, int week) throws ParseException {
        String conStr = null;
        int dayOfWeek = 0;

        if (week == 0) {
            conStr = str;
            dayOfWeek = getCalendar(conStr).get(Calendar.DAY_OF_WEEK);
        } else {
            conStr = addDays(str, week * 7);
            dayOfWeek = getCalendar(conStr).get(Calendar.DAY_OF_WEEK);
        }

        int gap = 0;
        if (dayOfWeek != 1)
            gap = dayOfWeek - 2;
        else
            gap = 6;

        return addDays(conStr, -gap);
    }

    /**
    * 주어진 날짜가 속한 주의 월요일 날짜를 구한다 <br>
    *
    * @param date Date객체
    * @param week 주어진날짜가 속한 주에서의 차이, 1 : 다음주, -1 : 전주
    * @return 월요일
    * @exception ParseException 문자열파싱시 발생
    */

    public static String getFirstDayOfWeek(Date date, int week) throws ParseException {
        Date conDate = null;
        int dayOfWeek = 0;

        if (week == 0) {
            conDate = date;
            dayOfWeek = getCalendar(conDate).get(Calendar.DAY_OF_WEEK);
        } else {
            conDate = addDays2Date(date, week * 7);
            dayOfWeek = getCalendar(conDate).get(Calendar.DAY_OF_WEEK);
        }

        int gap = 0;
        if (dayOfWeek != 1)
            gap = dayOfWeek - 2;
        else
            gap = 6;

        return addDays(conDate, -gap);
    }

    /**
    * 주어진 날짜가 속한 주의 일요일 날짜를 구한다 <br>
    *
    * @param str yyyymmdd 형식의 날짜문자열
    * @param week 주어진날짜가 속한 주에서의 차이, 1 : 다음주, -1 : 전주
    * @return 월요일
    * @exception ParseException 문자열파싱시 발생
    */

    public static String getLastDayOfWeek(String str, int week) throws ParseException {
        String conStr = null;
        int dayOfWeek = 0;

        if (week == 0) {
            conStr = str;
            dayOfWeek = getCalendar(conStr).get(Calendar.DAY_OF_WEEK);
        } else {
            conStr = addDays(str, week * 7);
            dayOfWeek = getCalendar(conStr).get(Calendar.DAY_OF_WEEK);
        }

        int gap = 0;
        if (dayOfWeek != 1)
            gap = 8 - dayOfWeek;
        else
            gap = 0;

        return addDays(conStr, gap);
    }

    /**
    * 주어진 날짜가 속한 주의 일요일 날짜를 구한다 <br>
    *
    * @param date Date객체
    * @param week 주어진날짜가 속한 주에서의 차이, 1 : 다음주, -1 : 전주
    * @return 월요일
    * @exception ParseException 문자열파싱시 발생
    */

    public static String getLastDayOfWeek(Date date, int week) throws ParseException {
        Date conDate = null;
        int dayOfWeek = 0;

        if (week == 0) {
            conDate = date;
            dayOfWeek = getCalendar(conDate).get(Calendar.DAY_OF_WEEK);
        } else {
            conDate = addDays2Date(date, week * 7);
            dayOfWeek = getCalendar(conDate).get(Calendar.DAY_OF_WEEK);
        }

        int gap = 0;
        if (dayOfWeek != 1)
            gap = 8 - dayOfWeek;
        else
            gap = 0;

        return addDays(conDate, gap);
    }

    /**
    * 주어진 날짜가 속한 주가 월의 몇째주인지를 구한다 <br>
    *
    * @param str yyyymmdd 형식의 날짜문자열
    * @return 몇째주
    */

    public static int getWeek(String str) {
        return getCalendar(str).get(Calendar.WEEK_OF_MONTH);
    }

    /**
    * 주어진 날짜가 속한 주가 월의 몇째주인지를 구한다 <br>
    *
    * @param date Date객체
    * @return 몇째주
    */

    public static int getWeek(Date date) {
        return getCalendar(date).get(Calendar.WEEK_OF_MONTH);
    }

    /**
    * 주어진 문자열로 날짜셋팅한 calendar class 구하기 <br>
    *
    * @param str yyyymmdd 형식의 날짜문자열
    * @return Calendar-인스턴스
    */

    public static Calendar getCalendar(String str) {
        int yy = Integer.parseInt(str.substring(0, 4));
        int mm = Integer.parseInt(str.substring(4, 6)) - 1;
        int dd = Integer.parseInt(str.substring(6, 8));

        Calendar cal = Calendar.getInstance();
        cal.set(yy, mm, dd);
        return cal;
    }

    /**
    * 주어진 {@link Date} 객체로 날짜셋팅한 calendar class 구하기 <br>
    *
    * @param date Date객체
    * @return Calendar-인스턴스
    */

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
    * 날짜문자열(yyyymmdd)에 날짜를 더한(혹은 뺀) 일자를 구함 <br>
    *
    * @param str yyyyMMdd 형식의 날짜문자열
    * @param days 더할, 혹은 뺄 날수
    * @return yyyyyMMdd 형식의 8자리 날짜
    * @exception 날짜문자열 형식이 잘못되었을 경우 ParseException return
    */

    public static String addDays(String str, int days) throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        Date date = fmt.parse(str);
        date.setTime(date.getTime() + (long) days * 1000L * 60L * 60L * 24L);
        return fmt.format(date);
    }

    /**
    * date에 날짜를 더한(혹은 뺀) 일자를 구함 <br>
    *
    * @param date Date객체
    * @param days 더할, 혹은 뺄 날수
    * @return yyyyyMMdd 형식의 8자리 날짜
    * @exception 날짜문자열 형식이 잘못되었을 경우 ParseException return
    */

    public static String addDays(Date date, int days) throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        date.setTime(date.getTime() + (long) days * 1000L * 60L * 60L * 24L);
        return fmt.format(date);
    }

    /**
    * 날짜문자열(yyyymmdd)에 날짜를 더한(혹은 뺀) 일자를 구함 <br>
    *
    * @param str yyyyMMdd 형식의 날짜문자열
    * @param days 더할, 혹은 뺄 날수
    * @return yyyyyMMdd 형식의 8자리 날짜
    * @exception 날짜문자열 형식이 잘못되었을 경우 ParseException return
    */

    public static Date addDays2Date(String str, int days) throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        Date date = fmt.parse(str);
        date.setTime(date.getTime() + (long) days * 1000L * 60L * 60L * 24L);
        return date;
    }

    /**
    * 날짜문자열(yyyymmdd)에 날짜를 더한(혹은 뺀) 일자를 구함 <br>
    *
    * @param date Date객체
    * @param days 더할, 혹은 뺄 날수
    * @return date
    * @exception 날짜문자열 형식이 잘못되었을 경우 ParseException return
    */

    public static Date addDays2Date(Date date, int days) throws ParseException {
        date.setTime(date.getTime() + (long) days * 1000L * 60L * 60L * 24L);
        return date;
    }

    /**
    * 주어진 문자열이 주어진 요일과 일치하는지 아닌지 여부리턴 <br>
    *
    * @param str yyyyMMdd 형식의 날짜문자열
    * @param dayOfWeek 요일 (SUNDAY~SATURDAY중 하나)
    * @return 날짜와 요일이 일치할시 true,아니면 false를 리턴한다
    */

    public static boolean isDayOfWeek(String str, int dayOfWeek) {
        int day = getCalendar(str).get(Calendar.DAY_OF_WEEK);
        if (day == dayOfWeek)
            return true;
        else
            return false;
    }

    /**
     * 주어진 문자열이 주어진 요일과 일치하는지 아닌지 여부리턴 <br>
     *
     * @param date Date객체
     * @param dayOfWeek 요일 (SUNDAY~SATURDAY중 하나)
     * @return 날짜와 요일이 일치할시 true,아니면 false를 리턴한다
     */

    public static boolean isDayOfWeek(Date date, int dayOfWeek) {
        int day = getCalendar(date).get(Calendar.DAY_OF_WEEK);
        if (day == dayOfWeek)
            return true;
        else
            return false;
    }

    /**
    * 주어진 날짜가 속한 주의 요일을 구함 <br>
    *
    * @param str yyyymmdd 형식의 날짜
    * @param dayOfWeek 요일 (SUNDAY ~ SATURDAY 중 하나)
    * @return yyyyyMMdd 형식의 8자리 날짜
    */

    public static String getSpecialDayOfWeek(String str, int dayOfWeek) throws ParseException {
        int gap = 0;
        int day = getCalendar(str).get(Calendar.DAY_OF_WEEK);

        if (day == dayOfWeek)
            return str;
        else if (dayOfWeek == SUNDAY)
            gap = 8 - day;
        else if (day < dayOfWeek)
            gap = dayOfWeek - day;
        else
            gap = - (day - dayOfWeek);

        if (day == SUNDAY)
            gap = gap - 7;
        return addDays(str, gap);
    }

    /**
    * 14자리 (yyyyMMddHH24MISS) 형식의 날짜를 04/05 (MM/dd) 로 바꿈
    *
    * @param date yyyyMMddHH24MISS 형식의 날짜
    * @param delim 구분자
    * return MMDD 월일문자열
    */

    public static String convertDayOnly(String date, String delim) {
        return date.substring(4, 6) + delim + date.substring(6, 8);
    }


    /**
    * 현재(한국기준) 시간정보를 얻는다.                     <BR>
    * (예) 입력파리미터인 format string에 "yyyyMMddhh"를 셋팅하면 1998121011과 같이 Return.  <BR>
    * (예) format string에 "yyyyMMddHHmmss"를 셋팅하면 19990114232121과 같이
    * (예) 밀리세컨드는 yyyyMMddHHmmssSSS
    *      0~23시간 타입으로 Return. <BR>
    *      String CurrentDate = BaseUtil.getKST("yyyyMMddHH");<BR>

    * @param    format      얻고자하는 현재시간의 Type
    * @return   string      현재 한국 시간.
    */
    public static String getCurrentTime(String format) {
        //1hour(ms) = 60s * 60m * 1000ms
        int millisPerHour = 60 * 60 * 1000;
        SimpleDateFormat fmt= new SimpleDateFormat(format);

        SimpleTimeZone timeZone = new SimpleTimeZone(9*millisPerHour,"KST");
        fmt.setTimeZone(timeZone);

        String str = fmt.format(new java.util.Date(System.currentTimeMillis()));

        return str;
    }

    /**
     * 현재(한국기준) 날짜정보를 얻는다.                     <BR>
     * 표기법은 yyyy-mm-dd                                  <BR>
     * @return  String      yyyymmdd형태의 현재 한국시간.   <BR>
     */
    public static String getCurrentDate(){
        return getCurrentDate("");
    }

    /**
     * 현재(한국기준) 날짜정보를 얻는다.                     <BR>
     * 표기법은 yyyy-mm-dd                                  <BR>
     * @return  String      yyyymmdd형태의 현재 한국시간.   <BR>
     */
    public static String getCurrentDate(String dateType) {
        Calendar aCalendar = Calendar.getInstance();

        int year = aCalendar.get(Calendar.YEAR);
        int month = aCalendar.get(Calendar.MONTH) + 1;
        int date = aCalendar.get(Calendar.DATE);
		String strDate = Integer.toString(year) +
                ((month<10) ? "0" + Integer.toString(month) : Integer.toString(month)) +
                ((date<10) ? "0" + Integer.toString(date) : Integer.toString(date));
		
		if(!"".equals(dateType)) strDate = convertDate(strDate, "yyyyMMdd", dateType);

        return  strDate;
    }

    /**
    * 입력한 날짜 기준으로 몇일 전,후(입력날짜는 yyyyMMdd 형식)
    * @param    dayString   string date (19991002)
    * @param    day         가감하려고 하는 일자
    * @return   String
    */
    public static String getDateWithSpan(String dayString, int day) {
        SimpleDateFormat m_formatter = new SimpleDateFormat("yyyyMMdd");
        int y = Integer.parseInt( dayString.substring(0,4) );
        int m = Integer.parseInt( dayString.substring(4,6) );
        int d = Integer.parseInt( dayString.substring(6,8) );

        Calendar aCalendar = Calendar.getInstance();
        aCalendar.set(y, m-1, d+day);

        return m_formatter.format( aCalendar.getTime() );
    }
    
    /**
     * long형 날짜 String을 주어진 날짜 포맷으로 변경해 주는 메서드
     *
     * @param timeString long형 날짜 String
     * @return 날짜 포맷을 변경한 String
     */
    public static String getFormatDate(String timeString, String format) {
    	long timeLong = 0L;
		if( timeString == null || timeString.length()==0 ) {
			timeLong = (new Date()).getTime();
		}
		else {
			timeLong = Long.valueOf( timeString ).longValue();
		}
		
		Date date = new Date();
		date.setTime( timeLong );
		
		SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
		
		return simpledateformat.format(date);
    }
    
    /**
     * 날짜형태의 String의 날짜 포맷을 [yyyy-MM-dd HH:mm:ss]에서 
     * [yyyyMMddHHmmss]으로(default value) 변경해 주는 메서드
     *
     * @param strSource 바꿀 날짜 String
     * @return 소스 String의 날짜 포맷을 변경한 String
     */
    public static String convertDate(String strSource) {
        return convertDate(strSource, "", "", "");
    }

    /**
     * 날짜형태의 String의 날짜 포맷만을 변경해 주는 메서드
     *
     * @param  strSource       바꿀 날짜 String
     * @param  toDateFormat    원하는 날짜 형태
     * @return 소스 String의 날짜 포맷을 변경한 String
     */
    public static String convertDate(String strSource, String toDateFormat) {
        return convertDate(strSource, "", toDateFormat, "");
    }
    
    /**
     * 날짜형태의 String의 날짜 포맷만을 변경해 주는 메서드
     *
     * @param  strSource       바꿀 날짜 String
     * @param  fromDateFormat  기존의 날짜 형태
     * @param  toDateFormat    원하는 날짜 형태
     * @return 소스 String의 날짜 포맷을 변경한 String
     */
    public static String convertDate(String strSource, String fromDateFormat, String toDateFormat) {
        return convertDate(strSource, fromDateFormat, toDateFormat, "");
    }

    /**
     * 날짜형태의 String의 날짜 포맷 및 TimeZone을 변경해 주는 메서드
     *
     * @param  strSource       바꿀 날짜 String
     * @param  fromDateFormat  기존의 날짜 형태
     * @param  toDateFormat    원하는 날짜 형태
     * @param  strTimeZone     변경할 TimeZone(""이면 변경 안함)
     * @return  소스 String의 날짜 포맷을 변경한 String
     */
    public static String convertDate(String strSource, String fromDateFormat, String toDateFormat, String strTimeZone) {
        SimpleDateFormat simpledateformat = null;
        Date date = null;
        if(StringUtil.isNullToString(strSource).trim().equals("")) {
            return "";
        }
        if(StringUtil.isNullToString(fromDateFormat).trim().equals(""))
            fromDateFormat = "yyyyMMddHHmmss";                    // default값
        if(StringUtil.isNullToString(toDateFormat).trim().equals(""))
            toDateFormat = "yyyy-MM-dd HH:mm:ss";                 // default값
        try {
            simpledateformat = new SimpleDateFormat(fromDateFormat);
            date = simpledateformat.parse(strSource);
            if (!StringUtil.isNullToString(strTimeZone).trim().equals("")) {
                simpledateformat.setTimeZone(TimeZone.getTimeZone(strTimeZone));
            }
            simpledateformat = new SimpleDateFormat(toDateFormat);
        }
        catch(Exception exception) {
        	return strSource;
//            System.out.println(exception.toString());
//            exception.printStackTrace();
        }

        if( simpledateformat!= null && date != null) {
            return simpledateformat.format(date);
        } else {
        	return strSource;
        }
        
    }
    
    
    /**
     * 현재 년수와 달수로 그 달이 몇일까지 있는지 보여 주는 메서드
     *
     * @param dateYear  년수 
     * @param dateMonth  달
     * @return 소스 String의 날짜 포맷을 변경한 String
     */
    public String getTotalDays(String dateYear, String dateMonth) {
    	String[] ar = {"31","28","31","30","31","30","31","31","30","31","30","31"};
    	ar[1] = leapYear(Integer.parseInt(dateYear));
    	
    	return ar[Integer.parseInt(dateMonth)-1];
    }
    
    /**
     * 연도를 입력 받아 해당 연도 2월의 말일(일수)를 문자열로 반환한다.
     * 
     * @param year
     * @return 해당 연도 2월의 말일(일수)
     */
    public String leapYear(int year) {
    	if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
    		return "29";
    	}

    	return "28";
    }
    
    
    /**
     * 주어진 날짜를 yyyy 형식으로 돌려줌 <BR>
     *
     * @param date Date 객체
     * @return yyyy 형식의 4자리 날짜
     */

     public String getYearDate(Date date) {
         SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy");
         return simpledateformat.format(date);
     }
     
     
     /**
      * 주어진 날짜를 MM 형식으로 돌려줌 <BR>
      *
      * @param date Date 객체
      * @return MM 형식의 2자리 날짜
      */

      public String getMonthDate(Date date) {
          SimpleDateFormat simpledateformat = new SimpleDateFormat("MM");
          return simpledateformat.format(date);
      }
    
    // 2006. 02. 13. 시군구고도화 공통1팀(통합처리공통지원)
  	// 박규태 추가
    //-----------------------------------------------------------------------
    /**
     * <p>
     * HH24MISS 형식의 시간문자열에 '시분초'를 붙여준다.
     * </p>
     * 
     * <pre>
     * </pre>
     *
     * @param timeStr HH24MISS 형식의 시간문자열
     * @return 변환된 문자열
     */
    public static String formatTime(String timeStr) {
		if (timeStr == null || timeStr.length() == 0)
			return "";
		if (timeStr.length() == 6) {
			return timeStr.substring(0, 2) + "시 " + timeStr.substring(2, 4) + "분 "
					+ timeStr.substring(4, 6) + "초";
		}
		return "";
	}

    /**
	 * <p>
	 * yyyyMMdd 형식의 날짜 String을 여러가지 형태로 반환한다.
	 * </p>
	 * 
	 * <pre>
	 *  DateUtil.formatDate("20040101", 1) = "2004년1월1일"
	 *  DateUtil.formatDate("20040101", 2) = "2004년 1월 1일" // 공백 하나
	 *  DateUtil.formatDate("20040121", 3) = "2004년  1월 21일" // 월,일이 한자리일 경우 공백 둘, 두자리일 경우 공백 하나
	 *  DateUtil.formatDate("20040101", 4) = "2004년01월01일" // 월,일이 한자리일 경우 0 포함
	 *  DateUtil.formatDate("20040101", 5) = "2004년 01월 01일"
	 * </pre>
	 * 
	 * @param dateStr 8자리의 날짜 문자열
	 * @param type 포맷 타입
	 * @return 변환된 날짜 문자열
	 * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우. 
	 *         입력 값이 <code>null</code>인 경우.
	 */
	public static String formatDate(String dateStr, int type) {
        if (dateStr == null || dateStr.trim().length() != 8)
            throw new IllegalArgumentException("Invalid date format: " + dateStr);

        String str = dateStr.trim();
        String yyyy = "";
        String mm = "";
        String dd = "";
        String yyyyKr = "년";
        String mmKr = "월";
        String ddKr = "일";

        yyyy = str.substring(0, 4);
        mm = str.substring(4, 6);
        dd = str.substring(6);

        switch (type) {
		case 1:
			mm = mm.startsWith("0") ? mm.substring(1) : mm;
			dd = dd.startsWith("0") ? dd.substring(1) : dd;
			break;
		case 2:
			mm = mm.startsWith("0") ? " "+mm.substring(1) : " "+mm;
			dd = dd.startsWith("0") ? " "+dd.substring(1) : " "+dd;
			break;
		case 3:
			mm = mm.startsWith("0") ? "  "+mm.substring(1) : " "+mm;
			dd = dd.startsWith("0") ? "  "+dd.substring(1) : " "+dd;
			break;
		case 5:
			mm = " "+mm;
			dd = " "+dd;
			break;
		default:
			break;
		} // (type == 4) -> do nothing
        
        return yyyy + yyyyKr + mm + mmKr + dd + ddKr;
	}
	
    /**
	 * <p>입력받은 연도가 윤년인지 아닌지 검사한다.</p>
	 * 
	 * <pre>
	 * DateUtil.isLeapYear(2004) = false
	 * DateUtil.isLeapYear(2005) = true
	 * DateUtil.isLeapYear(2006) = true
	 * </pre>
	 * 
	 * @param  year 연도
	 * @return  윤년 여부
	 */
    public static boolean isLeapYear(int year) {
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			return false;
		}
		return true;
	}
    
    /**
	 * <p>지정한 연도의 각 월별로 말일을 구한다. 1월 부터 12월까지의 모든 날 수 반환.</p>
	 * 
	 * <pre>
	 * DateUtil.getMonthDaysArray(2006).length = 12
	 * DateUtil.getMonthDaysArray(2006)[1] = 28 //2월
	 * DateUtil.getMonthDaysArray(2004)[1] = 29 //2월
	 * DateUtil.getMonthDaysArray(2001)[7] = 31 //8월
	 * DateUtil.getMonthDaysArray(2008)[11] = 31 //12월
	 * </pre>
	 * 
	 * @param  year 연도
	 * @return  해당 연도 모든 달의 일수(말일)이 입력된 int 배열
	 */
    public static int[] getMonthDaysArray(int year) {
		int[] a1 = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int[] a2 = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (year <= 1582) {
			if ((year % 4) == 0) {
				if (year == 4) {
					return a1;
				}
				return a2;
			}
		} else {
			if (((year % 4) == 0) && ((year % 100) != 0)) {
				return a2;
			} else if ((year % 400) == 0) {
				return a2;
			}
		}

		return a1;
	}
    
    /**
	 * <p>지정한 연도의 일수를 구한다.</p>
	 * 
	 * <pre>
	 * DateUtil.getDaysInYear(1996) = 366
	 * DateUtil.getDaysInYear(2003) = 365
	 * DateUtil.getDaysInYear(2004) = 366
	 * DateUtil.getDaysInYear(2006) = 365
	 * </pre>
	 * 
	 * @param year 연도
	 * @return 해당 연도의 일수
	 */
    public static int getDaysInYear(int year) {
		if (year > 1582) {
			if (year % 400 == 0)
				return 366;
			else if (year % 100 == 0)
				return 365;
			else if (year % 4 == 0)
				return 366;
			else
				return 365;
		} else if (year == 1582) {
			return 355;
		} else if (year > 4) {
			if (year % 4 == 0)
				return 366;
			else
				return 365;
		} else if (year > 0) {
			return 365;
		} else {
			return 0;
		}
	}
    
    /**
	 * <p>
	 * 지정한 연도에서 지정한 월의 일수(말일)을 구한다.
	 * </p>
	 * 
	 * <pre>
	 *  DateUtil.getDaysInMonth(1991, 1) = 31
	 *  DateUtil.getDaysInMonth(2001, 8) = 31
	 *  DateUtil.getDaysInMonth(2006, 2) = 28
	 *  DateUtil.getDaysInMonth(2008, 12) = 31
	 * </pre>
	 * 
	 * @param year 연도
	 * @param month 월
	 * @return 해당 월의 말일
	 * @throws IllegalArgumentException 년, 월 입력 값의 형식이 잘못된 경우. 
	 *         입력 값이 <code>null</code>인 경우.
	 */
    public static int getDaysInMonth(int year, int month) {
		if (month < 1 || month > 12)
			throw new IllegalArgumentException("Invalid month: " + month);

		int[] b = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (month != 2 && year != 1582)
			return b[month - 1];
		if (month != 2 && year == 1582) {
			if (month != 10)
				return b[month - 1];
			else
				return b[month - 1] - 10;
		}

		if (year > 1582) {
			if (year % 400 == 0)
				return 29;
			else if (year % 100 == 0)
				return 28;
			else if (year % 4 == 0)
				return 29;
			else
				return 28;
		} else if (year == 1582) {
			return 28;
    	} else if (year > 4) {
			if (year % 4 == 0)
				return 29;
			else
				return 28;
		} else if (year > 0) {
			return 28;
		} else { 
			throw new IllegalArgumentException("Invalid year: " + year);
		}
	}
    
    /**
	 * <p>
	 * 지정한 월의 일수(말일)을 구한다.
	 * </p>
	 * 
	 * <pre>
	 * // 2006년의 경우,
	 * DateUtil.getDaysInMonth(1)  = 31
	 * DateUtil.getDaysInMonth(2)  = 28
	 * DateUtil.getDaysInMonth(6)  = 30
	 * DateUtil.getDaysInMonth(11) = 30
	 * </pre>
	 * 
	 * @param month 월
	 * @return 해당 월의 말일
	 */
    public static int getDaysInMonth(int month) {
    	int year = getCurrentYear();
    	return getDaysInMonth(year, month);
    }
    
    /**
	 * <p>지정한 월의 일수(말일)을 구한다.</p>
	 * 
	 * <pre>
	 * DateUtil.getDaysInMonth("1991", "01") = 31
	 * DateUtil.getDaysInMonth("2001", "8")  = 31
	 * DateUtil.getDaysInMonth("2006", "2")  = 28
	 * DateUtil.getDaysInMonth("2008", "12") = 31
	 * </pre>
	 * 
	 * @param yearStr 연도
	 * @param monthStr 월
	 * @return  해당 월의 말일
	 */
    public static int getDaysInMonth(String yearStr, String monthStr) {
    	return getDaysInMonth(Integer.parseInt(yearStr), Integer.parseInt(monthStr));
    }
    
    /**
	 * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열에서 월을 추출하여, 
	 * 해당 월의 일수(말일)을 구한다.</p>
	 * 
	 * <pre>
	 * DateUtil.getDaysInMonth("19920101") = 31
	 * DateUtil.getDaysInMonth("20010831") = 31
	 * DateUtil.getDaysInMonth("20060202") = 28
	 * DateUtil.getDaysInMonth("20081263") = 31 // 월까지만 유효하면 정상으로 처리
	 * </pre>
	 * 
	 * @param dateStr  yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열
	 * @return  해당 월의 말일
	 * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우. 
	 *         입력 값이 <code>null</code>인 경우.
	 */
    public static int getDaysInMonth(String dateStr) {
    	if (dateStr == null || !(dateStr.trim().length() == 8 || dateStr.trim().length() == 10)) {
    		throw new IllegalArgumentException("Invalid date format: " + dateStr);
    	}
    	if (dateStr.length() == 10) {
    		dateStr = StringUtil.removeMinusChar(dateStr);
    	}
    	return getDaysInMonth(dateStr.substring(0,4), dateStr.substring(4,6));
	}
    
    /**
	 * <p>지정한 연도의 두번째달(2월, February) 일수(말일)을 구한다.</p>
	 * 
	 * <pre>
	 * DateUtil.getFebLastDay(1981) = 28
	 * DateUtil.getFebLastDay(1987) = 28
	 * DateUtil.getFebLastDay(1992) = 29
	 * DateUtil.getFebLastDay(2000) = 29
	 * DateUtil.getFebLastDay(2006) = 28
	 * </pre>
	 * 
	 * @param  year 연도
	 * @return  2월의 말일
	 */
    public static int getFebLastDay(int year) {
    	return isLeapYear(year) ? 28 : 29;
    }
    
    /**
	 * <p>입력한 년, 월, 일이 유효한지 검사.</p>
	 * 
	 * @param  year 연도
	 * @param  month 월
	 * @param  day 일
	 * @return  유효한 날짜인지 여부
	 */
    public static boolean checkDate(String year, String month, String day) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");

			Date result = formatter.parse(year + "." + month + "." + day);
			String resultStr = formatter.format(result);

			if (resultStr.equalsIgnoreCase(year + "." + month + "." + day))
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

    /**
	 * <p>현재 연도를 기준으로 입력한 월, 일이 유효한지 검사.</p>
	 * 
	 * @param  month 월
	 * @param  day 일
	 * @return  유효한 날짜인지 여부
	 */
    public static boolean checkDate(String month, String day) {
		String year = String.valueOf(getCurrentYear());
		return checkDate(year, month, day);
	}
    
    /**
	 * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 유효한 날짜인지 검사.</p>
	 * 
	 * <pre>
	 * DateUtil.checkDate("1999-02-35") = false
	 * DateUtil.checkDate("2000-13-31") = false
	 * DateUtil.checkDate("2006-11-31") = false
	 * DateUtil.checkDate("2006-2-28")  = false
	 * DateUtil.checkDate("2006-2-8")   = false
	 * DateUtil.checkDate("20060228")   = true
	 * DateUtil.checkDate("2006-02-28") = true
	 * </pre>
	 * 
	 * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @return  유효한 날짜인지 여부
	 */
    public static boolean checkDate(String dateStr) {
    	if (dateStr == null || !(dateStr.trim().length() == 8 || dateStr.trim().length() == 10)) {
    		return false;
    	}
    	if (dateStr.length() == 10) {
    		dateStr = StringUtil.removeMinusChar(dateStr);
    	}
    	String year = dateStr.substring(0,4);
    	String month = dateStr.substring(4,6);
    	String day = dateStr.substring(6);
    	
    	return checkDate(year, month, day);
    }
    
    /**
	 * <p>어제 날짜 문자열을 yyyyMMdd 형식으로 반환한다.</p>
	 * 
	 * <pre>
	 * // 오늘이 2006년 2월 15일이면, 
	 * DateUtil.getYesterDay() = "20060214"
	 * </pre>
	 * 
	 * @return  yyyyMMdd 형식의 날짜 문자열
	 */
    public static String getYesterDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return sdf.format(cal.getTime());
    }
    
    /**
	 * <p>현재 시간을 yyyy-MM-dd HH:mm:ss 형식으로 반환한다.</p>
	 * 
	 * <pre>
	 * // 현재 시간이 2006년 2월 15일 15시 43분 59초면,
	 * DateUtil.getCurrentTime() = "2006-02-15 15:43:59"
	 * </pre>
	 * 
	 * @return  yyyy-MM-dd HH:mm:ss 형식의 시간 문자열
	 */
    public static String getCurrentTime() {
    	return getCurrentTime("yyyy-MM-dd HH:mm:ss");
    }
    
    /**
	 * <p>현재 요일을 반환한다.</p>
	 * 
	 * <pre>
	 * // 현재 요일이 수요일이면, 4를 반환
	 * // 숫자값은 그 의미를 알기 힘드므로 DateUtil에 미리 정의된 상수를 사용해서 요일을
	 * // 구분한다(java.util.Calendar의 상수와 동일함).
	 * DateUtil.getCurrentDayOfWeek() = DateUtil.WEDNESDAY =4
	 * </pre>
	 * 
	 * @return  요일 상수
	 * @see #SUNDAY
	 * @see #MONDAY
	 * @see #TUESDAY
	 * @see #WEDNESDAY
	 * @see #THURSDAY
	 * @see #FRIDAY
	 * @see #SATURDAY
	 */
    public static int getCurrentDayOfWeek() {
    	Calendar cal = Calendar.getInstance();
    	return cal.get(Calendar.DAY_OF_WEEK);
    }
    
    /**
	 * <p>현재 연도를 반환한다.</p>
	 * 
	 * <pre>
	 * DateUtil.getCurrentYear() = 2006
	 * </pre>
	 * 
	 * @return  연도
	 */
    public static int getCurrentYear() {
    	Calendar cal = Calendar.getInstance();
    	return cal.get(Calendar.YEAR);
    }
    
    /**
	 * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 년, 월, 일을 
	 * 증감한다. 년, 월, 일은 가감할 수를 의미하며, 음수를 입력할 경우 감한다.</p>
	 * 
	 * <pre>
	 * DateUtil.addYearMonthDay("19810828", 0, 0, 19)  = "19810916"
	 * DateUtil.addYearMonthDay("20060228", 0, 0, -10) = "20060218"
	 * DateUtil.addYearMonthDay("20060228", 0, 0, 10)  = "20060310"
	 * DateUtil.addYearMonthDay("20060228", 0, 0, 32)  = "20060401"
	 * DateUtil.addYearMonthDay("20050331", 0, -1, 0)  = "20050228"
	 * DateUtil.addYearMonthDay("20050301", 0, 2, 30)  = "20050531"
	 * DateUtil.addYearMonthDay("20050301", 1, 2, 30)  = "20060531"
	 * DateUtil.addYearMonthDay("20040301", 2, 0, 0)   = "20060301"
	 * DateUtil.addYearMonthDay("20040229", 2, 0, 0)   = "20060228"
	 * DateUtil.addYearMonthDay("20040229", 2, 0, 1)   = "20060301"
	 * </pre>
	 * 
	 * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param  year 가감할 년. 0이 입력될 경우 가감이 없다
	 * @param  month 가감할 월. 0이 입력될 경우 가감이 없다
	 * @param  day 가감할 일. 0이 입력될 경우 가감이 없다
	 * @return  yyyyMMdd 형식의 날짜 문자열
	 * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우. 
	 *         입력 값이 <code>null</code>인 경우.
	 */
	public static String addYearMonthDay(String dateStr, int year, int month, int day) {
		if (dateStr == null || !(dateStr.trim().length() == 8 || dateStr.trim().length() == 10)) {
			throw new IllegalArgumentException("Invalid date format: " + dateStr);
		}
		if (dateStr.length() == 10) {
			dateStr = StringUtil.removeMinusChar(dateStr);
		}
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			cal.setTime(sdf.parse(dateStr));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format: " + dateStr);
		}
		
		if (year != 0) 
			cal.add(Calendar.YEAR, year);
		if (month != 0) 
			cal.add(Calendar.MONTH, month);
		if (day != 0) 
			cal.add(Calendar.DATE, day);
		return sdf.format(cal.getTime());
	}
	
    /**
	 * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 년을 
	 * 증감한다. <code>year</code>는 가감할 수를 의미하며, 음수를 입력할 경우 감한다.</p>
	 * 
	 * <pre>
	 * DateUtil.addYear("20000201", 62)  = "20620201"
	 * DateUtil.addYear("20620201", -62) = "20000201"
	 * DateUtil.addYear("20040229", 2)   = "20060228"
	 * DateUtil.addYear("20060228", -2)  = "20040228"
	 * DateUtil.addYear("19000101", 200) = "21000101"
	 * </pre>
	 * 
	 * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param  year 가감할 년. 0이 입력될 경우 가감이 없다
	 * @return  yyyyMMdd 형식의 날짜 문자열
	 * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우. 
	 *         입력 값이 <code>null</code>인 경우.
	 */
	public static String addYear(String dateStr, int year) {
		return addYearMonthDay(dateStr, year, 0, 0);
	}
	
    /**
	 * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 월을 
	 * 증감한다. <code>month</code>는 가감할 수를 의미하며, 음수를 입력할 경우 감한다.</p>
	 * 
	 * <pre>
	 * DateUtil.addMonth("20010201", 12)  = "20020201"
	 * DateUtil.addMonth("19800229", 12)  = "19810228"
	 * DateUtil.addMonth("20040229", 12)  = "20050228"
	 * DateUtil.addMonth("20050228", -12) = "20040228"
	 * DateUtil.addMonth("20060131", 1)   = "20060228"
	 * DateUtil.addMonth("20060228", -1)  = "20060128"
	 * </pre>
	 * 
	 * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param  month 가감할 월. 0이 입력될 경우 가감이 없다
	 * @return  yyyyMMdd 형식의 날짜 문자열
	 * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우. 
	 *         입력 값이 <code>null</code>인 경우.
	 */
	public static String addMonth(String dateStr, int month) {
		return addYearMonthDay(dateStr, 0, month, 0);
	}
	
    /**
	 * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열을 입력 받아 일(day)를  
	 * 증감한다. <code>day</code>는 가감할 수를 의미하며, 음수를 입력할 경우 감한다.
	 * <br/><br/>
	 * 위에 정의된 addDays 메서드는 사용자가 ParseException을 반드시 처리해야 하는 불편함이
	 * 있기 때문에 추가된 메서드이다.</p>
	 * 
	 * <pre>
	 * DateUtil.addDay("19991201", 62) = "20000201"
	 * DateUtil.addDay("20000201", -62) = "19991201"
	 * DateUtil.addDay("20050831", 3) = "20050903"
	 * DateUtil.addDay("20050831", 3) = "20050903"
     * // 2006년 6월 31일은 실제로 존재하지 않는 날짜이다 -> 20060701로 간주된다
	 * DateUtil.addDay("20060631", 1) = "20060702"
	 * </pre>
	 * 
	 * @param  dateStr 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param  day 가감할 일. 0이 입력될 경우 가감이 없다
	 * @return  yyyyMMdd 형식의 날짜 문자열
	 * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우. 
	 *         입력 값이 <code>null</code>인 경우.
	 */
	public static String addDay(String dateStr, int day) {
		return addYearMonthDay(dateStr, 0, 0, day);
	}
	
	public static Date toDate( String s) {
		if( s==null) return null;
		if (!checkDate(s)) return null;
		s = s.replaceAll("-",  "");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			return sdf.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}
	
    /**
	 * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열 <code>dateStr1</code>과 <code>
	 * dateStr2</code> 사이의 일 수를 구한다.<br>
	 * <code>dateStr2</code>가 <code>dateStr1</code> 보다 과거 날짜일 경우에는
	 * 음수를 반환한다. 동일한 경우에는 0을 반환한다.</p>
	 * 
	 * <pre>
	 * DateUtil.getDaysDiff("20060228","20060310") = 10
	 * DateUtil.getDaysDiff("20060101","20070101") = 365
	 * DateUtil.getDaysDiff("19990228","19990131") = -28
	 * DateUtil.getDaysDiff("20060801","20060802") = 1
	 * DateUtil.getDaysDiff("20060801","20060801") = 0
	 * </pre>
	 * 
	 * @param  dateStr1 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param  dateStr2 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @return  일 수 차이.
	 * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우. 
	 *         입력 값이 <code>null</code>인 경우.
	 */
	public static int getDaysDiff(String dateStr1, String dateStr2) {
    	Date date1 = toDate( dateStr1);
    	Date date2 = toDate( dateStr2);
    	
    	if( date1 == null || date2 == null) {
    		throw new IllegalArgumentException("Invalid date format: args[0]=" + dateStr1 + " args[1]=" + dateStr2);
		}
		int days1 = (int)((date1.getTime()/3600000)/24);
		int days2 = (int)((date2.getTime()/3600000)/24);
		
		return days2 - days1;
	}
	
    /**
	 * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열 <code>dateStr1</code>과 <code>
	 * dateStr2</code> 중 뒤의 날짜가 앞의 날짜보다 큰지 검증한다.<br>
	 * <code>dateStr2</code>가 <code>dateStr1</code> 보다 미래 날짜일 경우에만 true를
	 * 반환한다.</p>
	 * 
	 * <pre>
	 * DateUtil.isToGreaterThanFrom("20060101", "20060102") = true
	 * DateUtil.isToGreaterThanFrom("20000629", "20011123") = true
	 * DateUtil.isToGreaterThanFrom("20060101", "20051225") = false
	 * DateUtil.isToGreaterThanFrom("19800102", "19800101") = false
	 * </pre>
	 * 
	 * @param  dateStr1 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param  dateStr2 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @return  두번째 날짜가 첫번째 날짜보다 큰지 안큰지의 여부
	 * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우. 
	 *         입력 값이 <code>null</code>인 경우.
	 */
	public static boolean isToGreaterThanFrom(String dateStr1, String dateStr2) {
		return getDaysDiff(dateStr1, dateStr2) > 0;
	}
	
    /**
	 * <p>yyyyMMdd 혹은 yyyy-MM-dd 형식의 날짜 문자열 <code>dateStr1</code>과 <code>
	 * dateStr2</code> 중 어떤 날짜가 큰지를 구한다.<br>
	 * <code>dateStr2</code>가 <code>dateStr1</code> 보다 과거 날짜일 경우에는
	 * 1을 반환하며, 미래 날짜일 경우에는 -1을 반환한다. 동일한 경우에는 0을 반환한다.
	 * <br><br>
	 * <b>!주의!</b><br>
	 * 뒤의 날짜가 큰 경우 <b>-1</b>을 반환한다!</p>
	 * 
	 * <pre>
	 * DateUtil.isDaysDiff("20060101", "20060102") = -1
	 * DateUtil.isDaysDiff("20060101", "20060101") = 0
	 * DateUtil.isDaysDiff("20070302", "20020101") = 1
	 * </pre>
	 * 
	 * @param  dateStr1 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @param  dateStr2 날짜 문자열(yyyyMMdd, yyyy-MM-dd의 형식)
	 * @return  일 수 차이.
	 * @throws IllegalArgumentException 날짜 포맷이 정해진 바와 다를 경우. 
	 *         입력 값이 <code>null</code>인 경우.
	 */
	public static int isDaysDiff(String dateStr1, String dateStr2) {
		int diff = getDaysDiff(dateStr1, dateStr2);
		if (diff == 0) {
			return 0;
		} else if (diff > 0){
			return -1;
		} else {
			return 1;
		}
	}
}
