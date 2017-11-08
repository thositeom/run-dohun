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

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 문자열과 관련된 메서드를 정의한 공통 Util Class.
 * 
 * @author <${developer}> (${email})
 * @author Last changed by: ${developer}
 * @version 3.2.2
 * @since 1.0-SNAPSHOT
 */
public class StringUtil {

    /**
     * 빈 문자열 <code>""</code>.
     */
    public static final String EMPTY = "";
    
    /**
     * <p>The maximum size to which the padding constant(s) can expand.</p>
     */
    private static final int PAD_LIMIT = 8192;
    
    /**
     * <p>An array of <code>String</code>s used for padding.</p>
     *
     * <p>Used for efficient space padding. The length of each String expands as needed.</p>
     */
    private static final String[] PADDING = new String[Character.MAX_VALUE];

    static {
        // space padding is most common, start with 64 chars
        PADDING[32] = "                                                                ";
    }
    
    /**
     * 원본 문자열의 포함된 특정 문자열을 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replace(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        while (source.indexOf(subject) >= 0) {
            preStr = source.substring(0, source.indexOf(subject));
            nextStr = source.substring(source.indexOf(subject) + subject.length(), source.length());
            source = nextStr;
            rtnStr.append(preStr).append(object);
        }
        rtnStr.append(nextStr);
        return rtnStr.toString();
    }

    /**
     * 원본 문자열의 포함된 특정 문자열 첫번째 한개만 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열 / source 특정문자열이 없는 경우 원본 문자열
     */
    public static String replaceOnce(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        if (source.indexOf(subject) >= 0) {
            preStr = source.substring(0, source.indexOf(subject));
            nextStr = source.substring(source.indexOf(subject) + subject.length(), source.length());
            rtnStr.append(preStr).append(object).append(nextStr);
            return rtnStr.toString();
        } else {
            return source;
        }
    }

    /**
     * <code>subject</code>에 포함된 각각의 문자를 object로 변환한다.
     * 
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replaceChar(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        char chA;

        for (int i = 0; i < subject.length(); i++) {
            chA = subject.charAt(i);

            if (source.indexOf(chA) >= 0) {
                preStr = source.substring(0, source.indexOf(chA));
                nextStr = source.substring(source.indexOf(chA) + 1, source.length());
                source = rtnStr.append(preStr).append(object).append(nextStr).toString();
            }
        }
        return source;
    }

    /**
     * 문자열의 CharSet을 unicode(8859_1)로 바꾸는 메서드
     * @param source 원본 문자열
     * @return result 유니코드로 변환된 문자열
     */
    public static String toUnicode(String source) {
        String ret = null;
        if (source == null)
            return null;
        try {
            ret = new String(source.getBytes(), "8859_1");
        } catch (UnsupportedEncodingException e) {
            ret = null;
        }
        return ret;
    }

    /**
     * 문자열의 CharSet을 EUC-KR(KSC5601)로 바꾸는 메서드.
     * @param source 원본 문자열
     * @return result EUC-KR로 변환된 문자열
     */
    public static String toEuckr(String source) {
        String ret = null;
        if (source == null)
            return null;
        try {
            ret = new String(source.getBytes(), "KSC5601");
        } catch (UnsupportedEncodingException e) {
            ret = null;
        }
        return ret;
    }

    /**
     * 문자열의 unicode(8859_1) CharSet을 넘겨 받아 EUC-KR(KSC5601)로 바꾸는 메서드.
     * @param source unicode(8859_1) 원본 문자열
     * @return result EUC-KR로 변환된 문자열
     */
    public static String toEuckr2(String source) {
        String ret = null;
        if (source == null)
            return null;
        try {
            ret = new String(source.getBytes("8859_1"), "KSC5601");
        } catch (UnsupportedEncodingException e) {
            ret = null;
        }
        return ret;
    }

    /**
     * 문자열을 원하는 형태의 CharSet으로 바꾸는 메서드.
     * @param source 원본 문자열
     * @param charset CharSet
     * @return result 지정 charset으로 변환된 문자열
     */
    public static String toCharset(String source, String charset) {
        String ret = null;
        if (source == null)
            return null;
        try {
            ret = new String(source.getBytes("8859_1"), charset);
        } catch (UnsupportedEncodingException e) {
            ret = null;
        }
        return ret;
    }

    /**
     * DB에 넣기 위해 single quotation 입력을 처리하는 메서드
     * @param source 원본 문자열
     * @return single quotation 처리된 문자열
     */
    public static String procQuotation(String source) {
        String ret = null;
        if (source == null)
            return null;
        ret = replace(source, "'", "''");
        return ret;
    }

    /**
     * single quotation 입력을 처리하고 앞뒤로 single quotation을 추가하는 메서드
     * @param source 원본 문자열
     * @return single quotation 처리된 문자열
     */
    public static String autoQuotation(String source) {
        return "'" + procQuotation(source) + "'";
    }

    /**
     * String의 공백을 제거한다.
     * @param source 원본 문자열
     * @return null이 아닌 경우 공백 제거, null인 경우 empty String 리턴
     */
    public static String isNullTrim(String source) {
        if (source != null) {
            return source.trim();
        } else {
            return "";
        }
    }

    /**
     * 문자열이 null인지 확인하고 null인 경우 지정된 문자열로 바꾸는 메서드
     * @param source 원본 문자열
     * @param value null일 경우 바뀔 문자열
     * @return resultVal 문자열
     */
    public static String isNullToString(String source, String value) {
        String retVal;
        if (source == null || source.length() < 1) {
            retVal = value;
        } else {
            retVal = source;
        }
        retVal = replace(retVal, "'", "`"); // add by khko 20030908 '를 `로 바꾼다
        return retVal;
    }

    /**
     * 객체가 null인지 확인하고 null인 경우 "" 로 바꾸는 메서드
     * @param object 원본 객체
     * @return resultVal 문자열
     */
    public static String isNullToString(Object object) {
        String string = "";
        if (object != null) {
            string = object.toString();
        }
        return string;
    }

    /**
     * 문자배열이 null인지 확인하고 null인 경우 지정된 문자열로 바꾸는 메서드
     * @param source 원본 문자배열
     * @param value null일 경우 바뀔 문자열
     * @return resultVal 문자열
     */
    public static String isNull(String[] source, String value) {
        String retVal;
        if (source == null) {
            retVal = value;
        } else {
            retVal = source[0];
        }
        return retVal;
    }

