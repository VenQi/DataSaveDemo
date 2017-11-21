package com.data.www.datasavedemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwh on 2017/11/13.
 */

public class PermissionUtil {
    public static PermissionUtil util;
    private Activity activity;
    private PermissionCallBack callBack;
    int requestCodes;
    public PermissionUtil(Activity activity){
        this.activity = activity;
    }
//    public static PermissionUtil getInstance(Activity activity){
//        if (util == null){
//            util = new PermissionUtil(activity);
//        }
//        return util;
//    }

    public void requestPermissions(String[] permissions,int requestcode,PermissionCallBack callBack){
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M) {
            int permissionsLength = permissions.length;
            int[] grantResults = new int[]{};
            for (int i=0;i<permissionsLength;i++){
                grantResults[i] = PackageManager.PERMISSION_GRANTED;
            }
            callBack.onpermissionsGranted(permissions);
            return;
        }

        this.callBack = callBack;
        this.requestCodes = requestcode;
        List<String> permissionList = checkMorePermissions(activity, permissions);
        if (permissionList.size() == 0){
            callBack.onpermissionsGranted(permissionList.toArray(new String[permissionList.size()]));
        }else {

//            boolean isFirst = true;
//            for (int i = 0; i < permissionList.size(); i++) {
//                String permission = permissionList.get(i);
//                if (judgePermission(activity, permission)) {
//                    isFirst = false;
//                    break;
//                }
//            }
//            String[] unauthorizedMorePermissions = permissionList.toArray(new String[permissionList.size()]);
//            if (isFirst)// 用户之前已拒绝过权限申请
//                callBack.onUserHasAlreadyTurnedDownAndDontAsk(unauthorizedMorePermissions);
//            else       // 用户之前已拒绝并勾选了不在询问、用户第一次申请权限。
//                callBack.onUserHasAlreadyTurnedDown(unauthorizedMorePermissions);
            requestMorePermissions(permissionList,requestcode);
        }
    }
    /**
     * 请求多个权限
     */
    private void requestMorePermissions(List permissionList, int requestCode) {
        String[] getpermissions = (String[]) permissionList.toArray(new String[permissionList.size()]);
        ActivityCompat.requestPermissions(activity, getpermissions, requestCode);
    }
    /**
     * 判断是否已拒绝过权限
     *
     * @return
     * @describe :如果应用之前请求过此权限但用户拒绝，此方法将返回 true;
     * -----------如果应用第一次请求权限或 用户在过去拒绝了权限请求，
     * -----------并在权限请求系统对话框中选择了 Don't ask again 选项，此方法将返回 false。
     */
    private boolean judgePermission(Context context, String permission) {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission))
            return true;
        else
            return false;
    }
    /**
     * 检测多个权限
     *
     * @return 未授权的权限
     */
    private  List<String> checkMorePermissions(Context context, String[] permissions) {
        List<String> permissionList = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (!checkPermission(context, permissions[i]))
                permissionList.add(permissions[i]);
        }
        return permissionList;
    }

    /**
     * 检测权限
     *
     * @return true：已授权； false：未授权；
     */
    private boolean checkPermission(Context context, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            return false;
    }


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        boolean isBannedPermission = false;
        List<String> permissionList = checkMorePermissions(activity, permissions);
        if (permissionList.size() == 0)
            callBack.onpermissionsGranted((String[])permissionList.toArray(new String[permissionList.size()]));
        else {
            for (int i = 0; i < permissionList.size(); i++) {
                if (!judgePermission(activity, permissionList.get(i))) {
                    isBannedPermission = true;
                    break;
                }
            }
            //　已禁止再次询问权限
            if (isBannedPermission) {
                callBack.onUserHasAlreadyTurnedDownAndDontAsk(permissions);//TODO 提示
                showToAppSettingDialog();
            } else { // 拒绝权限
                callBack.onUserHasAlreadyTurnedDown(permissions);
                //TODO 打开设置授权的设置页面
            }
        }

//        if (requestCode == requestCodes){
//            if (grantResults.length>0){
//                for (int i=0;i<grantResults.length;i++){
//                    if (grantResults[i]!=PackageManager.PERMISSION_GRANTED){
//                        ActivityCompat.requestPermissions(activity,
//                                permissions,
//                                OASISPlatformConstant.REQUEST_CODE_CHECKPERMISSIONS_REQUEST);
//                    }else
//                        callBack.permissionsGranted(permissions);
//                }
//            }
//        }
    }

    /**
     * 显示前往应用设置Dialog
     *
     */
    private void showToAppSettingDialog() {
        new AlertDialog.Builder(activity)
                .setTitle("需要权限")
                .setMessage("我们需要相关权限，才能实现功能，点击前往，将转到应用的设置界面，请开启应用的相关权限。")
                .setPositiveButton("前往", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toAppSetting(activity);
                    }
                })
                .setNegativeButton("取消", null).show();
    }

    /**
     * 跳转到权限设置界面
     */
    public static void toAppSetting(Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(intent);
    }

    public interface PermissionCallBack{
        void onpermissionsGranted(String[] permissions);
        void onUserHasAlreadyTurnedDownAndDontAsk(String[] permissions);
        void onUserHasAlreadyTurnedDown(String[] permissions);
    }
}
