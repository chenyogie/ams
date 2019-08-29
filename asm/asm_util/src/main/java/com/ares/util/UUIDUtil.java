package com.ares.util;

import com.sun.org.apache.regexp.internal.REUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @program: asm
 * @Date: 2019/8/9 19:49
 * @Author: Chenyogie
 * @Description: 使用uuid的工具类
 */
public class UUIDUtil {

    /**
     * 创建借阅编号
     * @return
     */
    public static String createBorrowNum(){
        String prefix = "JY";
        return createNum(prefix);
    }

    /**
     * 创建归还的编号
     * @return
     */
    public static String createRevertNum(){
        String prefix = "GH";
        return createNum(prefix);
    }

    public static String createNum(String prefix){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //获取到当前年月日时间的字符串表示
        String dateStr = sdf.format(new Date());
        //随机生成一个uuid值，并获取其地址值[也是唯一的]
        int i = UUID.randomUUID().hashCode();
        //将地址值转换为16进制字符串
        String s = Integer.toHexString(i);
        return prefix + "-" + dateStr + "-" + s;
    }




}
