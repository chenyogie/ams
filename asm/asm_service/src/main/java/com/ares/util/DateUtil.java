package com.ares.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: asm
 * @Date: 2019/8/12 22:37
 * @Author: Chenyogie
 * @Description:
 */
public class DateUtil {

    /**
     * 将yyyy-MM-dd HH:mm:ss 格式的时间转化为yyyy-MM-dd(精确度降低)
     * 并以毫秒值返回数据
     * @param date
     * @return
     */
    public static Long formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date);
        Date result = null;
        try {
            result = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result.getTime();
    }

    /**
     * 返回当前系统0点的毫秒值时间
     * @return
     */
    public static Long getToday(){
        return formatDate(new Date());
    }
}
