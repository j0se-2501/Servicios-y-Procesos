
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author jmartin
 */
public class Ppal extends Thread{

    /**
     * @param args the command line arguments
     */
    private String palabra;
    private Thread hilo;
    private int cant;
    
    public Ppal (String palabra) {
        this.palabra = palabra;
        hilo = new Thread(this);
        hilo.start();
        while (hilo.isAlive());        
        System.out.println("La palabra " + palabra + " se encuentra contenida en " + 
                            cant + " archivos");
    }
   
    public void run() {
        
            if (tiene())
                cant++;
        
    }
    
 private synchronized boolean tiene() {
        boolean existe = false;
        try {
            FileReader fr = new FileReader("ejemplo.txt");
            BufferedReader br = new BufferedReader(fr);
            String linea = br.readLine();
            while (linea != null) {
                if (linea.indexOf(palabra) != -1)
                    existe = true;
                linea = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return existe;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new Ppal("rojo");
        new Ppal("verde");

    }
    
}
