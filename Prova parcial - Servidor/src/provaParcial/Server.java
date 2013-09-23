/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package provaParcial;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author 31117317
 */
public class Server implements JogoDaVelha {
    
    int[][] tabuleiro;
    int[] qtdJogadas;

    public Server(){
        this.iniciarJogo();
    }
    
    private void iniciarJogo(){
        this.tabuleiro = new int[3][3];
        this.qtdJogadas = new int[2];
        qtdJogadas[0] = -1; /* Jogador 1 */
        qtdJogadas[1] = -1; /* Jogador 2 */
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getIdJogador(){
        //verificar se o jogo já foi finalizado
        if(quemGanhou() != -1){
            //inicia um novo jogo
            this.iniciarJogo();
        }
        
        if(qtdJogadas[0] == -1){
            qtdJogadas[0]++;
            return 1;
        }else if(qtdJogadas[1] == -1){
            qtdJogadas[1]++;
            return 2;
        }else{
            return 0;
        }
    }
    
    /**
     *
     * @param jogador
     * @param peca
     * @return
     */
    @Override
    public boolean jogada(int idJogador, Peca peca) {
        if(tabuleiro[peca.getPosicaoX()-1][peca.getPosicaoY()-1] == 0){
            tabuleiro[peca.getPosicaoX()-1][peca.getPosicaoY()-1] = idJogador;
            qtdJogadas[idJogador-1]++;
            return true;
        }else{
            return false;
        }
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getTabuleiro(){
        String resultado = "";
        
        for(int i=0; i<tabuleiro.length; i++){
            for(int j=0; j<tabuleiro[i].length; j++){
                resultado += (tabuleiro[i][j] == 0 ? 0 : tabuleiro[i][j]) + " ";
            }
            resultado += "\n";
        }
        
        return resultado;
    }
    
    /**
     *
     * @param idJogador
     * @return
     */
    @Override
    public boolean minhaVez(int idJogador){
        int idOutroJogador;
        if(idJogador == 1){
            idOutroJogador = 2;
        }else{
            idOutroJogador = 1;
        }
        
        //para a primeira jogada
        if(qtdJogadas[0] == qtdJogadas[1]){
            if(idJogador == 1){
                return true;
            }else{
                return false;
            }
        }else{
            if(qtdJogadas[idJogador-1] > qtdJogadas[idOutroJogador-1]){
                return false;
            }else{
                return true;
            }
        }
    };
    
    /**
     *
     * @return
     */
    @Override
    public int quemGanhou(){
        //verifica se alguem ganhou
        //ganhou na linha
        for(int i=0; i<tabuleiro.length; i++){
            if(tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] && tabuleiro[i][2] != 0){
                return tabuleiro[i][0];
            }
        }
        //ganhou na coluna
        for(int j=0; j<tabuleiro[0].length; j++){
            if(tabuleiro[0][j] == tabuleiro[1][j] && tabuleiro[1][j] == tabuleiro[2][j] && tabuleiro[2][j] != 0){
                return tabuleiro[0][j];
            }
        }
        
        //verifica se deu velha (ninguém ganhou)
        boolean deuVelha = true;
        for(int i=0; i<tabuleiro.length; i++){
            for(int j=0; j<tabuleiro[i].length; j++){
                if(tabuleiro[i][j] == 0){
                    deuVelha = false;
                }
            }
        }
        if(deuVelha){
            return 0;
        }
        
        return -1;
    };
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            
            JogoDaVelha obj = new Server();
            JogoDaVelha stub = (JogoDaVelha) UnicastRemoteObject.exportObject(obj, 0);
            // Bind the remote object's stub in the registry
            registry.bind("JogoDaVelha", stub);

            System.out.println("Servidor pronto!");
        } catch (Exception e) {
            System.err.println("Exceção do servidor: " + e.toString());
        }
    }
}
