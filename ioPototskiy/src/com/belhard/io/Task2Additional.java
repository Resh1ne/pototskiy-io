package com.belhard.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2Additional {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String in = "resources/in/Files.java";
        String out = "resources/out/Files.java";

        System.out.println("1: Copying a file with comments\n2: Copying a file without comments");
        int number = userInputInt(scanner);
        copyFile(number, in, out);
    }

    private static void deleteComments(PrintWriter pw, String line) {
        Pattern pattern = Pattern.compile("(/\\*\\*|//.*|/\\*|\\A\\p{Blank}*\\*.*)");
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
            pw.println(line);
        }
    }

    private static void copyFile(int number, String in, String out) {
        try (FileReader fr = new FileReader(in);
                BufferedReader br = new BufferedReader(fr);
                PrintWriter pw = new PrintWriter(out)) {

            String line;

            if (number == 1) {
                while ((line = br.readLine()) != null) {
                    pw.println(line);
                }
            } else if (number == 2) {
                while ((line = br.readLine()) != null) {
                    deleteComments(pw, line);
                }
            } else {
                throw new RuntimeException("Invalid input, enter 1 or 2");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int userInputInt(Scanner scan) {
        int a;
        if (scan.hasNextInt()) {
            a = scan.nextInt();
        } else {
            throw new RuntimeException("You did not enter an integer!");
        }
        return a;
    }
}
