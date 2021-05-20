package com.tmri.enforcement.app.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "tm_videoinfo")
public class VideoInfo {
    @Id
    private String lsh;
    private String hphm;
    private String hpzl;
    private String clsbdh;
    private String cyqxh;
    private String cyqtd;
    private Date video_begin;
    private Date video_end;
    private int jycs;
    private String video_name;
    private String video_size;
    private String sfzmhm;
    private String ip;
    private String port;
    private String uri;
    @Generated(hash = 438827912)
    public VideoInfo(String lsh, String hphm, String hpzl, String clsbdh,
            String cyqxh, String cyqtd, Date video_begin, Date video_end, int jycs,
            String video_name, String video_size, String sfzmhm, String ip,
            String port, String uri) {
        this.lsh = lsh;
        this.hphm = hphm;
        this.hpzl = hpzl;
        this.clsbdh = clsbdh;
        this.cyqxh = cyqxh;
        this.cyqtd = cyqtd;
        this.video_begin = video_begin;
        this.video_end = video_end;
        this.jycs = jycs;
        this.video_name = video_name;
        this.video_size = video_size;
        this.sfzmhm = sfzmhm;
        this.ip = ip;
        this.port = port;
        this.uri = uri;
    }
    @Generated(hash = 296402066)
    public VideoInfo() {
    }
    public String getLsh() {
        return this.lsh;
    }
    public void setLsh(String lsh) {
        this.lsh = lsh;
    }
    public String getHphm() {
        return this.hphm;
    }
    public void setHphm(String hphm) {
        this.hphm = hphm;
    }
    public String getHpzl() {
        return this.hpzl;
    }
    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }
    public String getClsbdh() {
        return this.clsbdh;
    }
    public void setClsbdh(String clsbdh) {
        this.clsbdh = clsbdh;
    }
    public String getCyqxh() {
        return this.cyqxh;
    }
    public void setCyqxh(String cyqxh) {
        this.cyqxh = cyqxh;
    }
    public String getCyqtd() {
        return this.cyqtd;
    }
    public void setCyqtd(String cyqtd) {
        this.cyqtd = cyqtd;
    }
    public Date getVideo_begin() {
        return this.video_begin;
    }
    public void setVideo_begin(Date video_begin) {
        this.video_begin = video_begin;
    }
    public Date getVideo_end() {
        return this.video_end;
    }
    public void setVideo_end(Date video_end) {
        this.video_end = video_end;
    }
    public int getJycs() {
        return this.jycs;
    }
    public void setJycs(int jycs) {
        this.jycs = jycs;
    }
    public String getVideo_name() {
        return this.video_name;
    }
    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }
    public String getVideo_size() {
        return this.video_size;
    }
    public void setVideo_size(String video_size) {
        this.video_size = video_size;
    }
    public String getSfzmhm() {
        return this.sfzmhm;
    }
    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }
    public String getIp() {
        return this.ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getPort() {
        return this.port;
    }
    public void setPort(String port) {
        this.port = port;
    }
    public String getUri() {
        return this.uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }


    @Override
    public String toString() {
        return "VideoInfo{" +
                "lsh='" + lsh + '\'' +
                ", hphm='" + hphm + '\'' +
                ", hpzl='" + hpzl + '\'' +
                ", clsbdh='" + clsbdh + '\'' +
                ", cyqxh='" + cyqxh + '\'' +
                ", cyqtd='" + cyqtd + '\'' +
                ", video_begin=" + video_begin +
                ", video_end=" + video_end +
                ", jycs=" + jycs +
                ", video_name='" + video_name + '\'' +
                ", video_size='" + video_size + '\'' +
                ", sfzmhm='" + sfzmhm + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
