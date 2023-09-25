package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;
//import studentCoursesMgmt.util.CourseInfo;

public class ReadCourseInfo implements ReadFile<String,CourseInfo> {
    
    public HashMap<String,CourseInfo> readFile(String file,String errFile)throws FileNotFoundException{
        HashMap<String,CourseInfo> info = new HashMap<String,CourseInfo>();
        try{
            File f1 = new File(file);
            Scanner reader1 = new Scanner(f1);
            while(reader1.hasNextLine()){
                String s = reader1.nextLine();
                String[] data= s.split(":");
                info.put(data[0],processCourseInfo(data));
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
        System.out.println("CourseInfo : " + info);
        return info;
    }
    public CourseInfo processCourseInfo(String[] line){
        CourseInfo ci=new CourseInfo();
        ci.setCapacity(Integer.parseInt(line[1]));
        ci.setTime(Integer.parseInt(line[2]));
        return ci;
    }
}
