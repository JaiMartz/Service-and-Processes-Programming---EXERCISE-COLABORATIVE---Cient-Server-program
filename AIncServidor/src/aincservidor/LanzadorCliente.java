/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aincservidor;


/**
 *
 * @author Jairo
 */
public class LanzadorCliente {
        public static void main(String[] args) {
            new Cliente("localhost", 9090).iniciar(); 
    }
}
