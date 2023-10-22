package com.belhard.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.belhard.utils.Util;

public class Task1 {
    public static void main(String[] args) {
        File file = new File("resources", "in");
        Pattern pattern = Pattern.compile(".(jpeg|jpg)");

        for (String fileName : file.list()) {
            Matcher matcher = pattern.matcher(fileName);
            if (matcher.find()) {
                Util.copyFileUseStream("resources/in/" + fileName, "resources/out/" + fileName);
                log("resources/out/logTask1.txt", fileName);
            }
        }
    }

    private static void log(String path, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(path, true)) {
            String str = new Date().toString() + " | " + fileName + '\n';
            byte[] bytes = str.getBytes();
            fos.write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
