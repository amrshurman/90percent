/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp3008csvreader;

import java.util.HashMap;

/**
 *
 * @author Jacob
 */
public class UserData {
    
    private final String id;
    
    private HashMap<String, UserSchemeData> schemes = new HashMap<>();
    private HashMap<String, Record> startRecords = new HashMap<>();
    
    public UserData(String id){
        this.id = id;
    }
    
    public void putStartRecord(Record rec){
        if (!rec.userID.equals(id) || !rec.data.equals("start"))
            return;
        
        String recId = createRecordString(rec);
        startRecords.put(recId, rec);
    }
    
    private String createRecordString(Record rec){
        return rec.site+"-"+rec.scheme;
    }
    
    public Record getMatchingRecord(Record rec){
        if (!rec.userID.equals(id))
            return null;
        
        String recId = createRecordString(rec);
        Record matchRec = startRecords.getOrDefault(recId, null);
        startRecords.remove(recId);
        return matchRec;
    }
    
    public void addRecordData(UserSchemeData.LoginAttemptData data){
        UserSchemeData scheme = schemes.getOrDefault(data.getScheme(), null);
        if (scheme == null){
            scheme = new UserSchemeData(id, data.getScheme());
            schemes.put(data.getScheme(), scheme);
        }
        scheme.addAttempt(data);
    }
    
    public String getID() { return id; }
    
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
