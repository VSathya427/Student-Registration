package studentCoursesMgmt.util;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class FileProcessor {
    public void readcoursePrefs(String file1, String errFile)throws FileNotFoundException{
        try{
            File f1 = new File(file1);
            Scanner reader1 = new Scanner(f1);
            while(reader1.hasNextLine()){
                System.out.println(reader1.nextLine());
            }
            reader1.close();
        }
        catch(FileNotFoundException e){
             PrintStream printStream = new PrintStream(errFile);
             System.setErr(printStream);
             //e.printStackTrace();
             System.err.println("Error in reading coursePrefs File");
        }
    }
    public void readcourseInfo(String file2){
        try{
            File f2 = new File(file2);
            Scanner reader2 = new Scanner(f2);
            while(reader2.hasNextLine()){
                System.out.println(reader2.nextLine());
            }
            reader2.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
