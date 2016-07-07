package com.example.acer.terminalsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 17.06.2016.
 */
public class    TerminalAdapter extends BaseAdapter implements
        View.OnClickListener {
    final String LOG_TAG = "PointAdapterLog";
    Activity activity;
    List<Point> rData = new ArrayList<Point>();
    static LayoutInflater inflater = null;
    Context mContext;

    public TerminalAdapter(Activity a, ArrayList<Point> rD,
                       Context context) {
        this.mContext = context;
        activity = a;
        rData = rD;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return rData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void onClick(View v) {
    }

    /*********
     * Create a holder Class to contain inflated xml file elements
     *********/
    public static class ViewHolder {
        public TextView text2;
        public TextView text;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;
        if (convertView == null) {
            //****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.list_view_row, null);
            /****** View Holder Object to contain tabitem.xml file elements ******/
            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.text);
            holder.text2 = (TextView) vi.findViewById(R.id.text2);
            /************ Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }
        Point item = rData.get(position);
        Context context = parent.getContext();
        holder.text.setText(item.getPoint_name());
        /******** Set Item Click Listner for LayoutInflater for each row ***********/
        vi.setOnClickListener(new OnItemClickListener(position, item));
        return vi;
    }

    /*********
     * Called when Item click in ListView
     ************/
    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;
        private Point item_text;
        OnItemClickListener(int position, Point note) {
            mPosition = position;
            item_text = note;
        }
        @Override
        public void onClick(View arg0) {
            Intent myIntent = new Intent(mContext, ShowPointActivity.class);
            myIntent.putExtra("text_note", item_text.getPoint_name());
            myIntent.putExtra("lat", item_text.getLat());
            myIntent.putExtra("longt", item_text.getLongt());
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(myIntent);
        }
    }
}
