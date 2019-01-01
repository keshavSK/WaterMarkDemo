package com.example.dell_pc.demomatrimony;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class GottraAdapter extends BaseAdapter{
    private Context mContext;
    List<GottraModel>list;

    public GottraAdapter(Context mContext, List<GottraModel> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.row_gottra_spinner, parent, false);

        AppCompatTextView tvHeading = convertView.findViewById(R.id.row_grotra_textView);

        GottraModel gotramodel = list.get(position);


        tvHeading.setText(gotramodel.getGottraName());

        return convertView;
    }
}
