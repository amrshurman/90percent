/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp3008csvreader;

/**
 * A data structure to store the data on login attempts for a given user under a given scheme
 * @author Jacob
 */
public class UserSchemeData {
    
    private final String userID;
    private final String scheme;
    
    private long avgSuccessTime = 0, avgFailTime = 0;
    private int totalSuccess = 0, totalFail = 0;
    
    /**
     * Creates the data structure to store the login data for a user under a given scheme
     * @param userID The user id
     * @param passScheme The password scheme id
     */
    public UserSchemeData(String userID, String passScheme){
        this.userID = userID;
        this.scheme = passScheme;
    }
    
    /**
     * Adds a login attempt for this scheme
     * @param data The data structure containing the data for the login attempt
     */
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
    
    /**
     * A data structure to hold data on a login attempt
     */
    public static class LoginAttemptData {
        
        private final String userID;
        private final String scheme;
        private final long time;
        private final boolean success;
        
        /**
         * Creates a data structure to contain data on a login attempt
         * @param userID The user id for the attempt
         * @param scheme The password scheme for the attempt
         * @param time The time taken for the attempt
         * @param success True if the attempt was successful
         */
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
