package com.example.currencychange.Model;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefrencesHelper  {
    public static final String FROM_CODE = "from" ;
    public static  final String TO_CODE = "to" ;
    public static final String FROM_NM_CODE = "from_nm" ;
    public static  final String TO_NM_CODE = "to_nm" ;
    public static  final String RATE_CODE = "rate" ;
    public static  final String STATUS_CODE = "status" ;
    public static  final String TRACK_CODE = "track" ;

    private static   SharedPreferences preferences ;

    public static boolean getStatus(Context context) {
        preferences = context.getSharedPreferences(STATUS_CODE, Context.MODE_PRIVATE);
        return preferences.getBoolean(STATUS_CODE, false);

    }

    public static String getFrom(Context context) {
         preferences = context.getSharedPreferences(FROM_CODE, Context.MODE_PRIVATE);
        return preferences.getString(FROM_CODE, "ILS");
    }


    public static String getTo(Context context) {
        preferences = context.getSharedPreferences(TO_CODE, Context.MODE_PRIVATE);
        return preferences.getString(TO_CODE, "USD");
    }

    public static String getFromNmCode(Context context) {
        preferences = context.getSharedPreferences(FROM_NM_CODE, Context.MODE_PRIVATE);
        return preferences.getString(FROM_NM_CODE, "PS");
    }


    public static String getToNmCode(Context context) {
        preferences = context.getSharedPreferences(TO_NM_CODE, Context.MODE_PRIVATE);
        return preferences.getString(TO_NM_CODE, "US");
    }



    public static void setRate(Context context , double rate) {
        preferences = context.getSharedPreferences(RATE_CODE, Context.MODE_PRIVATE);
        if(getRate(context)>=rate) setStatus(context,false);
        else setStatus(context , true);
      preferences.edit().putString(RATE_CODE,rate+"").commit();
    }



    public static void setFrom(Context context , String from) {
        preferences = context.getSharedPreferences(FROM_CODE, Context.MODE_PRIVATE);

        preferences.edit().putString(FROM_CODE,from).commit();
    }

    public static void setTo(Context context , String to) {
        preferences = context.getSharedPreferences(TO_CODE, Context.MODE_PRIVATE);

        preferences.edit().putString(TO_CODE,to).commit();
    }

    public static void setFromNmCode(Context context , String from) {
        preferences = context.getSharedPreferences(FROM_NM_CODE, Context.MODE_PRIVATE);

        preferences.edit().putString(FROM_NM_CODE,from).commit();
    }

    public static void setToNmCode(Context context , String to) {
        preferences = context.getSharedPreferences(TO_NM_CODE, Context.MODE_PRIVATE);

        preferences.edit().putString(TO_NM_CODE,to).commit();
    }


    private static void setStatus(Context context , boolean isRaised) {
        preferences = context.getSharedPreferences(STATUS_CODE, Context.MODE_PRIVATE);

        preferences.edit().putBoolean(STATUS_CODE,isRaised).commit();
    }

    public static void setTrack(Context context , boolean isTracked) {
        preferences = context.getSharedPreferences(TRACK_CODE, Context.MODE_PRIVATE);

        preferences.edit().putBoolean(TRACK_CODE,isTracked).commit();
    }
    public static double getRate(Context context) {
        preferences = context.getSharedPreferences(RATE_CODE, Context.MODE_PRIVATE);
        return Double.parseDouble(preferences.getString(RATE_CODE, "0.0"));
    }

    public static boolean getTrack(Context context) {
        preferences = context.getSharedPreferences(TRACK_CODE, Context.MODE_PRIVATE);
        return preferences.getBoolean(TRACK_CODE, false);

    }
}
