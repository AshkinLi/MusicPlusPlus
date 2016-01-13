package com.ashkin.musicplusplus.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ashkin.musicplusplus.R;
import com.ashkin.musicplusplus.app.Config;
import com.ashkin.musicplusplus.bean.MusicItem;

/**
 * 音乐列表适配器
 */
public class MusicListItemAdapter extends BaseAdapter {

    private Context mContext;
    private Cursor mCursor;

    public MusicListItemAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public int getCount() {
        return mCursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        MusicItem item = new MusicItem();
        item.setData(mCursor.getString(mCursor.getColumnIndex(Config.MUSIC_DATA)));
        item.setTitle(mCursor.getString(mCursor.getColumnIndex(Config.MUSIC_TITLE)));
        item.setArtist(mCursor.getString(mCursor.getColumnIndex(Config.MUSIC_TITLE)));
        item.setArtist(mContext.getString(mCursor.getColumnIndex(Config.MUSIC_ARTIST)));
        item.setDuration(mCursor.getInt(mCursor.getColumnIndex(Config.MUSIC_DURATION)));
        return item;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        mCursor.moveToPosition(position);

        ViewHolder holder = null;

        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_music, parent, false);

            holder = new ViewHolder();

            holder.mNoTextView = (TextView) convertView.findViewById(R.id.music_no_id);
            holder.mTitleTextView = (TextView) convertView.findViewById(R.id.music_title_id);
            holder.mArtistTextView = (TextView) convertView.findViewById(R.id.music_artist_id);
            holder.mDurationTextView = (TextView) convertView.findViewById(R.id.music_duration_id);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mNoTextView.setText(position + 1 + "");
        holder.mTitleTextView.setText(mCursor.getString(mCursor.getColumnIndex(Config.MUSIC_TITLE)));
        holder.mArtistTextView.setText(mCursor.getString(mCursor.getColumnIndex(Config.MUSIC_ARTIST)));
        holder.mDurationTextView.setText(mCursor.getString(mCursor.getColumnIndex(Config.MUSIC_DURATION)));

        return convertView;
    }

    private static class ViewHolder {
        TextView mNoTextView;
        TextView mTitleTextView;
        TextView mArtistTextView;
        TextView mDurationTextView;
    }
}
