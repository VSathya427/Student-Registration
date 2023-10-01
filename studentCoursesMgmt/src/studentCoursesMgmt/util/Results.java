package studentCoursesMgmt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Class to calculate the results and display them

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	double averageSatisfaction;
    int count = 0;
    public Results(){
        averageSatisfaction = 0.0;
        count = 0;
    }
    //Method to process the output
    public void processOutput(HashMap<Integer,Set<String>> output,String File,int ch){
        for(Map.Entry<Integer,Set<String>> stu : output.entrySet()){
            int id = stu.getKey();
            Set<String> assignedSet= stu.getValue();
            Pattern pattern = Pattern.compile("[0-9]{1,13}(\\.[0-9]*)?");
            Double satisfaction=0.0;
            String[] course = new String[3];
            int index = 0;
            for(String s : assignedSet){
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    satisfaction = Double.valueOf(matcher.group());
                    averageSatisfaction += satisfaction;
                    count++;
                }    
                else{
                    course[index++] = s;
                }   
            }
            switch(ch){
                case 0: {
                    redirectToFile(id, course, satisfaction, File);
                    break;
                }
                case 1:{
                    stdOUT(id, course,satisfaction);
                    break;
                } 
                default : {
                    System.out.println("Select Either choice 0 or 1");
                    break;
                }

            }
        }
    }
    //Method to redirect output to File.
    public void redirectToFile(int id, String[] course,double satisfaction, String File){       
        PrintStream ps = FileProcessor.writeOutputToFile(File);
        System.out.print(id+" : "+course[2]+" : "+course[1]+" : "+course[0]+" :: "+"SatisfactionRating = ");
        System.out.printf("%.2f ",satisfaction);
        System.out.println();
        ps.close();
    }
    //Method to redirect output to stdout.
    public void stdOUT(int id, String[] course,double satisfaction){
        System.out.print(id+" : "+course[2]+" : "+course[1]+" : "+course[0]+" :: "+"Satisfaction Rating = ");
        System.out.printf("%.2f ",satisfaction);
        System.out.println();
    }
    public void printSatisfaction(String File){
        PrintStream ps = FileProcessor.writeOutputToFile(File);
        double calc = averageSatisfaction / count;
        if(!Double.isNaN(calc)){
            System.out.printf("AverageSatisfactionRating= %.2f ",calc);
        }
        ps.close();
    }
     public void fiveLine(HashMap<String,CourseInfo> info, String file,String resFile, String confFile, String errFile){
        HashMap<Integer, List<String>> data = new HashMap<Integer, List<String>>(); 
        try{
            File f1 = new File(file);
            Scanner reader1 = new Scanner(f1);
            int linecount = 0;
            ReadFile<Integer, List<String>, List<String>> rcp = new ReadCoursePrefs();
            StudentMgmt sm = new StudentMgmt();
            while(reader1.hasNextLine()){
                String[] s = FileProcessor.readLine(reader1).split(" ");
                List<String> pref = rcp.processFile(s);
                int id = Integer.parseInt(s[0]);
                data.put(id,pref);
                System.err.println(data);
                if(linecount == 5){
                    HashMap<Integer, Set<String>> schedule = sm.scheduleCourse(info, data, confFile,errFile ); 
                    processOutput(schedule, resFile, 0);
                    linecount=0;
                    data.clear();
                }
                else if(linecount<5 && !reader1.hasNextLine()){
                    HashMap<Integer, Set<String>> schedule = sm.scheduleCourse(info, data, confFile,errFile ); 
                    processOutput(schedule, resFile, 0);
                    linecount=0;
                    data.clear();
                }
                linecount++;
            }
            reader1.close();
            printSatisfaction(resFile);
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
     }
}
