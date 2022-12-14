package com.mycompany.batalha_naval_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GatewayService extends Remote{
  //  int somarNumeros(int a, int b)throws RemoteException;
    int [][] inicializarTabuleiro(int[][] tabuleiro)throws RemoteException;
    //organizando
    void mostrarTabuleiro(int[][] tabuleiro)throws RemoteException; // conferir type
    //organizando
    int [][]iniciarNavios(int[][] navios)throws RemoteException;
    //organizando
    boolean acertou(int[] tiro, int[][] navios) throws RemoteException;
    //Organizando
    void darDica(int[] tiro, int[][] navios, int tentativa) throws RemoteException;
    //Organizando
    int [][]alterarTabuleiro(int[] tiro, int[][] navios, int[][] tabuleiro) throws RemoteException;
    
    void darTiro(int[] tiro) throws RemoteException;
    
}