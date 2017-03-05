/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csserver;
import java.io.PrintWriter;
/**
 *
 * mukesh
 */
public class MessageDispatcher extends Thread {
    @Override
    public void run() {
        while(true) {
            try {
            String str = Server.q.dequeue();
            for(PrintWriter o : Server.noslist) {
                o.println(str);
            }
            } catch (Exception ex){
            }
        }
    }
}