    /**
     * 문자열이 null인지 확인하고 null인 경우 지정된 문자열로 바꾸는 메서드
     * @param source 원본 문자배열
     * @param value null일 경우 바뀔 문자열
     * @return resultVal 문자열
     */
    public static String isNull2(String source, String value) {
        String str;
        char ch;
        String Result = "";
        int i;
        if (source == null) {
            str = value;
        } else {
            str = source;
        }
        for (i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (ch == '"') {
                Result += "\"";
            } else if (ch == '\'') {
                Result += "\"";
            } else {
                Result += ch;
            }
        }
        return Result;
    }

    /**
     * 문자열이 null인지 확인하고 null인 경우 지정된 문자열로 바꾸는 함수. ',' 문자가 있는경우 제거한다.
     * @param source 원본 문자열
     * @param value null일경우 바뀔 문자열
     * @return 문자열
     */
    public static String isNull3(String source, String value) {
        String retVal;
        if (source == null || source.length() < 1) {
            retVal = value;
        } else {
            retVal = source;
        }
        retVal = replace(retVal, ",", ""); // add by khko 20030916 ','를 제거한다
        return retVal;
    }

    /**
     * 문자열을 int형으로 변환하고, null인지 확인하여 null인 경우 지정된 int로 바꾸는 메서드
     * @param source 원본 문자열
     * @param value null일 경우 바뀔 정수값
     * @return resultVal 변환된 정수
     */
    public static int isNullToInt(String source, int value) {
        int resultVal = 0;
        try {
            if (source == null || source.length() < 1) {
                resultVal = value;
            } else {
                resultVal = Integer.parseInt(source);
            }
        } catch (Exception e) {
            resultVal = value;
        }
        return resultVal;
    }

    /**
     * 문자열을 지정한 분리자에 의해 배열로 리턴하는 메서드.
     * @param source 원본 문자열
     * @param separator 분리자
     * @return result 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator) throws NullPointerException {
        String[] returnVal = null;
        int cnt = 1;

        int index = source.indexOf(separator);
        int index0 = 0;
        while (index >= 0) {
            cnt++;
            index = source.indexOf(separator, index + 1);
        }
        returnVal = new String[cnt];
        cnt = 0;
        index = source.indexOf(separator);
        while (index >= 0) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);
        return returnVal;
    }

    /**
     * 문자열을 지정한 분리자에 의해 지정된 길이의 배열로 리턴하는 메서드.
     * @param source 원본 문자열
     * @param separator 분리자
     * @param arraylength 배열 길이
     * @return 분리자로 나뉘어진 문자열 배열
     */
    public static String[] split(String source, String separator, int arraylength) throws NullPointerException {
        String[] returnVal = new String[arraylength];
        int cnt = 0;
        int index0 = 0;
        int index = source.indexOf(separator);
        while (index >= 0 && cnt < (arraylength - 1)) {
            returnVal[cnt] = source.substring(index0, index);
            index0 = index + 1;
            index = source.indexOf(separator, index + 1);
            cnt++;
        }
        returnVal[cnt] = source.substring(index0);
        if (cnt < (arraylength - 1)) {
            for (int i = cnt + 1; i < arraylength; i++) {
                returnVal[i] = "";
            }
        }
        return returnVal;
    }

    /**
     * 문자열배열을 지정한 분리자에 의해 합한 문자열로 리턴하는 메서드.
     * @param source 원본 문자열 배열
     * @param separator 분리자
     * @return 분리자 합친 문자열
     */
    public static String patch(String[] source, String separator) throws NullPointerException {
        return patch(source, separator, false);
    }

    /**
     * 문자열배열을 지정한 분리자에 의해 합한 문자열로 리턴하는 메서드.
     * @param source 원본 문자열 배열
     * @param separator 분리자
     * @param isblankinsert 빈값에서 분리자를 표시할 지 여부
     * @return 분리자 합친 문자열
     */
    public static String patch(String[] source, String separator, boolean isblankinsert) throws NullPointerException {
        StringBuffer returnVal = new StringBuffer();
        int cnt = 0;
        if (source != null) {
            for (int i = 0; i < source.length; i++) {
                if (!isblankinsert) {
                    if (cnt != 0)
                        returnVal.append(separator);
                    returnVal.append(isNullToString(source[i], ""));
                    cnt++;
                } else {
                    if (isNullToString(source[i], "").length() > 0) {
                        if (cnt != 0)
                            returnVal.append(separator);
                        returnVal.append(source[i]);
                        cnt++;
                    }
                }
            }
        }
        return returnVal.toString();
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 지정한길이에다가 해당 문자열을 붙여주는 메서드.
     * @param source 원본 문자열 배열
     * @param output 더할문자열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, String output, int slength) {
        String returnVal = null;
        if (source != null) {
            if (source.length() > slength) {
                returnVal = source.substring(0, slength) + output;
            } else
                returnVal = source;
        }
        return returnVal;
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 해당 문자열을 삭제하는 메서드
     * @param source 원본 문자열 배열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, int slength) {
        String result = null;
        if (source != null) {
            if (source.length() > slength) {
                result = source.substring(0, slength);
            } else
                result = source;
        }
        return result;
    }

