package studentCoursesMgmt.util;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
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
        PrintStream ps = FileProcessor.writeOutputToFile(File);
        double calc = averageSatisfaction / count;
        if(!Double.isNaN(calc)){
            System.out.printf("AverageSatisfactionRating= %.2f ",calc);
        }
        ps.close();
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
}
