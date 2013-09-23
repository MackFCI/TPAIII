/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package provaParcial;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author 31117317
 */
public interface JogoDaVelha extends Remote {
    int getIdJogador() throws RemoteException;
    boolean jogada(int idJogador, Peca peca) throws RemoteException;
    String getTabuleiro() throws RemoteException;
    boolean minhaVez(int idJogador) throws RemoteException;
    int quemGanhou() throws RemoteException;
}
