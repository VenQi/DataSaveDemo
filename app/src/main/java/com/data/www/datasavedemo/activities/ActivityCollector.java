package com.data.www.datasavedemo.activities;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yhy
 * @time 2017/1/9 13:50
 */
public class ActivityCollector {
    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void fillActivity() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }

    public static Activity getTopActivity() {
   return activities.get(activities.size()-1);

    }
}