    /**
     * 넘겨받은 문자열의 한글 문자열 포함 여부를 체크하는 메서드
     * @param uni20 체크할 문자열
     * @return check 한글 문자열 포함 여부
     */
    public static boolean checkHan(String uni20) {
        boolean check = false;
        if (uni20 == null || uni20.equals("")) {
            return check;
        } else {
            int len = uni20.length();
            char c;
            for (int i = 0; i < len; i++) {
                c = uni20.charAt(i);
                if (c < 0xac00 || 0xd7a3 < c) {
                    check = false;
                } else {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    /**
     * 넘겨받은 두개의 문자열을 숫자로 변환하여 곱한 값을 반환하는 메서드
     * @param a 첫번째 문자열
     * @param b 두번째 문자열
     * @return 두개의 문자열을 숫자로 변환하여 곱한 값
     */
    public static int calString(String a, String b) {
        int result = 0;
        if (a == null || a.equals("") || b == null || b.equals("")) {
            result = 0;
        } else {
            result = Integer.parseInt(isNullTrim(a)) * Integer.parseInt(isNullTrim(b));
        }
        return result;
    }

    /**
     * 숫자형 스트링을 Locale.KOREA 의 숫자형식으로 표시한다.
     * 
     * @param number 
     * @return 숫자형식의 문자열
     */
    public static String formatComma(String number) {
        return formatComma(Double.parseDouble(number));
    }

    /**
     * double 형 숫자를 Locale.KOREA 의 숫자형식으로 표시한다.(콤마표시)(김용훈 대리 요청)
     * 2005/06/18 다시 추가
     * @param number
     * @return 숫자형식의 문자열
     */
    public static String formatComma(double number) {
        return formatComma(number, Locale.KOREA);
    }

    /**
     * 숫자형 스트링을 locale 의 숫자형식으로 표시한다.(콤마표시)(김용훈 대리 요청)
     * 2005/06/18 다시 추가
     * @param number
     * @param locale
     * @return 숫자형식의 문자열
     */
    public static String formatComma(String number, Locale locale) {
        return formatComma(Double.parseDouble(number), locale);
    }

    /**
     * double 형 숫자를 locale 의 숫자형식으로 표시한다.(콤마표시)(김용훈 대리 요청)
     * 2005/06/18 다시 추가
     * @param number
     * @param locale
     * @return 숫자형식의 문자열
     */
    public static String formatComma(double number, Locale locale) {
        NumberFormat form = NumberFormat.getInstance(locale);
        return form.format(number);
    }
    
    /**
     * 더블형 타입을 pattern 에 맞게 나타나게 한다.
     * (예 : #,     * @param obj
     * @param pattern
     * @return pattern 의 형식으로 리턴한다.
     */
    public static String formatDecimal(double obj, String pattern){
        DecimalFormat form = new DecimalFormat(pattern);
        return form.format(obj);
    }
    
    /**
     * 자리수만큼 0을 채워준다.   
     * @param length - 자리수 
     * @param contents - 내용 
     * @return 자리 수 만큼 0을 채운 문자열
     */
	public static String fixNumericLength(int length, String contents){
		if(contents==null)contents="";
		StringBuffer sb = new StringBuffer();
		int gap = length - contents.getBytes().length;
		for(int i = 0; i<gap; i++)sb.append("0");		
		return sb.append(contents).toString();
	}

    // 2006. 02. 13. 시군구고도화 공통1팀(통합처리공통지원)
	// 박규태 추가
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * String이 비었거나("") 혹은 null인지 검증한다.
	 * </p>
	 * 
	 * <pre>
	 *  StringUtil.isEmpty(null)      = true
	 *  StringUtil.isEmpty(&quot;&quot;)        = true
	 *  StringUtil.isEmpty(&quot; &quot;)       = false
	 *  StringUtil.isEmpty(&quot;bob&quot;)     = false
	 *  StringUtil.isEmpty(&quot;  bob  &quot;) = false
	 * </pre>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * @return <code>true</code> if the String is empty or null
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

    /**
	 * <p>
	 * String 객체의 첫번째 캐릭터를 char 원시타입으로 반환한다. 비어 있는 String
	 * 객체일 때에는 Exception을 던진다.
	 * </p>
	 * 
	 * <pre>
	 *    StringUtil.toChar(null) = IllegalArgumentException
	 *    StringUtil.toChar(&quot;&quot;)   = IllegalArgumentException
	 *    StringUtil.toChar(&quot;A&quot;)  = 'A'
	 *    StringUtil.toChar(&quot;BA&quot;) = 'B'
	 * </pre>
	 * 
	 * @param str
	 *            the character to convert
	 * @return the char value of the first letter of the String
	 * @throws IllegalArgumentException
	 *             if the String is empty
	 */
    public static char toChar(String str) {
        if (isEmpty(str)) {
            throw new IllegalArgumentException("The String must not be empty");
        }
        return str.charAt(0);
    }
    
    /**
     * <p>String 객체의 첫번째 캐릭터를 char 원시타입으로 반환하며, <code>null</code>
     * 혹은 빈 문자열("")일 경우에는 defaultValue를 반환.
     * 
     * <pre>
     *   StringUtil.toChar(null, 'X') = 'X'
     *   StringUtil.toChar("", 'X')   = 'X'
     *   StringUtil.toChar("A", 'X')  = 'A'
     *   StringUtil.toChar("BA", 'X') = 'B'
     * </pre>
     *
     * @param str  the character to convert
     * @param defaultValue  the value to use if the  Character is null
     * @return the char value of the first letter of the String or the default if null
     */
    public static char toChar(String str, char defaultValue) {
        if (StringUtil.isEmpty(str)) {
            return defaultValue;
        }
        return str.charAt(0);
    }
    
    /**
	 * <p>{@link String#toLowerCase()}를 이용하여 소문자로 변환한다.</p>
	 *
	 * <p>A <code>null</code> input String returns <code>null</code>.</p>
	 *
	 * <pre>
	 * StringUtil.lowerCase(null)  = null
	 * StringUtil.lowerCase("")    = ""
	 * StringUtil.lowerCase("aBc") = "abc"
	 * </pre>
	 *
	 * @param str  the String to lower case, may be null
	 * @return the lower cased String, <code>null</code> if null String input
	 */
	public static String lowerCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toLowerCase();
	}
    
    /**
	 * <p>{@link String#toUpperCase()}를 이용하여 대문자로 변환한다.</p>
	 *
	 * <p>A <code>null</code> input String returns <code>null</code>.</p>
	 *
	 * <pre>
	 * StringUtil.upperCase(null)  = null
	 * StringUtil.upperCase("")    = ""
	 * StringUtil.upperCase("aBc") = "ABC"
	 * </pre>
	 *
	 * @param str  the String to upper case, may be null
	 * @return the upper cased String, <code>null</code> if null String input
	 */
	public static String upperCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toUpperCase();
	}
	
	/**
	 * <p>{@link String#toUpperCase()}를 이용하여 첫번째 문자만 대문자로 변환한다.</p>
	 *
	 * <p>A <code>null</code> input String returns <code>null</code>.</p>
	 *
	 * <pre>
	 * StringUtil.firstUpperCase(null)  = null
	 * StringUtil.firstUpperCase("")    = ""
	 * StringUtil.firstUpperCase("aBc") = "Abc"
	 * </pre>
	 *
	 * @param str  the String to upper case, may be null
	 * @return the upper cased String, <code>null</code> if null String input
	 */
	public static String firstUpperCase(String str) {
		if (str == null) {
			return null;
		}
		String s = str.toLowerCase();
		if(s != null && s.length() > 0)
            return Character.toUpperCase(s.charAt(0)) + s.substring(1);
        else
            return "";
	}
	
    /**
     * <p>입력된 String의 앞쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <p>A <code>null</code> input String returns <code>null</code>.
     * An empty string ("") input returns the empty string.</p>
     *
     * <p>If the stripChars String is <code>null</code>, whitespace is
     * stripped as defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtil.stripStart(null, *)          = null
     * StringUtil.stripStart("", *)            = ""
     * StringUtil.stripStart("abc", "")        = "abc"
     * StringUtil.stripStart("abc", null)      = "abc"
     * StringUtil.stripStart("  abc", null)    = "abc"
     * StringUtil.stripStart("abc  ", null)    = "abc  "
     * StringUtil.stripStart(" abc ", null)    = "abc "
     * StringUtil.stripStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     *
     * @param str  the String to remove characters from, may be null
     * @param stripChars  the characters to remove, null treated as whitespace
     * @return the stripped String, <code>null</code> if null String input
     */
    public static String stripStart(String str, String stripChars) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
                start++;
            }
        }
        return str.substring(start);
    }

    /**
     * <p>입력된 String의 뒤쪽에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     *
     * <p>A <code>null</code> input String returns <code>null</code>.
     * An empty string ("") input returns the empty string.</p>
     *
     * <p>If the stripChars String is <code>null</code>, whitespace is
     * stripped as defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtil.stripEnd(null, *)          = null
     * StringUtil.stripEnd("", *)            = ""
     * StringUtil.stripEnd("abc", "")        = "abc"
     * StringUtil.stripEnd("abc", null)      = "abc"
     * StringUtil.stripEnd("  abc", null)    = "  abc"
     * StringUtil.stripEnd("abc  ", null)    = "abc"
     * StringUtil.stripEnd(" abc ", null)    = " abc"
     * StringUtil.stripEnd("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str  the String to remove characters from, may be null
     * @param stripChars  the characters to remove, null treated as whitespace
     * @return the stripped String, <code>null</code> if null String input
     */
    public static String stripEnd(String str, String stripChars) {
        int end;
        if (str == null || (end = str.length()) == 0) {
            return str;
        }

        if (stripChars == null) {
            while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                end--;
            }
        }
        return str.substring(0, end);
    }

    /**
     * <p>입력된 String의 앞과 뒤에서 공백문자(whitespace)를 모두 제거한다.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <p>A <code>null</code> input String returns <code>null</code>.</p>
     *
     * <pre>
     * StringUtil.strip(null)     = null
     * StringUtil.strip("")       = ""
     * StringUtil.strip("   ")    = ""
     * StringUtil.strip("abc")    = "abc"
     * StringUtil.strip("  abc")  = "abc"
     * StringUtil.strip("abc  ")  = "abc"
     * StringUtil.strip(" abc ")  = "abc"
     * StringUtil.strip(" ab c ") = "ab c"
     * </pre>
     *
     * @param str  the String to remove whitespace from, may be null
     * @return the stripped String, <code>null</code> if null String input
     */
    public static String strip(String str) {
        return strip(str, null);
    }

    /**
     * <p>입력된 String의 앞, 뒤에서 두번째 인자로 전달된 문자(stripChars)를 모두 제거한다.</p>
     * 
     * <p>This is similar to {@link String#trim()} but allows the characters
     * to be stripped to be controlled.</p>
     *
     * <p>A <code>null</code> input String returns <code>null</code>.
     * An empty string ("") input returns the empty string.</p>
     *
     * <p>If the stripChars String is <code>null</code>, whitespace is
     * stripped as defined by {@link Character#isWhitespace(char)}.
     * Alternatively use {@link #strip(String)}.</p>
     *
     * <pre>
     * StringUtil.strip(null, *)          = null
     * StringUtil.strip("", *)            = ""
     * StringUtil.strip("abc", null)      = "abc"
     * StringUtil.strip("  abc", null)    = "abc"
     * StringUtil.strip("abc  ", null)    = "abc"
     * StringUtil.strip(" abc ", null)    = "abc"
     * StringUtil.strip("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str  the String to remove characters from, may be null
     * @param stripChars  the characters to remove, null treated as whitespace
     * @return the stripped String, <code>null</code> if null String input
     */
    public static String strip(String str, String stripChars) {
        if (isEmpty(str)) {
            return str;
        }
        str = stripStart(str, stripChars);
        return stripEnd(str, stripChars);
    }
	
    /**
     * <p>입력된 String의 앞과 뒤에서 공백문자(whitespace)를 모두 제거한다. 입력 값이
     * <code>null</code>이면 빈문자열("")을 반환한다.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtil.strip(null)     = ""
     * StringUtil.strip("")       = ""
     * StringUtil.strip("   ")    = ""
     * StringUtil.strip("abc")    = "abc"
     * StringUtil.strip("  abc")  = "abc"
     * StringUtil.strip("abc  ")  = "abc"
     * StringUtil.strip(" abc ")  = "abc"
     * StringUtil.strip(" ab c ") = "ab c"
     * </pre>
     *
     * @param str  the String to be stripped, may be null
     * @return the trimmed String, or an empty String if <code>null</code> input
     */
    public static String stripToEmpty(String str) {
        return str == null ? EMPTY : strip(str, null);
    }
    

    /**
     * <p>입력된 String의 앞과 뒤에서 공백문자(whitespace)를 모두 제거한다. 공백을 
     * 제거한 후의 값이 빈문자열("")이면 <code>null</code>을 반환한다.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtil.strip(null)     = null
     * StringUtil.strip("")       = null
     * StringUtil.strip("   ")    = null
     * StringUtil.strip("abc")    = "abc"
     * StringUtil.strip("  abc")  = "abc"
     * StringUtil.strip("abc  ")  = "abc"
     * StringUtil.strip(" abc ")  = "abc"
     * StringUtil.strip(" ab c ") = "ab c"
     * </pre>
     *
     * @param str  the String to be stripped, may be null
     * @return the stripped String,
     *  <code>null</code> if whitespace, empty or null String input
     */
    public static String stripToNull(String str) {
        if (str == null) {
            return null;
        }
        str = strip(str, null);
        return str.length() == 0 ? null : str;
    }
    
    /**
     * <p>Returns padding using the specified delimiter repeated
     * to a given length.</p>
     *
     * <pre>
     * StringUtil.padding(0, 'e')  = ""
     * StringUtil.padding(3, 'e')  = "eee"
     * StringUtil.padding(-2, 'e') = IndexOutOfBoundsException
     * </pre>
     *
     * @param repeat  number of times to repeat delim
     * @param padChar  character to repeat
     * @return String with repeated character
     * @throws IndexOutOfBoundsException if <code>repeat &lt; 0</code>
     */
    private static String padding(int repeat, char padChar) {
        // be careful of synchronization in this method
        // we are assuming that get and set from an array index is atomic
        String pad = PADDING[padChar];
        if (pad == null) {
            pad = String.valueOf(padChar);
        }
        while (pad.length() < repeat) {
            pad = pad.concat(pad);
        }
        PADDING[padChar] = pad;
        return pad.substring(0, repeat);
    }
    
    /**
     * <p>문자열의 우측(끝)에 공백(' ')을 덧붙인다.</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtil.rightPad(null, *)   = null
     * StringUtil.rightPad("", 3)     = "   "
     * StringUtil.rightPad("bat", 3)  = "bat"
     * StringUtil.rightPad("bat", 5)  = "bat  "
     * StringUtil.rightPad("bat", 1)  = "bat"
     * StringUtil.rightPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @return right padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String rightPad(String str, int size) {
        return rightPad(str, size, ' ');
    }

    /**
     * <p>문자열의 우측(끝)에 지정한 문자(character)를 덧붙인다.</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtil.rightPad(null, *, *)     = null
     * StringUtil.rightPad("", 3, 'z')     = "zzz"
     * StringUtil.rightPad("bat", 3, 'z')  = "bat"
     * StringUtil.rightPad("bat", 5, 'z')  = "batzz"
     * StringUtil.rightPad("bat", 1, 'z')  = "bat"
     * StringUtil.rightPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padChar  the character to pad with
     * @return right padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String rightPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return rightPad(str, size, String.valueOf(padChar));
        }
        return str.concat(padding(pads, padChar));
    }

    /**
     * <p>문자열의 우측(끝)에 지정한 문자열(String)을 덧붙인다.</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtil.rightPad(null, *, *)      = null
     * StringUtil.rightPad("", 3, "z")      = "zzz"
     * StringUtil.rightPad("bat", 3, "yz")  = "bat"
     * StringUtil.rightPad("bat", 5, "yz")  = "batyz"
     * StringUtil.rightPad("bat", 8, "yz")  = "batyzyzy"
     * StringUtil.rightPad("bat", 1, "yz")  = "bat"
     * StringUtil.rightPad("bat", -1, "yz") = "bat"
     * StringUtil.rightPad("bat", 5, null)  = "bat  "
     * StringUtil.rightPad("bat", 5, "")    = "bat  "
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padStr  the String to pad with, null or empty treated as single space
     * @return right padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String rightPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return rightPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return str.concat(new String(padding));
        }
    }

    /**
     * <p>문자열의 좌측에 공백(' ')을 덧붙인다.</p>
     *
     * <p>The String is padded to the size of <code>size<code>.</p>
     *
     * <pre>
     * StringUtil.leftPad(null, *)   = null
     * StringUtil.leftPad("", 3)     = "   "
     * StringUtil.leftPad("bat", 3)  = "bat"
     * StringUtil.leftPad("bat", 5)  = "  bat"
     * StringUtil.leftPad("bat", 1)  = "bat"
     * StringUtil.leftPad("bat", -1) = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @return left padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String leftPad(String str, int size) {
        return leftPad(str, size, ' ');
    }

    /**
     * <p>문자열의 좌측에 지정한 문자(character)를 덧붙인다.</p>
     *
     * <p>Pad to a size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtil.leftPad(null, *, *)     = null
     * StringUtil.leftPad("", 3, 'z')     = "zzz"
     * StringUtil.leftPad("bat", 3, 'z')  = "bat"
     * StringUtil.leftPad("bat", 5, 'z')  = "zzbat"
     * StringUtil.leftPad("bat", 1, 'z')  = "bat"
     * StringUtil.leftPad("bat", -1, 'z') = "bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padChar  the character to pad with
     * @return left padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     * @since 2.0
     */
    public static String leftPad(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.length();
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return leftPad(str, size, String.valueOf(padChar));
        }
        return padding(pads, padChar).concat(str);
    }

    /**
     * <p>문자열의 좌측에 지정한 문자열(String)을 덧붙인다..</p>
     *
     * <p>Pad to a size of <code>size</code>.</p>
     *
     * <pre>
     * StringUtil.leftPad(null, *, *)      = null
     * StringUtil.leftPad("", 3, "z")      = "zzz"
     * StringUtil.leftPad("bat", 3, "yz")  = "bat"
     * StringUtil.leftPad("bat", 5, "yz")  = "yzbat"
     * StringUtil.leftPad("bat", 8, "yz")  = "yzyzybat"
     * StringUtil.leftPad("bat", 1, "yz")  = "bat"
     * StringUtil.leftPad("bat", -1, "yz") = "bat"
     * StringUtil.leftPad("bat", 5, null)  = "  bat"
     * StringUtil.leftPad("bat", 5, "")    = "  bat"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padStr  the String to pad with, null or empty treated as single space
     * @return left padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String leftPad(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return leftPad(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }
    
    /**
     * <p>숫자의 좌측에 지정한 문자열(String)을 덧붙인다..</p>
     */
    public static String leftPad(int str, int size, String padStr) {
    	
    		return leftPad(String.valueOf(str), size, padStr);
    }

    /**
     * <p>문자열에서 {@link Character#isWhitespace(char)}에 정의된 
     * 모든 공백문자를 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeWhitespace(null)         = null
     * StringUtil.removeWhitespace("")           = ""
     * StringUtil.removeWhitespace("abc")        = "abc"
     * StringUtil.removeWhitespace("   ab  c  ") = "abc"
     * </pre>
     *
     * @param str  the String to delete whitespace from, may be null
     * @return the String without whitespaces, <code>null</code> if null String input
     */
    public static String removeWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        int sz = str.length();
        char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz) {
            return str;
        }
        return new String(chs, 0, count);
    }
    
    /**
     * <p>입력된 문자열 값이 <code>null</code>인 경우, 빈문자열("")을 반환.</p>
     *
     * <pre>
     * StringUtil.defaultString(null)  = ""
     * StringUtil.defaultString("")    = ""
     * StringUtil.defaultString("bat") = "bat"
     * </pre>
     *
     * @see String#valueOf(Object)
     * @param str  the String to check, may be null
     * @return the passed in String, or the empty String if it
     *  was <code>null</code>
     */
    public static String defaultString(String str) {
        return str == null ? EMPTY : str;
    }
    
    /**
     * <p>입력된 문자열 값이 <code>null</code>인 경우, 두번째 인자 값을 반환한다.
     * <code>null</code>아닌 경우 그대로 반환.</p>
     *
     * <pre>
     * StringUtil.defaultString(null, "NULL")  = "NULL"
     * StringUtil.defaultString("", "NULL")    = ""
     * StringUtil.defaultString("bat", "NULL") = "bat"
     * </pre>
     *
     * @see String#valueOf(Object)
     * @param str  the String to check, may be null
     * @param defaultStr  the default String to return
     *  if the input is <code>null</code>, may be null
     * @return the passed in String, or the default if it was <code>null</code>
     */
    public static String defaultString(String str, String defaultStr) {
        return str == null ? defaultStr : str;
    }
    
    /**
     * <p>기준 문자열에 포함된 모든 대상 문자(char)를 제거한다.</p>
     *
     * <p>A <code>null</code> source string will return <code>null</code>.
     * An empty ("") source string will return the empty string.</p>
     *
     * <pre>
     * StringUtil.remove(null, *)       = null
     * StringUtil.remove("", *)         = ""
     * StringUtil.remove("queued", 'u') = "qeed"
     * StringUtil.remove("queued", 'z') = "queued"
     * </pre>
     *
     * @param str  the source String to search, may be null
     * @param remove  the char to search for and remove, may be null
     * @return the substring with the char removed if found,
     *  <code>null</code> if null String input
     */
    public static String remove(String str, char remove) {
        if (isEmpty(str) || str.indexOf(remove) == -1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != remove) {
                chars[pos++] = chars[i];
            }
        }
        return new String(chars, 0, pos);
    }
    
    /**
     * <p>문자열 내부의 콤마 character(,)를 모두 제거한다.</p>
     *
     * <p>A <code>null</code> source string will return <code>null</code>.
     * An empty ("") source string will return the empty string.</p>
     *
     * <pre>
     * StringUtil.removeCommaChar(null)       = null
     * StringUtil.removeCommaChar("")         = ""
     * StringUtil.removeCommaChar("asdfg,qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str  the source String to search, may be null
     * @return the substring with the ',' removed if found,
     *  <code>null</code> if null String input
     */
    public static String removeCommaChar(String str) {
        return remove(str, ',');
    }
    
    /**
     * <p>문자열 내부의 마이너스 character(-)를 모두 제거한다.</p>
     *
     * <p>A <code>null</code> source string will return <code>null</code>.
     * An empty ("") source string will return the empty string.</p>
     *
     * <pre>
     * StringUtil.removeMinusChar(null)       = null
     * StringUtil.removeMinusChar("")         = ""
     * StringUtil.removeMinusChar("a-sdfg-qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str  the source String to search, may be null
     * @return the substring with the ',' removed if found,
     *  <code>null</code> if null String input
     */
    public static String removeMinusChar(String str) {
        return remove(str, '-');
    }
    
    /**
     * <p>문자열에서 숫자만 추출한다.</p>
     *
     * <pre>
     * StringUtil.keepNumberChar(null)       = null
     * StringUtil.keepNumberChar("")         = ""
     * StringUtil.keepNumberChar("1한글5과숫자1234") = "151234"
     * StringUtil.keepNumberChar("abc한글과123영문과숫자423098") = "123423098"
     * </pre>
     *
     * @param str  the source String to search, may be null
     * @return 0~9 character로만 구성된 문자열,
     *  <code>null</code> if null String input
     */
    public static String keepNumberChar(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer buffer = new StringBuffer(str.length());
        char[] chrs = str.toCharArray();
        int sz = chrs.length;
        for(int i=0; i<sz; i++) {
            if (47 < chrs[i] && chrs[i] < 58) {
                buffer.append(chrs[i]);
            }
        }
        return buffer.toString();
    }
    
    /**
     * <p>주민등록번호에 분리자('-') 추가.</p>
     *
     * <pre>
     * StringUtil.insertMinusCharInSsn(null) = null
     * StringUtil.insertMinusCharInSsn("8001291018717") = "800129-1018717"
     * StringUtil.insertMinusCharInSsn("8001012017212") = "800101-2017212"
     * StringUtil.insertMinusCharInSsn("7504011010")    = IllegalArgumentException
     * </pre>
     *
     * @param ssn  구분자가 없이 숫자만으로 이루어진 주민등록번호 문자열
     * @return '-' 구분자가 삽입된 주민등록번호 문자열,
     *  <code>null</code> if null String input
     */
    public static String insertMinusCharInSsn(String ssn) {
    	if (ssn == null) {
    		return ssn;
    	}
    	if (ssn.trim().length() != 13) {
    		throw new IllegalArgumentException("Invalid SSN: " + ssn + " Length=" + ssn.trim().length());
    	}
    	return new StringBuffer(ssn).insert(6,'-').toString();
    }
    
    /**
     * <p>입력된 문자열을 <code>repeat</code>에 지정한 횟수만큼 반복해서 붙인 후 
     * 반환한다.</p>
     *
     * <pre>
     * StringUtil.repeat(null, 2) = null
     * StringUtil.repeat("", 0)   = ""
     * StringUtil.repeat("", 2)   = ""
     * StringUtil.repeat("a", 3)  = "aaa"
     * StringUtil.repeat("ab", 2) = "abab"
     * StringUtil.repeat("a", -2) = ""
     * </pre>
     *
     * @param str  the String to repeat, may be null
     * @param repeat  number of times to repeat str, negative treated as zero
     * @return a new String consisting of the original String repeated,
     *  <code>null</code> if null String input
     */
    public static String repeat(String str, int repeat) {
        if (str == null) {
            return null;
        }
        if (repeat <= 0) {
            return EMPTY;
        }
        int inputLength = str.length();
        if (repeat == 1 || inputLength == 0) {
            return str;
        }
        if (inputLength == 1 && repeat <= PAD_LIMIT) {
            return padding(repeat, str.charAt(0));
        }

        int outputLength = inputLength * repeat;
        switch (inputLength) {
            case 1 :
                char ch = str.charAt(0);
                char[] output1 = new char[outputLength];
                for (int i = repeat - 1; i >= 0; i--) {
                    output1[i] = ch;
                }
                return new String(output1);
            case 2 :
                char ch0 = str.charAt(0);
                char ch1 = str.charAt(1);
                char[] output2 = new char[outputLength];
                for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }
                return new String(output2);
            default :
                StringBuffer buf = new StringBuffer(outputLength);
                for (int i = 0; i < repeat; i++) {
                    buf.append(str);
                }
                return buf.toString();
        }
    }
    
    /**
     * <p>지정한 <code>len</code> 숫자만큼 우측에서 문자열을 추출한다.<p>
     *
     * <p>If <code>len</code> characters are not available, or the String
     * is <code>null</code>, the String will be returned without an
     * an exception. An exception is thrown if len is negative.</p>
     *
     * <pre>
     * StringUtil.right(null, *)    = null
     * StringUtil.right(*, -ve)     = ""
     * StringUtil.right("", *)      = ""
     * StringUtil.right("abc", 0)   = ""
     * StringUtil.right("abc", 2)   = "bc"
     * StringUtil.right("abc", 4)   = "abc"
     * </pre>
     *
     * @param str  the String to get the rightmost characters from, may be null
     * @param len  the length of the required String, must be zero or positive
     * @return the rightmost characters, <code>null</code> if null String input
     */
    public static String right(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return EMPTY;
        }
        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(str.length() - len);
        }
    }
    
    /**
     * <p>문자열에서 newline character(\n, \r)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeEnterChar(null) = null
     * StringUtil.removeEnterChar("") = ""
     * StringUtil.removeEnterChar("\r\n\n\n\r\r\n") = ""
     * StringUtil.removeEnterChar("\n\u00A0\n\r\u202F\r") = "\u00A0\u202F"
     * StringUtil.removeEnterChar("\n\r한글\r") = "한글"
     * StringUtil.removeEnterChar("소나기가 \r\n내리는 \r\n밤이었습니다") = "소나기가 내리는 밤이었습니다"
     * StringUtil.removeEnterChar("소나기가 \n내리는 \n밤이었습니다") = "소나기가 내리는 밤이었습니다"
     * </pre>
     *
     * @param str  the String to delete newline from, may be null
     * @return the String without newline, <code>null</code> if null String input
     */
    public static String removeEnterChar(String str) {
        if (isEmpty(str)) {
            return str;
        }
        int sz = str.length();
        char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++) {
            if (str.charAt(i) != '\n' && str.charAt(i) != '\r') {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz) {
            return str;
        }
        return new String(chs, 0, count);
    }
    
    /**
     * <p><code>str</code> 중 <code>searchStr</code>의 시작(index) 위치를 반환.</p>
     *
     * <p>입력값 중 <code>null</code>이 있을 경우 <code>-1</code>을 반환.</p>
     *
     * <pre>
     * StringUtil.indexOf(null, *)          = -1
     * StringUtil.indexOf(*, null)          = -1
     * StringUtil.indexOf("", "")           = 0
     * StringUtil.indexOf("aabaabaa", "a")  = 0
     * StringUtil.indexOf("aabaabaa", "b")  = 2
     * StringUtil.indexOf("aabaabaa", "ab") = 1
     * StringUtil.indexOf("aabaabaa", "")   = 0
     * </pre>
     *
     * @param str  the String to check, may be null
     * @param searchStr  the String to find, may be null
     * @return the first index of the search String,
     *  -1 if no match or <code>null</code> string input
     */
    public static int indexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return str.indexOf(searchStr);
    }
    
    /**
	 * <p>오라클의 decode 함수와 동일한 기능을 가진 메서드이다.
	 * <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면
	 * <code>returStr</code>을 반환하며, 다르면  <code>defaultStr</code>을 반환한다.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.decode(null, null, "foo", "bar")= "foo"
	 * StringUtil.decode("", null, "foo", "bar") = "bar"
	 * StringUtil.decode(null, "", "foo", "bar") = "bar"
	 * StringUtil.decode("하이", "하이", null, "bar") = null
	 * StringUtil.decode("하이", "하이  ", "foo", null) = null
	 * StringUtil.decode("하이", "하이", "foo", "bar") = "foo"
	 * StringUtil.decode("하이", "하이  ", "foo", "bar") = "bar"
	 * </pre>
	 * 
	 * @param sourceStr 비교할 문자열
	 * @param compareStr 비교 대상 문자열
	 * @param returnStr sourceStr와 compareStr의 값이 같을 때 반환할 문자열
	 * @param defaultStr sourceStr와 compareStr의 값이 다를 때 반환할 문자열
	 * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며,
	 *         <br/>다르면 defaultStr을 반환한다.
	 */
	public static String decode(String sourceStr, String compareStr, String returnStr, String defaultStr) {
		if (sourceStr == null && compareStr == null) {
			return returnStr;
		}
		
		if (sourceStr == null && compareStr != null) {
			return defaultStr;
		}

		if (sourceStr.trim().equals(compareStr)) {
			return returnStr;
		}

		return defaultStr;
	}

	/**
	 * <p>오라클의 decode 함수와 동일한 기능을 가진 메서드이다.
	 * <code>sourStr</code>과 <code>compareStr</code>의 값이 같으면
	 * <code>returStr</code>을 반환하며, 다르면  <code>sourceStr</code>을 반환한다.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.decode(null, null, "foo") = "foo"
	 * StringUtil.decode("", null, "foo") = ""
	 * StringUtil.decode(null, "", "foo") = null
	 * StringUtil.decode("하이", "하이", "foo") = "foo"
	 * StringUtil.decode("하이", "하이 ", "foo") = "하이"
	 * StringUtil.decode("하이", "바이", "foo") = "하이"
	 * </pre>
	 * 
	 * @param sourceStr 비교할 문자열
	 * @param compareStr 비교 대상 문자열
	 * @param returnStr sourceStr와 compareStr의 값이 같을 때 반환할 문자열
	 * @return sourceStr과 compareStr의 값이 동일(equal)할 때 returnStr을 반환하며,
	 *         <br/>다르면 sourceStr을 반환한다.
	 */
	public static String decode(String sourceStr, String compareStr, String returnStr) {
		return decode(sourceStr, compareStr, returnStr, sourceStr);
	}
	
    /**
     * <p>입력 문자열의 크기를 조정한다. 문자열이 주어진 <code>size</code> 보다 길면 
     * 자르고, 작으면 공백(' ')을 뒤에 추가한다
     * (2byte 문자(한글)의 경우 길이를 2로 간주한다).</p>
     * 
     * <p>2byte 문자가 1byte만 잘려야 할 경우 해당 문자를 버린다.</p>
     * 
     * <pre>
	 * StringUtil.rightPadOrSubsting("abc영어공부", 4)  = "abc"
	 * StringUtil.rightPadOrSubsting("abc영어공부", 5)  = "abc영"
	 * StringUtil.rightPadOrSubsting("abc영어공부", 9)  = "abc영어공"
	 * StringUtil.rightPadOrSubsting("abc영어공부", 11) = "abc영어공부"
	 * StringUtil.rightPadOrSubsting("abc영어공부", 12) = "abc영어공부 "
	 * StringUtil.rightPadOrSubsting("abc영어공부", 16) = "abc영어공부     "
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @return 문자열을 주어진 길이로 자르거나, 뒤에 공백을 붙여서 반환한다. 문자열 길이와
     * 주어진 길이가 같을 경우 주어진 문자열을 반환한다.
     * <code>null</code> if null String input
     */
	public static String rightPadOrSubsting(String str, int size) {
		if (str == null) {
			return null;
		}
		int strLen = str.getBytes().length;
        int pads = size - strLen;
        if (pads == 0) {
            return str;
        } else if (pads > 0) {
        	return rightPad2(str, size, ' ');
        } else {
        	return substring2ByteString(str, size);
        }
	}
	
	/**
	 * <p>지정한 <code>size</code>가 될 때까지 <code>padStr</code>을 <code>str</code>의 
	 * 우측에 붙인다(2byte 문자(한글)의 경우 길이를 2로 간주한다).<br>
	 * <code>padStr</code>가 <code>null</code>이거나 빈 문자열("")이면 공백문자(' ')를 
	 * 사용한다.</p>
	 * 
	 * <p>2byte 문자가 1byte만 잘려야 할 경우 해당 문자를 버린다.</p>
	 * 
     * <pre>
	 * StringUtil.rightPad2("한글과 abc", 4, null) = "한글과 abc"
	 * StringUtil.rightPad2("한글과 abc", 4, "") = "한글과 abc"
	 * StringUtil.rightPad2("한글과 abc", 11, null) = "한글과 abc "
	 * StringUtil.rightPad2("한글과 abc", 11, "") = "한글과 abc "
	 * StringUtil.rightPad2("한글과 abc", 12, "a") = "한글과 abcaa"
	 * StringUtil.rightPad2("한글과 abc", 12, "한글") = "한글과 abc한"
	 * StringUtil.rightPad2("한글과 abc", 13, "한글") = "한글과 abc한"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padStr  the String to pad with, null or empty treated as single space
     * @return right padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
	public static String rightPad2(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = " ";
        }
        
        // 2byte character는 2자리로 계산해야하기 때문...
        int padLen = padStr.getBytes().length;
        int strLen = str.getBytes().length;
        int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        // 2byte character는 1이 될 수 없음...
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return rightPad2(str, size, padStr.charAt(0));
        }

        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(substring2ByteString(padStr, pads));
        } else {
        	int gap = padLen - padStr.length();
        	char[] padChars = padStr.toCharArray();
        	char[] padding = new char[pads];
        	if (gap == 0) {
        		for (int i = 0; i < pads; i++) {
        			padding[i] = padChars[i % padLen];
        		}
        		return str.concat(new String(padding));
        	} else {
        		int padLen2 = padStr.length(); 
            	for (int i = 0; i < pads - gap; i++) {
            		padding[i] = padChars[i % padLen2];
            	}
            	return substring2ByteString(str.concat(new String(padding)), size);
        	}
        }
    }
	
    /**
     * <p>공백문자(' ')를 지정한 길이가 될 때까지 우측에 덧붙인다
     * (2byte 문자(한글)의 경우 길이를 2로 간주한다).</p>
     *
     * <p>The String is padded to the size of <code>size</code>.</p>
     *
     * <pre>
	 * StringUtil.rightPad2("한글", 0)   = "한글"
	 * StringUtil.rightPad2("한글", 1)   = "한글"
	 * StringUtil.rightPad2("한글", 4)   = "한글"
	 * StringUtil.rightPad2("한글", 5)   = "한글 "
	 * StringUtil.rightPad2("한글", 10)  = "한글      "
	 * StringUtil.rightPad2("한글a", 10) = "한글a     "
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @return right padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String rightPad2(String str, int size) {
        return rightPad2(str, size, ' ');
    }
    
    /**
	 * <p>지정한 <code>size</code>가 될 때까지 <code>padChar</code>를 <code>str</code>의 
	 * 우측에 붙인다(2byte 문자(한글)의 경우 길이를 2로 간주한다).<br>
	 * <code>padChar</code>가 <code>null</code>이거나 빈 문자열("")이면 공백문자(" ")를 
	 * 사용한다.</p>
	 * 
     * <pre>
     * StringUtil.rightPad2("한글", 3, null) = "한글"
     * StringUtil.rightPad2("한글", 4, null) = "한글"
     * StringUtil.rightPad2("한글", 5, null) = "한글 "
     * StringUtil.rightPad2("한글", 5, ' ') = "한글 "
     * StringUtil.rightPad2("한글", 5, 'a') = "한글a"
     * StringUtil.rightPad2("한글", 5, '\n') = "한글\n"
     * StringUtil.rightPad2("abc와 한글", 11, 'z') = "abc와 한글z"
     * StringUtil.rightPad2("abc와 한글", 15, 'z') = "abc와 한글zzzzz"
     * </pre>
     *
     * @param str  the String to pad out, may be null
     * @param size  the size to pad to
     * @param padChar  the character to pad with
     * @return right padded String or original String if no padding is necessary,
     *  <code>null</code> if null String input
     */
    public static String rightPad2(String str, int size, char padChar) {
        if (str == null) {
            return null;
        }
        int pads = size - str.getBytes().length;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (pads > PAD_LIMIT) {
            return rightPad2(str, size, String.valueOf(padChar));
        }
        return str.concat(padding2(pads, padChar));
    }
    
    private static String padding2(int repeat, char padChar) {
        // be careful of synchronization in this method
        // we are assuming that get and set from an array index is atomic
        String pad = PADDING[padChar];
        if (pad == null) {
            pad = String.valueOf(padChar);
        }
        while (pad.length() < repeat) {
            pad = pad.concat(pad);
        }
        PADDING[padChar] = pad;
        return substring2ByteString(pad, repeat);
    }
    
    private static String substring2ByteString(String str, int endIndex) {
		if (str == null) {
			return null;
		}

		byte[] strByte = str.getBytes();
		int i, strLen;

		strLen = strByte.length;

		if (strLen <= endIndex) {
			return str;
		}

		int cnt = 0;
		for (i = 0; i < endIndex; i++) {
			if ((((int) strByte[i]) & 0xff) > 0x80) {
				cnt++;
			}
		}
		if ((cnt % 2) == 1) {
			i--;
		}

		return new String(strByte, 0, i);
	}
}