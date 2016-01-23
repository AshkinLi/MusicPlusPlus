package com.ashkin.musicplusplus.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    public void onFragmentInteraction(URL url) {

    }

    @Override
    public void onFragmentInteraction(MusicItem item) {

    }
}
