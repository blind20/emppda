package com.tmri.enforcement.app.common;


public class Constants {

    /**
     * ①是否正式发行版本
     */
    public static final boolean IS_RELEASE = true; //正式版本
    public static final int FACTORY_MINGDU = 10;
    public static final int FACTORY_JINGSHENG = 20;
    public static final int FACTORY_HIK = 30;
    /**
     * ②该版本使用哪个厂家
     */
    public static final int FACTORY_ID = FACTORY_MINGDU;
    public static final String PREFS_FACTORY_ID = "PREFS_FACTORY_ID";
    //---------------------------------------------------------------------------------------
    public static final String PREFS_EMP_SERVICE_IP = "PREFS_EMP_SERVICE_IP";
    public static final String PREFS_EMP_SERVICE_PORT = "PREFS_EMP_SERVICE_PORT";
    public static final String PREFS_VIDEO_SYSTEM_IP = "PREFS_VIDEO_SYSTEM_IP";
    public static final String PREFS_VIDEO_SYSTEM_PORT = "PREFS_VIDEO_SYSTEM_PORT";

    public static final String SCHEMA_EMP_START = "start";
    public static final String SCHEMA_EMP_STOP = "stop";

    public static final String BASE_URL="http://192.168.1.1:8080/cms/";

    /**
     *自动开始/结束录像
     *
     */
    public static final boolean IS_AUTO_TRIGGER = true; //是否无需手动点击

}
