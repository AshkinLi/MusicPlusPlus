package com.ashkin.musicplusplus.utils;

import android.database.Cursor;

import com.ashkin.musicplusplus.app.Config;

/**
 * Cursor 的工具类
 */
public class CursorUtil {

    public static String getData(Cursor cursor, int position) {

        if (null != cursor) {
            cursor.moveToPosition(position);

            return cursor.getString(cursor.getColumnIndex(Config.MUSIC_DATA));
        }

        return null;
    }

    public static String getTitle(Cursor cursor, int position) {

        if (null != cursor) {
            cursor.moveToPosition(position);

            return cursor.getString(cursor.getColumnIndex(Config.MUSIC_TITLE));
        }

        return null;
    }

    public static String getArtist(Cursor cursor, int position) {

        if (null != cursor) {
            cursor.moveToPosition(position);

            return cursor.getString(cursor.getColumnIndex(Config.MUSIC_ARTIST));
        }

        return null;
    }

    public static String getAlbum(Cursor cursor, int position) {

        if (null != cursor) {
            cursor.moveToPosition(position);

            return cursor.getString(cursor.getColumnIndex(Config.MUSIC_ALBUM));
        }

        return null;
    }

    public static String getDuration(Cursor cursor, int position) {

        if (null != cursor) {
            cursor.moveToPosition(position);

            int duration = cursor.getInt(cursor.getColumnIndex(Config.MUSIC_DURATION));

            return StringUtil.durationToData(duration);
        }
        return null;
    }
}
