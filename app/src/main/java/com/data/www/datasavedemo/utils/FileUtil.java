package com.data.www.datasavedemo.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by qwh on 2017/9/20.
 * used to write something to Android data file/read content
 */

public class FileUtil {
    public static final String basePath = Environment.getExternalStorageDirectory()+"";
//    private Context context;
//
//    private FileUtil(){
//    }
//    public FileUtil getInstance(Context context){
//        this.context = context;
//        return FileUtilHolder.INSTANCE;
//    }
//    //静态内部类实现单例模式
//   private  static class  FileUtilHolder{
//        private static FileUtil INSTANCE = new FileUtil();
//    }


    public static void writeTofile(String name,String content,Context context){
        FileOutputStream opt = null;
        OutputStreamWriter opw = null;
        try {
            opt = context.openFileOutput(name,Context.MODE_APPEND);
            opw = new OutputStreamWriter(opt,"UTF-8");
            opw.write(content);
            //保证输出缓冲区中的所有内容
            opw.flush();
            opt.flush();
            //后打开的先关闭，逐层向内关闭
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!= opw){
                try {
                    opw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null!= opt){
                try {
                    opt.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getContent(String name,Context context){
        FileInputStream inputstr = null;
        InputStreamReader reader = null;
        char input[] = new char[]{};
        try {
            inputstr = context.openFileInput(name);
            reader = new InputStreamReader(inputstr,"UTF-8");
            //fis.available()文件可用长度
            input=new char[inputstr.available()];
            reader.read(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!= inputstr){
                try {
                    inputstr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null!= reader){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return input.toString();
    }

    private  File getFile(String name){
        File file = new File(name);
        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }
        return file;
    }

    public  void deleteFile(String name){

    }

    public static void savaFile(String name ,String content){
        File file = new File(basePath,name);
        Log.e("filepath",file.getAbsolutePath());
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        FileOutputStream ops;
        try {
            FileOutputStream fos=new FileOutputStream(file,true);
            OutputStreamWriter osw=new OutputStreamWriter(fos);
            osw.write(content+"\n");
            osw.flush();
            osw.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getContent2(String name ){
        File file = new File(basePath,name);
        Log.e("filepath",file.getAbsolutePath());
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        FileOutputStream ops;
        String in = null;
        try {
            FileInputStream fis=new FileInputStream(file);
            InputStreamReader isr=new InputStreamReader(fis, "UTF-8");
            char[] input=new char[fis.available()];
            isr.read(input);
            isr.close();
            fis.close();
            in=new String(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }
}
