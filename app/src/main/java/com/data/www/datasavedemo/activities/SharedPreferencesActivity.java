package com.data.www.datasavedemo.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.data.www.datasavedemo.R;

public class SharedPreferencesActivity extends AppCompatActivity {

    EditText editText;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        editText = (EditText) findViewById(R.id.editText);
        sp= getSharedPreferences("spdemo", Context.MODE_PRIVATE);
        editor =sp.edit();
    }
    public void btnSPClick(View v){
        switch (v.getId()){
            case R.id.btn_sp_save:
                String s = editText.getText().toString();
                if (TextUtils.isEmpty(s))
                    Toast.makeText(SharedPreferencesActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                else {
                    editor.putString("password", s);
                    editor.commit();
                }
                 break;
            case R.id.btn_sp_removepass:
                editor.remove("password");
                editor.commit();
                break;
            case R.id.btn_sp_showpass:
                String password = sp.getString("password","");
                Toast.makeText(SharedPreferencesActivity.this,password,Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
