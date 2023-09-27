package studentCoursesMgmt.util;

import java.io.FileNotFoundException;
import java.util.HashMap;


public interface ReadFile<K,V,T> {
    public HashMap<K,V> readFile(String File, String errFile)throws FileNotFoundException;
    public T processFile(String[] s);
} 
