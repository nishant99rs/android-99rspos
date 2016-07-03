package com.mycompany.apps;



import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.godbtech.sync.GBrowserNotifyMessageEvt;
import com.godbtech.sync.GNativeSync;
import com.godbtech.sync.GSyncServerConfig;
import com.godbtech.sync.GSyncStatusEvt;
import com.godbtech.sync.GSyncable;
import com.godbtech.sync.GUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SyncService extends Service  {

    private static final String TAG = SyncService.class.getSimpleName();
    Intent syncIntent;


    GoDBSyncAlarmManager goDBSyncAlarmManager = new GoDBSyncAlarmManager();

    EditText statusEditText = null;
    private boolean    syncInProgress = false;
    private boolean    didSyncSucceed = false;


    //private GSyncStatusHandler gSyncStatusHandler = new GSyncStatusHandler();
    //private GBrowserNotifyHandler gBrowserNotifyHandler = new GBrowserNotifyHandler();
    // private GSyncResultHandler gSyncResultNotifyHandler = new GSyncResultHandler();
    // private GSyncItemStatusHandler gSyncItemStatusHandler = new GSyncItemStatusHandler();


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if( null == intent )
        {
            Log.d( "GoDBSyncService", "This is a System Restart");
            /* restart the service with startService().
             * This will ensure that the delay between system force-stop and restart
             * is always 5 seconds
             */
            startService(new Intent(getBaseContext(), SyncService.class));

        }

        /*Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();*/
        loadSyncLibrary();
        syncIntent=new Intent();
        syncIntent.putExtras(getSyncBudle());
        syncInProgress = true;
        didSyncSucceed = false;
        doSync(syncIntent);
        goDBSyncAlarmManager.setAlarm(this);
        Log.e("^^^^^^^1^^^^^^", goDBSyncAlarmManager.toString());
        return START_STICKY;
    }




    @Override
    public void onDestroy() {
        Log.d("GoDBSyncService", "In onDestroy");
        goDBSyncAlarmManager.cancelAlarm(this);
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Load sync library.
     */
    private void loadSyncLibrary()
    {
        try
        {
            String trgLib = "gSyncDLL";
            System.loadLibrary(trgLib);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("GSync", e.getMessage());
        }
    }


//    public class GoDBSyncAlarmManager extends BroadcastReceiver
//
//    {
//
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.d( TAG, "ALarm fired" );
//            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//            PowerManager.WakeLock wirellock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
//            wirellock.acquire();
//            doSync(syncIntent);
//            wirellock.release();
//
//        }
//        public void setAlarm(Context context)
//        {
//            Log.d(TAG, "Setting Alarm" );
//            AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//            Intent intent = new Intent(context, GoDBSyncAlarmManager.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
//            doSync(syncIntent);
//            am.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(), 10000, pendingIntent); // Millisec * Second * Minute
//
//        }
//
//        public void cancelAlarm(Context context)
//        {
//            Intent intent = new Intent(context, GoDBSyncAlarmManager.class);
//            PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
//            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//            alarmManager.cancel(sender);
//        }
//
//
//    }
//





    protected GSyncServerConfig getSyncConfigFromSyncIntent(Intent syncIntent)
    {
        GSyncServerConfig gCfg = new GSyncServerConfig();
//        doSync(syncIntent);
        gCfg.setSyncServerAddress(syncIntent.getStringExtra("syncServerIP"));// set SyncServer Address, can be IP
        gCfg.setSyncServerPort(syncIntent.getIntExtra("syncServerPort", 80));// set SyncServer Port
        gCfg.setSyncServerBasePath(syncIntent.getStringExtra("syncServerBasePath"));// set Base path
        gCfg.setSyncServerUsername(syncIntent.getStringExtra("syncServerUsername"));
        gCfg.setSyncServerPassword(syncIntent.getStringExtra("syncServerPassword"));
        gCfg.setSyncServerUserID(syncIntent.getStringExtra("syncServerUserID"));
        gCfg.setLogEnabled(true);//enable logging
        gCfg.setDBName(syncIntent.getStringExtra("dbName"));//db name
        gCfg.setSyncMode(syncIntent.getIntExtra("syncMode", 1));// set Synchronization to full sync if missing
        gCfg.setUseProxy(syncIntent.getBooleanExtra("proxyEnabled", false));
        gCfg.setHttpProxy(syncIntent.getStringExtra("httpProxy"));
        gCfg.setHttpProxyPort(syncIntent.getIntExtra("httpProxyPort", 8080));
        gCfg.setSockConnectTimeoutMillis(syncIntent.getIntExtra("sockConnectTimeoutMillis", 0));
        gCfg.setSockSendTimeoutMillis(syncIntent.getIntExtra("sockSendTimeoutMillis", 0));
        gCfg.setSockRecvTimeoutMillis(syncIntent.getIntExtra("sockRecvTimeoutMillis", 0));
        gCfg.setD4S(syncIntent.getStringExtra("d4s"));
        gCfg.setChunkedTableList(syncIntent.getStringExtra("chunkedTableList"));
        gCfg.setMaxRecChunkSize(syncIntent.getIntExtra("maxRecChunkSize", 0));
        //Log.d("GSync", "Syncing with " + gCfg.toString());
        return gCfg;
    }

    private GSyncable mSyncableListener = new GSyncable() {
        @Override
        public void syncStatusEvent(GSyncStatusEvt statEvt) {
            String msg = statEvt.getMsg();
            if (msg == null)
                msg = "";
            Bundle b = new Bundle();
            b.putString("syncstatusmsg", msg);
            b.putInt("status1",statEvt.getTStatus1());
            b.putInt("status2max", statEvt.getStatus2Max());
            b.putInt("status2",statEvt.getStatus2());
            b.putInt("status3max",statEvt.getStatus3Max());
            b.putInt("status3",statEvt.getStatus3());
            Message m = Message.obtain();
            m.setData(b);
            Log.d(TAG, "Sync returned...with status " + statEvt.getTStatus1());
            Log.d( TAG, "Sync returned...with status " + b );

            if(statEvt.getTStatus1() == 100) {

                syncInProgress=false;
                didSyncSucceed=false;
                //m.setTarget(gSyncResultNoti);

            } else if(statEvt.getTStatus1() == 200) {
                didSyncSucceed=true;
                syncInProgress=false;
            }
          /*  else
                m.sendToTarget();*/
        }





        @Override
        public void browserNotifyEvent(GBrowserNotifyMessageEvt gBrowserNotifyMessageEvt) {

        }

        @Override
        public void itemStatusEvent(String s, String s1) {

        }
    };

    public void doSync(Intent syncIntent)
    {
        Log.d( TAG, "Started do Sync");
        GNativeSync gNSync = GNativeSync.getNativeSyncSingleton();
        //doSync(syncIntent);
        gNSync.addSyncListener( mSyncableListener );// important register sync listener to recieve notifications
        GSyncServerConfig gCfg = getSyncConfigFromSyncIntent(syncIntent);
        String rootPath = syncIntent.getStringExtra("dbName");
        if(rootPath==null || rootPath.lastIndexOf("/")<=0) return;
        rootPath = rootPath.substring(0, rootPath.lastIndexOf("/"));
        extractPEMFile(getAssets(), rootPath);//extracts on every launch, you can change it to extract only if file isnt present.
        clearViews();
        gNSync.startSync(gCfg);
        Log.d(TAG, "Sync Completed");
    }

    private void clearViews()
    {
        // statusEditText.setText("");
    }


    protected static Bundle getSyncBudle()
    {
        Bundle b = new Bundle();

        //db name, this will be available in that package
        //do not change this, this is the only directory where this activity can write to
        //specifying another directory will cause the sync to fail

        String dbName = "/data/data/" + GMainActivity.class.getPackage().getName() + "/sync.bdb.db";

      /*
      //if you are using encrypted SQLITE Sync agent use code below
      //Note using this code on an unencrypted SQlite Sync agent will not work
      //For obtaining SQLITE encrypted Sync Agent contact GoDB support
      //start
      String key     = "mykey";
      //NOTE: Important, For demonstration purposes key is being hardcoded here,
      //In production this key should not be available stored on the device
      //or hardcoded into the application. How to handle key is outside the scope
      //of this demo app.
      //start SQLITE ENCRYPTION
      String algorithm = "rc4";//RC4,AES128-OFB,AES258-OFB are currently Supported algorithms
      boolean shouldKey = true;
      if(Util.doesFileExist(dbName))
      {
         if(Util.isSqliteEncrypted(dbName))//if sqlite is already encrypted, lets rekey it
         {
            shouldKey = false;
            dbName = dbName + ";rekey="+algorithm+":"+key;
         }
      }
      if(shouldKey)
         dbName = dbName + ";key="+algorithm+":"+key;//if sqlite is not yet created, or is not encrypted, lets key it
      //end SQLITE ENCRYPTION
       */
        b.putString("dbName", dbName);
        b.putString("syncServerUsername",  "1454999957");//sync user name
        b.putString("syncServerPassword", GUtils.getSHADigest("SHA-256", "36@333"));//SHA-256 sync server password
        //b.putString("syncServerPassword",    GUtils.getMD5Hash("mypassword"));//MD5 server password
        b.putString("syncServerUserID",    "3");//if needed

        b.putInt("syncMode",            GSyncServerConfig.SYNC_DELTA);//delta sync
        b.putBoolean("logEnabled",           true);//logs "http.log", "sync.log", "syncstat.log" will be available in
        //folder /data/data/getClass().getPackage().getName()
        //after synchronization
        b.putString("syncServerIP",       "52.76.28.14");//sync server ip/domain
        //b.putString("syncServerIP",     "https://www.myserver.com");//sync server ip/domain if https is supported
        //if https is supported please make sure its "https://" + servername

        b.putInt("syncServerPort",           8080);//sync server http port
        //b.putInt("syncServerPort",      443);//sync server https port if SSL is enabled

        b.putString("syncServerBasePath",  "/godbss/");//sync server basepath ex: http://www.yourdomain.com/godbss/, here godbss is the basepath

        //socket parameters
        b.putInt("sockConnectTimeoutMillis", 5000);//five seconds
        b.putInt("sockSendTimeoutMillis",  30000);//30 seconds
        b.putInt("sockRecvTimeoutMillis",  30000);//30 seconds

        //proxy parameters if needed
        b.putBoolean("proxyEnabled",      false);
        b.putString("httpProxy",         "192.168.0.23");//only valid if proxyEnabled is set to true
        b.putInt("httpProxyPort",        8080);//only valid if proxyEnabled is set to true

        b.putString("d4s",                 "customparam1|customval1|customparam2|customval2");//D4S

        //b.putString("chunkedTableList",  "meap_order_item");//name of the table which you want to download in chunks
        //b.putInt("maxRecChunkSize",     10);//chunk size in number of records

        // b.putString("gcmProjectID",        GCM_PROJECT_ID);//only if you need GCM notifications

        return b;
    }



    void extractPEMFile(AssetManager mgr, String rootPath)
    {
        try
        {
            String[] filelist = mgr.list("");
            for(String asset: filelist)
            {
                if(asset.equalsIgnoreCase("ROOT.pem"))
                {
                    File f = new File(rootPath, "ROOT.pem");
                    FileOutputStream os = new FileOutputStream(f);
                    byte[] buf = new byte[200 * 1024];
                    int n;
                    InputStream is = mgr.open(asset);
                    while ((n = is.read(buf)) > 0)
                        os.write(buf, 0, n);
                    os.close();
                    is.close();
                }
            }
        }
        catch(Exception e)
        {
        }
    }}