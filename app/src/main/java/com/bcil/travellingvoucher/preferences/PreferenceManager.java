package com.bcil.travellingvoucher.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by NG on 21-Sep-2017.
 */

public class PreferenceManager {


    private Context context;
    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    public final String MY_PREFS_NAME = "myPreference";


    //User Details

    public PreferenceManager(Context context) {
        this.context = context;
        this.editor = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE).edit();
        this.prefs = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE);

    }

    public void putPreferenceBoolValues(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String putPreferenceValues(String key, String value) {
        editor.putString(key, value);
        editor.commit();
        return key;
    }


    public void putPreferenceIntValues(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    /* public void putPreferencefloatValues(String key, float value) {
         editor.putFloat(key, value);
         editor.commit();
     }

     public float getPreferencefloatValues(String key) {
         return prefs.getFloat(key, 0);
     }
 */
    public boolean getPreferenceBoolValues(String key) {
        return prefs.getBoolean(key, true);
    }

    public int getPreferenceIntValues(String key) {
        return prefs.getInt(key, 0);
    }

    public String getPreferenceValues(String key) {
        return prefs.getString(key, "");
    }

    public void clearSharedPreferance() {
        prefs.edit().clear().commit();
    }

    public void putPreferencDoubleValues(String key, double value) {
        editor.putLong(key, Double.doubleToRawLongBits(value));
        editor.commit();
    }

    public double getPreferencDoubleValues(String key, double defaultValue) {
        return Double.longBitsToDouble(prefs.getLong(key, Double.doubleToLongBits(defaultValue)));
    }


}

