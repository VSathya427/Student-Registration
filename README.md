# CS542: Assignment 1
## Name: Sathya Vemulapalli


Slack Days Used : 3

-----------------------------------------------------------------------
-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in studentCoursesMgmt/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile studentCoursesMgmt/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile studentCoursesMgmt/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile studentCoursesMgmt/src/build.xml run -Darg0=<input_file.txt> -Darg1=<delete_file.txt> -Darg2=<output1_file.txt> -Darg3=<output2_file.txt> -Darg4=<output3_file.txt>

The code submitted runs using the following command.

ant -buildfile studentCoursesMgmt/src/build.xml run -Darg0=coursePrefs.txt -Darg1=courseInfo.txt -Darg2=regResults.txt -Darg3=regConflicts.txt -Darg3=errorLog.txt  

## Replace <fileName.txt> with real file names. For example, if the files are available in the path,
## you can run it in the following manner:

ant -buildfile studentCoursesMgmt/src/build.xml run -Darg0=input_file.txt -Darg1=delete_file.txt -Darg2=output1_file.txt -Darg3=output2_file.txt -Darg4=output3_file.txt

Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
## Description:

The algorithm follows fcfs but processes 5 linesa at a time and then the next five lines. Each of the five lines are stored in a Hashmap and sent here and there to the vaious classes and finally the result is displayed in the Result class. 

The code can also be run line by line by storing the entire input in a single hashmap and by uncommenting the lines in the main which are commented.
Line by line provides more flexibility in the usage of the various classes from the methods by five lines at a time will be more space efficient in case of really large data files.
### Driver Package
#### List of Classes :
- Driver : Main class which consists of main from where the whole code is run

### Util Package
#### List of Interface :
- FileDisplayInterface : Implemented by class Result
- StdoutDisplayInterface : Implemented by class Result
- ReadFile : Implemented by class ReadCoursePrefs and ReadCourseInfo
#### List of Classes :

- BoundaryConditionCheckException : Custom exception class for boundry checks.
- CourseInfo : Class to store capacity and timings of the available courses.
- FileProcessor : Class where majority of the file processing takes place.
- ReadCourseInfo : Class to read file which contains course information.
- ReadCoursePrefs : Class to read file which contains course preferences.
- Results : Class to display all the results.
- StudentMgmt : Class which has the main course scheduling algorithm.

#### List of data structure utilised :

- HashMap<String,CourseInfo> : Used to store course information with the course name as key and CourseInfo class as the value which stores capacity and time slots for each key.

- List<String> : Used to store the course preferences as a list.

-  HashMap<Integer,List<String>> : used to store course preferences  with student id as Integer key and Preferences a list of strings.

- Set<String> : Used to store assigned course as each person can't be assigned two same courses and set only takes unique values.

- HashMap<Integer,Set<String>> : Used to store assigned courses as values for each integer student id key.

#### Method description by class:
- CourseInfo
    - public int getCapacity() : 
        returns capacity of the course
    - public int getTime() :
        returns time of the course
    - public void setCapacity(int c) :
        set's the capacity of course
    - public void setTime(int t) :
        set's time slot of the course
- FileProcessor
    - public static String readLine(Scanner line) : return's a line from file for processing 
    -  public static void writeErrorToFile(String file,Exception e) : Method to write error to to a file.
    - public static PrintStream writeOutputToFile(String file) : Method to write output to file and return it as a print stream.
    - public static void cleanFile(String file) : Method to clear contents of any file.

- ReadCourseInfo 
    - public HashMap<String,CourseInfo> readFile(String file,String errFile) : Reads a file and returns it as HashMap<String,CourseInfo>.
    - public CourseInfo processFile(String[] line) : Sets class with values and returns it as an instance of CourseInfo.

- ReadCoursePrefs
    - public HashMap<Integer,List<String>> readFile(String file,String errFile) : Reads a file and returns it as HashMap<Integer,List<String>>.
    - public List<String> processFile(String[] s) : Takes an array of string as an input and returns a list of preferences as a string.

- Results 
    - public void processOutput(HashMap<Integer,Set<String>> output,String File,int ch) : Takes the assigned course as parameter and sends it as output.
    - public void redirectToFile(int id, String[] course,double satisfaction, String File) : Redirects output to file.
    - public void stdOUT(int id, String[] course,double satisfaction) : Redirects output to std.
    - public void printSatisfaction(String File) : Method to print Satisfaction rating.

    - public void fiveLine(HashMap<String,CourseInfo> info, String file,String resFile, String confFile, String errFile) : Method which takes info as input and processes 5 lines at a time and prints it.


#### Method description by Interface :
- ReadFile<K,V,T>
    - public HashMap<K,V> readFile(String File, String errFile)throws FileNotFoundException;
        : Method to readfile and return Hashmap with generic types as keys and values.

    - public T processFile(String[] s);
        : Method to process file and return generic type.


## Running the submitted file

Extact the tar 
```bash
  tar -xvzf svemulapalli_assigment1.tar.gz
```
You will get the following directory :
    studentCoursesMgmt

Then just run the ant build commands mentioned above

You need to add the txt files to the extracted directory mentioned above to make sure the code can access them.



-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.""

Date: 10/01/2023


