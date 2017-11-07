package com.example.chrisantuseze.hadum.Timetablee.Days;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.chrisantuseze.hadum.R;
import com.example.chrisantuseze.hadum.Timetablee.Adapters.ThursAdapter;
import com.example.chrisantuseze.hadum.Timetablee.Adds.AddThurs;
import com.example.chrisantuseze.hadum.Timetablee.DBs.ThursDB;

import static android.provider.BaseColumns._ID;
import static com.example.chrisantuseze.hadum.Timetablee.DBs.ThursDB.TABLE_NAME;


/**
 * A simple {@link Fragment} subclass.
 */
public class THURSDAY extends Fragment {

    private RecyclerView recyclerView;
    private ThursAdapter adapter;
    private SQLiteDatabase mDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thurs, container, false);


        ThursDB dbHelper = new ThursDB(getContext());
        mDb = dbHelper.getWritableDatabase();
        recyclerView = (RecyclerView)view. findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Cursor cursor = getAllGuests();
        adapter = new ThursAdapter(getContext(), cursor);

        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton)view. findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddThurs.class));

            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long)viewHolder.itemView.getTag();

                removeQuest(id);
                adapter.swapCursor(getAllGuests());

            }
        }).attachToRecyclerView(recyclerView);

        return view;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Cursor getAllGuests() {
        // COMPLETED (6) Inside, call query on mDb passing in the table name and projection String [] order by COLUMN_TIMESTAMP
        return mDb.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
    private boolean removeQuest(long id){
        return mDb.delete(TABLE_NAME, _ID+"="+id, null)>0;
    }
}

