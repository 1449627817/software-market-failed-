package com.sicau.util;

/**
 * Description:获取id类
 * @author tzw
 * CreateTime 18:47 2019/2/10
 **/
public class IDUtil {

    public static String getUUID(){
        String UUID = String.valueOf(java.util.UUID.randomUUID());
        return UUID.replace("-","");
    }

}
