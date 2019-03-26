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
 *
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
        HashMap<String, UserData> users = new HashMap<>();
        
        try {
            CSVReader textReader = new CSVReader("../data/text21.csv");
            CSVReader imageReader = new CSVReader("../data/imagept21.csv");
            
            readDataFromFile(textReader, users);
            readDataFromFile(imageReader, users);
            
            CSVWriter writer = new CSVWriter("../data/loginData.csv");
            writer.writeNext(new String[]{"userID", "scheme", "Logins.Total", "Logins.success", "Logins.Failure", "Logins.avgSuccess", "Logins.avgFail"});
            
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
    
    private static void readDataFromFile(CSVReader reader, HashMap<String, UserData> users) throws IOException{
        String[] line = null;
        while ((line = reader.readNext()) != null){
            Record rec = parseRecord(line);
            UserData user = users.getOrDefault(rec.userID, null);
            if (user == null){
                user = new UserData(rec.userID);
                users.put(rec.userID, user);
            }

            if (rec.event.equals("enter") && rec.data.equals("start")){
                user.putStartRecord(rec);
            } else if (rec.event.equals("login")){
                Record matchRec = user.getMatchingRecord(rec);
                if (matchRec != null){
                    long time = rec.time.getTime() - matchRec.time.getTime();
                    boolean success = rec.data.equals("success");
                    UserSchemeData.LoginAttemptData data = new UserSchemeData.LoginAttemptData(user.getID(), rec.scheme, time, success);
                    user.addRecordData(data);
                }
            }    
        }
    }
}
