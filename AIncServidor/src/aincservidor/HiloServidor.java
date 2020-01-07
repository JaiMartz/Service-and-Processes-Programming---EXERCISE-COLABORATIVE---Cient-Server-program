/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aincservidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jairo
 */
public class HiloServidor extends Thread {
    private final Socket cliente;
    private final AIncServidor server;
    private PrintWriter escritura;
    private boolean seguir = true;

    /**HiloServidor builder.
     * Constructor de HiloServidor
     * @param socket recibe el valor del host al que se va a conectar
     * @param server recibe la clase padre AIncServidor
     */
    public HiloServidor(Socket socket, AIncServidor server) {
        this.cliente = socket;
        this.server = server;
        System.out.println("Se ha creado un nuevo hilo");
    }
    
    @Override
    public void run(){
        
        System.out.println("Esperando...");
        try{
            while(seguir){
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                escritura = new PrintWriter(cliente.getOutputStream(),true);
                
                System.out.println("Entrada en bucle");
                String lector = entrada.readLine();
                
                System.out.println("Se ha leido "+lector);
                String[] convertidor = lector.split(" ");
               switch(convertidor[0]){
                    case "1":
                        Thread.sleep(700);
                        System.out.println("Consulta 1 recibida");
                        System.out.println("*********** LISTADO ***********");
                        escritura.println(server.getListado());
                        break;
                    case "2":
                        System.out.println("Consulta 2 recibida");
                        escritura.println(server.getCliente(convertidor[1]));
                        System.out.println("consulta enviada");
                        break;
                    case "3":
                        seguir = false;
                        break;
                }
            }
            cliente.close();
        }catch(IOException e){
            e.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
