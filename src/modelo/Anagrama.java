/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    public static char[] obtenerVectorLetras(String palabra, char[] v) {
        v = new char[palabra.length()];
        for (int i = 0; i < palabra.length(); i++) {
            v[i] = palabra.charAt(i);
        }

        return v;
    }

    public static String anagrama(String palabra, File diccionario) throws FileNotFoundException {
        String res="";
        if ((diccionario).exists()) {
            Scanner sc = new Scanner(diccionario);
            String palabraAnalizada;
            

            while (sc.hasNext()) {
                palabraAnalizada = sc.next();
                ArrayList<Character> listaChars = new ArrayList<Character>();
                for (int i = 0; i < palabra.length(); i++) {
                    listaChars.add(palabra.charAt(i));
                }
                ArrayList<Character> listaAnalizada = new ArrayList<Character>();
                for (int i = 0; i < palabraAnalizada.length(); i++) {
                    listaAnalizada.add(palabraAnalizada.charAt(i));
                }
                boolean encontrado;
                boolean salida = false;
                if (listaChars.size() == listaAnalizada.size()) {
                    for (int i = 0; i < listaChars.size() && salida == false; i++) {
                        encontrado = false;
                        for (int j = 0; j < listaAnalizada.size() && encontrado == false; j++) {
                            if (listaChars.get(i).compareTo(listaAnalizada.get(j)) == 0) {
                                listaChars.set(i, '0');
                                encontrado = true;
                            }

                        }
                        if (encontrado == false) {
                            //System.out.println("No fue encontrado el caracter");
                            salida = true;
                        }
                    }
                    if (salida == false) {
                        //System.out.println(listaChars.toString());
                        res+="La palabra " + palabra + " es anagramica de " + palabraAnalizada+"\n";
                        //System.out.println("La palabra " + palabra + " es anagramica de " + palabraAnalizada);
                    }
                }
                listaChars.removeAll(listaChars);
                listaAnalizada.removeAll(listaChars);

            }
            
        } else {
            System.out.println("No existe el fichero");
        }
        return res;
    }

    public static boolean comprobarString(char[] v) {
        //System.out.println(Arrays.toString(v));
        for (int i = 0; i < v.length; i++) {
            if (v[i] == 'x') {
                System.out.println(Arrays.toString(v));
            } else {
                return false;
            }
        }

        return true;
    }

}
