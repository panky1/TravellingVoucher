package com.bcil.travellingvoucher.controllers;


import com.bcil.travellingvoucher.utils.AppConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ninad Gawankar on 25-07-2017.
 */

public class FileController {

    public static String[] readSettingFile(String settingFilePath, String settingFile) throws IOException {
        try {
            String[] credentials = new String[2];
            File fin = new File(settingFilePath + settingFile);
            FileInputStream fis = new FileInputStream(fin);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            int i = 0;
            while ((line = br.readLine()) != null) {
//                if(i<2){
                    if(!line.equals(AppConstants.EMPTY)){
                        String[] tempLine1 = line.split("~");
                        credentials[i] = tempLine1[1];
//                        i++;
                    }

//                    break;
//                }

            }
            br.close();
            return credentials;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{e.toString()};
        }

    }
}