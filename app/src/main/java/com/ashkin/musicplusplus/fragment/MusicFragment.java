package com.ashkin.musicplusplus.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashkin.musicplusplus.R;

/**
 * 音乐列表 Fragment
 */
public class MusicFragment extends BaseFragment {
    private static final String[] title = {"音乐", "歌手", "专辑"};
    public static final int MUSIC = 0;
    public static final int ARTIST = 1;
    public static final int ALBUM = 2;

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

        initView(view);

        return view;
    }

    private void initView(View view) {
        TabLayout mTabLayout = (TabLayout) view.findViewById(R.id.music_tablayout_id);
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.music_viewpager_id);
        mViewPager.setAdapter(new MusicPagerAdapter(getActivity().getSupportFragmentManager()));
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
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }
}
