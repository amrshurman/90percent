/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp3008csvreader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The main class of the system
 * @author Jacob
 */
public class COMP3008CSVReader {

    static final String TIMEFORMAT = "yyyy-MM-dd hh:mm:ss";
    static final SimpleDateFormat dateFormat = new SimpleDateFormat(TIMEFORMAT);
    static final int TIMECOL = 0, USERCOL = 1, SITECOL = 2, SCHEMECOL = 3, MODECOL = 4, EVENTCOL = 5, DATACOL = 6;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Creates a hashmap to store the users in with the user id's as the keys
        HashMap<String, UserData> users = new HashMap<>();
        
        try {
            //Creates readers for the log files
            CSVReader textReader = new CSVReader("../data/text21.csv");
            CSVReader imageReader = new CSVReader("../data/imagept21.csv");
            CSVReader pairReader = new CSVReader("../data/KeyPairPassword.csv");
            
            //Reads the data from the files
            readDataFromFile(textReader, users);
            readDataFromFile(imageReader, users);
            readDataFromFile(pairReader, users);
            
            //Creates a writer for the new log file and writes out the headers
            CSVWriter writer = new CSVWriter("../data/loginData.csv");
            writer.writeNext(new String[]{"userID", "scheme", "Logins.Total", "Logins.success", "Logins.Failure", "Logins.avgSuccess", "Logins.avgFail"});
            
            //Writes out all the data from the user classes
            for (UserData user : users.values()){
                for (UserSchemeData data : user.getAllSchemeData()){
                    writer.writeNext(new String[]{data.getUserID(), data.getScheme(), Integer.toString(data.getTotalSuccess()+data.getTotalFail()), 
                        Integer.toString(data.getTotalSuccess()), Integer.toString(data.getTotalFail()), 
                        Long.toString(data.getAvgSuccessTime()), Long.toString(data.getAvgFailTime())});
                }
            }
            writer.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(COMP3008CSVReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(COMP3008CSVReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Parses a string array from the log files into a record data structure
     * @param line The array of elements from a row in the log files
     * @return A record data structure containing all the parsed data from the string array
     */
    private static Record parseRecord(String[] line){
        Record rec = new Record();
        try {
            rec.time = dateFormat.parse(line[TIMECOL]);
        } catch (ParseException ex) {
            Logger.getLogger(COMP3008CSVReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        rec.userID = line[USERCOL];
        rec.site = line[SITECOL];
        rec.scheme = line[SCHEMECOL];
        rec.mode = line[MODECOL];
        rec.event = line[EVENTCOL];
        rec.data = line[DATACOL];
        return rec;
    }
    
    /**
     * Reads and parses all the data from a log file
     * @param reader The reader for the log file
     * @param users The hashmap to store data about users in
     * @throws IOException 
     */
    private static void readDataFromFile(CSVReader reader, HashMap<String, UserData> users) throws IOException{
        String[] line = null;
        //Loops through the entire file
        while ((line = reader.readNext()) != null){
            //Parses the array from the log file into a record data structure
            Record rec = parseRecord(line);
            //Gets the user data for this log, or creates user data if it doesn't exist yet
            UserData user = users.getOrDefault(rec.userID, null);
            if (user == null){
                user = new UserData(rec.userID);
                users.put(rec.userID, user);
            }

            //If the event of the log is enter or start, keep a record of it in the users data
            if (rec.event.equals("enter") && rec.data.equals("start")){
                user.putStartRecord(rec);
            } else if (rec.event.equals("login")){
                //If the event of the log is loging, get the matching start record for it if it exists
                Record matchRec = user.getMatchingRecord(rec);
                if (matchRec != null){
                    //Get the users login time from the login record and it's corresponding start record
                    long time = rec.time.getTime() - matchRec.time.getTime();
                    //Get if the user was successful and add the record of the login to the user's login attampts
                    boolean success = rec.data.equals("success");
                    UserSchemeData.LoginAttemptData data = new UserSchemeData.LoginAttemptData(user.getID(), rec.scheme, time, success);
                    user.addRecordData(data);
                }
            }    
        }
    }
}
