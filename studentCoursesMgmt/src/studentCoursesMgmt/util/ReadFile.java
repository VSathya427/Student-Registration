package studentCoursesMgmt.util;

import java.io.FileNotFoundException;
import java.util.HashMap;


public interface ReadFile<K,V> {
    public HashMap<K,V> readFile(String File, String errFile)throws FileNotFoundException;
} 
