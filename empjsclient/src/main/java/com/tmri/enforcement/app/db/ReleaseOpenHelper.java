package com.tmri.enforcement.app.db;

import android.content.Context;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.tmri.enforcement.app.gen.DaoMaster;
import com.tmri.enforcement.app.gen.VideoInfoDao;

import org.greenrobot.greendao.database.Database;

public class ReleaseOpenHelper extends DaoMaster.OpenHelper {
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db,new MigrationHelper.ReCreateAllTableListener(){
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        },VideoInfoDao.class);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
