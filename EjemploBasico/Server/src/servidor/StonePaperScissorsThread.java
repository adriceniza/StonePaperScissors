/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tibur
 */
public class StonePaperScissorsThread extends Thread{
    Socket sk;
    public StonePaperScissorsThread(Socket sk){
        this.sk = sk;
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = sk.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            os = sk.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            Inet4Address ip = (Inet4Address) sk.getInetAddress();
            String laIP = ip.getHostAddress();

            String[] posibilidades = {"Piedra","Papel","Tijera"};


            
            while(true){
                String linea = br.readLine();
                if(Arrays.asList(posibilidades).contains(linea))
                {
                    System.out.println(laIP +": " + linea);
                    int randomNum = (int)(Math.random()*2+1);
                    String eleccion = posibilidades[randomNum];
                    bw.write("Servidor : " + eleccion);
                    bw.newLine();
                    bw.flush();
                }else{
                    bw.write("Has elegido una opción invalida");
                    bw.newLine();
                    bw.flush();
                }
                
               
            }
            
        } catch (IOException ex) {
            Logger.getLogger(StonePaperScissorsThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(is != null) is.close();
            } catch (IOException ex) {
                Logger.getLogger(StonePaperScissorsThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
}
