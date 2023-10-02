package studentCoursesMgmt.util;


import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <h1>Student Management</h1>
 * The StudentMgmt implements an application that
 * simply assigns course using FCFS scheduling policy
 * 
 * @author Sathya Vemulapalli
 * @version 1.0
 * @since 2023-29-09
 */

public class StudentMgmt {
    /**
     * This methdod does all the main computing to Schedule courses
     * @param courses This is the first parameter which stores availeble courses information
     * @param prefs This is the second parameter which stores the preferences of the students
     * @param conflFile This is the third parameter which stores file name to which the registration conflicts need to be redirected. 
     * @param errFile This is the fourth parameter which stores file name to which the error log needs to be redirected.
     * @return Hashmap This returns the final scheduled courses
     */
    public HashMap<Integer,Set<String>> scheduleCourse(HashMap<String,CourseInfo> courses,HashMap<Integer,List<String>> prefs, String conflFile,String errFile){
        HashMap<Integer,Set<String>> register = new HashMap<>();
        for (Map.Entry<Integer, List<String>> student : prefs.entrySet()) {
            int id = student.getKey();
            List<String> pref = student.getValue();
            Set<String> assigned= new HashSet<>();
            for(String c: pref){
                int cap = courses.get(c).getCapacity();
                if(cap>0 && !checkTimeConflict(assigned,c,courses,conflFile,errFile,id)){
                    assigned.add(c);
                    courses.get(c).setCapacity(cap-1);
                }
                if (cap == 0) {
                    assigned.add("NA");
                    // if(checkTimeConflict(assigned, c, courses, conflFile, errFile, id)){
                    //     assigned.add("NAA");
                    // }
                    if(!allCapacityNull(pref, courses) && assigned.size()<=2){
                        assigned.add("NAA");
                    }
                    if(allCapacityNull(pref, courses)){
                        assigned.add("NAA");
                    }
                    if(allCapacityNull(pref, courses)&&assigned.contains("NAA")){
                        assigned.add("NAAA");
                    }
                }
                if(assigned.size()==3){
                    assigned.add(String.valueOf(calculateSatisfaction(assigned,pref)));
                    register.put(id,assigned);
                    break;
                }
            }
        }   
        return register;
    } 
    /**
     * This method checks if there are any 
     * time conflicts in the assigned courses 
     * and the course that is going to be assigned.
     * @param assigned This is the first parameter which stores a set of assigned courses
     * @param course This is the second parameter which stores The couse which is going to be assigned
     * @param courses This is the third parameter which stores availeble courses information
     * @param conflFile This is the fourth parameter which stores file name to which the registration conflicts need to be redirected. 
     * @param errFile This is the fifth parameter which stores file name to which the error log needs to be redirected.
     * @param id This is the sixth parameter which stores the id of the student to whom the course is going to be assigned
     * @return boolean This returns true if there is a timeconflict else false.
     */
    public boolean checkTimeConflict(Set<String> assigned,String course, HashMap<String,CourseInfo> courses,String conflFile, String errFile, int id){
        for(String a: assigned){
            try {
                if(courses.get(a).getTime() == courses.get(course).getTime()){
                    try {
                        PrintStream pf = FileProcessor.writeOutputToFile(conflFile);
                        if(!a.equals(course)){
                            System.out.println("Time conflict for course : "+a+" and course " +course +" for student "+id);
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
    /**
     * This methods calculates the satisfaction rating 
     * of the student based on the students preferences 
     * and the courses that have been assigned to the student 
     * @param assigned This is the first parameter which stores the courses assigned to the student.
     * @param prefs This is the second parameter which stores the preferences of the student.
     * @return double This returns satisfaction rating.
     */
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
        //System.out.println(result);
        return result;
    }
    /**
     * This method checks if all the courses are 
     * out of capacity 
     * @param pref This is the first parameter which stores the preferences of the student.
     * @param courses This is the second parameter which stores the course information.
     * @return boolean Returns true if all the courses are full.
     */
    public boolean allCapacityNull(List<String> pref,HashMap<String,CourseInfo> courses){
        for(String s:pref){
            if(courses.get(s).getCapacity()!=0){
                return false;
            }
        }
        return true;
    }
}
