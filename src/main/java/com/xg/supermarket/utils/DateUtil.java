package com.xg.supermarket.utils;

import com.xg.supermarket.config.ConstantsConfig;
import com.xg.supermarket.exception.BizException;
import tk.mybatis.mapper.util.StringUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date getDate(String str){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// 这里要与时间字符串的格式一样即可，否则报错
        if(StringUtil.isEmpty(str)){
            return null;
        }
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            throw new BizException("时间格式参数不正确，yyyy-MM-dd");
        }
    }
    public static String toDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    /**
     * 日期是本月的第几天
     *
     * @param date
     *            天
     * @return 当前天数
     * @throws ParseException
     *             数据转换异常
     */
    public static String getMostDay(String date) throws ParseException {
        // 当天日期是本月的第几天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDay = format.parse(date);
        Calendar ca = Calendar.getInstance();
        ca.setTime(nowDay);
        int num = ca.get(Calendar.DAY_OF_MONTH);
        String time = String.valueOf(num);
        return time;
    }

    /**
     * 获取日期在当月天数
     *
     * @param date
     *            天
     * @return 当月天数
     * @throws Exception
     *             异常
     */
    public static String getMonthDay(String date) throws Exception {
        // 当前月最大日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dates = sdf.parse(date);// String-->Date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dates);// 设置日历时间
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        String mostDate = String.valueOf(day);
        return mostDate;
    }

    /**
     * 当年第一天
     *
     * @param date
     *            日期
     * @return 天
     * @throws Exception
     *             异常
     */
    public static String getThisYear(String date) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy");
        Date time = format3.parse(date);
        String time1 = format3.format(time);
        Date startTime = format.parse(time1 + "-01-01");
        String dates = format.format(startTime);
        return dates;
    }

    /**
     * 当月第一天
     *
     * @param date
     *            天
     * @return 天
     * @throws ParseException
     *             异常
     */
    public static String getFirstDayToMonth(String date) throws ParseException {
        // 获取截止当前天数
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date nowDay = format.parse(date);
        String time = format.format(nowDay);
        String firstDay = time + "-01";
        return firstDay;
    }

    /**
     * 当年第一天
     *
     * @param date
     *            天
     * @return 天
     * @throws ParseException
     *             异常
     */
    public static String getFirstDay(String date) {
        // 获取截止当前天数
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Date nowDay = null;
        try {
            nowDay = format.parse(date);
        } catch (ParseException e) {
            throw new BizException("日期转换异常");
        }
        String time = format.format(nowDay);
        String firstDay = time + "-01-01";
        return firstDay;
    }

    /**
     * 获取日期到年初的天数
     *
     * @param date
     *            天
     * @return 天数
     * @throws Exception
     *             异常
     */
    public static int getDayCount(String date) throws Exception {
        // 获取截止当前天数
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String year = date.substring(0, ConstantsConfig.CUT_OUT);
        String starttime = year + "-01-01 00:00:00";
        String endtime = date + " 00:00:00";
        Date d1 = format.parse(starttime);
        Date d2 = format.parse(endtime);
        long s1 = d1.getTime();
        long s2 = d2.getTime();
        int days = (int) ((s2 - s1) / ConstantsConfig.EVERYDAY_SECOND);
        return days;
    }

    /**
     * 月底到年初的天数
     *
     * @param date
     *            天
     * @return 天
     * @throws Exception
     *             异常
     */
    public static int getYearCount(String date) throws Exception {
        String count = getMonthDay(date);
        String firstDay = getFirstDay(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM");
        long startTime = format.parse(firstDay).getTime();
        long endTime = format2.parse(date).getTime();
        String string = format2.format(endTime);
        Date dates = format.parse(string + "-" + count);
        long endMonthTime = dates.getTime();
        long dayMonth = (endMonthTime - startTime) / ConstantsConfig.EVERYDAY_SECOND;
        return (int) dayMonth;
    }


    /**
     * 获取给定日起前一天
     *
     * @param date
     *            天
     * @return 天
     * @throws ParseException
     *             异常
     */
    public static String getYesterDay(String date) throws ParseException {
        // 获取给定日起过去15天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date dateTemp = sdf.parse(date);
        cal.setTime(dateTemp);
        cal.add(Calendar.DATE, ConstantsConfig.MINUS_1);
        Date dates = cal.getTime();
        String yesterDay = sdf.format(dates);
        return yesterDay;
    }

    /**
     * 获取给定日起前一周
     *
     * @param date
     *            天
     * @return 天
     * @throws ParseException
     *             异常
     */
    public static String getLastWeekDay(String date) throws ParseException {
        // 获取给定日起过去15天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date dateTemp = sdf.parse(date);
        cal.setTime(dateTemp);
        cal.add(Calendar.DATE, ConstantsConfig.MINUS_7);
        Date dates = cal.getTime();
        String yesterDay = sdf.format(dates);
        return yesterDay;
    }

    /**
     * 获取给定日起过去15天
     *
     * @param date
     *            天
     * @return 天
     * @throws ParseException
     *             异常
     */
    public static String getLastFifthDay(String date) throws ParseException {
        // 获取给定日起过去15天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date dateTemp = sdf.parse(date);
        cal.setTime(dateTemp);
        cal.add(Calendar.DATE, ConstantsConfig.MINUS_15); // -15
        Date dates = cal.getTime();
        String lastFifthDay = sdf.format(dates);
        return lastFifthDay;
    }

    /**
     * 获取给定日起一个月
     *
     * @param date
     *            天
     * @return 天
     * @throws ParseException
     *             异常
     */
    public static String getLastMonthDay(String date) throws ParseException {
        // 获取给定日起过去15天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date dateTemp = sdf.parse(date);
        cal.setTime(dateTemp);
        cal.add(Calendar.MONTH, ConstantsConfig.MINUS_1); // -1,1个月前
        Date dates = cal.getTime();
        String lastHalfYearDay = sdf.format(dates);
        return lastHalfYearDay;
    }

    /**
     * 获取给定日起3个月
     *
     * @param date
     *            天
     * @return 天
     * @throws ParseException
     *             异常
     */
    public static String getLastThreeMonthsDay(String date) throws ParseException {
        // 获取给定日起过去15天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date dateTemp = sdf.parse(date);
        cal.setTime(dateTemp);
        cal.add(Calendar.MONTH, ConstantsConfig.MINUS_3); // -1,1个月前
        Date dates = cal.getTime();
        String lastHalfYearDay = sdf.format(dates);
        return lastHalfYearDay;
    }

    /**
     * 获取给定日起过去半年
     *
     * @param date
     *            天
     * @return 天
     * @throws ParseException
     *             异常
     */
    public static String getLasthalfYearDay(String date) throws ParseException {
        // 获取给定日起过去15天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date dateTemp = sdf.parse(date);
        cal.setTime(dateTemp);
        cal.add(Calendar.MONTH, ConstantsConfig.MINUS_6); //-6,6个月前
        Date dates = cal.getTime();
        String lastHalfYearDay = sdf.format(dates);
        return lastHalfYearDay;
    }

    /**
     * 获取给定日去年同期
     *
     * @param date
     *            天
     * @return 天
     * @throws ParseException
     *             异常
     */
    public static String getToLastYearDay(String date) throws ParseException {
        // 获取给定日起过去15天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date dateTemp = sdf.parse(date);
        cal.setTime(dateTemp);
        cal.add(Calendar.YEAR, -1);
        Date dates = cal.getTime();
        String toLastYearDay = sdf.format(dates);
        return toLastYearDay;
    }
}
