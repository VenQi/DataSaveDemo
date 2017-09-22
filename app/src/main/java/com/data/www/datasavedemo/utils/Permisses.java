package com.data.www.datasavedemo.utils;

import android.Manifest;

/**
 * Created by qwh on 2017/9/20.
 */

public class Permisses {
    /**
     * Id to identify permission request.
     */
    public static final int REQUEST_CALENDAR = 0;
    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_CONTACTS = 2;
    public static final int REQUEST_LOCATION = 3;
    public static final int REQUEST_MICROPHONE = 4;
    public static final int REQUEST_PHONE = 5;
    public static final int REQUEST_SENSORS = 6;
    public static final int REQUEST_SMS = 7;
    public static final int REQUEST_STORAGE = 8;

    public static String[] getPermissions(int permissionId){
        String[] permissions;
        switch(permissionId) {
            case REQUEST_CALENDAR:
                return PERMISSION_CALENDAR;

            case REQUEST_CAMERA:
                return PERMISSION_CAMERA;

            case REQUEST_CONTACTS:
                return PERMISSION_CONTACTS;

            case REQUEST_LOCATION:
                return PERMISSION_LOCATION;

            case REQUEST_MICROPHONE:
                return PERMISSION_MICROPHONE;

            case REQUEST_PHONE:
                return PERMISSION_PHONE;

            case REQUEST_SENSORS:
                return PERMISSION_SENSORS;

            case REQUEST_SMS:
                return PERMISSION_SMS;

            case REQUEST_STORAGE:
                return PERMISSION_STORAGE;
            default:
                break;
        }
        return null;
    }

    /**
     * Used for runtime permissions related to user's calendar.
     * Used by the {}.
     */
    private static String[] PERMISSION_CALENDAR = {
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR
    };

    /**
     * Used for permissions that are associated with accessing camera
     * or capturing images/video from the device.
     * Used by the {}.
     */
    private static String[] PERMISSION_CAMERA = {
            Manifest.permission.CAMERA
    };

    /**
     * Used for runtime permissions related to contacts and profiles on this device.
     * Used by the {}.
     */
    private static String[] PERMISSION_CONTACTS = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS
    };

    /**
     * Used for permissions that allow accessing the device location.
     * Used by the {}.
     */
    private static String[] PERMISSION_LOCATION = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    /**
     * Used for permissions that are associated with accessing microphone audio from the device.
     * Used by the {.
     */
    private static String[] PERMISSION_MICROPHONE = {
            Manifest.permission.RECORD_AUDIO
    };

    /**
     * Used for permissions that are associated telephony features.
     * Used by the {}.
     */
    private static String[] PERMISSION_PHONE = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.USE_SIP,
            Manifest.permission.PROCESS_OUTGOING_CALLS
    };

    /**
     * Used for permissions that are associated with accessing camera or capturing images/video
     * from the device.
     * Used by the {}.
     */
    private static String[] PERMISSION_SENSORS = {
            Manifest.permission.BODY_SENSORS
    };

    /**
     * Used for runtime permissions related to user's SMS messages.
     * Used by the {}.
     */
    private static String[] PERMISSION_SMS = {
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_WAP_PUSH,
            Manifest.permission.RECEIVE_MMS
    };

    /**
     * Used for runtime permissions related to the shared external storage.
     * Used by the {}.
     */
    private static String[] PERMISSION_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

}
