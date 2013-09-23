package provaParcial;

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
        String host = (args.length < 1) ? null : args[0];
        int port = 1099;
        try {
            System.out.println("Jogo da Velha");
            
            Scanner teclado = new Scanner(System.in);
            
            JogoDaVelha stub = null;
            
            boolean conexao = false;
            while(!conexao){
                System.out.print("Digite o host do servidor (IP ou hostname): ");
                host = String.valueOf(teclado.next());
                try{
                    Registry registry = LocateRegistry.getRegistry(host, port);
                    stub = (JogoDaVelha) registry.lookup("JogoDaVelha");
                    conexao = true;
                }catch(java.rmi.ConnectException e){
                    System.err.println("Servidor não encontrado!");
                }catch(java.rmi.NotBoundException e){
                    System.err.println("Classe não encontrada no servidor!");
                }
            }
            
            int idJogador = stub.getIdJogador();
            if(idJogador == 0){
                throw new Exception("O jogo já foi iniciado!");
            }
            
            System.out.println("Bem vindo ao jogo da velha, você é o jogador "+idJogador);
            
            Peca jogador;
            boolean pecaJogadorValido;
            int posicaoX, posicaoY;
            
            /*
            while(idJogador < 1 || idJogador > 2){
                System.out.print("Digite o número do seu jogador (1 ou 2): ");
                idJogador = Integer.valueOf(teclado.next());
                if(idJogador < 1 || idJogador > 2){
                    System.err.println("Jogador inválido!");
                }
            }
            */
            
            if(!stub.minhaVez(idJogador) && stub.quemGanhou() == -1){
                System.out.print("Aguardando o outro jogador ...");
            }
            while(stub.quemGanhou() == -1){
                while(stub.minhaVez(idJogador)){
                    pecaJogadorValido = false;
                    while(!pecaJogadorValido){
                        posicaoX = 0;
                        posicaoY = 0;
                        System.out.println();
                        System.out.println(stub.getTabuleiro());
                        while(posicaoX < 1 || posicaoX > 3){
                            System.out.print("Digite a posição X (linha): ");
                            posicaoX = Integer.valueOf(teclado.next());
                            if(posicaoX < 1 || posicaoX > 3){
                                System.err.println("Posição X inválida!");
                            }
                        }
                        while(posicaoY < 1 || posicaoY > 3){
                            System.out.print("Digite a posição Y (coluna): ");
                            posicaoY = Integer.valueOf(teclado.next());
                            if(posicaoY < 1 || posicaoY > 3){
                                System.err.println("Posição Y inválida!");
                            }
                        }
                        jogador = new Peca(posicaoX, posicaoY);
                        pecaJogadorValido = stub.jogada(idJogador, jogador);
                        if(!pecaJogadorValido){
                            System.err.println("Esta posição já está ocupada!");
                        }else{
                            System.out.println();
                            System.out.println(stub.getTabuleiro());
                            System.out.print("Aguardando o outro jogador ...");
                        }
                    }
                }
                if(!stub.minhaVez(idJogador) && stub.quemGanhou() == -1){
                    System.out.print(".");
                    Thread.sleep(1000);
                }
            }
            
            System.out.println();
            if(stub.quemGanhou() == 0){
                System.out.println("Deu velha, ninguém ganhou !");
            }else if(stub.quemGanhou() == idJogador){
                System.out.println("PARABÉNS, você gahou o jogo !");
            }else{
                System.out.println("Você perdeu !");
            }
        } catch (Exception e) {
            System.err.println("Exceção do cliente: " + e.toString());
        }
    }
}
