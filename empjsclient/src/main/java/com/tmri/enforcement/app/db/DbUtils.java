package com.tmri.enforcement.app.db;

import java.util.List;

public class DbUtils {

    public void insertVideo(VideoInfo videoInfo){
        DbManager.getInstance().getVideoInfoDao().insertOrReplace(videoInfo);
    }

    public void updateVideo(VideoInfo videoInfo){
        DbManager.getInstance().getVideoInfoDao().update(videoInfo);
    }

    public void deleteVideo(VideoInfo videoInfo){
        DbManager.getInstance().getVideoInfoDao().deleteByKey(videoInfo.getLsh());
    }

    public void deleteVideo(String lsh){
        DbManager.getInstance().getVideoInfoDao().deleteByKey(lsh);
    }

    public void deleteAllVideo(){
        DbManager.getInstance().getVideoInfoDao().deleteAll();
    }

    public VideoInfo load(VideoInfo videoInfo){
        return DbManager.getInstance().getVideoInfoDao().load(videoInfo.getLsh());
    }

    public VideoInfo load(String lsh){
        return DbManager.getInstance().getVideoInfoDao().load(lsh);
    }

    public List<VideoInfo> loadAll(){
        return DbManager.getInstance().getVideoInfoDao().loadAll();
    }
}
