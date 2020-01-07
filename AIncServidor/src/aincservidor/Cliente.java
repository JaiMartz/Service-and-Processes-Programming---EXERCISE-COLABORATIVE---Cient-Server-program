/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aincservidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jairo
 */
public class Cliente {
    Socket socket;

    public Cliente(String host, int puerto){
        try{
            socket = new Socket(host, puerto);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void iniciar(){
        this.menu();
    }
    /**
     * ´Lanza un menu sobre el cual podremos elegir nuestras opciones
     */
    private void menu(){
        try {
            mostrarMenu();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            String comando = entrada.readLine();

            while (!comando.equals("3")) {

                switch (comando) {
                    case "1":
                        System.out.println("Ha seleccionado opción 1");
                        consultarClientes();
                        break;
                    case "2":
                        System.out.println("Ha seleccionado opción 2");
                        getDatosClienteByNIF();
                        break;
                    case "3":
                        socket.close();
                        new PrintWriter(new OutputStreamWriter(socket.getOutputStream())).write("3");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Inserte opción correcta");
                        break;
                }
                mostrarMenu();
                comando = entrada.readLine();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Debe seleccionar una opción númerica válida");
            menu();
        }
    }
        /**
         * Print the menu options by console.
         * Se imprimen las opciones de menu por pantalla
         */
        private void mostrarMenu() {
            System.out.println("Elije la opcion de consulta");
            System.out.println("1 - Consulta de clientes");
            System.out.println("2 - Consulta de clientes por NIF");
            System.out.println("3 - Salir");
        }
        
        /**
         * Send to the server the chosed option and print the answer.
         * Se manda al servidor la opcion elegida y se imprime la respuesta
         */
        private void consultarClientes(){
            try {
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter enviador = new PrintWriter(socket.getOutputStream(), true);
                System.out.println("Clintes enviados");
                enviador.println("1");
                System.out.println("*"+entrada.readLine().toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Send to the server the chosed option plus the nif and print the answer.
         * Se manda al servidor la opcion elegida mas el nif y se imprime la respuesta.
         */
        private void getDatosClienteByNIF(){
            try{
                System.out.println("Introduzca el NIF del cliente a buscar");
                String nif = new Scanner(System.in).nextLine();

                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter escritura = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                
                System.out.println("Se ha escrito la referencia");
                escritura.println("2 "+ nif);
                System.out.println(entrada.readLine().toString());
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
