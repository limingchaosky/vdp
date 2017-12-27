package cn.goldencis.vdp.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    //24小时过期时间
    public static final long OVER_DUE_TIME = 24 * 60 * 60 * 1000L;

    // 72小时过期时间
    public static final long OVER_DUE_TIME_SP = 72 * 60 * 60 * 1000L;

    public static final String FMT_DATE = "yyyy-MM-dd HH:mm:ss";

    //public static final SimpleDateFormat SimpleDateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    public static final String[] DateFormats = new String[] { "yyyy-MM-dd", "HH:mm", "yyyyMMddHHmm" };

    public static final String DateFormat = "yyyy-MM-dd";

    public static final String TimeFormat = "HH:mm";

    public static final String DateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    public static String getNowDateTime() {
        Date date = new Date();
        SimpleDateFormat fm = new SimpleDateFormat(DateTimeFormat, Locale.US);
        fm.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return fm.format(date);
    }

    /**.
     * 获取当前时间的前n个月或者后n个月
     * @param format 返回格式
     * @param n  月数
     * @param flag 1 表示当前时间前,2表示当前时间后
     * @return String
     */
    public static String getBeforDate(String format, int n, int flag) {

        if (Toolkit.isEmptyStr(format)) {
            format = FMT_DATE;
        }

        SimpleDateFormat df = new SimpleDateFormat(format);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, 1);

        if (1 == flag) {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - n);
        } else {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + n);
        }
        return df.format(calendar.getTime());
    }

    public static String format(Date date, String pattern) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(date);
    }

    /*
     * 获取系统当前日期时间(格式自定)
     *
     * @param format
     *            返回日期的格式 默认为 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getFormatDate(String format) {

        if (Toolkit.isEmptyStr(format)) {
            format = FMT_DATE;
        }
        Date d = new Date();
        DateFormat df = new SimpleDateFormat(format);

        return df.format(d);
    }

    public static String getFormatDate() {
        return getFormatDate(FMT_DATE);
    }

    /**.
     * 获取当前日期的前n天或后n天的时间
     *
     * @param i
     *            天数
     * @param flag
     *            1表示后N天 2表示前N天
     * @param format
     *            返回日期的格式 默认为 yyyy-MM-dd HH:mm:ss
     * @return String
     */
    @SuppressWarnings("deprecation")
    public static String getOtherDaytime(int i, String flag, String format) {

        if (Toolkit.isEmptyStr(format)) {
            format = FMT_DATE;
        }

        Date nowdate = new Date();
        DateFormat df = new SimpleDateFormat(format);

        if ("1".equals(flag)) {
            nowdate.setDate(nowdate.getDate() + i);

        } else {
            nowdate.setDate(nowdate.getDate() - i);
        }

        return df.format(nowdate);
    }

    /**.
     * 验证当前月份是否在当前X个月以内
     * @param month 月
     * @param scope X
     * @return boolean
     */
    public static boolean checkMonth(int month, int scope) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("MM");
        int nowmonth = Integer.parseInt(df.format(date));
        //int nowmonth = 5;
        int i = scope - 1;
        if (month < 1 || month > 12) {
            return false;
        } else {
            if (nowmonth > i) {
                return !(month > nowmonth || month < nowmonth - i);
            } else {
                return !(month < nowmonth || (month > nowmonth + (12 - scope) && month <= 12));
            }
        }
    }

    /**.
     * 获取当前日期前N个月份列表
     * @param scope N
     * @return List
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getMonthlist(int scope) {

        List list = new ArrayList();

        for (int i = 1; i <= scope; i++) {
            HashMap map = new HashMap();

            String startdate = getBeforDate("yyyyMMdd", i, 1);
            String enddate = getBeforDate("yyyyMMdd", i - 1, 1);
            String name = startdate.substring(0, 4) + "年" + startdate.substring(4, 6);

            map.put("startdate", startdate);
            map.put("enddate", enddate);
            map.put("name", name);

            list.add(map);

        }

        return list;
    }

    /**.
     * 获取当前月份
     * @return String
     */
    public static String getMonth() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("MM");

        return df.format(date);
    }

    /**.
     * 获取当前小时
     * @return String
     */
    public static String getHour() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("HH");

        return df.format(date);

    }

    /**.
     * 获取当前年份
     * @return String
     */
    public static String getYear() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy");

        return df.format(date);
    }

    /**.
     * 获取当前日期
     * @return String
     */
    public static String getDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd");

        return df.format(date);
    }

    /**.
     * 获取当前日期
     * @return String
     */
    public static String getNoowDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");

        return df.format(date);
    }

    /**.
     * 判断指定的日期是否已过期
     * @param checkedDate 日期
     * @param milliseconds 日期
     * @return boolean
     */
    public static boolean isOverdue(Date checkedDate, long milliseconds) {
        Calendar cd = Calendar.getInstance();
        Date now = cd.getTime();
        // 时间往前移多少毫秒
        now.setTime(now.getTime() - milliseconds);

        return checkedDate.before(now);
    }

    /**.
     * 将日期转化为中文形式
     * @param numdate 日期
     * @param format 格式
     * @return String
     */
    public static String changeNumDateToCHNdate(String numdate, String format) {

        String chndate = "";
        if (numdate != null && !"".equals(numdate)) {
            if ("yyyyMM".equals(format)) {

                chndate = numdate.substring(0, 4) + "年" + numdate.substring(4, 6) + "月";
            } else if ("yyyyMMdd".equals(format)) {
                chndate = numdate.substring(0, 4) + "年" + numdate.substring(4, 6) + "月" + numdate.substring(6, 8) + "日";
            }

        }

        return chndate;

    }

    /**.
     * 将指定字符串格式的时间串转化为需要的格式时间串
     * @param numdate 时间
     * @param inFormat 输入格式
     * @param outFormat 输出格式
     * @return String
     */
    public static String changeNumDateToDate(String numdate, String inFormat, String outFormat) {

        String returndate = "";
        Date date = null;

        try {

            SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
            date = sdf.parse(numdate);
            sdf = new SimpleDateFormat(outFormat);
            returndate = sdf.format(date);

        } catch (Exception e) {

            Toolkit.debug("日期转换失败 ", e);
        }

        return returndate;
    }

    /**.
     * 获取每个月的第一天
     * @param nowdate 时间
     * @param inFormat 输入格式
     * @param outFormat 输出格式
     * @return String
     */
    public static String getFistDay(String nowdate, String inFormat, String outFormat) {

        String returndate = "";

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
        try {
            date = sdf.parse(nowdate);

            Calendar cl = Calendar.getInstance();
            cl.setTime(date);
            cl.set(Calendar.DAY_OF_MONTH, 1);

            date = cl.getTime();

            sdf = new SimpleDateFormat(outFormat);
            returndate = sdf.format(date);

        } catch (ParseException e) {

            Toolkit.debug("获取该月第一天失败", e);
        }

        return returndate;

    }

    /**.
     * 获取后一个月的第一天
     * @param nowdate 时间
     * @param inFormat 输入格式
     * @param outFormat 输出格式
     * @return String
     */
    public static String getNextMonthFistDay(String nowdate, String inFormat, String outFormat) {

        String returndate = "";

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
        try {
            date = sdf.parse(nowdate);

            Calendar cl = Calendar.getInstance();
            cl.setTime(date);
            cl.set(Calendar.MONTH, cl.get(Calendar.MONTH) + 1);
            cl.set(Calendar.DAY_OF_MONTH, 1);

            date = cl.getTime();

            sdf = new SimpleDateFormat(outFormat);
            returndate = sdf.format(date);

        } catch (ParseException e) {

            Toolkit.debug("获取该月第一天失败", e);
        }

        return returndate;
    }

    /**.
     * 获取后一个月的第一天
     * @param fmt 格式
     * @return String
     */
    public static String getNextMonthFirstDay(String fmt) {
        String returndate = "";
        Date date = null;

        Calendar cl = Calendar.getInstance();
        cl.set(Calendar.MONTH, cl.get(Calendar.MONTH) + 1);
        cl.set(Calendar.DAY_OF_MONTH, 1);

        date = cl.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        returndate = sdf.format(date);

        return returndate;
    }

    /**.
     * 获取当月的第一天
     * @param fmt 格式
     * @return String
     */
    public static String getCurrentMonthFirstDay(String fmt) {
        String returndate = "";
        Date date = null;

        Calendar cl = Calendar.getInstance();
        cl.set(Calendar.MONTH, cl.get(Calendar.MONTH));
        cl.set(Calendar.DAY_OF_MONTH, 1);

        date = cl.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        returndate = sdf.format(date);

        return returndate;
    }

    /**.
     * 获取一个月的最后一天
     * @param fmt 格式
     * @return String
     */
    public static String getMonthLastDay(String fmt) {
        String returndate = "";
        Date date = null;
        Calendar cl = Calendar.getInstance();
        switch (cl.get(Calendar.MONTH)) {
        case 0:
            cl.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 1:

            int year = cl.get(Calendar.YEAR);

            if (isLeapYear(year)) {
                cl.set(Calendar.DAY_OF_MONTH, 29);
            } else {
                cl.set(Calendar.DAY_OF_MONTH, 28);
            }
            break;
        case 2:
            cl.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 3:
            cl.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 4:
            cl.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 5:
            cl.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 6:
            cl.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 7:
            cl.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 8:
            cl.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 9:
            cl.set(Calendar.DAY_OF_MONTH, 31);
            break;
        case 10:
            cl.set(Calendar.DAY_OF_MONTH, 30);
            break;
        case 11:
            cl.set(Calendar.DAY_OF_MONTH, 31);
            break;
        default:
            cl.set(Calendar.DAY_OF_MONTH, 30);
        }

        date = cl.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        returndate = sdf.format(date);

        return returndate;
    }

    /**.
     * 获取一个月的最后一天
     * @param nowdate 输入时间
     * @param inFormat 输入格式
     * @param outFormat 输出格式
     * @return String
     */
    public static String getLastDay(String nowdate, String inFormat, String outFormat) {

        String returndate = "";

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(inFormat);
        try {
            date = sdf.parse(nowdate);

            Calendar cl = Calendar.getInstance();
            cl.setTime(date);
            switch (cl.get(Calendar.MONTH)) {
            case 0:
                cl.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 1:

                int year = cl.get(Calendar.YEAR);

                if (isLeapYear(year)) {
                    cl.set(Calendar.DAY_OF_MONTH, 29);
                } else {
                    cl.set(Calendar.DAY_OF_MONTH, 28);
                }
                break;
            case 2:
                cl.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 3:
                cl.set(Calendar.DAY_OF_MONTH, 30);
                break;
            case 4:
                cl.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 5:
                cl.set(Calendar.DAY_OF_MONTH, 30);
                break;
            case 6:
                cl.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 7:
                cl.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 8:
                cl.set(Calendar.DAY_OF_MONTH, 30);
                break;
            case 9:
                cl.set(Calendar.DAY_OF_MONTH, 31);
                break;
            case 10:
                cl.set(Calendar.DAY_OF_MONTH, 30);
                break;
            case 11:
                cl.set(Calendar.DAY_OF_MONTH, 31);
                break;
            default:
                cl.set(Calendar.DAY_OF_MONTH, 30);
            }

            date = cl.getTime();

            sdf = new SimpleDateFormat(outFormat);
            returndate = sdf.format(date);

        } catch (ParseException e) {

            Toolkit.debug("获取该月最后一天失败", e);
        }

        return returndate;
    }

    /**.
     * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
     * 3.能被4整除同时能被100整除则不是闰年
     * @param year 年
     * @return boolean
     */
    public static boolean isLeapYear(int year) {

        if ((year % 400) == 0) {
            return true;
        } else if ((year % 4) == 0) {
            return (year % 100) != 0;
        } else {
            return false;
        }
    }

    /**.
     * 判断当前时间是否在时间区间内
     * @param begindate 开始时间
     * @param enddate 结束时间
     * @param format 格式
     * @return boolean
     */
    public static boolean checkBetweenDate(String begindate, String enddate, String format) {

        boolean flag = false;

        Calendar cd = Calendar.getInstance();
        Date nowdate = cd.getTime();
        DateFormat df = new SimpleDateFormat(format);

        try {

            flag = nowdate.after(df.parse(begindate));
            if (flag) {
                flag = nowdate.before(df.parse(enddate));
            }

        } catch (ParseException e) {
            Toolkit.debug("异常", e);

        }

        return !flag;
    }

    public static int getNextMonth() {
        int currentMonth = Integer.parseInt(getCurrentMonth());
        return ((currentMonth + 1) == 13) ? 1 : (currentMonth + 1);
    }

    public static String getCurrentMonth() {
        return getCurrentMonth("MM");
    }

    /**
     * 获得当前日期数
     * @return
     */
    public static String getCurrentDay() {
        return getCurrentDate("dd");
    }

    /**
     * 获得当前小时数
     * @return
     */
    public static String getCurrentHour() {
        return getCurrentDate("HH");
    }

    /**.
     * 获得当前月份
     * @param pattern (格式，如：yyyy年MM月)
     * @return String
     */
    public static String getCurrentMonth(String pattern) {
        return getCurrentDate(pattern);
    }

    /**.
     * 获得当前日期
     * @param pattern (格式，如：yyyy年MM月dd日)
     * @return String
     */
    public static String getCurrentDate(String pattern) {
        SimpleDateFormat date = new SimpleDateFormat(pattern);
        return date.format(new Date());
    }

    /**
     * 将日期字符串转成Date类型
     * @param str 格式:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date strToDate(String str) {
        String[] dateTime = str.split(" ");
        String[] date = dateTime[0].split("-");
        String[] time = dateTime[1].split(":");

        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]) - 1;
        int day = Integer.parseInt(date[2]);
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        int second = Integer.parseInt(time[2]);

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute, second);

        return cal.getTime();
    }

    /**
     * 将日期字符串转成日期类型
     * @return
     */
    public static Date strToDate(String str, String pattern) {
        try {
            SimpleDateFormat date = new SimpleDateFormat(pattern);
            return date.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获得当前日期时，格式：MM月dd日HH时
     * @return String
     */
    public static String getCurrentDateHour() {
        return getCurrentDateHour("MM月dd日HH时");
    }

    /**
     * 获得当前日期时
     * @param pattern (格式，如：yyyy年MM月dd日HH时)
     * @return
     */
    public static String getCurrentDateHour(String pattern) {
        return getCurrentDate(pattern);
    }

    /**
     * 获得前n个月份，包括当前月份
     * @param number 月份数
     * @return 返回月份格式：yyyyMM，如：200810
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List getBeforeMonths(int number) {
        List list = new ArrayList();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);

        for (int i = 0; i < number; i++) {
            String year = "" + cal.get(Calendar.YEAR);
            String month = ""
                    + (cal.get(Calendar.MONTH) >= 10 ? ("" + cal.get(Calendar.MONTH)) : ("0" + cal.get(Calendar.MONTH)));
            if (cal.get(Calendar.MONTH) == 0) {
                year = "" + (cal.get(Calendar.YEAR) - 1);
                month = "12";
            }

            list.add(year + month);

            cal.add(Calendar.MONTH, -1);
        }

        return list;
    }

    /**
     * 将指定格式的日期字符串(yyyyMMddHH24miss)解析为指定格式的日期(时间)
     * @param pattern
     * @param source
     */
    public static String parse(String pattern, String source) {
        int year = Integer.parseInt(source.substring(0, 4));
        int month = Integer.parseInt(source.substring(4, 6)) - 1;
        int day = Integer.parseInt(source.substring(6, 8));
        int hour = Integer.parseInt(source.substring(8, 10));
        int minute = Integer.parseInt(source.substring(10, 12));
        int second = Integer.parseInt(source.substring(12));

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute, second);

        SimpleDateFormat date = new SimpleDateFormat(pattern);

        return date.format(cal.getTime());
    }

    /**
     * 获得前n个月份
     * @param number 月份数
     * @return 返回月份格式：yyyyMM，如：200810
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List getLastMonths(int number) {
        List list = new ArrayList();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));

        for (int i = 0; i < number; i++) {
            String year = "" + cal.get(Calendar.YEAR);
            String month = ""
                    + (cal.get(Calendar.MONTH) >= 10 ? ("" + cal.get(Calendar.MONTH)) : ("0" + cal.get(Calendar.MONTH)));
            if (cal.get(Calendar.MONTH) == 0) {
                year = "" + (cal.get(Calendar.YEAR) - 1);
                month = "12";
            }

            list.add(year + month);

            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
        }

        return list;
    }

    /**
     * 获得上个月份
     * @return
     */
    public static int getLastMonth() {
        int currentMonth = Integer.parseInt(getCurrentMonth());
        return ((currentMonth - 1) == 0) ? 12 : (currentMonth - 1);
    }

    /**
     * 获得当前年
     * @return
     */
    public static String getCurrentYear() {
        return getCurrentDate("yyyy");
    }

    /**
     * 获得上个月份，格式：yyyyMM，如：200810
     * @return
     */
    public static String getLastMonthWithYear() {
        int lastMonth = getLastMonth();
        String year = (lastMonth == 12) ? ("" + (Integer.parseInt(getCurrentYear()) - 1)) : getCurrentYear();
        String month = (lastMonth < 10) ? ("0" + lastMonth) : ("" + lastMonth);
        return year + month;
    }

    /**
     * 格式化月份
     * @param pattern
     * @param monthStyle 格式：yyyyMM，如：200810
     * @return
     */
    public static String formatOfMonth(String pattern, String monthStyle) {
        if ((monthStyle == null) || (monthStyle.trim().equals(""))) {
            return "";
        }

        int year = Integer.parseInt(monthStyle.substring(0, 4));
        int month = Integer.parseInt(monthStyle.substring(4, 6)) - 1;

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);

        DateFormat date = new SimpleDateFormat(pattern);

        return date.format(cal.getTime());
    }

    /**.
     * 检查日期的有效性
     * @param date 格式：yyyyMMdd
     * @return boolean
     */
    public static boolean isValidDate(String date) {
        boolean bResult = true;

        if ((date == null) || (date.trim().length() != 8)) {
            bResult = false;
        } else {
            try {
                int year = Integer.parseInt(date.substring(0, 4));
                int month = Integer.parseInt(date.substring(4, 6));
                int day = Integer.parseInt(date.substring(6));
                Calendar cal = Calendar.getInstance();
                cal.setLenient(false);
                // 允许严格检查日期格式
                cal.set(year, month - 1, day);
                cal.getTime();
            } catch (Exception e) {
                bResult = false;
            }
        }

        return bResult;
    }

    /**
     * 近月（n月前/后），n为正数为n月后，n为负数为n月前.
     * 格式yyyyMM
     * @param num
     * @return
     */
    public static String nearMonth(int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, num);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        String ye = String.valueOf(year);
        String mo = String.valueOf(month);
        if (mo.length() == 1) {
            mo = "0" + mo;
        }
        return ye + mo;
    }

    /**
     * 取当前时间,格式为YYYYMMDDHHMMSS的日期字符串.
     * <P>
     * @return String 当前日期字符串.
     * @see Date
     */
    /* public static String getNowDefault() {
         Calendar curdate = Calendar.getInstance();
         curdate = Calendar.getInstance(Locale.CHINESE);
         return SimpleDateTimeFormat.format(curdate.getTime());
     }*/

    public static String getFormatDate(Date date, String pattern) {
        SimpleDateFormat f = new SimpleDateFormat(pattern);
        return f.format(date);
    }

    public static List<String> findDates(String dBegin, String dEnd, String pattern) {
        List<String> dateStrList = new ArrayList<String>();

        if (StringUtil.isEmpty(dBegin) || StringUtil.isEmpty(dEnd)) {
            return dateStrList;
        }
        List<Date> list = findDates(strToDate(dBegin, FMT_DATE), strToDate(dEnd, FMT_DATE));
        if (list != null) {
            for (Date date : list) {
                dateStrList.add(getFormatDate(date, pattern));
            }
        }

        return dateStrList;
    }

    public static List<Date> findDates(Date dBegin, Date dEnd) {
        List<Date> lDate = new ArrayList<Date>();

        if (dBegin == null || dEnd == null) {
            return lDate;
        }
        //lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();

        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);

        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);

        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            lDate.add(calBegin.getTime());
            //根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
        }
        return lDate;
    }

    /**
     * 某个时间点 加减 N个月
     * @param format
     * @param n
     * @param flag
     * @return
     */
    public static String getDateAdd(int n, String dateStr, String format) {

        if (Toolkit.isEmptyStr(format)) {
            format = FMT_DATE;
        }

        SimpleDateFormat df = new SimpleDateFormat(format);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strToDate(dateStr, format));

        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + n);
        return df.format(calendar.getTime());
    }

    /**
     * 某个时间点 加减 几天
     * @param format
     * @param n
     * @param flag
     * @return
     */
    public static String getDateAddDay(int n, String dateStr, String format) {

        if (Toolkit.isEmptyStr(format)) {
            format = FMT_DATE;
        }

        SimpleDateFormat df = new SimpleDateFormat(format);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strToDate(dateStr, format));

        calendar.add(Calendar.DATE, n);
        return df.format(calendar.getTime());
    }
}
