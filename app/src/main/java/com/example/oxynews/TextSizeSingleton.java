package com.example.oxynews;


import android.app.Activity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;



public class TextSizeSingleton {
    private static TextSizeSingleton instance;
    private float currentTextSize;
    private float minTextSize;
    private float maxTextSize;

    private TextSizeSingleton() {
        // Initialize default values
        currentTextSize = 24f; // Default text size
        minTextSize = 15f;     // Minimum text size
        maxTextSize = 40.0f;   // Maximum text size
    }

    public static TextSizeSingleton getInstance() {
        if (instance == null) {
            instance = new TextSizeSingleton();
        }
        return instance;
    }

    public void setCurrentTextSize(float currentTextSize) {
        if (currentTextSize < minTextSize) {
            this.currentTextSize = minTextSize;
        } else if (currentTextSize > maxTextSize) {
            this.currentTextSize = maxTextSize;
        } else {
            this.currentTextSize = currentTextSize;
        }
        applyTextSizeToArticleText();
    }


    public float getCurrentTextSize() {
        return currentTextSize;
    }



    private void applyTextSizeToArticleText() {
        // Find all TextViews with the tag "articleText" and update their text size
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            ViewGroup rootView = currentActivity.getWindow().getDecorView().findViewById(android.R.id.content);
            applyTextSizeToArticleText(rootView);
        }
    }

    private void applyTextSizeToArticleText(View view) {
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if ("articleText".equals(textView.getTag())) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, currentTextSize);
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                applyTextSizeToArticleText(viewGroup.getChildAt(i));
            }
        }
    }

    private Activity getCurrentActivity() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            Object currentActivityThread = currentActivityThreadMethod.invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(currentActivityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field activityField = activityRecordClass.getDeclaredField("activity");
                activityField.setAccessible(true);
                Activity activity = (Activity) activityField.get(activityRecord);
                if (activity != null && !activity.isFinishing()) {
                    return activity;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
