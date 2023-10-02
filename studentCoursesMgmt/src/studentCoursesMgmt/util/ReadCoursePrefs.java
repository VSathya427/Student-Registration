package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ReadCoursePrefs implements ReadFile<Integer,List<String>,List<String>>{
    //method to readcourseprefs file and store it in a hashmap.
    public HashMap<Integer,List<String>> readFile(String file,String errFile){
        HashMap<Integer,List<String>> data = new HashMap<Integer,List<String>>();    
        try{
            File f1 = new File(file);
            Scanner reader1 = new Scanner(f1);
            while(reader1.hasNextLine()){
                String[] s = FileProcessor.readLine(reader1).split(" ");
                List<String> pref = processFile(s);
                int id = Integer.parseInt(s[0]);
                if(id<100 || id>999){
                    throw new BoundaryConditionCheckException("Student Id should be between 100 and 999");
                }
                data.put(id,pref);
            }
            reader1.close();
        }
        catch(FileNotFoundException e){
            System.err.println("Error in reading course preferences File. For Stack Trace check errorLog.txt");
            FileProcessor.writeErrorToFile(errFile,e);
            System.exit(0);
        }
        catch (Exception e) {
            System.err.println( "Error in coursePrefs input format. Format input and run code again. For Stack Trace check errorLog.txt");
            FileProcessor.writeErrorToFile(errFile, e);
            System.exit(0);
        }
        return data;
    }
    //method to process the file and return a list of preferences
    public List<String> processFile(String[] s){
        String[] prefs = new String[9];
        List<String> courses = new ArrayList<>();
        int j=0;
        if(s.length<10 && s.length>10){
            System.err.println("Input not in required format");
        }
        else{
            s[s.length-1] = s[s.length-1].replace(";","");
            for(int i=1;i<s.length;i++){
                prefs[j] = s[i];
                courses.add(s[i]);
                j++;
            }
        }
        return courses;
    }
}
