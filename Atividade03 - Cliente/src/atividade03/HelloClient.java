/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade03;

import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import javax.rmi.*;
import java.util.Vector;
import javax.naming.NamingException;
import javax.naming.InitialContext;
import javax.naming.Context;

/**
 *
 * @author 31117317
 */
public class HelloClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Context ic;
        Object objref;
        HelloInterface hi;

        try {
            ic = new InitialContext();

            // STEP 1: Get the Object reference from the Name Service
            // using JNDI call.
            objref = ic.lookup("HelloService");
            System.out.println("Client: Obtained a ref. to Hello server.");

            // STEP 2: Narrow the object reference to the concrete type and
            // invoke the method.
            hi = (HelloInterface) PortableRemoteObject.narrow(objref, HelloInterface.class);
            hi.sayHello( " MARS " );
        } catch( Exception e ) {
            System.err.println( "Exception " + e + "Caught" );
            e.printStackTrace( );
            return;
        }
    }
}
