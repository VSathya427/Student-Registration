package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
/**
 * class to read file in which course info is stored
 */
public class ReadCourseInfo implements ReadFile<String,CourseInfo,CourseInfo> { 
    //Method to read file and store it in a hashmap.
    public HashMap<String,CourseInfo> readFile(String file,String errFile){
        HashMap<String,CourseInfo> info = new HashMap<String,CourseInfo>();
        try{
            File f1 = new File(file);
            Scanner reader1 = new Scanner(f1);
            while(reader1.hasNextLine()){
                String s = FileProcessor.readLine(reader1);
                String[] data= s.split(":");
                info.put(data[0],processFile(data));
            }
            reader1.close();
        }
        catch(FileNotFoundException e){
            System.err.println("Error in reading courseInfo File. For Stack Trace check errorLog.txt");
            FileProcessor.writeErrorToFile(errFile, e);
            System.exit(0);
        }
        catch(Exception e){
            System.err.println("Error in Course Information input format. Format input and run code again. For Stack Trace check errorLog.txt");
            FileProcessor.writeErrorToFile(errFile, e);
            System.exit(0);
        }
        return info;
    }
    //method to set course capacity and time.
    public CourseInfo processFile(String[] line){
        CourseInfo ci=new CourseInfo();
        ci.setCapacity(Integer.parseInt(line[1]));
        ci.setTime(Integer.parseInt(line[2]));
        return ci;
    }
}
