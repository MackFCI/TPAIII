/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade02;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author 31117317
 */
public class Client {
    
    private Client() {}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String host = (args.length < 1) ? null : args[0];
        int port = 1099;
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            Calculadora stub = (Calculadora) registry.lookup("Calculadora");
            Double response = stub.soma(3.5, 2.5);
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }
}
