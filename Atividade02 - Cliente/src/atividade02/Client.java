package atividade02;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    
    private Client() {}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*String host = "172.16.19.17";*/
		String host = (args.length < 1) ? null : args[0];
        int port = 1099;
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            CalculadoraComplexo stub = (CalculadoraComplexo) registry.lookup("CalculadoraComplexo");
            
            Scanner teclado = new Scanner(System.in);
            
            
            System.out.println("Digite o primeiro real");
            Double real1 = Double.valueOf(teclado.next());
            System.out.println("Digite o primeiro imaginario");
            Double imaginario1 = Double.valueOf(teclado.next());
            System.out.println("Digite o segundo real");
            Double real2 = Double.valueOf(teclado.next());
            System.out.println("Digite o segundo imaginario");
            Double imaginario2 = Double.valueOf(teclado.next());
            
            Complexo n1 = new Complexo(real1,imaginario1);
            Complexo n2 = new Complexo(real2,imaginario2);
            
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
            System.err.println("Exceção do cliente: " + e.toString());
        }
    }
}
