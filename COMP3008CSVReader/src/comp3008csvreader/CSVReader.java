/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp3008csvreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * A reader class that reads data from a csv file
 * @author Jacob
 */
public class CSVReader {
    
    private BufferedReader br = null;
    private FileReader fr = null;
    private String delim = ",";
    
    /**
     * Creates a new csv reader class that reads from the given file
     * @param fileName The file address to read from
     * @throws java.io.FileNotFoundException
     */
    public CSVReader(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        br = new BufferedReader(fr = new FileReader(file));
    }
    
    /**
     * Creates a new csv reader class that reads from the given file
     * @param fileName The file address to read from
     * @param delim The delimeter that the given file uses
     * @throws java.io.FileNotFoundException
     */
    public CSVReader(String fileName, String delim) throws FileNotFoundException {
        this(fileName);
        this.delim = delim;
    }
    
    /**
     * Reads the next line from the csv and returns it as a string array
     * @return An array containing all the data from a row seperated based on the delimeter into elements.
     *          Will return null if at end of file
     * @throws IOException 
     */
    public String[] readNext() throws IOException{
        if (br == null)
            return null;
        String fullLine = br.readLine();
        if (fullLine != null)
            return fullLine.split(delim);
        return null;
    }
    
    /**
     * Closes the reader
     * @throws IOException 
     */
    public void close() throws IOException{
        fr.close();
        br.close();
    }
    
}
