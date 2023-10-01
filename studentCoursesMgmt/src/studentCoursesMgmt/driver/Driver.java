package studentCoursesMgmt.driver;

import java.io.FileNotFoundException;
import java.util.HashMap;

import studentCoursesMgmt.util.ReadFile;
import studentCoursesMgmt.util.Results;
import studentCoursesMgmt.util.StudentMgmt;
import studentCoursesMgmt.util.ReadCoursePrefs;
import studentCoursesMgmt.util.CourseInfo;
import studentCoursesMgmt.util.ReadCourseInfo;
import studentCoursesMgmt.util.FileProcessor;
/**
 * @author Sathya Vemulapalli
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
		FileProcessor.cleanFile(args[4]);
		FileProcessor.cleanFile(args[3]);
		FileProcessor.cleanFile(args[2]);

		// ReadFile<Integer,List<String>,List<String>> rcp = new ReadCoursePrefs();

		ReadFile<String,CourseInfo,CourseInfo> rci = new ReadCourseInfo();

		// StudentMgmt sm = new StudentMgmt();

		Results res = new Results();
		HashMap<String,CourseInfo> info =  rci.readFile(args[1], args[4]);

		// HashMap<Integer,List<String>> prefs = rcp.readFile(args[0], args[4]);
		// HashMap<Integer,Set<String>> schedule = sm.scheduleCourse(info,prefs,args[3],args[4]);
		// res.processOutput(schedule, args[2],0);

		res.fiveLine(info, args[0],args[2], args[3], args[4]);
	}
}
