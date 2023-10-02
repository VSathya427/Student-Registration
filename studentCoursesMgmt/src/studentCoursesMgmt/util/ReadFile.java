package studentCoursesMgmt.util;

import java.util.HashMap;

/**
 * Interface for reading both files
 */
public interface ReadFile<K,V,T> {
    public HashMap<K,V> readFile(String File, String errFile);
    public T processFile(String[] s);
} 
