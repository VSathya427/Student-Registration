package studentCoursesMgmt.driver;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;

import studentCoursesMgmt.util.ReadFile;
import studentCoursesMgmt.util.StudentMgmt;
import studentCoursesMgmt.util.ReadCoursePrefs;
import studentCoursesMgmt.util.CourseInfo;
import studentCoursesMgmt.util.ReadCourseInfo;
/**
 * @author placeholder
 *
 */
public class Driver {
	public static void main(String[] args)throws FileNotFoundException {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */

	    if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}") || args[4].equals("${arg4}")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 5 argumnets.");
			System.exit(0);
		}
		System.out.println("Hello :" +args[0]);
		ReadFile<Integer,List<String>,List<String>> obj = new ReadCoursePrefs();
		ReadFile<String,CourseInfo,CourseInfo> obj2 = new ReadCourseInfo();
		StudentMgmt object = new StudentMgmt();
		try{
			// obj.readFile(args[0],args[4]);
			// obj2.readFile(args[1],args[4]);
			HashMap<String,CourseInfo> info =  obj2.readFile(args[1], args[4]);
			HashMap<Integer,List<String>> prefs = obj.readFile(args[0], args[4]);
			object.scheduleCourse(info,prefs);
		}catch(FileNotFoundException e){
			 PrintStream printStream = new PrintStream(args[4]);
             System.setErr(printStream);
			 System.err.println("Error in reading Error file");
             e.printStackTrace();
		}
		System.out.println("Hello World! Lets get started with the assignment");

	}
}
