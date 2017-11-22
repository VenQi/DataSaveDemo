package com.data.www.datasavedemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import com.data.www.datasavedemo.R;
import com.data.www.datasavedemo.entity.HistoryItem;
import com.data.www.datasavedemo.utils.DBHelper;
import com.data.www.datasavedemo.utils.PermissionUtil;

import java.util.List;


public class PhoneCallStateActivity extends AppCompatActivity {

    private PermissionUtil util;
    private TextView textView;
    TelephonyManager manager ;
    String result = "监听电话状态：/n";
    List<HistoryItem> items;
    DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call_state);
        helper = new DBHelper(this);
        items = helper.getAllHistory();
        textView = (TextView) findViewById(R.id.phone_state_result);
    }

    @Override
    protected void onResume() {
        super.onResume();
        textView.setText(items.size()+"----");
    }
}
