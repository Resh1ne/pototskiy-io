package com.belhard.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Util {
    public static String fullReadFile(String path) {
        StringBuilder content = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(path); //
                BufferedInputStream bis = new BufferedInputStream(fis)) {
            byte[] bytes = bis.readAllBytes();
            content.append(new String(bytes));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void streamReadFile(String path) {
        try (FileInputStream in = new FileInputStream(path)) {
            int data;
            byte[] buffer = new byte[1024];
            while ((data = in.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, data));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFileUseStream(String input, String out) {
        try (FileInputStream fis = new FileInputStream(input);//
                FileOutputStream fos = new FileOutputStream(out);//
                ) {
            int data;
            byte[] buffer = new byte[1024];
            while ((data = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, data);
            }           

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
