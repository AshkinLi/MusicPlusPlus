package com.ashkin.musicplusplus.utils;

import android.database.Cursor;

import com.ashkin.musicplusplus.app.Config;
import com.ashkin.musicplusplus.bean.MusicItem;

public class MusicItemUtil {

    /**
     * 将 Cursor 对象转化成 MusicItem
     */
    public static MusicItem getMusicItem(Cursor cursor) {
        MusicItem item = null;

        if (null != cursor) {
            item = new MusicItem();
            item.setData(cursor.getString(cursor.getColumnIndex(Config.MUSIC_DATA)));
            item.setAlbum(cursor.getString(cursor.getColumnIndex(Config.MUSIC_ALBUM)));
            item.setArtist(cursor.getString(cursor.getColumnIndex(Config.MUSIC_ARTIST)));
            item.setTitle(cursor.getString(cursor.getColumnIndex(Config.MUSIC_TITLE)));
            item.setDuration(cursor.getInt(cursor.getColumnIndex(Config.MUSIC_DURATION)));
        }

        return item;
    }

}
