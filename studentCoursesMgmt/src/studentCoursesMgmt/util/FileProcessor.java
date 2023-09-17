package studentCoursesMgmt.util;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class FileProcessor {
    public void readcoursePrefs(String file1){
        try{
            File f1 = new File(file1);
            Scanner reader1 = new Scanner(f1);
            while(reader1.hasNextLine()){
                System.out.println(reader1.nextLine());
            }
            reader1.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public void readcourseInfo(String f2){

    }
}
