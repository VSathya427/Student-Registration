package studentCoursesMgmt.util;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class FileProcessor {
    public static String readLine(Scanner line){
        return line.nextLine();
    }
    public static void writeErrorToFile(String file,Exception e){
        try {
            PrintStream printStream = new PrintStream(file);
            System.setErr(printStream);
            e.printStackTrace();
            printStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

     public static PrintStream writeOutputToFile(String file){
        PrintStream printStream=null;
        try {
            printStream = new PrintStream(new FileOutputStream(file, true));
            System.setOut(printStream);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return printStream;
     }
     public static void cleanFile(String file){
        try {
            PrintWriter cleaner = new PrintWriter(file);
            cleaner.print("");
            cleaner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
}
