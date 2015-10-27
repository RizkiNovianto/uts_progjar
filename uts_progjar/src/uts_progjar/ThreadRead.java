/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uts_progjar;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Foremist
 */
public class ThreadRead implements Runnable {
    
    private InputStream is;
    int p=0;
    String[] hashes = new String[3];
    String inLine = new String();
    
    public ThreadRead(InputStream is){
        this.is = is;
    }
    
    @Override
    public void run(){
        int len;
        byte[] buf;
        int read = 1;
        
        while(read == 1){
            try {
                buf = new byte[1000];
                len = is.read(buf);
                if(len == -1) break;
                inLine = new String(buf);
                System.out.print(inLine);
                
                if(inLine.startsWith("Hash:\n")) {
                    p = 1;
                    //hashes = inLine.split("\\n");
                }
                
            } catch (IOException ex) {
                Logger.getLogger(ThreadRead.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int phase()
    {
        return p;
    }
    
    public String returnHash(){
        //return hashes;
        return inLine;
    }
}
