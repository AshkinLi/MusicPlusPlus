package com.ashkin.musicplusplus.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.ashkin.musicplusplus.R;
import com.ashkin.musicplusplus.fragment.BaseFragment.OnFragmentInteractionListener;
import com.ashkin.musicplusplus.fragment.FavouriteFragment;
import com.ashkin.musicplusplus.fragment.MusicFragment;
import com.ashkin.musicplusplus.fragment.RecentFragment;
import com.ashkin.musicplusplus.fragment.SettingsFragment;
import com.ashkin.musicplusplus.fragment.ShareFragment;
import com.ashkin.musicplusplus.utils.LogUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements OnNavigationItemSelectedListener, OnFragmentInteractionListener {

    public static final String TAG = "MainActivity";

    private Toolbar toolbar;

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        showFragment(R.id.nav_music);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showFragment(R.id.nav_music);
    }

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
                    shareFragment = shareFragment.newInstance();
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
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//            drawer.openDrawer(GravityCompat.START);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

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

    @Override
    public void onFragmentInteraction(String msg) {
        Snackbar.make(findViewById(R.id.playbar_id), msg, Snackbar.LENGTH_LONG).show();
    }
}
