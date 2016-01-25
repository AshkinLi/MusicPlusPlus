package com.ashkin.musicplusplus.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashkin.musicplusplus.R;
import com.ashkin.musicplusplus.activity.MainActivity;
import com.ashkin.musicplusplus.adapter.CursorMusicAdapter;
import com.ashkin.musicplusplus.utils.LogUtil;

/**
 * 音乐列表
 */
public class MusicListFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = "MusicListFragment";

    private OnFragmentInteractionListener mListener;

    private MainActivity mContext = null;
    private RecyclerView mRecyclerView;

    public MusicListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getContext() instanceof MainActivity) {
            mContext = (MainActivity) getContext();
        }

        View view = inflater.inflate(R.layout.fragment_music_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.music_list_id);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        getLoaderManager().initLoader(0, null, this);
        return view;
    }

    public void onItemPressed(Cursor cursor, int position) {
        LogUtil.i(TAG, "onItemPressed: position = " + position);

        if (mListener != null) {
            mListener.onFragmentInteraction(cursor, position);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION
        };

        return new CursorLoader(mContext,
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                MediaStore.Audio.Media.TITLE);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mRecyclerView.setAdapter(new CursorMusicAdapter(this, data));
        mContext.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
