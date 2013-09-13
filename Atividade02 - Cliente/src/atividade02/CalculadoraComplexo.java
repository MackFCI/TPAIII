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
public interface CalculadoraComplexo extends Remote {
    Complexo soma(Complexo a, Complexo b) throws RemoteException;
    Complexo sub(Complexo a, Complexo b) throws RemoteException;
    Complexo mult(Complexo a, Complexo b) throws RemoteException;
    Complexo div(Complexo a, Complexo b) throws RemoteException;
}
