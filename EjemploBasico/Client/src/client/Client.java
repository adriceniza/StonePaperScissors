
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {
    final static int PORT = 40080;
    final static String HOST = "localhost";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Socket sk = new Socket(HOST, PORT);
            
            enviarMensajesAlServidor(sk);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void enviarMensajesAlServidor(Socket sk) {
        OutputStream os = null;
        InputStream is = null;
        try {
            os = sk.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            is = sk.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            Scanner sc = new Scanner(System.in);
            String linea;
            
            while(true){
                System.out.println("Es tu turno , elige entre Piedra , Papel o Tijera: ");
                String eleccionCliente;
                linea = sc.nextLine();
                eleccionCliente = linea;
                bw.write(linea);
                bw.newLine();
                bw.flush();
                System.out.println(br.readLine());
                linea = br.readLine();
                String eleccionServidor = linea.split(":")[1];
                System.out.println("El servidor dice: " + linea);


                if(eleccionCliente.equals("piedra") && eleccionServidor.equals("Piedra"))
                {
                    System.out.println("Empate");
                }
                else if(eleccionCliente.equals("Papel" )&& eleccionServidor.equals("Papel"))
                {
                    System.out.println("Empate");
                }
                else if(eleccionCliente.equals("Tijera") && eleccionServidor.equals("Tijera"))
                {
                    System.out.println("Empate");
                }
                else if(eleccionCliente.equals("Piedra") && eleccionServidor.equals("Papel"))
                {
                    System.out.println("Gana el servidor");
                }
                else if(eleccionCliente.equals("Piedra") && eleccionServidor.equals("Tijera"))
                {
                    System.out.println("Gana el cliente");
                }
                else if(eleccionCliente.equals("Papel" )&& eleccionServidor.equals("Piedra"))
                {
                    System.out.println("Gana el cliente");
                }
                else if(eleccionCliente.equals("Papel" )&& eleccionServidor.equals("Tijera"))
                {
                    System.out.println("Gana el servidor");
                }
                else if(eleccionCliente.equals("Tijera") && eleccionServidor.equals("Piedra"))
                {
                    System.out.println("Gana el servidor");
                }
                else if(eleccionCliente.equals("Tijera") && eleccionServidor.equals("Papel"))
                {
                    System.out.println("Gana el cliente");
                }else
                {
                    System.out.println("Ha ocurrido un error" + eleccionCliente + eleccionServidor);
                    
                }     
               
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(os != null) os.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
