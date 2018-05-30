package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Metodos {
    public static void procesaString(File fichero, int[] array) {
        int cont = 0;
        String cadena;
        try {
            Scanner scn = new Scanner(fichero);
            while (scn.hasNext()){
                cadena = scn.next();
                cont = cadena.length();
                if (cont < 14){
                    array[cont] += 1;
                } else array[14] += 1;
            }
            scn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void recorreVector(int[] histo) {
        int cont;
        for (int x = 1; x < histo.length; x++) {
            cont = histo[x];
            imprimeAsteriscos(cont, x);
        }
    }

    public static void imprimeAsteriscos(int cant, int j) {
        if (cant != 0){
            for (int z = 0; z < 2; z++) {
                System.out.print(j + " ");
                for (int x = 0; x < cant; x++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }
    }

    public static ArrayList<String> meterStringArray(File fichero) {
        ArrayList<String> array = new ArrayList<>();
        try {
            Scanner scn = new Scanner(fichero);
            while (scn.hasNext()){
                array.add(scn.next());
            }
            scn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void imprimirModa(ArrayList<String> array) {
        String moda = "";
        String aux2 = "";
        int contModa = 0, contador = 0;
        for (String auxA :
                array) {
            for (String auxB :
                    array) {
                if (auxA.equals(auxB))
                    contador++;
            }
            if (contador > contModa){
                moda = auxA;
                contModa = contador;
            }
            contador = 0;
        }
        System.out.println("La moda es \"" + moda + "\" que aparece " + contModa + " veces.");
    }
}
