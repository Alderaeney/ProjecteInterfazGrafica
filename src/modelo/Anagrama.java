/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mati
 */
public class Anagrama {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }

    public static char[] obtenerVectorLetras(String palabra, char[] v) {
        v = new char[palabra.length()];
        for (int i = 0; i < palabra.length(); i++) {
            v[i] = palabra.charAt(i);
        }

        return v;

    }

    public void anagrama(String palabra, String diccionario) {
        if (new File(diccionario).exists()) {
            char[] letras = null;
            letras = obtenerVectorLetras(palabra, letras);
            int cont = 0;
            char[] aux = null;
            Scanner sc = new Scanner(diccionario);
            String palabraAnalizada = "";
            while (sc.hasNext()) {
                palabraAnalizada = sc.next();
                aux = obtenerVectorLetras(palabraAnalizada, aux);
                if (letras.length == aux.length) {
                    cont++;

                    boolean encontrada, salida = false;
                    for (int i = 0; i < letras.length && salida == false; i++) {
                        encontrada = false;
                        for (int j = 0; j < letras.length && encontrada == false; j++) {
                            Character c = (char) letras[i];
                            Character c2 = (char) aux[j];

                            if (c.compareTo(c2) == 0) {//si encontramos coincidencia
                                aux[j] = 'x';
                                encontrada = true;
                                //System.out.println("La letra--->> "+c+" de "+palabraAnalizada+"<<---coinciden con  ---> "+c2);
                            }

                        }
                        if (comprobarString(aux) == false) {
                            salida = true;
                        }

                        if (encontrada == false) {
                            salida = true;
                        }
                    }
                    if (salida == false) {
                        System.out.println("LA PALABRA " + palabraAnalizada + " ES ANAGRAMICA DE " + palabra);
                    }
                }
            }
        }else{
            System.out.println("No existe el fichero");
        }
    }

    public static boolean comprobarString(char[] v) {
        System.out.println(Arrays.toString(v));
        for (int i = 0; i < v.length; i++) {
            if (v[i] == 'x') {

            } else {
                return false;
            }
        }
        return true;
    }

}
