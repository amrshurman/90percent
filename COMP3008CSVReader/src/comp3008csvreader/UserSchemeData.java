/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp3008csvreader;

/**
 *
 * @author Jacob
 */
public class UserSchemeData {
    
    private final String userID;
    private final String scheme;
    
    private long avgSuccessTime = 0, avgFailTime = 0;
    private int totalSuccess = 0, totalFail = 0;
    
    
    public UserSchemeData(String userID, String passScheme){
        this.userID = userID;
        this.scheme = passScheme;
    }
    
    public void addAttempt(LoginAttemptData data) {
        //if (!userID.equals(data.userID) || !scheme.equals(data.scheme))
        //    return;
        
        if (data.success) {
            totalSuccess++;
            avgSuccessTime = (long)(avgSuccessTime * ((double)(totalSuccess-1)/(double)totalSuccess) + data.time/(double)totalSuccess);
        } else {
            totalFail++;
            avgFailTime = (long)(avgFailTime * ((double)(totalFail-1)/(double)totalFail) + data.time/(double)totalFail);
        }
    }
    
    public String getUserID() { return userID; }
    public String getScheme() { return scheme; }
    public long getAvgSuccessTime() { return avgSuccessTime; }
    public long getAvgFailTime() { return avgFailTime; }
    public int getTotalSuccess() { return totalSuccess; }
    public int getTotalFail() { return totalFail; }
    
    @Override
    public String toString(){
        return userID+","+scheme+","+totalSuccess+","+totalFail+","+avgSuccessTime+","+avgFailTime;
    }
    
    
    public static class LoginAttemptData {
        
        private final String userID;
        private final String scheme;
        private final long time;
        private final boolean success;
        
        public LoginAttemptData(String userID, String scheme, long time, boolean success){
            this.userID = userID;
            this.scheme = scheme;
            this.time = time;
            this.success = success;
        }
        
        public String getUserID() { return userID; }
        public String getScheme() { return scheme; }
    }
}
