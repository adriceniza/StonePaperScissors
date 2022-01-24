

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int PORT = 40080;
    
        try {
            ServerSocket sk = new ServerSocket(PORT);
            while(true){
                Socket socket = sk.accept();
                System.out.println("Someone appears");
                StonePaperScissorsThread hilo = new StonePaperScissorsThread(socket);
                hilo.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
