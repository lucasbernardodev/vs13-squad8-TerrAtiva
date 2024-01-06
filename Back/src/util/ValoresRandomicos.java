package util;

import java.util.Random;

public class ValoresRandomicos {
    static Random geradorRandomico = new Random();
    public static int gerarValorPositivo(){
        int inteiroAleatorio = geradorRandomico.nextInt(999);

        while (inteiroAleatorio < 0) {
            inteiroAleatorio = geradorRandomico.nextInt(999);
        }

        return  inteiroAleatorio;
    }
}
