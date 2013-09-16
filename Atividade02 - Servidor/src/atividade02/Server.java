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
public class Server  implements CalculadoraComplexo {

    public Server(){}
    
    /**
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public Complexo soma(Complexo a, Complexo b) {
        Double resultadoReal, resultadoImaginario;
        
        resultadoReal = a.getReal() + b.getReal();
        resultadoImaginario = a.getImaginario() + b.getImaginario();
        
        return new Complexo(resultadoReal, resultadoImaginario);
    }
    /**
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public Complexo sub(Complexo a, Complexo b) {
        Double resultadoReal, resultadoImaginario;
        
        resultadoReal = a.getReal() - b.getReal();
        resultadoImaginario = a.getImaginario() - b.getImaginario();
        
        return new Complexo(resultadoReal, resultadoImaginario);
    }
    /**
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public Complexo mult(Complexo a, Complexo b) {
        Double resultadoReal, resultadoImaginario;
        
        resultadoReal = (a.getReal() * b.getReal()) - (a.getImaginario() * b.getImaginario());
        resultadoImaginario = (a.getReal() * b.getImaginario()) + (a.getImaginario() * b.getReal());
        
        return new Complexo(resultadoReal, resultadoImaginario);
    }
    /**
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public Complexo div(Complexo a, Complexo b) {
        Double resultadoReal, resultadoImaginario;
        
        resultadoReal = (
            (
                (
                    a.getReal() * b.getReal()
                ) + (
                    a.getImaginario() * b.getImaginario()
                )
            ) / (
                (
                    b.getReal() * b.getReal()
                ) + (
                    b.getImaginario() * b.getImaginario()
                )
            )
        );
        resultadoImaginario = (
            (
                a.getImaginario() * b.getReal()
            ) - (
                a.getReal() * b.getImaginario()
            ) / (
                (
                    b.getReal() * b.getReal()
                ) + (
                    b.getImaginario() * b.getImaginario()
                )
            )
        );
        
        return new Complexo(resultadoReal, resultadoImaginario);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            
            CalculadoraComplexo obj = new Server();
            CalculadoraComplexo stub = (CalculadoraComplexo) UnicastRemoteObject.exportObject(obj, 0);
            // Bind the remote object's stub in the registry
            registry.bind("CalculadoraComplexo", stub);

            System.err.println("Servidor pronto!");
            
            System.out.println(java.rmi.server.RemoteServer.getClientHost());
        } catch (Exception e) {
            System.err.println("Exceção do servidor: " + e.toString());
        }
    }
}
