package com.mircea.botez.cyclingtracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mircea.botez.model.CyclingActivity;

import java.util.List;

/**
 * Created by botez on 2/6/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<CyclingActivity> activityList;
    private CustomItemClickListener listener;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.text_view);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CustomAdapter(List<CyclingActivity> activityList) {
        this.activityList = activityList;
    }

    public CustomAdapter(Context mContext, List<CyclingActivity> data, CustomItemClickListener listener) {
        this.activityList = data;
        this.mContext = mContext;
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //...
        final ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, vh.getPosition());
            }
        });
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(activityList.get(position).getStartTime().toString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return activityList.size();
    }
}
