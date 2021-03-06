package com.bcil.travellingvoucher.controllers;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Ninad Gawankar on 02-08-2016.
 */
public class AssetsController {
    String FILE_NAME, FILE_PATH;
    private static Context mContext;

    public AssetsController(Context mContext) {
        this.mContext = mContext;
    }

    public static void copyAssets(Context context, String FileName, String folderName) {
        try {
            File settingFilePath = new File(folderName);
            if (!settingFilePath.exists()) {
                settingFilePath.mkdirs();
            }
        } catch (Exception e) {
            Log.e("tag", "Failed to create folder.", e);
        }
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null) {
            for (String filename : files) {
                if (filename.equals(FileName)) {
                    InputStream in = null;
                    OutputStream out = null;
                    try {
                        in = assetManager.open(filename);
                        File outFile = new File(folderName + "/", filename);
                        out = new FileOutputStream(outFile);
                        copyFile(in, out);
                    } catch (IOException e) {
                        Log.e("tag", "Failed to copy asset file: " + filename, e);
                    } finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                                // NOOP
                            }
                        }
                        if (out != null) {
                            try {
                                out.close();
                            } catch (IOException e) {
                                // NOOP
                            }
                        }
                    }
                }
            }
        }
    }


    public static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
}
