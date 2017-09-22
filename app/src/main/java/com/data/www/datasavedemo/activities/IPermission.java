package com.data.www.datasavedemo.activities;

import java.util.List;

/**
 * @author yhy
 * @time 2017/1/9 14:02
 */
public interface IPermission {
    void onGranted();

    void onDenied(List<String> deniedPermissions);

}
