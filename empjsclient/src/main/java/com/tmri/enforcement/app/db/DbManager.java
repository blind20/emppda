package com.tmri.enforcement.app.db;

import android.content.Context;

import com.tmri.enforcement.app.gen.DaoMaster;
import com.tmri.enforcement.app.gen.DaoSession;
import com.tmri.enforcement.app.gen.VideoInfoDao;

import org.greenrobot.greendao.database.Database;

public class DbManager {

    private DaoSession mDaoSession=null;
    private VideoInfoDao mVideoInfoDao=null;

    public DbManager() {}

    public DbManager init(Context context) {
        initDao(context);
        return this;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context,"video.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mVideoInfoDao = mDaoSession.getVideoInfoDao();
    }

    private static final class Holder{
        private static final DbManager INSTANCE = new DbManager();
    }

    public static DbManager getInstance(){
        return Holder.INSTANCE;
    }

    public VideoInfoDao getVideoInfoDao() {
        return mVideoInfoDao;
    }
}
