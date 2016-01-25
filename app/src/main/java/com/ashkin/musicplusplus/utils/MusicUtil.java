package com.ashkin.musicplusplus.utils;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.ashkin.musicplusplus.app.Config;

import java.io.IOException;

/**
 * 媒体播放工具类
 * Media Util
 */
public class MusicUtil implements MediaPlayer.OnPreparedListener {
    public static final String TAG = "MusicUtil";
    private static MusicUtil instance;

    private MediaPlayer mPlayer;
    private int mSec;

    private MusicUtil(final Context context) {
        this.mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                context.sendBroadcast(new Intent(Config.MUSIC_ACTION_COMPLETE));
            }
        });
    }

    public static void initialization(Context context) {
        if (null == instance) {
            instance = new MusicUtil(context);
        }
    }

    public static MusicUtil getInstance() {
        return instance;
    }

    /**
     * 恢复播放
     */
    public void start() {
        if (null != mPlayer && !mPlayer.isPlaying()) {
            LogUtil.i(TAG, "i : 恢复播放");
            mPlayer.start();
        }
    }

    /**
     * 播放音乐
     *
     * @param data 音乐的 data 数据
     */
    public void start(String data) {
        start(data, 0);
    }

    /**
     * 从 mSec 处开始播放音乐
     *
     * @param data 音乐的 data 数据
     * @param msec 音乐的毫秒数
     */
    public void start(String data, int msec) {
        try {
            this.mSec = msec;
            LogUtil.i(TAG, "i : 播放音乐, data = " + data + " mSec = " + msec);
            mPlayer.reset();
            mPlayer.setDataSource(data);
            mPlayer.prepareAsync();
        } catch (IOException e) {
            LogUtil.e(TAG, "e : IOException = " + e.getMessage());
        }
    }

    /**
     * 暂停音乐
     */
    public void pause() {
        if (null != mPlayer && mPlayer.isPlaying()) {
            LogUtil.i(TAG, "i : 暂停音乐");
            mPlayer.pause();
        }
    }

    /**
     * 释放 MediaPlayer 资源
     */
    public void release() {
        mPlayer.release();
        mPlayer = null;
    }

    /**
     * 音乐是否在播放
     *
     * @return 如果正在播放，返回 true
     */
    public boolean isPlaying() {
        return null != mPlayer && mPlayer.isPlaying();
    }

    /**
     * 返回当前播放的毫秒数
     *
     * @return current position
     */
    public int getCurrentPosition() {
        if (null != mPlayer && mPlayer.isPlaying()) {
            return mPlayer.getCurrentPosition();
        }

        return 0;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        LogUtil.i(TAG, "缓冲成功，开始播放");
        mp.seekTo(mSec);
        mp.start();
    }
}
