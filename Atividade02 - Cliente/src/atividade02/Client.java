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
            CalculadoraComplexo stub = (CalculadoraComplexo) registry.lookup("CalculadoraComplexo");
            
            Complexo n1 = new Complexo(1.0, 2.0);
            Complexo n2 = new Complexo(3.0, 4.0);
            
            Complexo resposta;
            
            resposta = stub.soma(n1, n2);
            System.out.println("Soma: " + resposta.getComplexo());
            
            resposta = stub.sub(n1, n2);
            System.out.println("Subtração: " + resposta.getComplexo());
            
            resposta = stub.mult(n1, n2);
            System.out.println("Multiplicação: " + resposta.getComplexo());
            
            resposta = stub.div(n1, n2);
            System.out.println("Divisão: " + resposta.getComplexo());
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }
}
