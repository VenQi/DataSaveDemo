package com.data.www.datasavedemo.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.data.www.datasavedemo.R;

import java.lang.ref.WeakReference;

/**
 * 这个类是为了验证Handler在activity关闭之后是否会继续运行
 * 验证方法：
 * 在生命周期stop中发送一个延时消息，在生命周期方法ondestory打印日志，
 * 然后在handler中打印日志。如果handler打印的日志在ondestory日志后面出现，那么就证明handler是
 * 在activity运行之后继续执行，反之就不会执行了。
 *
 * 验证结果
 *
 * 10-25 11:01:04.038 3039-3039/com.data.www.datasavedemo E/TestHandlerActivity: before super onStop
 10-25 11:01:04.038 3039-3039/com.data.www.datasavedemo E/TestHandlerActivity: after super onStop
 10-25 11:01:04.038 3039-3039/com.data.www.datasavedemo E/TestHandlerActivity: before super destory
 10-25 11:01:04.038 3039-3039/com.data.www.datasavedemo E/TestHandlerActivity: after super destory
 10-25 11:01:10.046 3039-3039/com.data.www.datasavedemo E/TestHandlerActivity: delay message

    通过以上结果清晰的看到了延时是执行了，所以如果在handler中执行了dialog等方法可能会出现badtoken的exception
 */
public class TestHandlerActivity extends AppCompatActivity {
    public static final String TAG = TestHandlerActivity.class.getSimpleName();
    private MyHandler myHandler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);
        myHandler =  new MyHandler(this);;

    }
    class MyHandler extends Handler{
        private WeakReference<TestHandlerActivity> outer;
        public MyHandler(TestHandlerActivity activity){
            outer = new WeakReference<TestHandlerActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            TestHandlerActivity mouter = outer.get();
            super.handleMessage(msg);
            Log.e(TAG,"delay message");
            mouter.showDialog();
        }
    }

    @Override
    protected void onStop() {
        Log.e(TAG,"before super onStop ");
        myHandler.sendEmptyMessageDelayed(0,1000*6);
        super.onStop();
        Log.e(TAG,"after super onStop ");
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG,"before super destory ");
        super.onDestroy();
        Log.e(TAG,"after super destory ");
//        if (null!=myHandler){
//            myHandler.removeMessages(0);
//            myHandler = null;
//        }
    }
    public void showDialog(){
        Log.e(TAG,"showDialog");
//        if (this.isFinishing())
//            return;
        Toast.makeText(this,"Message after finish",Toast.LENGTH_LONG).show();
//        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
//        builder.setTitle("testHandler").setMessage("我只是试试能不能出来")
//                .show();
//        builder.show();
    }
}
