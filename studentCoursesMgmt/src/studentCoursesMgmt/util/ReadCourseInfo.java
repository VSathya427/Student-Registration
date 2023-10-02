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
                if(!(data[0].charAt(0)>='A' && data[0].charAt(0)<='I')){
                    throw new BoundaryConditionCheckException("Letter Should be Between A and I");
                }
                if((Integer.parseInt(data[1])<10)||(Integer.parseInt(data[1])>99)){
                    throw new BoundaryConditionCheckException("Capacity should be between 10 and 99");
                }
                if((Integer.parseInt(data[2])<10)||(Integer.parseInt(data[2])>99)){
                     throw new BoundaryConditionCheckException("Course Time should be between 10 and 99");
                }
                info.put(data[0],processFile(data));
                if(info.size()>9){
                    throw new BoundaryConditionCheckException("Number of courses can't be more than 9");
                }
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
