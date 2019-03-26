/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordtester;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Jacob
 */
public class CSVWriter {
    
    private BufferedWriter bw = null;
    private FileWriter fw = null;
    private String delim = ",";
    
    public CSVWriter(String fileName) throws IOException {
        File file = new File(fileName);
        bw = new BufferedWriter(fw = new FileWriter(file));
    }
    
    public CSVWriter(String fileName, String delim) throws IOException {
        this(fileName);
        this.delim = delim;
    }
    
    public void writeNext(String[] strs) throws IOException{
        if (bw == null) 
            return;
        
        String fullLine = "";
        for (String s : strs)
            fullLine += s + delim;
        
        fullLine = fullLine.substring(0, fullLine.length()-1) + "\n";
        bw.write(fullLine);
    }
    
    public void close() throws IOException{
        //fw.close();
        bw.close();
    }
}
