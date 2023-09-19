package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class ReadCoursePrefs implements ReadFile{
    public void readFile(String file,String errFile)throws FileNotFoundException{
        try{
            File f1 = new File(file);
            Scanner reader1 = new Scanner(f1);
            while(reader1.hasNextLine()){
                System.out.println(reader1.nextLine());
            }
            reader1.close();
        }
        catch(FileNotFoundException e){
            System.err.println("Error in reading coursePrefs File. For Stack Trace check errorLog.txt");
            PrintStream printStream = new PrintStream(errFile);
            System.setErr(printStream);
            e.printStackTrace();
            System.exit(0);
        }
    }
}
