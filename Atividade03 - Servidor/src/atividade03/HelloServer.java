/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade03;

import javax.naming.InitialContext;
import javax.naming.Context;
import java.util.Properties;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

/**
 *
 * @author 31117317
 */
public class HelloServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            // Step 1: Instantiate the Hello servant
            HelloImpl helloRef = new HelloImpl();
            
            //Iniciar o Naming Service
            Properties props = new Properties();
            props.put("org.omg.CORBA.ORBInitialPort", "1050");
            props.put("org.omg.CORBA.ORBInitialHost", "localhost");
            ORB orb = ORB.init(args, props);

            // Step 2: Publish the reference in the Naming Service
            // using JNDI API
            Context initialNamingContext = new InitialContext();
            initialNamingContext.rebind("HelloService", helloRef );

            System.out.println("Hello Server: Ready...");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
            e.printStackTrace();
        }
    }
}