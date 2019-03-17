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
 *
 * @author Jacob
 */
public class CSVReader {
    
    private BufferedReader br = null;
    private FileReader fr = null;
    private String delim = ",";
    
    public CSVReader(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        br = new BufferedReader(fr = new FileReader(file));
    }
    
    public CSVReader(String fileName, String delim) throws FileNotFoundException {
        this(fileName);
        this.delim = delim;
    }
    
    public String[] readNext() throws IOException{
        if (br == null)
            return null;
        String fullLine = br.readLine();
        if (fullLine != null)
            return fullLine.split(delim);
        return null;
    }
    
    public void close() throws IOException{
        fr.close();
        br.close();
    }
    
}
