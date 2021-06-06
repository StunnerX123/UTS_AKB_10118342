package com.example.contohsqlite.nav;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import com.example.contohsqlite.AddCountryActivity;
import com.example.contohsqlite.DBManager;
import com.example.contohsqlite.DatabaseHelper;
import com.example.contohsqlite.ModifyCountryActivity;
import com.example.contohsqlite.R;

public class FragCatatan extends Fragment {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.SUBJECT, DatabaseHelper.KATEGORI, DatabaseHelper.DESC, DatabaseHelper.DATE };

    final int[] to = new int[] { R.id.title, R.id.kat, R.id.desc, R.id.date};

    Button add_record;

    public FragCatatan() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View rootView =inflater.inflate(R.layout.fragment_emp_list, container, false);

        add_record =(Button)rootView.findViewById(R.id.add_record);
        add_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(getActivity(), AddCountryActivity.class);
                startActivity(add_mem);
            }
        });
        return rootView;
    }

    public void onAttach(Context context){
        super.onAttach(context);
        dbManager = new DBManager(context);
    }

    public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView =inflater.inflate(R.layout.fragment_emp_list, container, false);

        listView = (ListView) rootView.findViewById(R.id.list_view);
        listView.setEmptyView(rootView.findViewById(R.id.empty));

        dbManager.open();
        Cursor cursor = dbManager.fetch();

        adapter = new SimpleCursorAdapter(getContext(), R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                //TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.title);
                TextView katTextView = (TextView) view.findViewById(R.id.kat);
                TextView descTextView = (TextView) view.findViewById(R.id.desc);
                TextView dateTextView = (TextView) view.findViewById(R.id.date);

                //String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String kat = katTextView.getText().toString();
                String desc = descTextView.getText().toString();
                String date = dateTextView.getText().toString();

                Intent modify_intent = new Intent(getActivity(), ModifyCountryActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("kat", kat);
                modify_intent.putExtra("date", date);
                modify_intent.putExtra("desc", desc);
                //modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
        return rootView;
    }
}
//Pengerjaan 5/06/2021