/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csserver;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author Admin
 */
public class Server {
 public static MessageQueue<String> q = new MessageQueue<>();
    public static ArrayList<PrintWriter> noslist = new ArrayList<>();
    
    public static void main(String[] args) throws Exception{
        System.out.println("Server Signing ON");
        ServerSocket ss = new ServerSocket(9081);
        MessageDispatcher md = new MessageDispatcher();
        md.setDaemon(true);
        md.start();
        for(int i = 0; i < 10; i++) 
        {
            Socket soc = ss.accept();
            System.out.println("Connection Established");
            Conversation c = new Conversation(soc);
            c.start();
        }
        
    }
    
}

class MessageQueue<T> {
    
    ArrayList<T> al = new ArrayList<>();
    
    synchronized public void enqueue(T i) {
        al.add(i);
        notify(); 
    }
    
    synchronized public T dequeue() {
        if(al.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Inerrupt occurred in wait for thread");
            }
        }
        return al.remove(0);
    }
    
    synchronized public void print() {
        for(T i : al) {
            System.out.println("-->" + i);
        }
    }
    
    @Override
    synchronized public String toString() {
        String str = null;
        for(T s : al) {
            str += "::" + s;
        }
        return str;
    }   
}
