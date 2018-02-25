package com.mircea.botez.cyclingtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.mircea.botez.model.CyclingActivity;
import com.mircea.botez.model.CyclingActivityHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by botez on 1/3/2018.
 */

public class UsersActivity extends AppCompatActivity implements View.OnClickListener, ActionMode.Callback{

    private TextView textViewName;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_users);
//
//        textViewName = findViewById(R.id.text1);
//        String nameFromIntent = getIntent().getStringExtra("EMAIL");
//        textViewName.setText("Welcome" + nameFromIntent);
//    }

    protected Object mActionMode;
    public int selectedItem = -1;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private AppCompatButton appCompatButtonStartCycling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        initViews();
        initListeners();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        CyclingActivityHelper cyclingActivityHelper = new CyclingActivityHelper();
        final List<CyclingActivity> activities = cyclingActivityHelper.getCyclingActivities(this);
        mAdapter = new CustomAdapter(activities);


        mAdapter = new CustomAdapter(this, activities, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //Log.d(TAG, "clicked position:" + position);
                long postId = activities.get(position).getId();
                // do what ever you want to do with it

                selectedItem = position;

                // Start the CAB using the ActionMode.Callback defined above
                mActionMode = UsersActivity.this.startActionMode(UsersActivity.this);
                v.setSelected(true);

            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initListeners() {
        appCompatButtonStartCycling.setOnClickListener(this);
    }

    private void initViews() {
        appCompatButtonStartCycling = (AppCompatButton) findViewById(R.id.appCompatCreateActivityButton);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.appCompatCreateActivityButton :
                createCyclingActivity();
                break;
        }
    }

    private void createCyclingActivity() {
        CyclingActivity activity = new CyclingActivity();
        activity.setStartTime(Calendar.getInstance().getTime());
        CyclingActivityHelper cyclingActivityHelper = new CyclingActivityHelper();
        cyclingActivityHelper.saveCyclingActivity(activity, this);
//        activity.save();
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        // Inflate a menu resource providing context menu items
        MenuInflater inflater = actionMode.getMenuInflater();
        // Assumes that you have "contexual.xml" menu resources
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.view_cycling_activity:
                show();
                // Action picked, so close the CAB
                actionMode.finish();
                return true;
            case R.id.delete_cycling_activity:
                //show();
                // Action picked, so close the CAB
                actionMode.finish();
                return true;
            default:
                return false;
        }
    }



    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        mActionMode = null;
        selectedItem = -1;
    }

    private void show() {
        Toast.makeText(UsersActivity.this, String.valueOf(selectedItem), Toast.LENGTH_LONG).show();
    }
}
