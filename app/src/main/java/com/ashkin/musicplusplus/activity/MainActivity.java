package com.ashkin.musicplusplus.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
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
import com.ashkin.musicplusplus.bean.MusicItem;
import com.ashkin.musicplusplus.fragment.FavouriteFragment;
import com.ashkin.musicplusplus.fragment.MusicFragment;
import com.ashkin.musicplusplus.fragment.RecentFragment;
import com.ashkin.musicplusplus.fragment.SettingsFragment;
import com.ashkin.musicplusplus.fragment.ShareFragment;
import com.ashkin.musicplusplus.utils.LogUtil;
import com.ashkin.musicplusplus.utils.MusicUtil;

import java.util.List;

public class MainActivity extends BaseActivity
        implements OnNavigationItemSelectedListener, View.OnClickListener {

    public static final String TAG = "MainActivity";

    private Toolbar toolbar;
    private TextView mTitle;
    private TextView mArtist;
    private ImageView mPlay;
    private ImageView mIcon;

    private Cursor mCursor = null;
    private int position = 0;
    private MusicItem mItem = null;

    private MusicFragment musicFragment;
    private FavouriteFragment favouriteFragment;
    private RecentFragment recentFragment;
    private SettingsFragment settingsFragment;
    private ShareFragment shareFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MusicUtil.initialization(this);

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
        // Handle navigation view item clicks here.
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

    public MusicItem getItem() {
        return mItem;
    }

    public void setItem(MusicItem mItem) {
        this.mItem = mItem;
    }

    public void updatePlaybar(MusicItem item) {
        mTitle.setText(item.getTitle());
        mArtist.setText(item.getArtist());

        if (MusicUtil.getInstance().isPlaying()) {
            mPlay.setImageResource(R.drawable.ic_music_play);
        } else {
            mPlay.setImageResource(R.drawable.ic_music_pause);
        }
    }

    @Override
    public void onFragmentInteraction(MusicItem item) {
        updatePlaybar(item);
        MusicUtil.getInstance().start(item.getData());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playbar_prev_id:
                // TODO:上一曲
                LogUtil.i(TAG, "239: 上一曲");

                int count = mCursor.getCount();
                if (getPosition() < 0) {
                    setPosition(mCursor.getCount() - 1);

                    mCursor.moveToPosition(getPosition());
                    MusicUtil.getInstance().start();
                } else {
                    setPosition(getPosition() - 1);

                    mCursor.moveToPosition(getPosition());
                    MusicUtil.getInstance().start();
                }
                break;
            case R.id.playbar_next_id:
                // TODO:下一曲
                LogUtil.i(TAG, "243: 下一曲");
                break;
            case R.id.playbar_play_id:
                // TODO: 播放、暂停
                if (MusicUtil.getInstance().isPlaying()) {
                    LogUtil.i(TAG, "Muisc is Playing");
                    MusicUtil.getInstance().pause();
                    mPlay.setImageResource(R.drawable.ic_music_play);
                } else {
                    LogUtil.i(TAG, "Music is pause");
                    MusicUtil.getInstance().start();
                    mPlay.setImageResource(R.drawable.ic_music_pause);
                }
                break;
            default:
                break;
        }
    }
}
