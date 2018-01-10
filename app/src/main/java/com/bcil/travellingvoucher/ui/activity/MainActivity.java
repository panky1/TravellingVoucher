package com.bcil.travellingvoucher.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.inputmethodservice.Keyboard;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bcil.travellingvoucher.R;
import com.bcil.travellingvoucher.controllers.AssetsController;
import com.bcil.travellingvoucher.controllers.StorageController;
import com.bcil.travellingvoucher.database.DatabaseHandler;
import com.bcil.travellingvoucher.preferences.PreferenceManager;
import com.bcil.travellingvoucher.utils.AppConstants;

/*import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;*/
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
/*import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;*/
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import static com.bcil.travellingvoucher.utils.AppConstants.MY_PERMISSIONS_REQUEST_WRITE_FILE;

public class MainActivity extends AppCompatActivity {

    private PreferenceManager preferenceManager;
    private DatabaseHandler databaseHandler;
    private String getxlsxfilename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler = new DatabaseHandler(MainActivity.this);
        preferenceManager = new PreferenceManager(MainActivity.this);
        boolean result = checkpermission();
        if (result) {
            createFile();
        }
        initView();
        initData();
    }

    private void createFile() {
        copySettings("TravellingVoucherFormat.xlsx");
    }

    private void copySettings(String settingsFile) {
        try {
            if (!StorageController.checkSettingFile(AppConstants.SETTING_FILE_PATH, settingsFile)) {
                //File settingFile = new File();
                AssetsController.copyAssets(MainActivity.this, settingsFile, AppConstants.SETTING_FILE_PATH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkpermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if(currentAPIVersion>=android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("Write file permission is necessary to read info!!!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_FILE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_FILE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


    private void initData() {
        try {
            getFileLocation();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            readXlsxFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }


    }

    private void readXlsxFile() throws IOException, InvalidFormatException {
        FileInputStream file = new FileInputStream(new File(Environment.getExternalStorageDirectory()
                + "/BCIL/Settings/", getxlsxfilename));
//        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new File(Environment.getExternalStorageDirectory()+"/BCIL/Settings/", getxlsxfilename));
    }

    private void getFileLocation() throws FileNotFoundException {
        File fileDirectory = new File(Environment.getExternalStorageDirectory()+ "/BCIL/Settings/");
        File[] dirFiles = fileDirectory.listFiles();
        getxlsxfilename = dirFiles[0].getName();
    }

    private void initView() {


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {


        if(requestCode == MY_PERMISSIONS_REQUEST_WRITE_FILE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                createFile();
            } else {
                //code for deny
                Toast.makeText(MainActivity.this,"Permission is denied",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
