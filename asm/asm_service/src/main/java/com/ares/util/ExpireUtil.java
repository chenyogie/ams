package com.ares.util;

import com.ares.domain.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: asm
 * @Date: 2019/8/12 22:42
 * @Author: Chenyogie
 * @Description:
 */
public class ExpireUtil {

    private static final String expire = "expire";
    private static final String today = "today";
    private static final String three = "three";
    private static final String aweek = "aweek";
    private static final String twoweek = "twoweek";
    private static final String onemonth = "onemonth";
    private static final String threemonth = "threemonth";
    private static final String halfayear = "halfayear";
    private static final String ayear = "ayear";

    /**
     * 根据过期的时限过滤数据
     * @param sncode 过期的时限
     * @param list   过滤的数据源
     * @return 返回过滤后的集合数据
     */
    public static List<Record> filterByDate(String sncode, List<Record> list) {
        ArrayList<Record> result = new ArrayList<>();
        for (Record record : list) {
            //获取元素的过期时间并格式化
            Date expirationdate = record.getExpirationdate();
            if(expirationdate!=null){
                //将日期格式为当天0点
                Long formatDate = DateUtil.formatDate(expirationdate);
                //获取现在的时间，并格式化为0点的时间
                Long nowDay = DateUtil.getToday();
                if(expire.equals(sncode)){//已经过期
                    if(nowDay>formatDate){
                        result.add(record);
                    }
                }else if(today.equals(sncode)){//今天到期
                    if(nowDay.equals(formatDate)){
                        result.add(record);
                    }
                }else if(three.equals(sncode)){//三天内到期
                    if((nowDay+getMsByDays(3))>formatDate && formatDate>nowDay){
                        result.add(record);
                    }
                }else if(aweek.equals(sncode)){//一周内到期
                    if((nowDay+getMsByWeek(1))>formatDate && formatDate>nowDay){
                        result.add(record);
                    }
                }else if(twoweek.equals(sncode)){//2周内到期
                    if((nowDay+getMsByWeek(2))>formatDate && formatDate>nowDay){
                        result.add(record);
                    }
                }else if(onemonth.equals(sncode)){//1个月内到期
                    if((nowDay+getMsByMonth(1))>formatDate && formatDate>nowDay){
                        result.add(record);
                    }
                }else if(threemonth.equals(sncode)){//三个月内到期
                    if((nowDay+getMsByMonth(3))>formatDate && formatDate>nowDay){
                        result.add(record);
                        continue;
                    }
                }else if(halfayear.equals(sncode)){//半年内到期
                    if((nowDay+getMsByMonth(6))>formatDate && formatDate>nowDay){
                        result.add(record);
                    }
                }else if(ayear.equals(sncode)){//一年内到期
                    if((nowDay+getMsByYear(1))>formatDate && formatDate>nowDay){
                        result.add(record);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 将天数转换成毫秒
     * @return
     */
    private static Long getMsByDays(int days){
        return days * 24 * 60 * 60 * 1000L;
    }

    /**
     * 将星期转换成毫秒
     * @return
     */
    private static Long getMsByWeek(int weeks){
        return weeks * 7 * 24 * 60 * 60 * 1000L;
    }

    /**
     * 将月份转换成毫秒
     * @return
     */
    private static Long getMsByMonth(int months){
        return months * 30 * 24 * 60 * 60 * 1000L;
    }

    /**
     * 将年份转换成毫秒
     * @return
     */
    private static Long getMsByYear(int years){
        return  years * 12 * 30 * 24 * 60 * 60 * 1000L;
    }


}
