package com.orangegangsters.github.swipyrefreshlayout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.orangegangsters.github.swiperefreshlayout.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oliviergoutay on 1/23/15.
 */
public class DummyListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mDummyStrings;

    public DummyListViewAdapter(Context mContext) {
        this.mContext = mContext;
        mDummyStrings = getDummyStrings();
    }

    @Override
    public int getCount() {
        return mDummyStrings.size();
    }

    @Override
    public Object getItem(int position) {
        return mDummyStrings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView==null){
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();

            convertView = inflater.inflate(R.layout.listview_cell, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mCellNumber = (TextView) convertView.findViewById(R.id.cell_number);
            viewHolder.mCellText = (TextView) convertView.findViewById(R.id.cell_text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mCellNumber.setText("" + position);
        viewHolder.mCellText.setText(mDummyStrings.get(position));

        return convertView;
    }

    static class ViewHolder {
        TextView mCellNumber;
        TextView mCellText;
    }

    public List<String> getDummyStrings() {
        List<String> dummyStrings = new ArrayList<>();

        dummyStrings.add("You want");
        dummyStrings.add("to test");
        dummyStrings.add("this library");
        dummyStrings.add("from both");
        dummyStrings.add("direction.");
        dummyStrings.add("You may");
        dummyStrings.add("be amazed");
        dummyStrings.add("when done");
        dummyStrings.add("so!");
        dummyStrings.add("I am");
        dummyStrings.add("going to");
        dummyStrings.add("add a little");
        dummyStrings.add("more lines");
        dummyStrings.add("for big");
        dummyStrings.add("smartphones.");

        return dummyStrings;
    }
}
