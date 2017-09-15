package com.data.www.datasavedemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.data.www.datasavedemo.R;
import com.data.www.datasavedemo.entity.GreenDaoUser;
import com.data.www.datasavedemo.utils.DBManager;

import java.util.List;


public class GreenDaoDemoActivity extends AppCompatActivity {
    EditText tv_name,tv_addr,tv_phone;
    TextView tv_result;
    DBManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_demo);
        initView();
        manager = DBManager.getInstance(this);
    }

    private void initView() {
        tv_name = (EditText) findViewById(R.id.name_green);
        tv_addr = (EditText) findViewById(R.id.address_green);
        tv_phone = (EditText) findViewById(R.id.phone_green);
        tv_result = (TextView) findViewById(R.id.tv_result_green);
        findViewById(R.id.insert_green).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GreenDaoUser p = new GreenDaoUser();
                p.setName(tv_name.getText().toString());
                p.setAddress(tv_addr.getText().toString());
                p.setPhone(tv_phone.getText().toString());
                manager.insert(p);
            }
        });
        findViewById(R.id.showresult_green).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<GreenDaoUser> users = manager.getUsers();
                StringBuilder builder = new StringBuilder();
                for (GreenDaoUser user:users) {
                    builder.append(user.getName()+" ");
                    builder.append(user.getAddress()+" ");
                    builder.append(user.getPhone()+"\n");
                }
                tv_result.setText(builder.toString());
            }
        });
        findViewById(R.id.delete_green).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GreenDaoUser p = new GreenDaoUser();
                p.setName(tv_name.getText().toString());
                p.setAddress(tv_addr.getText().toString());
                p.setPhone(tv_phone.getText().toString());
                manager.delete(tv_name.getText().toString());
            }
        });
        findViewById(R.id.deleteall_green).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.deleteAll();
            }
        });
    }
}
