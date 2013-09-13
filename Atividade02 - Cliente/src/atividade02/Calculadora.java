/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade02;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author 31117317
 */
public interface Calculadora extends Remote {
    Double soma(Double a, Double b) throws RemoteException;
    Double sub(Double a, Double b) throws RemoteException;
    Double mult(Double a, Double b) throws RemoteException;
    Double div(Double a, Double b) throws RemoteException;
}
