package ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import ejercicio1.SinCifrado;

public class ConCifrado {
	
	String mensaje;
	SinCifrado sinCifrado;
	File archivo;
	FileReader fr;
	BufferedReader br;
	FileWriter fw;
	BufferedWriter bw;
	MessageDigest md;
	byte[] digestUser;
	byte[] digestPass;
	StringBuffer hexString;
	
	
	 public ConCifrado() {
		 
	 }
   
     public void pedirDatosParaCifrar() {
    	 
      try {
    	  
    	 
   			
      archivo = new File("userListCifrada.txt");
      fw = new FileWriter(archivo);
   	  bw = new BufferedWriter (fw);
      sinCifrado = new SinCifrado();
      
      sinCifrado.pedirDatos();
      cifrarDatos();
      
      
      comprobarDatos();
      
      
      } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
     }
     
     public void cifrarDatos() {
    	 
    	 try {
    	 
    	 md = MessageDigest.getInstance("SHA-256");
    		

         //Passing data to the created MessageDigest Object
         md.update(sinCifrado.getUser().getBytes());
         
         //Compute the message digest
         digestUser = md.digest();      
        
         //Converting the byte array in to HexString format
         StringBuffer hexString = new StringBuffer();
         
         for (int i = 0;i<digestUser.length;i++) {
            hexString.append(Integer.toHexString(0xFF & digestUser[i]));
         }
         bw.write("User: "+ hexString.toString()+", ");
         System.out.println("User: "+ hexString.toString()+", ");
     	  
     	  
     	 md.update(sinCifrado.getPass().getBytes());
       
        //Compute the message digest
        digestPass = md.digest();      
      
        //Converting the byte array in to HexString format
        hexString = new StringBuffer();
       
        for (int i = 0;i<digestPass.length;i++) {
          hexString.append(Integer.toHexString(0xFF & digestPass[i]));
        }
        bw.write("Pass: "+ hexString.toString()+";\n");
   	    bw.close();
   	 
   	    System.out.println("Pass: "+ hexString.toString()+";\n");
   	 
   	    sinCifrado.pedirDatos();
   	 
    	 } catch (NoSuchAlgorithmException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	 
     }
     
     public void comprobarDatos() {
    	 
    	 System.out.println("Comprobacion de datos:");
    	 sinCifrado.pedirDatos();
    	 
     }

   
}

