Note: COMP3008CSVReader and PasswordTester are written in Java using Netbeans 8.2

COMP3008CSVReader:
This is the log file parser that reads all the log files from the password system testers and parses them into data that can then be used for statistics.
When running COMP3008CSVReader, it looks for the log files in the ../data folder relative to the project directory, the output file will be written to the same folder

PasswordTester:
This is the password system tester that was created to display and test the password scheme created with users
A built executable jar for PasswordTester is built and put in the executable folder for ease of running incase running through the IDE is a problem
The output log file for PasswordTester is written to the local desktop folder with the name KeyPairPassword-userID.csv

EvaluationStats:
Is the system written in R to create statistics and graphs from the compiled log data
Looks for the compiled log data in the ../data/loginData.csv file relative to the project directory

Executables: You can view our scheme in this folder

The data folder is where all compiled log data and processed log data is stored