package com.ashkin.musicplusplus.fragment;

import android.database.Cursor;
import android.support.v4.app.Fragment;

import com.ashkin.musicplusplus.bean.MusicItem;

import java.net.URL;

/**
 *
 */
public class BaseFragment extends Fragment {

    public void onItemPressed(Cursor cursor, int position) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String str);

        void onFragmentInteraction(Cursor cursor, int position);
    }
}
