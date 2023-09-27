package studentCoursesMgmt.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class StudentMgmt {
    public void scheduleCourse(HashMap<String,CourseInfo> courses,HashMap<Integer,List<String>> prefs){
        for (Map.Entry<Integer, List<String>> student : prefs.entrySet()) {
            int id = student.getKey();
            List<String> pref = student.getValue();
           //List<String> = new ArrayList<>()
            Set<String> assigned= new HashSet<>();
            for(String c: pref){
                int cap = courses.get(c).getCapacity();
                if(cap>0 && !checkTimeConflict(assigned,c,courses)){
                    assigned.add(c);
                    courses.get(c).setCapacity(cap-1);
                    System.out.println("Capcaity for course "+c+" is: "+courses.get(c).getCapacity());
                }
                if(assigned.size()==3){
                    break;
                }
            }
            System.out.println("Predferece oiderd : ");
            for(String ass : assigned){
                System.out.print(ass+ " ");
            }
            System.out.println();
        }

    } 
    public boolean checkTimeConflict(Set<String> assigned,String course, HashMap<String,CourseInfo> courses){
        for(String a: assigned){
            if(courses.get(a).getTime() == courses.get(course).getTime()){
                return true;
            }
        }
        return false;
    }
}
