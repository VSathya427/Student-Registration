package studentCoursesMgmt.driver;

import java.io.FileNotFoundException;
import java.util.HashMap;

import studentCoursesMgmt.util.CourseInfo;
import studentCoursesMgmt.util.FileProcessor;
import studentCoursesMgmt.util.ReadCourseInfo;
import studentCoursesMgmt.util.ReadFile;
import studentCoursesMgmt.util.Results;
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
		String coursePrefs = args[0];
		String courseInfo = args[1];
		String regResults = args[2];
		String conflictFile = args[3];
		String errorFile = args[4];
		FileProcessor.cleanFile(errorFile);
		FileProcessor.cleanFile(conflictFile);
		FileProcessor.cleanFile(regResults);

		//Uncomment line below for line by line
		// ReadFile<Integer,List<String>,List<String>> rcp = new ReadCoursePrefs();

		ReadFile<String,CourseInfo,CourseInfo> rci = new ReadCourseInfo();

		//Uncomment line below for line by line
		// StudentMgmt sm = new StudentMgmt();

		Results res = new Results();
		HashMap<String,CourseInfo> info =  rci.readFile(courseInfo, errorFile);

		//Uncomment three lines below for line by line
		// HashMap<Integer,List<String>> prefs = rcp.readFile(coursePrefs, errorFile);
		// HashMap<Integer,Set<String>> schedule = sm.scheduleCourse(info,prefs,conflictFile,errorFile);
		// res.processOutput(schedule, regResults,0);

		System.out.println("Output Written to "+regResults+" file.");
		//Comment line below when line by line used
		res.fiveLine(info, coursePrefs,regResults, conflictFile, errorFile);
	}
}
