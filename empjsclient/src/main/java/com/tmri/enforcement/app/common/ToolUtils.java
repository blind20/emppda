package com.tmri.enforcement.app.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.tmri.enforcement.app.db.VideoInfo;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ToolUtils {

    public static String getServerURL() {
        return "http://192.168.3.6:8088/cms";
    }

    /**
     * 获取当前本地apk的版本
     */
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取版本号名称
     */
    public static String getVersionName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }



    public static List<String> findRecentVideo(VideoInfo videoInfo){
        List<String> list = new ArrayList<>();
        String videoName = videoInfo.getVideo_name();
        int count = section(videoInfo.getVideo_end(), videoInfo.getVideo_begin());
        if (!TextUtils.isEmpty(videoName)) {
            if(count>1){

            }else {
                list.add(videoInfo.getUri());
            }
        }
        return null;
    }

    public List<String> findFileNameRecent(int count,String filepath){
        filepath = filepath.substring(0,filepath.lastIndexOf("/")+1);
        File todayFile = new File(filepath);
        if(todayFile.isDirectory()){
            File[] fileList = todayFile.listFiles();
        }
        return null;
    }


    public static String  getUrisFrom(List<String> list){
        String uris="";
        if (list == null||list.size()==0) {
            return null;
        }else {
            Iterator<String> it = list.iterator();
            while(it.hasNext()){
                uris = uris+";"+it.next();
            }
            return uris.substring(1,uris.length());
        }
    }

    public static List<String> getUrisFrom(VideoInfo videoInfo)  {
        String prex_uri;
        String uri = videoInfo.getUri();
        if (!TextUtils.isEmpty(uri)) {
            prex_uri = uri.substring(0, (uri.lastIndexOf("/"))+1);

            int count = section(videoInfo.getVideo_end(), videoInfo.getVideo_begin());
            List<String> list = new ArrayList<>();
            String videoName = videoInfo.getVideo_name();
            if (!TextUtils.isEmpty(videoName)) {
                videoName = videoName.substring(0, videoName.indexOf("."));
                if (count > 1) {
                    while (count-- > 0) {
                        list.add(prex_uri+videoName);
                        String temp = videoName;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                        Date date = null;
                        try {
                            date = sdf.parse(temp);
                            String name = sdf.format(new Date(date.getTime() - 1000 * 60 * 10));
                            videoName = name;
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                } else {
                    list.add(videoInfo.getUri());
                }
                return list;
            }
        }
        return null;
    }

    public static int section(Date end, Date begin) {
        if (begin == null || end == null) {
            return 0;
        }
        long interval = end.getTime() - begin.getTime();
        int section = (int) (interval / (1000 * 60 * 10));
        if (section > 0 && (interval - (1000 * 60 * 10) * section > 0)) {
            return section + 1;
        } else {
            return section;
        }
    }


}
