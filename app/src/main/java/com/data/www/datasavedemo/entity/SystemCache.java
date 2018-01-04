package com.data.www.datasavedemo.entity;

/**
 * Created by qwh on 2017/11/22.
 */

public class SystemCache {
    public static boolean outgoing_call = false;
    public static String call_phone_num = "";
    //判断是否正在通话
    public static boolean isCalling = false;
    //控制循环是否结束
    public static boolean isFinish = true;
    public static int lasttime = 0;
//    public static DataInterface dataInterface = new
    public static void init(){
        outgoing_call = false;
        call_phone_num = "";
        isCalling = false;
        isFinish = true;
        lasttime = 0;
    }
}
