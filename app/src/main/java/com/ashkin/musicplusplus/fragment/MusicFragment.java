package com.ashkin.musicplusplus.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashkin.musicplusplus.R;

/**
 * 音乐列表 Fragment
 */
public class MusicFragment extends BaseFragment {
    public static final String TAG = "MusicFragment";

    private static final String[] title = {"音乐", "歌手", "专辑"};
    private static final int MUSIC = 0;
    private static final int ARTIST = 1;
    private static final int ALBUM = 2;

    public static final int NUM_ITEMS = 3;

    private OnFragmentInteractionListener mListener;

    private MusicListFragment musicListFragment;
    private ArtistListFragment artistListFragment;
    private AlbumListFragment albumListFragment;

    public MusicFragment() {
        // Required empty public constructor
    }

    public static MusicFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        initFragment();

        initView(view);

        return view;
    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        Log.i(TAG, "MusicFragment onResume");
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        Log.i(TAG, "MusicFragment onPause");
//    }

    private void initFragment() {
        musicListFragment = new MusicListFragment();
        artistListFragment = new ArtistListFragment();
        albumListFragment = new AlbumListFragment();
    }

    private void initView(View view) {
        TabLayout mTabLayout = (TabLayout) view.findViewById(R.id.music_tablayout_id);
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.music_viewpager_id);
        mViewPager.setAdapter(new MusicPagerAdapter(getChildFragmentManager()));
        // getSup
        mTabLayout.setupWithViewPager(mViewPager);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseFragment.OnFragmentInteractionListener) {
            mListener = (BaseFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    class MusicPagerAdapter extends FragmentPagerAdapter {

        public MusicPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case MUSIC:
                    return title[MUSIC];
                case ARTIST:
                    return title[ARTIST];
                case ALBUM:
                    return title[ALBUM];
            }
            return super.getPageTitle(position);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case MUSIC:
                    if (null == musicListFragment) {
                        musicListFragment = new MusicListFragment();
                    }
                    return musicListFragment;
                case ARTIST:
                    if (null == artistListFragment) {
                        artistListFragment = new ArtistListFragment();
                    }
                    return artistListFragment;
                case ALBUM:
                    if (null == albumListFragment) {
                        albumListFragment = new AlbumListFragment();
                    }
                    return albumListFragment;
                default:
                    Log.i("MusicFragment", "return null");
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }
}
