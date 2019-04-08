package com.sicau.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:获取现在的时间
 * @author tzw
 * CreateTime 18:38 2019/2/10
 **/
public class VeDate {


   /**
    * @return  返回字符串格式 yyyy-MM-dd HH:mm:ss
    */
    public static String getStringDate() {
           Date currentTime = new Date();
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String dateString = formatter.format(currentTime);
           return dateString;
        }

}
