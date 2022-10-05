
package atividadeRmi.Calculadora;

import java.rmi.Naming;


public class CalculadoraClient {

    public static void main(String[] args) {
        try{
            ICalculadora c = (ICalculadora) Naming.lookup("rmi://localhost:1099/Calculadora");
            System.out.println("Resultado da soma "+c.soma(40, 5));
            
        }catch (Exception e){
            System.out.println("GenericException: " + e.toString());
        }
    }
}
