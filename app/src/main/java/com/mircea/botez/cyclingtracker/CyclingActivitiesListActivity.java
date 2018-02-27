package com.mircea.botez.cyclingtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mircea.botez.model.CyclingActivity;
import com.mircea.botez.model.CyclingActivityHelper;

import java.util.Calendar;
import java.util.List;

/**
 * Created by botez on 1/3/2018.
 */

public class CyclingActivitiesListActivity extends AppCompatActivity implements View.OnClickListener, ActionMode.Callback{

    private TextView textViewName;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.cycling_activities_list_users);
//
//        textViewName = findViewById(R.id.text1);
//        String nameFromIntent = getIntent().getStringExtra("EMAIL");
//        textViewName.setText("Welcome" + nameFromIntent);
//    }
    private List<CyclingActivity> activities;
    protected Object mActionMode;
    public Long selectedItem = -1L;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private AppCompatButton appCompatButtonStartCycling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cycling_activities_list_users);

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
        activities = cyclingActivityHelper.getCyclingActivities(this);

        mAdapter = new CustomAdapter(this, activities, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //Log.d(TAG, "clicked position:" + position);
                // do what ever you want to do with it

                selectedItem = activities.get(position).getId();

                // Start the CAB using the ActionMode.Callback defined above
                mActionMode = CyclingActivitiesListActivity.this.startActionMode(CyclingActivitiesListActivity.this);
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
        Intent accountsIntent = new Intent(this, CyclingAcActivity.class);
        startActivity(accountsIntent);

        CyclingActivity activity = new CyclingActivity();
        activity.setStartTime(Calendar.getInstance().getTime());
        CyclingActivityHelper cyclingActivityHelper = new CyclingActivityHelper();
        cyclingActivityHelper.saveCyclingActivity(activity, this);
        //TODO: go to cycling activity
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
                Intent accountsIntent = new Intent(this, CyclingAcViewerActivity.class);
                startActivity(accountsIntent);
                actionMode.finish();
                return true;
            case R.id.delete_cycling_activity:
                //show();
                // Action picked, so close the CAB
                deleteCyclingActivity(actionMode);
                //actionMode.finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        mActionMode = null;
        selectedItem = -1L;
    }

    private void deleteCyclingActivity(final ActionMode actionMode) {
        new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Do you really want to delete the activity?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        CyclingActivityHelper cyclingActivityHelper = new CyclingActivityHelper();
                        cyclingActivityHelper.deleteCyclingActivity(CyclingActivitiesListActivity.this, selectedItem);

                        for(int i = 0; i < activities.size(); i++) {
                            if (activities.get(i).getId().equals(selectedItem)) {
                                activities.remove(i);
                                mRecyclerView.removeViewAt(i);
                                mAdapter.notifyItemRemoved(i);
                                mAdapter.notifyItemRangeChanged(i, activities.size());
                                mAdapter.notifyDataSetChanged();
                                actionMode.finish();
                            }
                        }
                        Toast.makeText(CyclingActivitiesListActivity.this, "The cycling activity was deleted" + " " + selectedItem, Toast.LENGTH_SHORT).show();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    private void show() {
        Toast.makeText(CyclingActivitiesListActivity.this, String.valueOf(selectedItem), Toast.LENGTH_LONG).show();
    }
}
