package com.brianLin.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jensen on 2016/3/21.
 */
public class RegularUtils {

    private static final String EMAIL_REGUAL_EXPRESSION = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
    private static final String PHONE_NUM_REGUAL = "^1[3|4|5|8][0-9]\\d{8}$";

    /**
     * 检查是否符合手机号码格式
     */
    public static boolean isPhoneNum(String phone){
        Pattern p2   =   Pattern.compile(PHONE_NUM_REGUAL);
        Matcher m2   =   p2.matcher(phone);
        return m2.matches();
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    private final static byte[] hex = "0123456789ABCDEF".getBytes();
    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    private static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    public static boolean isMatchEmailFormat(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        if(isContainsChinese(email)){
            return false;
        }
        return emailer.matcher(email).matches();
    }

    private static final String regEx = "[\u4e00-\u9fa5]";
    private static final Pattern pat = Pattern.compile(regEx);

    /**
     * 是否包含中文
     * @param str
     * @return
     */
    public static boolean isContainsChinese(String str) {
        Matcher matcher = pat.matcher(str);
        boolean flg = false;
        if (matcher.find()) {
            flg = true;
        }
        return flg;
    }

    public static boolean isContainsFullWidthCharacter(String str) {
        return str.getBytes().length != str.length();
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isPhoneNumCN(String str) {
        Pattern pattern = Pattern.compile("[0-9]{11}");
        return pattern.matcher(str).matches();
    }

    public static String translate2SessionMessageData(long unixTime) {
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    public static String tranlate2Date(long unixTime) {

        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd  E  HH:mm");
        String time = formatter.format(unixTime);
        return time;
    }

    public static boolean isTimeIntevalLessThanTenSecond(long time1, long time2) {
        if (time1 <= 0 || time2 <= 0) {
            return true;
        }

        long interval = time1 - time2;
        if (Math.abs(interval) > 10) {
            return true;
        } else {
            return false;
        }
    }

    public static String translate2Second(long unixTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String sd = sdf.format(new Date(unixTime));
        return sd;
    }

    public static String secToTime(long duration) {
        String timeStr = null;
        long hour = 0;
        long minute = 0;
        long second = 0;
        if (duration <= 0)
            return "00:00";
        else {
            minute = duration / 60;
            if (minute < 60) {
                second = duration % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = duration - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":"
                        + unitFormat(second);
            }
        }
        return timeStr;
    }

    private static String unitFormat(long i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Long.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public static String format2MovementClock(long unixTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String sd = sdf.format(new Date(unixTime));
        return sd;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));

        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static int getStrLength(String strs) {

        String len = "";
        int j = 0;
        char[] c = strs.toCharArray();
        for (int i = 0; i < c.length; i++) {
            len = Integer.toBinaryString(c[i]);
            if (len.length() > 8) {
                j += 2;
            } else {
                j++;
            }
        }
        return j;
    }

}
