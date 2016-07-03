package Pojo;

import android.net.Uri;
import android.provider.BaseColumns;

import com.mycompany.apps.DBhelper;

/**
 * Created by w7 on 2/6/2016.
 */
public class Loginpo {

    public static DBhelper db;
    static final String TABLE_NAME = "employees";

    public static final String AUTHORITY = "com.linkwithweb.providers.MyActivity";

    // BaseColumn contains _id.
    public static final class User implements BaseColumns {

        public static final Uri CONTENT_URI = Uri
                .parse("content://com.linkwithweb.providers.MyActivity");

        // Table column
        public static final String USER_NAME = "Usr_Nm";
        public static final String PASS = "Password";


    }




}
