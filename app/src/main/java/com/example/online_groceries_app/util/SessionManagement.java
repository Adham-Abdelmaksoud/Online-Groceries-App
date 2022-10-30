package com.example.online_groceries_app.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    private static final String KEY_LOCATION = "LOCATION";
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    public Context context;

    public SessionManagement(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("MyPref", 0);
        editor = preferences.edit();
    }

    public void setLocation(String location){
        editor.putString(KEY_LOCATION, location);
        editor.commit();
    }

    public String getLocation(){
        return preferences.getString(KEY_LOCATION, null);
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }
}
