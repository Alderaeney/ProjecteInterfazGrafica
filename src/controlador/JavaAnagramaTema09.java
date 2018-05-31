/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaanagramatema09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mati
 */
public class JavaAnagramaTema09 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion;
        String palabra, palabraAnalizada;
        File diccionario = new File("listado-general.txt");
        char[] letras = null;
        Scanner teclado = new Scanner(System.in);
        try {
            Scanner sc = new Scanner(diccionario);
            mostrarMenu();
            opcion = teclado.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Introduce palabra a analizar");
                    teclado.nextLine();
                    palabra = teclado.nextLine();

                    letras = obtenerVectorLetras(palabra, letras);
                    int cont = 0;
                    char[] aux = null;

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
                    break;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(JavaAnagramaTema09.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void mostrarMenu() {
        System.out.println("Bienvenido al menu");
        System.out.println("1.-Para buscar el anagrama de una palabra en concreto");
    }

    public static char[] obtenerVectorLetras(String palabra, char[] v) {
        v = new char[palabra.length()];
        for (int i = 0; i < palabra.length(); i++) {
            v[i] = palabra.charAt(i);
        }

        return v;

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
