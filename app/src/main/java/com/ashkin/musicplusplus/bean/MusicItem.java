package com.ashkin.musicplusplus.bean;

/**
 * 音乐列表的实体类
 */
public class MusicItem {
    private String data;    // 音乐路径
    private String title;   // 音乐标题
    private String artist;  // 歌手
    private String album;   // 专辑
    private int duration;   // 歌曲时长

    public MusicItem() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
