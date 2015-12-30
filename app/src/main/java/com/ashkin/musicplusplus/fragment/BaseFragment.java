package com.ashkin.musicplusplus.fragment;

import android.net.Uri;
import android.support.v4.app.Fragment;

/**
 *
 */
public class BaseFragment extends Fragment {
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
