package com.ashkin.musicplusplus.fragment;

import android.support.v4.app.Fragment;

import com.ashkin.musicplusplus.bean.MusicItem;

import java.net.URL;

/**
 *
 */
public class BaseFragment extends Fragment {
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String str);

        void onFragmentInteraction(URL url);

        void onFragmentInteraction(MusicItem item);
    }
}
