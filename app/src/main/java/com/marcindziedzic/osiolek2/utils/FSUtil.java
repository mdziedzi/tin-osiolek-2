package com.marcindziedzic.osiolek2.utils;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FSUtil {

    public static List<File> getFilesFromLocalDirectory(final File folder, ArrayList<File> files) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                getFilesFromLocalDirectory(fileEntry, files);
            } else {
                System.out.println(fileEntry.getName());
                Log.i("files", fileEntry.getName());
                files.add(fileEntry);
            }
        }
        return files;
    }

    public static void initializeAppDirectory() {
        // File sdcard = Environment.getExternalStorageDirectory();
        File sdcard = new File("/storage/emulated/0/Download");
        // to this path add a new directory path
        File dir = new File(sdcard.getAbsolutePath() + "/osiolek/");
        // create this directory if not already created
        dir.mkdir();
        // create the file in which we will write the contents
        File file = new File(dir, "My-File-Name.txt");
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String data = "This is the content of my file";
        try {
            if (os != null) {
                os.write(data.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (os != null) {
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
