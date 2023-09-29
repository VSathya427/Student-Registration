package studentCoursesMgmt.util;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class to do all the file processing
 */

public class FileProcessor {
    /**
     * method to read a single line and return it
     * @param line takes line to read as parameter
     * @return String returns line
     */
    public static String readLine(Scanner line){
        return line.nextLine();
    }
    /**
     * Mehtod to write error to File
     * @param file file to write error to
     * @param e exception to write to file
     */
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
    /**
     * Method to write output to file 
     * @param file file to write output to
     * @return PrintStream to which the output needs to be redirected
     */
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
     /**
      * method to clean all the contents of a file
      * @param file file to clean the content of
      */
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
