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
public interface Hello extends Remote {
    String sayHello() throws RemoteException;
}
