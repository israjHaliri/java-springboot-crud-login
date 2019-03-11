package com.haliri.israj.appcore.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by f.putra on 8/25/17.
 */
public class AppUtils {

    private static class AppHolder {
        private static final AppUtils INSTANCE = new AppUtils();
    }

    public static AppUtils getInstance() {
        return AppHolder.INSTANCE;
    }

    public static Logger getLogger(Object o) {
        return LoggerFactory.getLogger(o.getClass());
    }

    public static String getDateByFormat(String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getDateByFormat(String format, int val){
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(yesterdayDate(val));
    }

    private static Date yesterdayDate(int val) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, val);
        return cal.getTime();
    }

    public static String removeString(String value, HashSet<String> character){
        for (String chr : character) {
            String tmp = value.replace(chr,"");
            value = tmp;
        }
        return value;
    }

    public static String formatDecimal(float number) {
        float epsilon = 0.004f;
        if (Math.abs(Math.round(number) - number) < epsilon) {
            return String.format("%10.0f", number);
        } else {
            return String.format("%10.2f", number);
        }
    }

    public static String getDateTillSecondTrim(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static Boolean saveImageBase64(String imageName, String imageString, String pathFile) {
        File tempDir = new File(pathFile);
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }

        byte[] decodedImg = Base64.getDecoder().decode(imageString.getBytes(StandardCharsets.UTF_8));
        Path destinationFile = Paths.get(pathFile, imageName);
        try {
            Files.write(destinationFile, decodedImg);
            return Boolean.TRUE;
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public static Boolean deleletFile(String nameFile, String pathFile) {
        File tempDir = new File(pathFile);
        File finalDestination = new File(tempDir + File.separator + nameFile);
        if (finalDestination.delete()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
