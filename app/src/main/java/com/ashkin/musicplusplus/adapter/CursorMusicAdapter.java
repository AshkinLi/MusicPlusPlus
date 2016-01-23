package com.ashkin.musicplusplus.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashkin.musicplusplus.R;
import com.ashkin.musicplusplus.activity.MainActivity;
import com.ashkin.musicplusplus.app.Config;
import com.ashkin.musicplusplus.bean.MusicItem;
import com.ashkin.musicplusplus.utils.MusicItemUtil;
import com.ashkin.musicplusplus.utils.StringUtil;

/**
 * 音乐列表适配器
 */
public class CursorMusicAdapter extends RecyclerView.Adapter<CursorMusicAdapter.MusicItemViewHolder> {

    private static final int ITEM_NORMAL = 0;
    private static final int ITEM_FOOTER = 1;

    private MainActivity mContext;
    private final LayoutInflater mLayoutInflater;
    private Cursor mCursor;
    private MusicItemOnClickListener mListener;

    public CursorMusicAdapter(Context context, Cursor cursor) {
        mLayoutInflater = LayoutInflater.from(context);
        if (context instanceof MainActivity) {
            mContext = (MainActivity) context;
        }
        this.mCursor = cursor;
        mListener = new MusicItemOnClickListener(mContext, mCursor);
    }

    @Override
    public MusicItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_NORMAL:
                return new MusicItemViewHolder(mLayoutInflater.inflate(R.layout.item_music, parent, false), ITEM_NORMAL);
            case ITEM_FOOTER:
                View playbar = new View(mContext);
                playbar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mContext.getPlaybarHeight()));
                return new MusicItemViewHolder(playbar, ITEM_FOOTER);
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mCursor.getCount()) {
            return ITEM_FOOTER;
        } else {
            return ITEM_NORMAL;
        }
    }

    @Override
    public void onBindViewHolder(MusicItemViewHolder holder, final int position) {
        if (position < mCursor.getCount()) {
            mCursor.moveToPosition(position);

            holder.mNoTextView.setText(position + 1 + "");
            holder.mTitleTextView.setText(mCursor.getString(mCursor.getColumnIndex(Config.MUSIC_TITLE)));
            holder.mArtistTextView.setText(mCursor.getString(mCursor.getColumnIndex(Config.MUSIC_ARTIST)));
            holder.mDurationTextView.setText(StringUtil.durationToData(mCursor.getInt(mCursor.getColumnIndex(Config.MUSIC_DURATION))));

            holder.mItemView.setId(position);
            holder.mItemView.setOnClickListener(mListener);
        }
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount() + 1;
    }

    /**
     * 列表项点击监听器
     */
    public static class MusicItemOnClickListener implements View.OnClickListener {

        private MainActivity mContext;
        private Cursor mCursor;

        public MusicItemOnClickListener(Context context, Cursor cursor) {
            mContext = (MainActivity) context;
            mCursor = cursor;
        }

        @Override
        public void onClick(View v) {
            mCursor.moveToPosition(v.getId());

            mContext.setCursor(mCursor);
            mContext.setPosition(v.getId());
            mContext.onFragmentInteraction(MusicItemUtil.getMusicItem(mCursor));
        }
    }

    /**
     * 列表项 ViewHolder
     */
    public static class MusicItemViewHolder extends RecyclerView.ViewHolder {

        View mItemView;
        TextView mNoTextView;
        TextView mTitleTextView;
        TextView mArtistTextView;
        TextView mDurationTextView;

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public MusicItemViewHolder(View itemView, int type) {
            super(itemView);

            if (type == ITEM_NORMAL) {
                mItemView = itemView;
                mNoTextView = (TextView) itemView.findViewById(R.id.music_no_id);
                mTitleTextView = (TextView) itemView.findViewById(R.id.music_title_id);
                mArtistTextView = (TextView) itemView.findViewById(R.id.music_artist_id);
                mDurationTextView = (TextView) itemView.findViewById(R.id.music_duration_id);
            }

        }
    }
}

