/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade03;

import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;

/**
 *
 * @author 31117317
 */

public class HelloImpl extends PortableRemoteObject implements HelloInterface {
    public HelloImpl() throws RemoteException {
        super(); // invoke rmi linking and remote object initialization
    }

    /**
     *
     * @param from
     * @throws java.rmi.RemoteException
     */
    @Override
    public void sayHello( String from ) throws RemoteException {
        System.out.println( "Hello from " + from + "!!" );
        System.out.flush();
    }
}