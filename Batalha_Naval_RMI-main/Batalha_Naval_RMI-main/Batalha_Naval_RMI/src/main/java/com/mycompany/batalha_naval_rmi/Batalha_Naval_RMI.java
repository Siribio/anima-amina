package com.mycompany.batalha_naval_rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Batalha_Naval_RMI {

//private static int tamanho_tabuleiro = 5;
    public static void main(String[] args) throws RemoteException {
    //tamanho_tabuleiro = Integer.parseInt(args[0]);
        try {

            GatewayService s = (GatewayService) Naming.lookup("rmi://localhost:1099/BatalhaNaval");
            GatewayServiceImpl c = new GatewayServiceImpl();

            int[][] tabuleiro = new int[Commons.TAMANHO_TABULEIRO][Commons.TAMANHO_TABULEIRO]; //numero magico
            int[][] navios = new int[Commons.NUM_NAVIOS][Commons.LINHA_COLUNA];//numero magico
            int[] tiro = new int[Commons.LINHA_COLUNA];//numero magico
            int tentativas = Commons.NUM_TENTATIVAS,
                 acertos = Commons.NUM_ACERTOS;
            Scanner entrada = new Scanner(System.in);
            System.out.println("Deseja jogar? s/n");
            String x = entrada.next();
            if ("s".equals(x)) {
                tabuleiro = s.inicializarTabuleiro(tabuleiro);
                navios = s.iniciarNavios(navios);
                do {
                    
                    s.mostrarTabuleiro(tabuleiro);
                    c.mostrarTabuleiro(tabuleiro);
                    c.darTiro(tiro);
                    if (c.acertou(tiro, navios)) {
                        c.darDica(tiro, navios, tentativas);
                        acertos++;
                        
                    } else {
                        c.darDica(tiro, navios, tentativas);
                     
                    }
                    tabuleiro = s.alterarTabuleiro(tiro, navios, tabuleiro);
         
                    tentativas++;

                    System.out.println("Tentativas: " + tentativas);
                    System.out.println("Acertos: " + acertos);
           
                } while (acertos != 3);
                System.out.println("\n\n\nJogo terminado. Você acertou os 3 navios em "+tentativas+" tentativas!\n");
                c.mostrarTabuleiro(tabuleiro);
            }else{
                System.out.println("Até a próxima!!!");
            }

        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.out.println("GenericException: " + e.toString());
        }
        
        
    }
    

}

