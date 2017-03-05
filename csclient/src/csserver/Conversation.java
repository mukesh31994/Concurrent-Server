/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csserver;
import java.net.*;
import java.io.*;
/**
 *
 * mukesh
 */
public class Conversation extends Thread {
    Socket soc;

    Conversation(Socket soc) {
        this.soc = soc;
    }

    @Override
    public void run() {
        try {
            BufferedReader nis = new BufferedReader(
                    new InputStreamReader(
                            soc.getInputStream()
                    )
            );  
            PrintWriter nos = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    soc.getOutputStream()
                            )
                    ), true
            );  
            
            Server.noslist.add(nos);
            String str = nis.readLine();
            while(!str.equals("End")) {
                Server.q.enqueue(str);
                System.out.println("Server Received " + str);
                str = nis.readLine();
            }
            nos.println("End");
            Server.noslist.remove(nos);
            System.out.println("Connection with "+
                    soc.getInetAddress().getHostAddress() + 
                    " Terminated");
        } catch (IOException ex) {
            System.out.println("IOException Ouccurred");
        }
    }
}
