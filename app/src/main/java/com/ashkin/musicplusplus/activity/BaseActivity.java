package com.ashkin.musicplusplus.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ashkin.musicplusplus.bean.MusicItem;
import com.ashkin.musicplusplus.fragment.BaseFragment;

import java.net.URL;

public class BaseActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onFragmentInteraction(String str) {

    }

    @Override
    public void onFragmentInteraction(Cursor cursor, int position) {

    }
}
