package com.ashkin.musicplusplus.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashkin.musicplusplus.R;
import com.ashkin.musicplusplus.app.Config;
import com.ashkin.musicplusplus.fragment.FavouriteFragment;
import com.ashkin.musicplusplus.fragment.MusicFragment;
import com.ashkin.musicplusplus.fragment.RecentFragment;
import com.ashkin.musicplusplus.fragment.SettingsFragment;
import com.ashkin.musicplusplus.fragment.ShareFragment;
import com.ashkin.musicplusplus.service.MusicService;
import com.ashkin.musicplusplus.utils.CursorUtil;
import com.ashkin.musicplusplus.utils.LogUtil;
import com.ashkin.musicplusplus.utils.MusicUtil;

import java.util.List;

public class MainActivity extends BaseActivity {

    public static final String TAG = "MainActivity";

    private Toolbar toolbar;
    private TextView mTitle;
    private TextView mArtist;
    private ImageView mPlay;
    private ImageView mIcon;

    private Cursor mCursor = null;
    private int position = 0;
    private boolean isPlaying = false;

    private MusicFragment musicFragment;
    private FavouriteFragment favouriteFragment;
    private RecentFragment recentFragment;
    private SettingsFragment settingsFragment;
    private ShareFragment shareFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initPlaybar();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        showFragment(R.id.nav_music);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showFragment(R.id.nav_music);
    }

    /**
     * 显示 Fragment
     * show Fragment
     *
     * @param id show Fragment with id
     */
    private void showFragment(int id) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                transaction.hide(fragment);
            }
        }

        switch (id) {
            case R.id.nav_music:
                if (null == musicFragment) {
                    musicFragment = MusicFragment.newInstance();
                    transaction.add(R.id.main_id, musicFragment);
                    Log.i(TAG, "musicFragment null");
                } else {
                    transaction.show(musicFragment);
                    Log.i(TAG, "musicFragment not null");
                }
                break;
            case R.id.nav_favourite:
                if (null == favouriteFragment) {
                    favouriteFragment = FavouriteFragment.newInstance();
                    transaction.add(R.id.main_id, favouriteFragment);
                } else {
                    transaction.show(favouriteFragment);
                }
                break;
            case R.id.nav_recent:
                if (null == recentFragment) {
                    recentFragment = RecentFragment.newInstance();
                    transaction.add(R.id.main_id, recentFragment);
                } else {
                    transaction.show(recentFragment);
                }
                break;
            case R.id.nav_settings:
                if (null == settingsFragment) {
                    settingsFragment = SettingsFragment.newInstance();
                    transaction.add(R.id.main_id, settingsFragment);
                } else {
                    transaction.show(settingsFragment);
                }
                break;
            case R.id.nav_share:
                if (null == shareFragment) {
                    shareFragment = ShareFragment.newInstance();
                    transaction.add(R.id.main_id, shareFragment);
                } else {
                    transaction.show(shareFragment);
                }
                break;
            default:
                break;
        }

        transaction.commit();
    }

    /**
     * 初始化 Toolbar
     */
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.menu_music);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_nav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    /**
     * 初始化 Playbar 的高度
     */
    private void initPlaybar() {
        mTitle = (TextView) findViewById(R.id.playbar_title_id);
        mArtist = (TextView) findViewById(R.id.playbar_artist_id);
        mIcon = (ImageView) findViewById(R.id.playbar_icon_id);
        mPlay = (ImageButton) findViewById(R.id.playbar_play_id);

        ImageButton mPrev = (ImageButton) findViewById(R.id.playbar_prev_id);
        ImageButton mNext = (ImageButton) findViewById(R.id.playbar_next_id);

        mPlay.setOnClickListener(this);
        mPrev.setOnClickListener(this);
        mNext.setOnClickListener(this);
    }

    /**
     * 返回 Playbar 的高度
     *
     * @return Playbar 的高度，Playbar height
     */
    public int getPlaybarHeight() {
        return findViewById(R.id.playbar_id).getHeight();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        super.onNavigationItemSelected(item);

        switch (item.getItemId()) {
            // 音乐列表界面
            case R.id.nav_music:
                toolbar.setTitle(R.string.menu_music);
                showFragment(R.id.nav_music);
                break;
            // 音乐收藏界面
            case R.id.nav_favourite:
                toolbar.setTitle(R.string.menu_favourite);
                showFragment(R.id.nav_favourite);
                break;
            // 最近播放界面
            case R.id.nav_recent:
                toolbar.setTitle(R.string.menu_recent);
                showFragment(R.id.nav_recent);
                break;
            // 设置界面
            case R.id.nav_settings:
                toolbar.setTitle(R.string.menu_settings);
                showFragment(R.id.nav_settings);
                break;
            // 分享界面
            case R.id.nav_share:
                toolbar.setTitle(R.string.menu_share);
                showFragment(R.id.nav_share);
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 返回当前设置的 Cursor 对象
     *
     * @return mCursor
     */
    public Cursor getCursor() {
        return mCursor;
    }

    /**
     * 设置当前的 Coursor 对象
     *
     * @param mCursor Cursor object on ListFragment
     */
    public void setCursor(Cursor mCursor) {
        this.mCursor = mCursor;
    }

    /**
     * 返回当前的歌曲编号
     *
     * @return position
     */
    public int getPosition() {
        return position;
    }

    /**
     * 设置当前的歌曲编号
     *
     * @param position 当前的歌曲编号
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * 更新 Playbar 的数据
     */
    public void updatePlaybar() {
        mTitle.setText(mCursor.getString(mCursor.getColumnIndex(Config.MUSIC_TITLE)));
        mArtist.setText(mCursor.getString(mCursor.getColumnIndex(Config.MUSIC_ARTIST)));

        if (isPlaying) {
            mPlay.setImageResource(R.drawable.ic_music_pause);
        } else {
            mPlay.setImageResource(R.drawable.ic_music_play);
        }
    }

    /**
     * Fragment 点击时的回调
     *
     * @param cursor   结果集
     * @param position 当前选择的编号
     */
    @Override
    public void onFragmentInteraction(Cursor cursor, int position) {
        super.onFragmentInteraction(cursor, position);

        setCursor(cursor);
        setPosition(position);
        cursor.moveToPosition(position);

        isPlaying = true;

        updatePlaybar();

        sendMusicServiceAction(Config.MUSIC_ACTION_START, 0);
    }

    /**
     * 往 MusicService 发送信息
     *
     * @param action 操作
     * @param msec   播放的开始时间
     */
    private void sendMusicServiceAction(String action, int msec) {
        Intent intent = new Intent(this, MusicService.class);
        switch (action) {
            // 播放音乐
            case Config.MUSIC_ACTION_START:
                intent.putExtra(Config.MUSIC_ACTION, Config.MUSIC_ACTION_START);
                intent.putExtra(Config.MUSIC_DATA, CursorUtil.getData(getCursor(), getPosition()));
                break;
            // 从 msec 处播放音乐
            case Config.MUSIC_ACTION_START_WITH_MSEC:
                intent.putExtra(Config.MUSIC_ACTION, Config.MUSIC_ACTION_START);
                intent.putExtra(Config.MUSIC_DATA, CursorUtil.getData(getCursor(), getPosition()));
                intent.putExtra(Config.MUSIC_MSEC, msec);
                break;
            // 恢复播放
            case Config.MUSIC_ACTION_RESUME:
                intent.putExtra(Config.MUSIC_ACTION, Config.MUSIC_ACTION_RESUME);
                break;
            // 暂停播放
            case Config.MUSIC_ACTION_PAUSE:
                intent.putExtra(Config.MUSIC_ACTION, Config.MUSIC_ACTION_PAUSE);
            default:
                break;
        }
        startService(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playbar_prev_id:
                LogUtil.i(TAG, "onClick: 上一曲");

                isPlaying = true;

                int prev = getPosition();

                if (prev == 0) {
                    setPosition(getCursor().getCount() - 1);

                    getCursor().moveToPosition(getPosition());

                    updatePlaybar();
                } else {
                    setPosition(getPosition() - 1);

                    getCursor().moveToPosition(getPosition());

                    updatePlaybar();
                }

                sendMusicServiceAction(Config.MUSIC_ACTION_START, 0);

                break;
            case R.id.playbar_next_id:
                LogUtil.i(TAG, "onClick: 下一曲");

                int next = getPosition();

                isPlaying = true;

                if (next == getCursor().getPosition() - 1) {
                    setPosition(0);

                    getCursor().moveToPosition(getPosition());

                    updatePlaybar();
                } else {
                    setPosition(getPosition() + 1);

                    getCursor().moveToPosition(getPosition());

                    updatePlaybar();
                }

                sendMusicServiceAction(Config.MUSIC_ACTION_START, 0);

                break;
            case R.id.playbar_play_id:
                if (MusicUtil.getInstance().isPlaying()) {
                    LogUtil.i(TAG, "Muisc is Playing");

                    isPlaying = false;
                    updatePlaybar();

                    sendMusicServiceAction(Config.MUSIC_ACTION_PAUSE, 0);
                } else {
                    LogUtil.i(TAG, "Music is pause");

                    isPlaying = true;
                    updatePlaybar();

                    sendMusicServiceAction(Config.MUSIC_ACTION_RESUME, 0);
                }
                break;
            default:
                break;
        }
    }
}
