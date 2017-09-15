package com.data.www.datasavedemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.data.www.datasavedemo.R;
import com.data.www.datasavedemo.entity.Person;
import com.data.www.datasavedemo.utils.DBHelper;

public class SqliteDBActivity extends AppCompatActivity {
    EditText tv_name,tv_addr,tv_phone;
    TextView tv_result;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_db);
        initView();
        db =  new DBHelper(this);
    }

    private void initView() {
        tv_name = (EditText) findViewById(R.id.name);
        tv_addr = (EditText) findViewById(R.id.address);
        tv_phone = (EditText) findViewById(R.id.phone);
        tv_result = (TextView) findViewById(R.id.tv_result);
        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person p = new Person();
                p.name = tv_name.getText().toString();
                p.address = tv_addr.getText().toString();
                p.phone = tv_phone.getText().toString();
                db.insert(p);
            }
        });
        findViewById(R.id.showresult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_result.setText(db.getAll());
            }
        });
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete(tv_name.getText().toString());
            }
        });
        findViewById(R.id.deleteall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delAll();
            }
        });
    }
}
