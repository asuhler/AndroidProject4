package com.example.listview;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Perkins on 10/28/2015.
 */
public class adapter extends BaseAdapter {

    //for layouts
    LayoutInflater myInflater;
    public List<BikeData> data = new ArrayList<BikeData>();

    //
    public static class ViewHolder {
        ImageView img1;
        TextView Model;
        TextView Description;
        TextView Price;
        String url;
    }


    //genrerate data we use
    private ArrayList<String> values = new ArrayList<String>();




    private final Activity_ListView mainActivity;

    public adapter(Activity_ListView activity, List<BikeData> data) {
        this.mainActivity = activity;
        myInflater = (LayoutInflater) mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(data!=null) {
            if(!data.isEmpty()){
                Log.e("LOG","I made it into the data set transfer from construtor");
                for(int i=0; i<data.size();i++) {
                    this.data.add(data.get(i));
                }
            }
        }

    }

    @Override
    public int getCount () {
        if(data!=null){
            Log.e("ARRRGGG", "I got the actual number of values in data");
            return data.size();
        }
        return 0;
    }

    @Override
    public Object getItem ( int position){
        return null;
    }

    @Override
    public long getItemId ( int position){
        return 0;
    }

    @Override
    public View getView ( int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = myInflater.inflate(R.layout.listview_row_layout, null);

            holder = new ViewHolder();
            holder.img1 = (ImageView) convertView.findViewById(R.id.imageView1);
            holder.Model = (TextView) convertView.findViewById(R.id.Model);
            holder.Description = (TextView) convertView.findViewById(R.id.Description);
            holder.Price = (TextView) convertView.findViewById(R.id.Price);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        //set the text
        holder.Model.setText(data.get(position).Model);
        holder.Description.setText(data.get(position).Description);
        holder.Price.setText(data.get(position).Price.toString());
        holder.url = data.get(position).Picture;

        //start a thread to download image
        //new DownloadImageTask(holder.url, holder.img1).execute();

        return convertView;
    }

    //adds all the data from the passed in data values

}
