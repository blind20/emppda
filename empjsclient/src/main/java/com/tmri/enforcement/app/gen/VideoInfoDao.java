package com.tmri.enforcement.app.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.tmri.enforcement.app.db.VideoInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "tm_videoinfo".
*/
public class VideoInfoDao extends AbstractDao<VideoInfo, String> {

    public static final String TABLENAME = "tm_videoinfo";

    /**
     * Properties of entity VideoInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Lsh = new Property(0, String.class, "lsh", true, "LSH");
        public final static Property Hphm = new Property(1, String.class, "hphm", false, "HPHM");
        public final static Property Hpzl = new Property(2, String.class, "hpzl", false, "HPZL");
        public final static Property Clsbdh = new Property(3, String.class, "clsbdh", false, "CLSBDH");
        public final static Property Cyqxh = new Property(4, String.class, "cyqxh", false, "CYQXH");
        public final static Property Cyqtd = new Property(5, String.class, "cyqtd", false, "CYQTD");
        public final static Property Video_begin = new Property(6, java.util.Date.class, "video_begin", false, "VIDEO_BEGIN");
        public final static Property Video_end = new Property(7, java.util.Date.class, "video_end", false, "VIDEO_END");
        public final static Property Jycs = new Property(8, int.class, "jycs", false, "JYCS");
        public final static Property Video_name = new Property(9, String.class, "video_name", false, "VIDEO_NAME");
        public final static Property Video_size = new Property(10, String.class, "video_size", false, "VIDEO_SIZE");
        public final static Property Sfzmhm = new Property(11, String.class, "sfzmhm", false, "SFZMHM");
        public final static Property Ip = new Property(12, String.class, "ip", false, "IP");
        public final static Property Port = new Property(13, String.class, "port", false, "PORT");
        public final static Property Uri = new Property(14, String.class, "uri", false, "URI");
    }


    public VideoInfoDao(DaoConfig config) {
        super(config);
    }
    
    public VideoInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"tm_videoinfo\" (" + //
                "\"LSH\" TEXT PRIMARY KEY NOT NULL ," + // 0: lsh
                "\"HPHM\" TEXT," + // 1: hphm
                "\"HPZL\" TEXT," + // 2: hpzl
                "\"CLSBDH\" TEXT," + // 3: clsbdh
                "\"CYQXH\" TEXT," + // 4: cyqxh
                "\"CYQTD\" TEXT," + // 5: cyqtd
                "\"VIDEO_BEGIN\" INTEGER," + // 6: video_begin
                "\"VIDEO_END\" INTEGER," + // 7: video_end
                "\"JYCS\" INTEGER NOT NULL ," + // 8: jycs
                "\"VIDEO_NAME\" TEXT," + // 9: video_name
                "\"VIDEO_SIZE\" TEXT," + // 10: video_size
                "\"SFZMHM\" TEXT," + // 11: sfzmhm
                "\"IP\" TEXT," + // 12: ip
                "\"PORT\" TEXT," + // 13: port
                "\"URI\" TEXT);"); // 14: uri
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"tm_videoinfo\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VideoInfo entity) {
        stmt.clearBindings();
 
        String lsh = entity.getLsh();
        if (lsh != null) {
            stmt.bindString(1, lsh);
        }
 
        String hphm = entity.getHphm();
        if (hphm != null) {
            stmt.bindString(2, hphm);
        }
 
        String hpzl = entity.getHpzl();
        if (hpzl != null) {
            stmt.bindString(3, hpzl);
        }
 
        String clsbdh = entity.getClsbdh();
        if (clsbdh != null) {
            stmt.bindString(4, clsbdh);
        }
 
        String cyqxh = entity.getCyqxh();
        if (cyqxh != null) {
            stmt.bindString(5, cyqxh);
        }
 
        String cyqtd = entity.getCyqtd();
        if (cyqtd != null) {
            stmt.bindString(6, cyqtd);
        }
 
        java.util.Date video_begin = entity.getVideo_begin();
        if (video_begin != null) {
            stmt.bindLong(7, video_begin.getTime());
        }
 
        java.util.Date video_end = entity.getVideo_end();
        if (video_end != null) {
            stmt.bindLong(8, video_end.getTime());
        }
        stmt.bindLong(9, entity.getJycs());
 
        String video_name = entity.getVideo_name();
        if (video_name != null) {
            stmt.bindString(10, video_name);
        }
 
        String video_size = entity.getVideo_size();
        if (video_size != null) {
            stmt.bindString(11, video_size);
        }
 
        String sfzmhm = entity.getSfzmhm();
        if (sfzmhm != null) {
            stmt.bindString(12, sfzmhm);
        }
 
        String ip = entity.getIp();
        if (ip != null) {
            stmt.bindString(13, ip);
        }
 
        String port = entity.getPort();
        if (port != null) {
            stmt.bindString(14, port);
        }
 
        String uri = entity.getUri();
        if (uri != null) {
            stmt.bindString(15, uri);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VideoInfo entity) {
        stmt.clearBindings();
 
        String lsh = entity.getLsh();
        if (lsh != null) {
            stmt.bindString(1, lsh);
        }
 
        String hphm = entity.getHphm();
        if (hphm != null) {
            stmt.bindString(2, hphm);
        }
 
        String hpzl = entity.getHpzl();
        if (hpzl != null) {
            stmt.bindString(3, hpzl);
        }
 
        String clsbdh = entity.getClsbdh();
        if (clsbdh != null) {
            stmt.bindString(4, clsbdh);
        }
 
        String cyqxh = entity.getCyqxh();
        if (cyqxh != null) {
            stmt.bindString(5, cyqxh);
        }
 
        String cyqtd = entity.getCyqtd();
        if (cyqtd != null) {
            stmt.bindString(6, cyqtd);
        }
 
        java.util.Date video_begin = entity.getVideo_begin();
        if (video_begin != null) {
            stmt.bindLong(7, video_begin.getTime());
        }
 
        java.util.Date video_end = entity.getVideo_end();
        if (video_end != null) {
            stmt.bindLong(8, video_end.getTime());
        }
        stmt.bindLong(9, entity.getJycs());
 
        String video_name = entity.getVideo_name();
        if (video_name != null) {
            stmt.bindString(10, video_name);
        }
 
        String video_size = entity.getVideo_size();
        if (video_size != null) {
            stmt.bindString(11, video_size);
        }
 
        String sfzmhm = entity.getSfzmhm();
        if (sfzmhm != null) {
            stmt.bindString(12, sfzmhm);
        }
 
        String ip = entity.getIp();
        if (ip != null) {
            stmt.bindString(13, ip);
        }
 
        String port = entity.getPort();
        if (port != null) {
            stmt.bindString(14, port);
        }
 
        String uri = entity.getUri();
        if (uri != null) {
            stmt.bindString(15, uri);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public VideoInfo readEntity(Cursor cursor, int offset) {
        VideoInfo entity = new VideoInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // lsh
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // hphm
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // hpzl
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // clsbdh
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // cyqxh
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // cyqtd
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)), // video_begin
            cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)), // video_end
            cursor.getInt(offset + 8), // jycs
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // video_name
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // video_size
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // sfzmhm
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // ip
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // port
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14) // uri
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VideoInfo entity, int offset) {
        entity.setLsh(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setHphm(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setHpzl(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setClsbdh(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCyqxh(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCyqtd(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setVideo_begin(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
        entity.setVideo_end(cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)));
        entity.setJycs(cursor.getInt(offset + 8));
        entity.setVideo_name(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setVideo_size(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setSfzmhm(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setIp(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setPort(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setUri(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
     }
    
    @Override
    protected final String updateKeyAfterInsert(VideoInfo entity, long rowId) {
        return entity.getLsh();
    }
    
    @Override
    public String getKey(VideoInfo entity) {
        if(entity != null) {
            return entity.getLsh();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VideoInfo entity) {
        return entity.getLsh() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
