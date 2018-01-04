package com.sample.qwh.contentresoverdemo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button query;
    ListView list;
    List<String> arraylist;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ContentResolver resolver = getContentResolver();
        query = (Button)findViewById(R.id.get_content);
        list = (ListView)findViewById(R.id.content_list);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.data.www.datasavedemo.provider/person");
                Cursor cursor = resolver.query(uri,null,null,null,null);
                if (arraylist == null)
                    arraylist = new ArrayList<String>();
                else
                    arraylist.clear();
                if (cursor != null){
                    while(cursor.moveToNext()){
                        StringBuilder builder = new StringBuilder();
                        builder.append("name:"+cursor.getString(cursor.getColumnIndex("name"))+"  ");
                        builder.append("name:"+cursor.getString(cursor.getColumnIndex("phone"))+"\n");
                        builder.append("name:"+cursor.getString(cursor.getColumnIndex("address")));
                        arraylist.add(builder.toString());
                    }
                    cursor.close();
                }
                if (adapter == null) {
                    adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arraylist);
                    list.setAdapter(adapter);
                }else
                    adapter.notifyDataSetChanged();
            }
        });
    }
}
