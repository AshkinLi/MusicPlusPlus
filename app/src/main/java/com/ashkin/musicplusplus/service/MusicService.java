package com.ashkin.musicplusplus.service;

import android.app.IntentService;
import android.content.Intent;

import com.ashkin.musicplusplus.app.Config;
import com.ashkin.musicplusplus.utils.LogUtil;
import com.ashkin.musicplusplus.utils.MusicUtil;

public class MusicService extends IntentService {

    public static final String TAG = "MusicService";

    public MusicService() {
        super("MusicService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        LogUtil.i(TAG, "onStartCommand");

        handlerAction(intent);
    }

    private void handlerAction(Intent intent) {

        String action = intent.getStringExtra(Config.MUSIC_ACTION);

        switch (action) {
            // 播放音乐
            case Config.MUSIC_ACTION_START:
                MusicUtil.getInstance().start(intent.getStringExtra(Config.MUSIC_DATA));
                break;
            case Config.MUSIC_ACTION_START_WITH_MSEC:
                MusicUtil.getInstance().start(intent.getStringExtra(Config.MUSIC_DATA), intent.getIntExtra(Config.MUSIC_MSEC, 0));
                break;
            case Config.MUSIC_ACTION_RESUME:
                MusicUtil.getInstance().start();
                break;
            case Config.MUSIC_ACTION_PAUSE:
                MusicUtil.getInstance().pause();
                break;
            default:
                break;
        }
    }
}
