package com.data.www.datasavedemo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.data.www.datasavedemo.entity.DaoMaster;
import com.data.www.datasavedemo.entity.DaoSession;
import com.data.www.datasavedemo.entity.GreenDaoUser;
import com.data.www.datasavedemo.entity.GreenDaoUserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by qwh on 2017/9/15.
 */

public class DBManager {
    private final static String dbName = "test_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    public GreenDaoUserDao getWriteUserDao() {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        GreenDaoUserDao userDao = daoSession.getGreenDaoUserDao();
        return userDao;
    }

    public GreenDaoUserDao getReadUserDao() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession =  daoMaster.newSession();
        GreenDaoUserDao userDao = daoSession.getGreenDaoUserDao();
        //前三行是关键，任何操作，增删改查都是在这个基础上进行的
        return userDao;
    }
    //获取所有的User
    public List<GreenDaoUser> getUsers(){
        GreenDaoUserDao userDao = getReadUserDao();
        QueryBuilder<GreenDaoUser> builder = userDao.queryBuilder();
        List<GreenDaoUser> user = builder.list();
        return user;
    }
    //插入User
    public void insert(GreenDaoUser user){
        GreenDaoUserDao userDao = getWriteUserDao();
        userDao.insert(user);
    }
    //根据名字删除user
    public void delete(String name){
        GreenDaoUserDao userDao = getWriteUserDao();
        userDao.deleteInTx(getusersByName(name));
    }
    //根据名字查询users
    public List<GreenDaoUser> getusersByName(String name){
        GreenDaoUserDao userDao = getReadUserDao();
        List<GreenDaoUser> users = userDao.queryRaw("where name=?",new String[]{name});
        return users;
    }
    //删除所有数据库记录
    public void deleteAll(){
        GreenDaoUserDao userDao = getWriteUserDao();
        userDao.deleteAll();
    }
}
