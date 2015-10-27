/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uts_progjar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Foremist
 */
public class Uts_progjar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            byte[] buf = new byte[10];
            
            Socket socket = new Socket("10.151.34.155", 6666);
            
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            
           int username = 0;
            
            int len;
            //byte[] buf;
            
            while(true){
                
//                buf = new byte[1000];
//                len = is.read(buf);
//                if(len == -1) break;
//                System.out.print(new String(buf));
//                
                ThreadRead tr = new ThreadRead(is);
                java.lang.Thread t = new java.lang.Thread(tr);
                t.start();
                
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String msg = br.readLine()+"\r\n";
                if(username==0){
                    //String usr = msg;
                    msg = "username:"+msg;
                    username=1;
                }
                else msg = "result:"+msg;
                
                if(tr.phase() == 1){
                    String hashPart = tr.returnHash();
                    int length = Character.getNumericValue(hashPart.charAt(15));
                    msg = "Hash:"+hashPart.substring(18, 18+length)+"\n\r\n";
                }
                
                os.write(msg.getBytes());
                os.flush();
                
                System.out.println(msg);

            }
            
        } catch (IOException ex) {
            Logger.getLogger(Uts_progjar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
