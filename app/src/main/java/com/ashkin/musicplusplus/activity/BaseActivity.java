package com.ashkin.musicplusplus.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.ashkin.musicplusplus.fragment.BaseFragment;
import com.ashkin.musicplusplus.utils.LogUtil;
import com.ashkin.musicplusplus.utils.MusicUtil;

public class BaseActivity extends AppCompatActivity implements
        BaseFragment.OnFragmentInteractionListener, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtil.i(getClass().getName(), "onCreate");

        MusicUtil.initialization(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        MusicUtil.getInstance().release();
    }

    @Override
    public void onFragmentInteraction(String str) {

    }

    @Override
    public void onFragmentInteraction(Cursor cursor, int position) {
        LogUtil.i(getClass().getName(), "onFragmentInteraction");
    }

    @Override
    public void onClick(View v) {
        LogUtil.i(getClass().getName(), "onClick");
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        LogUtil.i(getClass().getName(), "onNavigationItemSelected");
        return false;
    }
}
