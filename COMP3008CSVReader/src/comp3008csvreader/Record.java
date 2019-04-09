/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp3008csvreader;

import java.util.Date;

/**
 * A data structure to store and sort data from a log file record
 * @author Jacob
 */
public class Record {
    
    public Date time = null;
    public String userID = "";
    public String site = "";
    public String scheme = "";
    public String mode = "";
    public String event = "";
    public String data = "";
    public boolean success = false;
}
