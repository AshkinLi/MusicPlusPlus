package com.ashkin.musicplusplus.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashkin.musicplusplus.R;
import com.ashkin.musicplusplus.app.Config;
import com.ashkin.musicplusplus.utils.StringUtil;

/**
 * 音乐列表适配器
 */
public class CursorMusicAdapter extends RecyclerView.Adapter<CursorMusicAdapter.MusicItemViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private Cursor mCursor;

    public CursorMusicAdapter(Context context, Cursor cursor) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mCursor = cursor;
    }

    @Override
    public MusicItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MusicItemViewHolder(mLayoutInflater.inflate(R.layout.item_music, parent, false));
    }

    @Override
    public void onBindViewHolder(MusicItemViewHolder holder, int position) {
        mCursor.moveToPosition(position);

        holder.mNoTextView.setText(position + 1 + "");
        holder.mTitleTextView.setText(mCursor.getString(mCursor.getColumnIndex(Config.MUSIC_TITLE)));
        holder.mArtistTextView.setText(mCursor.getString(mCursor.getColumnIndex(Config.MUSIC_ARTIST)));
        holder.mDurationTextView.setText(StringUtil.durationToData(mCursor.getInt(mCursor.getColumnIndex(Config.MUSIC_DURATION))));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public static class MusicItemViewHolder extends RecyclerView.ViewHolder {

        TextView mNoTextView;
        TextView mTitleTextView;
        TextView mArtistTextView;
        TextView mDurationTextView;

        public MusicItemViewHolder(View itemView) {
            super(itemView);

            mNoTextView = (TextView) itemView.findViewById(R.id.music_no_id);
            mTitleTextView = (TextView) itemView.findViewById(R.id.music_title_id);
            mArtistTextView = (TextView) itemView.findViewById(R.id.music_artist_id);
            mDurationTextView = (TextView) itemView.findViewById(R.id.music_duration_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "点击了！！", Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }
}

