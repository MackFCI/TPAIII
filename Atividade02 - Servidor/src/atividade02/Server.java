/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade02;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author 31117317
 */
public class Server implements Calculadora {

    public Server() {}
    
    /**
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public Double soma(Double a, Double b) {
        return a+b;
    }
    /**
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public Double sub(Double a, Double b){
        return a-b;
    }
    /**
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public Double mult(Double a, Double b){
        return a*b;
    }
    /**
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public Double div(Double a, Double b){
        return a/b;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Calculadora obj = new Server();
            Calculadora stub = (Calculadora) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Calculadora", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
        }
    }
}
