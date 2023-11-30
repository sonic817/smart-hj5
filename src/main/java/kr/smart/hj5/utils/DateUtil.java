package kr.smart.hj5.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    // 날짜 패턴
    public static final String DATE_PATTERN = "yyyy-MM-dd";  // 년-월-일
    public static final String DATE_PATTERN_2 = "yyyyMMdd";  // 년월일
    public static final String DATE_PATTERN_3 = "dd";  // 일
    public static final String DATE_PATTERN_4 = "yyMMdd";  // 연도 두 자리 + 월 + 일
    public static final String DATE_PATTERN_5 = "yyyy-MM";  // 년-월
    public static final String DATE_PATTERN_6 = "yyyy";  // 년
    public static final String DATE_PATTERN_7 = "MM";  // 월

    // 날짜와 시간 패턴
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";  // 년-월-일 시:분:초
    public static final String DATE_TIME_PATTERN_2 = "yyyy-MM-dd HH:mm:ss.S";  // 년-월-일 시:분:초.밀리초

    /**
     * 현재 시간 가져오기
     *
     * @return
     */
    public static String getyyyyMMddHHmmssS() {
        return getCurrentTime(DATE_TIME_PATTERN_2);
    }

    public static String getyyyy_MM_dd() {
        return getCurrentTime(DATE_PATTERN);
    }

    public static String getCurrentTime(String DateTimeParttern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateTimeParttern);
        String formattedDate = dateFormat.format(new Date());
        return formattedDate;
    }
}