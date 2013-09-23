/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade03;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author 31117317
 */
public interface HelloInterface extends Remote {
    public void sayHello( String from ) throws RemoteException;
}