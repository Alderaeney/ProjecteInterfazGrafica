/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.util.Arrays;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author sportak
 */
public class Antivirus {

    public static int contCoincidencias = 0;

    /*
    public static void main(String[] args) {
        File archivo = new File("C:\\");
        File archivo2 = new File("C:\\Users\\sportak\\Downloads\\Padre de Familia - Temporada 1 Completa [DVDrip MP4][Cap. 101_107][AAC 2.0 Castellano]");
        try {

            recorrerDirectoriosTodos(archivo2);
            System.out.println("*****************************************************************");
            System.out.println("HUBO " + Antivirus.contCoincidencias + " COINCIDENCIAS ENCONTRADAS");
            System.out.println("*****************************************************************");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Antivirus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Antivirus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     */

    public static void recorrerDirectoriosTodos(File ruta) throws NoSuchAlgorithmException, IOException {
        File[] ficheros = ruta.listFiles();
        String hashLocal;

        if (ficheros != null) {
            for (int i = 0; i < ficheros.length; i++) {
                if (!ficheros[i].getName().equalsIgnoreCase("$Recycle.Bin") && ficheros[i].length() < 100000000) { // Analizar ficheros menores de 100megas
                    if (ficheros[i].isDirectory()) {
                        //System.out.println("Entrando en " + ficheros[i].getName());
                        //System.out.println(ruta.getAbsolutePath() + "/" + ficheros[i].getName());
                        File nuevaRuta = new File(ruta.getAbsolutePath() + "/" + ficheros[i].getName());
                        recorrerDirectoriosTodos(nuevaRuta);
                    }
                    if (ficheros[i].isFile()) {
                        hashLocal = obtenerHashDeFichero(ficheros[i]);
                        System.out.println(hashLocal);
                        if (estaEnLaBlackList(hashLocal)) {
                            Antivirus.contCoincidencias++;
                        }
                    }
                }
            }
        }

    }

    public static String obtenerHashDeFichero(File f) throws NoSuchAlgorithmException, IOException {
        MessageDigest.getInstance("MD5");
        String filename = f.getAbsolutePath();
        //String checksum = "5EB63BBBE01EEED093CB22BB8F5ACDC3";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Files.readAllBytes(Paths.get(filename)));
        byte[] digest = md.digest();
        String myChecksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return myChecksum;
    }

    public static boolean estaEnLaBlackList(String f) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("db.txt"));
        String hashFichero;
        while (sc.hasNext()) {
            hashFichero = sc.nextLine();
            if (hashFichero.equalsIgnoreCase(f)) {
                return true;
            }
        }
        sc.close();
        return false;
    }

}
