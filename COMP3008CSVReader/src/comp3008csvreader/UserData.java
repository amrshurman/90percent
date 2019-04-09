/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp3008csvreader;

import java.util.HashMap;

/**
 * A data structure to hold the data for a given user
 * @author Jacob
 */
public class UserData {
    
    private final String id;
    
    private HashMap<String, UserSchemeData> schemes = new HashMap<>();
    private HashMap<String, Record> startRecords = new HashMap<>();
    
    /**
     * Creates a data structure to hold the data for the user with the given id
     * @param id The id of the user that data is being store for
     */
    public UserData(String id){
        this.id = id;
    }
    
    /**
     * Adds a start record for this user
     * @param rec The start record to be added
     */
    public void putStartRecord(Record rec){
        if (!rec.userID.equals(id) || !rec.data.equals("start"))
            return;
        
        String recId = createRecordID(rec);
        startRecords.put(recId, rec);
    }
    
    /**
     * Creates an id string for a given record
     * @param rec The record to create the id for
     * @return An id string for the given record
     */
    private String createRecordID(Record rec){
        return rec.site+"-"+rec.scheme;
    }
    
    /**
     * Gets the corresponding record for the given record
     * @param rec The record to find the corresponding record for
     * @return The record that corresponding with the given record
     */
    public Record getMatchingRecord(Record rec){
        if (!rec.userID.equals(id))
            return null;
        
        String recId = createRecordID(rec);
        Record matchRec = startRecords.getOrDefault(recId, null);
        startRecords.remove(recId);
        return matchRec;
    }
    
    /**
     * Adds new login attempt data for the given user
     * @param data The data structure containing the data on the login attempt
     */
    public void addRecordData(UserSchemeData.LoginAttemptData data){
        UserSchemeData scheme = schemes.getOrDefault(data.getScheme(), null);
        if (scheme == null){
            scheme = new UserSchemeData(id, data.getScheme());
            schemes.put(data.getScheme(), scheme);
        }
        scheme.addAttempt(data);
    }
    
    /**
     * Gets the id of this user
     * @return The id of this user
     */
    public String getID() { return id; }
    
    /**
     * Gets an array of all the schemes used by this user
     * @return An array of all the schemes used by this user
     */
    public UserSchemeData[] getAllSchemeData() { return schemes.values().toArray(new UserSchemeData[schemes.values().size()]); }
    
    @Override
    public String toString(){
        String str = "";
        for (UserSchemeData scheme : schemes.values()){
            str += scheme.toString()+"\n";
        }
        return str;
    }
}
