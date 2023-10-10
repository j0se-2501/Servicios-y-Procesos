package ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
	StringBuffer hexStringUser;
	StringBuffer hexStringPass;
	
	 public ConCifrado() {
		 
	 }
     
     public void cifrarDatos() { //meto los user y pass del usuario en dos string cifrados por MessageDigest
    	 
    	 sinCifrado = new SinCifrado();
         sinCifrado.pedirDatos(); //llamo a la clase sinCifrado ya que al final pido los datos igual
    	 
    	 try {
        	 
        	 md = MessageDigest.getInstance("SHA-256");
        		
             md.update(sinCifrado.getUser().getBytes());
             
             digestUser = md.digest();      
            
             hexStringUser = new StringBuffer();
             
             for (int i = 0;i<digestUser.length;i++) {
                hexStringUser.append(Integer.toHexString(0xFF & digestUser[i])); //guardo el string cifrado del user y de la pass por separado
                											//de esta forma después puedo controlarlos en el metodo de comprobar
             }
             
         	md.update(sinCifrado.getPass().getBytes());
           
            digestPass = md.digest();      
          
            hexStringPass = new StringBuffer();
           
            for (int i = 0;i<digestPass.length;i++) {
              hexStringPass.append(Integer.toHexString(0xFF & digestPass[i]));
            }
    	 
    	 }  catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	 }
     }
     
     public void escribirDatosCifradosEnArchivo() {
    	 
    	cifrarDatos(); 
       
    	try {
    		
        	archivo = new File("userListCifrada.txt");
            fw = new FileWriter(archivo);
         	 bw = new BufferedWriter (fw);
        	bw.write("User: "+ hexStringUser.toString()+", ");
            System.out.println("User: "+ hexStringUser.toString()+", ");
        	bw.write("Pass: "+ hexStringPass.toString()+";\n");
        	System.out.println("Pass: "+ hexStringPass.toString()+";\n");
        	bw.close();
   	    
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
    	 
     }
     
     public void comprobarCoincidenciaDatos() {
    	
    	 System.out.println("Comprobación de datos. Repita las credenciales:");
    	 cifrarDatos();
    	 
    	 try {
    	 
         archivo = new File("userListCifrada.txt");
		 fr = new FileReader(archivo);
		 br = new BufferedReader(fr);
		 String dato;
			while ((dato = br.readLine()) != null) {
				 
					if (dato.equals("User: "+hexStringUser+", Pass: "+hexStringPass+";")) { 
						System.out.println("Datos correctos"); 
					} else
						System.out.println("Datos incorrectos");
			}
         
 		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			
			try {
				if (null != fr) {
					fr.close();
					fw.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
    	 
     }
     
     

   
}

