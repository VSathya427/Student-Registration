package studentCoursesMgmt.util;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	public void processOutput(HashMap<Integer,Set<String>> output,String File){
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
                }    
                else{
                    course[index++] = s;
                }   
            }
            redirectToFile(id, course, satisfaction, File);
        }
    }
    public static void redirectToFile(int id, String[] course,double satisfaction, String File){       
        System.out.println("Ffejflke:"+File);
        PrintStream ps = FileProcessor.writeOutputToFile(File);
        System.out.print(id+" : "+course[2]+" : "+course[1]+" : "+course[0]+" :: "+"Satisfaction Rating = ");
        System.out.printf("%.2f ",satisfaction);
        System.out.println();
        ps.close();
    }
}
