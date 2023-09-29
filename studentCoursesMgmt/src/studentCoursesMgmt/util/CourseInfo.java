package studentCoursesMgmt.util;
/**
 * Class to store and access the capacity and time slots 
 * for all the courses using getters and setters.
 */
public class CourseInfo {
    int capacity=0;
    int time = 0;
    public int getCapacity(){
        return capacity;
    }
    public int getTime(){
        return time;
    }
    public void setCapacity(int c){
        this.capacity = c;
    }
    public void setTime(int t){
        this.time = t;
    }
}
