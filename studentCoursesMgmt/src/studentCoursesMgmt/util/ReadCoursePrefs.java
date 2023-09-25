package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.HashMap;

public class ReadCoursePrefs implements ReadFile<Integer,String[]>{
    public HashMap<Integer,String[]> readFile(String file,String errFile)throws FileNotFoundException{
        HashMap<Integer,String[]> data = new HashMap<Integer,String[]>();    
        try{
            int linecount = 0;
            File f1 = new File(file);
            Scanner reader1 = new Scanner(f1);
            while(reader1.hasNextLine()){
                String[] s = reader1.nextLine().split(" ");
                String[] pref = processCoursePrefs(s);
                //System.out.println(reader1.nextLine());
                int id = Integer.parseInt(s[0]);
                data.put(id,pref);
                linecount++;
                if(linecount == 5){
                    
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
    public String[] processCoursePrefs(String[] s){
        //String[] s = line.split(" ");
        String[] prefs = new String[9];
        int j=0;
        if(s.length<10 && s.length>10){
            System.err.println("Input not in required format");
        }
        else{
            s[s.length-1] = s[s.length-1].substring(0, 1);
            for(int i=1;i<s.length-1;i++){
                prefs[j] = s[i];
                j++;
            }
        }
        return prefs;
    }
}
