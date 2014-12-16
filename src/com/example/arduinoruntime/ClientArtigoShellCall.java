//CÓDIGO DA CLASSE CLIENTARTIGOSHELLCALL QUE DEVERÁ SER EXECUTADA EM UM AMBIENTE JAVASE.
/*package com.example.arduinoruntime;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class ClientArtigoShellCall {

    public static void main(String[] args) {
        BufferedWriter escritorLinhas;
        OutputStreamWriter escritorCaracteres;
        OutputStream escritorSocket;

        while (true){
            String comando = JOptionPane.showInputDialog("Comando:");
            if (comando.equals("exit")){
                System.exit(0);
            }
            try {
                //am broadcast -a minhaintent -e teste 993643538
                Socket s = new Socket("192.168.1.150", 8080);

                escritorSocket = s.getOutputStream();
                escritorCaracteres = new OutputStreamWriter(escritorSocket);
                escritorLinhas = new BufferedWriter(escritorCaracteres);
                escritorLinhas.write(comando);
                escritorLinhas.flush();
                escritorLinhas.close();

                s.close();
            } catch (UnknownHostException e) {} 
            catch (IOException e) {}
        }
    }
}
*/