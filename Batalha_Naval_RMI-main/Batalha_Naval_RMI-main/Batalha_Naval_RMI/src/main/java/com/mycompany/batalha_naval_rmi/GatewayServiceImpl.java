package com.mycompany.batalha_naval_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.Scanner;

public class GatewayServiceImpl extends UnicastRemoteObject implements GatewayService {

    public GatewayServiceImpl() throws RemoteException {

    }

    /*    @Override
    public int somarNumeros(int a, int b) throws RemoteException {
        return a+b;
    }*/
    @Override
    public int[][] inicializarTabuleiro(int[][] tabuleiro) throws RemoteException {

        for (int linha = 0; linha < 5; linha++) {
            for (int coluna = 0; coluna < 5; coluna++) {
                tabuleiro[linha][coluna] = -1;
            }
        }
        return tabuleiro; //Conferir o que vai retornar e ajustar se precisar
    }

    @Override
    public void mostrarTabuleiro(int[][] tabuleiro) throws RemoteException {
        System.out.println("\t1 \t2 \t3 \t4 \t5");
        System.out.println();

        for (int linha = 0; linha < 5; linha++) {
            System.out.print((linha + 1) + "");
            for (int coluna = 0; coluna < 5; coluna++) {
                switch (tabuleiro[linha][coluna]) {
                    case -1 ->
                        System.out.print("\t" + Commons.AGUA);
                    case 0 ->
                        System.out.print("\t" + Commons.TIRO);
                    case 1 ->
                        System.out.print("\t" + Commons.ACERTO);
                    default -> {
                    }
                }

            }
            System.out.println();
        }
        //Conferir o que vai retornar e ajustar se precisar
    }

    @Override
    public int[][] iniciarNavios(int[][] navios) throws RemoteException {
        Random sorteio = new Random();

        for (int navio = 0; navio < 3; navio++) {
            navios[navio][0] = sorteio.nextInt(5);
            navios[navio][1] = sorteio.nextInt(5);

            //agora vamos checar se esse par não foi sorteado
            //se foi, so sai do do...while enquanto sortear um diferente
            for (int anterior = 0; anterior < navio; anterior++) {
                if ((navios[navio][0] == navios[anterior][0]) && (navios[navio][1] == navios[anterior][1])) {
                    do {
                        navios[navio][0] = sorteio.nextInt(5);
                        navios[navio][1] = sorteio.nextInt(5);
                    } while ((navios[navio][0] == navios[anterior][0]) && (navios[navio][1] == navios[anterior][1]));
                }
            }

        }
        return navios;
    }

    @Override
    public boolean acertou(int[] tiro, int[][] navios) throws RemoteException {

        for (int[] navio : navios) {
            if (tiro[0] == navio[0] && tiro[1] == navio[1]) {

                System.out.printf("Você acertou o tiro (%d,%d)\n", tiro[0] + 1, tiro[1] + 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public void darDica(int[] tiro, int[][] navios, int tentativa) throws RemoteException {
        int linha = 0,
                coluna = 0;

        for (int[] navio : navios) {
            if (navio[0] == tiro[0]) {
                linha++;
            }
            if (navio[1] == tiro[1]) {
                coluna++;
            }
        }

        System.out.printf("""
                          
                          Dica %d: 
                          linha %d -> %d navios
                          coluna %d -> %d navios
                                             """, tentativa, tiro[0] + 1, linha, tiro[1] + 1, coluna);
        

    }

    @Override
    public int[][] alterarTabuleiro(int[] tiro, int[][] navios, int[][] tabuleiro) throws RemoteException {
        if (acertou(tiro, navios)) {
            tabuleiro[tiro[0]][tiro[1]] = 1;
        } else {
            tabuleiro[tiro[0]][tiro[1]] = 0;
        }
        return tabuleiro;
    }
    
    @Override
    public void darTiro(int[] tiro) throws RemoteException {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Linha: ");
        tiro[0] = entrada.nextInt();
        tiro[0]--;

        System.out.print("Coluna: ");
        tiro[1] = entrada.nextInt();
        tiro[1]--;

}
}
    
