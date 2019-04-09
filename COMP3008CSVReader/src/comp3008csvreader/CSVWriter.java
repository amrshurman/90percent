/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp3008csvreader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A writer class that writes data out to a csv file
 * @author Jacob
 */
public class CSVWriter {
    
    private BufferedWriter bw = null;
    private FileWriter fw = null;
    private String delim = ",";
    
    /**
     * Creates a new csv writer class that writes to the given file
     * @param fileName The file address to write to
     * @throws IOException 
     */
    public CSVWriter(String fileName) throws IOException {
        File file = new File(fileName);
        bw = new BufferedWriter(fw = new FileWriter(file));
    }
    
    /**
     * Creates a new csv writer class that writes to the given file
     * @param fileName The file address to write to
     * @param delim The delimeter to use in the csv file
     * @throws IOException 
     */
    public CSVWriter(String fileName, String delim) throws IOException {
        this(fileName);
        this.delim = delim;
    }
    
    /**
     * Writes the given array of strings to a new row in the csv file.
     * Each element in the array is seperated by the deleimeter for this file
     * @param strs The array of strings to write to the file
     * @throws IOException 
     */
    public void writeNext(String[] strs) throws IOException{
        if (bw == null) 
            return;
        
        String fullLine = "";
        for (String s : strs)
            fullLine += s + delim;
        
        fullLine = fullLine.substring(0, fullLine.length()-1) + "\n";
        bw.write(fullLine);
    }
    
    /**
     * Closes the writer
     * @throws IOException 
     */
    public void close() throws IOException{
        //fw.close();
        bw.close();
    }
}
