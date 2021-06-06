package com.example.contohsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class CountryListActivity extends AppCompatActivity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    private Context context;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.SUBJECT, DatabaseHelper.KATEGORI, DatabaseHelper.DESC, DatabaseHelper.DATE };

    final int[] to = new int[] { R.id.id, R.id.title, R.id.kat, R.id.desc, R.id.date};

    Button add_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);

        add_record =(Button)findViewById(R.id.add_record);
        add_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(CountryListActivity.this, AddCountryActivity.class);
                startActivity(add_mem);
                finish();
            }
        });

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.title);
                TextView katTextView = (TextView) view.findViewById(R.id.kat);
                TextView descTextView = (TextView) view.findViewById(R.id.desc);
                TextView dateTextView = (TextView) view.findViewById(R.id.date);

                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String kat = katTextView.getText().toString();
                String desc = descTextView.getText().toString();
                String date = dateTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyCountryActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("kat", kat);
                modify_intent.putExtra("date", date);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }
}
//Pengerjaan 5/06/2021