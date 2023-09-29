package studentCoursesMgmt.util;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class StudentMgmt {
    public HashMap<Integer,Set<String>> scheduleCourse(HashMap<String,CourseInfo> courses,HashMap<Integer,List<String>> prefs, String conflFile,String errFile){
        HashMap<Integer,Set<String>> register = new HashMap<>();
        for (Map.Entry<Integer, List<String>> student : prefs.entrySet()) {
            int id = student.getKey();
            List<String> pref = student.getValue();
            //<String> assigned= new ArrayList<>();
            System.out.println(pref);
            Set<String> assigned= new HashSet<>();
            for(String c: pref){
                int cap = courses.get(c).getCapacity();
                //TRY1
                if(cap>0 && !checkTimeConflict(assigned,c,courses,conflFile,errFile,id)){
                    assigned.add(c);
                    courses.get(c).setCapacity(cap-1);
                    System.out.println("Capcaity for course "+c+" is: "+courses.get(c).getCapacity());
                }
                // else if(cap==0){
                //     assigned.add("");
                //     System.err.println("Course "+c+" Out of capacity");
                    
                // }
                //TRY2
                // while (cap > 0 && !checkTimeConflict(assigned, c, courses, conflFile, errFile, id)) {
                //     assigned.add(c);
                //     courses.get(c).setCapacity(cap - 1);
                //     System.out.println("Capacity for course " + c + " is: " + courses.get(c).getCapacity());
                // }
                if (cap == 0) {
                    System.err.println("Course " + c + " is out of capacity");
                    assigned.add("NA");
                    if(allCapacityNull(pref, courses)){
                        assigned.add("NA2");
                    }
                    if(allCapacityNull(pref, courses)&&assigned.contains("NA2")){
                        assigned.add("NA3");
                    }
                }
                // if(cap==0 && allCapacityNull(pref, courses) && assigned.size()<3){
                //     System.out.println(id+" "+assigned);
                //     assigned.add("NA");
                // }
                if(assigned.size()==3){
                    assigned.add(String.valueOf(calculateSatisfaction(assigned,pref)));
                    register.put(id,assigned);
                    break;
                }
            }
        }
        System.out.println(register);
        return register;
    } 
    public boolean checkTimeConflict(Set<String> assigned,String course, HashMap<String,CourseInfo> courses,String conflFile, String errFile, int id){
        for(String a: assigned){
            try {
                if(courses.get(a).getTime() == courses.get(course).getTime()){
                    try {
                        PrintStream pf = FileProcessor.writeOutputToFile(conflFile);
                        if(!a.equals(course)){
                            System.out.println("Time conflict for course : "+a+" and course " +course +"for student "+id);
                        }
                        pf.close();
                    } catch (Exception e) {
                        System.err.println("Error in reading conflicts File. For Stack Trace check errorLog.txt");
                        FileProcessor.writeErrorToFile(errFile,e);
                        System.exit(0);
                    }
                    return true;
                }   
            } catch (NullPointerException e) {
                
            }
        }
        return false;
    }
    public double calculateSatisfaction(Set<String> assigned,List<String> prefs){
        double result = 0;
        int sat = 9;
        int satisfaction = 0;
        for(int i=0;i<9;i++){
            if(assigned.contains(prefs.get(i))){
                satisfaction += sat;
            }
            sat--;
        }
        result = Double.valueOf(satisfaction)/3;
        System.out.println(result);
        return result;
    }
    public boolean allCapacityNull(List<String> pref,HashMap<String,CourseInfo> courses){
        for(String s:pref){
            System.out.println("Capacity : "+courses.get(s).getCapacity());
            if(courses.get(s).getCapacity()!=0){
                return false;
            }
        }
        return true;
    }
}
