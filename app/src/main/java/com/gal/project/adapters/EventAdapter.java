package com.gal.project.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.gal.project.R;

import java.util.List;


public class EventAdapter  <P> extends ArrayAdapter<com.gal.project.models.Event> {
    Context context;
    List<com.gal.project.models.Event> objects;

    public  EventAdapter(Context context, int resource, int textViewResourceId, List<com.gal.project.models.Event> objects) {
        super(context, resource, textViewResourceId, objects);

        this.context = context;
        this.objects = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.raw_event, parent, false);

        TextView tvEventName = (TextView)view.findViewById(R.id.tvEventName);
        TextView tvEventDate = (TextView)view.findViewById(R.id.tvEventDate);

        TextView tvEventTime = (TextView)view.findViewById(R.id.tvEventTime);
        TextView tvEventCat = (TextView)view.findViewById(R.id.tvEventCat);

        TextView tvEventCity = (TextView)view.findViewById(R.id.tvEventCity);
        TextView tvEventJoined = (TextView)view.findViewById(R.id.tvEventJoinedNe);




        com.gal.project.models.Event temp = objects.get(position);
        tvEventName.setText(temp.getName()+"");
        tvEventDate.setText(temp.getDate()+"");
        tvEventTime.setText(temp.getTime()+"");
        tvEventCat.setText(temp.getType()+"");
        tvEventCity.setText(temp.getCity()+"");
        tvEventJoined.setText(temp.getJoined()+"");

        return view;
    }

}
