package org.example.utils.helpers;

import org.example.utils.logger.LogController;

import java.io.File;

public class FileHelper {
    protected static LogController LOGGER = new LogController(FileHelper.class);

    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    public static void createDir(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
