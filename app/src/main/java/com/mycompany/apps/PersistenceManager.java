package com.mycompany.apps;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rspl-nishant on 28/3/16.
 */
public class PersistenceManager {


    private static final String  LOCAL_PREFERENCES = "retail-perf";
    private static final String STORE_ID_KEY = "Store_Id";
    private static final String SYNC_DONE_ATLEAST_ONCE_KEY = "sync-done-once";

    public static void saveStoreId(Context context, String storeId) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences( LOCAL_PREFERENCES,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( STORE_ID_KEY, storeId );
        editor.commit();
    }

    public static String getStoreId(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(
                LOCAL_PREFERENCES,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString( STORE_ID_KEY, "store-id-not-saved-yet");
    }

    public static void setSyncDone(Context context, boolean syncDone) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOCAL_PREFERENCES,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SYNC_DONE_ATLEAST_ONCE_KEY, syncDone );
        editor.commit();
    }

    public static boolean wasSyncDoneAtleastOnce(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(
                LOCAL_PREFERENCES,
                Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean( SYNC_DONE_ATLEAST_ONCE_KEY, false);
    }




}


