package com.data.www.datasavedemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ListView;

import com.data.www.datasavedemo.R;
import com.data.www.datasavedemo.adapters.CallHistoryAdapter;
import com.data.www.datasavedemo.entity.HistoryItem;
import com.data.www.datasavedemo.utils.DBHelper;
import com.data.www.datasavedemo.utils.PermissionUtil;

import java.util.List;


public class PhoneCallStateActivity extends AppCompatActivity {

    private PermissionUtil util;
    private ListView listView;
    TelephonyManager manager ;
    String result = "监听电话状态：/n";
    List<HistoryItem> items;
    DBHelper helper;
    CallHistoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call_state);
        helper = new DBHelper(this);
        listView = (ListView) findViewById(R.id.phone_call_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        items = helper.getAllHistory();
        Log.e("items size：",items.size()+"");
        if (adapter == null) {
            adapter = new CallHistoryAdapter(items,this);
            listView.setAdapter(adapter);
        }else
            adapter.notifyDataSetChanged();
    }


}
