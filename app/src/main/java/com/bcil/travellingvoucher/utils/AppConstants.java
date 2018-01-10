package com.bcil.travellingvoucher.utils;

import android.os.Environment;

/**
 * Created by NG on 21-Sep-2017.
 */

public class AppConstants {
    public static final String DirectoryName = "Printing";
    public static final int MY_PERMISSIONS_REQUEST_WRITE_FILE = 123;
    public static final int READ_BLOCK_SIZE = 512;//Block Size for text File
    public static final String FileName = "ipinfo.txt";
    public static final String PORTNUMBER = "PORTNUMBER";
    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";
    public static final String LOGIN = "Login";
    public static final String CONNECTEDNETWORKIP = "192.168.1.125";
    public static final String CSV1STATUS = "CSV1STATUS";
    public static final String CSV2STATUS = "CSV2STATUS";
    public static final String STATUS = "STATUS";
    public static final String SELECTED_MAC_ADDRESS = "SELECTED_MAC_ADDRESS";
    public static final String PRINTERSTATUS = "PRINTERSTATUS";
    public static final String SETTING_FILE_PATH = Environment.getExternalStorageDirectory() + "/BCIL/Settings/";
    public static final String EMPTY = "";
    public static final String MAC_ADDRESS = "AC:3F:A4:1B:1B:65";
    public static String IPADDRESS;
}
