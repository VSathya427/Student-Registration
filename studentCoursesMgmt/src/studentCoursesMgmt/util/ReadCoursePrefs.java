package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadCoursePrefs implements ReadFile<Integer,List<String>,List<String>>{
    public HashMap<Integer,List<String>> readFile(String file,String errFile)throws FileNotFoundException{
        HashMap<Integer,List<String>> data = new HashMap<Integer,List<String>>();    
        try{
            int linecount = 0;
            File f1 = new File(file);
            Scanner reader1 = new Scanner(f1);
            while(reader1.hasNextLine()){
                String[] s = reader1.nextLine().split(" ");
                List<String> pref = processFile(s);
                //System.out.println(reader1.nextLine());
                int id = Integer.parseInt(s[0]);
                data.put(id,pref);
                linecount++;
                if(linecount == 5){
                    data.clear();

                }
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
        System.out.println("CoursePrefs : "+data);
        return data;
    }
    public List<String> processFile(String[] s){
        //String[] s = line.split(" ");
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
