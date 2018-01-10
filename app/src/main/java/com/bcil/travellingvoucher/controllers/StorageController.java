package com.bcil.travellingvoucher.controllers;

import android.app.Activity;
import android.os.Environment;


import com.bcil.travellingvoucher.utils.PermissionsProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Ninad Gawankar on 04-08-2016.
 */

public class StorageController {
    public StorageController(Activity activity) {
        //
        PermissionsProvider.verifyStoragePermissions(activity);
    }

    //
    public boolean checkFile(String file_path, String file_name) {
        try {
            File file = new File(file_path);
            return file.exists();
        } catch (Exception e) {
            return false;
        }
    }

    //
    public static boolean checkSettingFile(String file_path, String file_name) {
        try {
            File file = new File(file_path + "/" + file_name);
            return file.exists();
        } catch (Exception e) {
            return false;
        }
    }


    public boolean copyFile(String file_name, String source, String destination) {
        try {
            String src = Environment.getExternalStorageDirectory().getPath() + "/" + source + "/" + file_name;
            String dst = Environment.getExternalStorageDirectory().getPath() + "/" + destination + "/" + file_name;
            InputStream inStream;
            inStream = null;
            OutputStream outStream = null;
            try {
                File src_file = new File(src);
                File dst_file = new File(dst);
                inStream = new FileInputStream(src_file);
                outStream = new FileOutputStream(dst_file);
                byte[] buffer = new byte[1024];
                int length;
                //copy the file content in bytes
                while ((length = inStream.read(buffer)) > 0) {
                    outStream.write(buffer, 0, length);
                }
                inStream.close();
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //
    public boolean deleteFile(String file_path, String file_name) {
        try {
            File src_file = new File(file_path + "/" + file_name);
            src_file.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //
    public boolean moveFile(String file_name, String source, String destination) {
        try {
            String src = Environment.getExternalStorageDirectory().getPath() + "/" + source + "/" + file_name;
            String dst = Environment.getExternalStorageDirectory().getPath() + "/" + destination + "/" + file_name;
            InputStream inStream;
            inStream = null;
            OutputStream outStream = null;
            try {
                File src_file = new File(src);
                File dst_file = new File(dst);
                inStream = new FileInputStream(src_file);
                outStream = new FileOutputStream(dst_file);
                byte[] buffer = new byte[1024];
                int length;
                //copy the file content in bytes
                while ((length = inStream.read(buffer)) > 0) {
                    outStream.write(buffer, 0, length);
                }
                inStream.close();
                outStream.close();
                //delete the original file
                src_file.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //
    public boolean copyDirectory(File sourceLocation, File targetLocation) {
        try {
            if (sourceLocation.isDirectory()) {
                if (!targetLocation.exists()) {
                    targetLocation.mkdir();
                }
                String[] children = sourceLocation.list();
                for (int i = 0; i < children.length; i++) {
                    copyDirectory(new File(sourceLocation, children[i]),
                            new File(targetLocation, children[i]));
                }
                return true;
            } else {
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);
                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    //
    public static String getStoragePath(String fileName) {
        return Environment.getExternalStorageDirectory().getPath().toString() + "/infiniti/" + fileName;
    }
}
