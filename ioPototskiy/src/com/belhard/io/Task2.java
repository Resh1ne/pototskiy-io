package com.belhard.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.regex.Pattern;

public class Task2 {

    public static void main(String[] args) {
        Path in = Paths.get("resources", "in");
        Path out = Paths.get("resources", "out");

        copyFile(in, out);
    }

    private static void copyFile(Path in, Path out) {
        Pattern pattern = Pattern.compile(".*\\.(jpg|jpeg)");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(in)) {
            for (Path file : stream) {
                if (pattern.matcher(file.getFileName().toString()).matches()) {
                    Path destinationFile = out.resolve(file.getFileName());

                    Files.copy(file, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                    log(out.resolve("logTask2.txt"), destinationFile);
                    System.out.println("Скопирован файл: " + file.getFileName());

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void log(Path outInFile, Path file) {
        try (OutputStream os = Files.newOutputStream(outInFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            String str = new Date().toString() + "    " + Files.size(file) + "bytes" + "    " + file.getFileName() + '\n';
            byte[] bytes = str.getBytes();
            os.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}