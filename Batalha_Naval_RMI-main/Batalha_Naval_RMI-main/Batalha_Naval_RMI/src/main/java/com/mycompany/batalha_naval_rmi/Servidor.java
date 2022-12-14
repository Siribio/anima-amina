package com.mycompany.batalha_naval_rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Servidor {

     public static void main(String[] args){
        try {
            LocateRegistry.createRegistry(1099);
            GatewayService s = new GatewayServiceImpl();

            Naming.rebind("rmi://localhost:1099/BatalhaNaval", s);

            System.out.println("Servidor Conectado, Aguardando...");
        } catch (MalformedURLException | RemoteException e) {
            System.out.println("Trouble: " + e.getLocalizedMessage());
        }
    }

 
}
