package com.data.www.datasavedemo.activities;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.data.www.datasavedemo.R;
import com.data.www.datasavedemo.utils.FileUtil;
import com.data.www.datasavedemo.utils.Permisses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileIoActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "FileIoActivity";
    EditText ed_filename,ed_filecontent;
    TextView tv_result;
    Map<String,String> noticeMap;
    @Override
    String getNotice(String permission) {
        return noticeMap.get(permission);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_io);
        ed_filename = (EditText) findViewById(R.id.filename);
        ed_filecontent = (EditText) findViewById(R.id.content);
        tv_result = (TextView) findViewById(R.id.result);
        ((Button)findViewById(R.id.save)).setOnClickListener(this);
        ((Button)findViewById(R.id.getresult)).setOnClickListener(this);
        noticeMap = new HashMap<>();
        noticeMap.put(Manifest.permission.READ_EXTERNAL_STORAGE,"需要读的权限读取文件内容");
        noticeMap.put(Manifest.permission.WRITE_EXTERNAL_STORAGE,"需要写文件的权限向文件记录内容");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save:
                BaseActivity.requestRunTimePermission(Permisses.getPermissions(Permisses.REQUEST_STORAGE), new IPermission() {
                    @Override
                    public void onGranted() {
                        FileUtil.savaFile(ed_filename.getText().toString(),ed_filecontent.getText().toString());
                    }

                    @Override
                    public void onDenied(List<String> deniedPermissions) {
                        for (String permission:deniedPermissions
                                ) {
                            Log.e("permission",permission+"-----");
                        }
                    }
                });

                break;
            case R.id.getresult:
                BaseActivity.requestRunTimePermission(Permisses.getPermissions(Permisses.REQUEST_STORAGE), new IPermission() {
                    @Override
                    public void onGranted() {
                        tv_result.setText(FileUtil.getContent2(ed_filename.getText().toString()));
                    }

                    @Override
                    public void onDenied(List<String> deniedPermissions) {
                        for (String permission:deniedPermissions
                             ) {
                            Log.e("permission",permission+"-----");
                        }

                    }
                });

                break;
            default:
                break;
        }
    }
}
