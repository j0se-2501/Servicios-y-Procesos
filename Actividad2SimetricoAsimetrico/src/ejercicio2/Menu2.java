package ejercicio2;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;

public class Menu2 {
	
	static KeyPairGenerator keyPairGen;
	static KeyPair parejaClaves;
	static String mensaje;
	static Scanner sc = new Scanner (System.in);
	static byte[] mensajeCifrado;
	
public static void MostrarMenu() {
	
	generarKeysAsimetricas();
	
	int opcion=-1; //menu para elegir si se aporta una clave o se genera una
	
	while(opcion!=0) {
	
	System.out.println("\n---------------------------------------------\n\n"
			+ "Clave publica: "+Base64.getEncoder().encodeToString(parejaClaves.getPublic().getEncoded())+"\n"
			+ "Clave privada: "+Base64.getEncoder().encodeToString(parejaClaves.getPrivate().getEncoded())+"\n\n"
			+ "1. Cifrar Mensaje.\n"
			+ "2. Descifrar mensaje.\n"
			+ "3. Regenerar claves.\n\n"
			+ "0. Salir\n\n"
			+ "Elija opcion.");
	
	opcion = sc.nextInt();
	
	try {
	
	switch(opcion) {
	case 1: { cifrarMensaje();
			break;
			}
	case 2: { descifrarMensaje();
			break;
			}
	case 3: { generarKeysAsimetricas();
			break;
			}
	default: System.out.println("Introduzca una opcion correcta.");
	}
	
	} catch (InputMismatchException e) {
		
	}
	
	
	
	}
}
	
public static void generarKeysAsimetricas() {
	
	try {
	
	//Creating KeyPair generator object
	
	keyPairGen = KeyPairGenerator.getInstance("RSA");
	
    //Initializing the KeyPairGenerator
    keyPairGen.initialize(512);
    
    //Generating the pair of keys
    parejaClaves = keyPairGen.generateKeyPair();
    
    System.out.println("Claves generadas.");
	
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static void cifrarMensaje() {
	
	if (parejaClaves == null) System.out.println("\nEs necesario generar primero la pareja de claves."); 
	
	else {
	
	System.out.println("Introduzca un mensaje para cifrar:");
	
	sc.nextLine();
	mensaje=sc.nextLine();
	
	//Creating a Cipher object
    
	try {
		
		Cipher cipher;
		cipher = Cipher.getInstance("RSA");
	
      
    //Initializing a Cipher object
    cipher.init(Cipher.ENCRYPT_MODE, parejaClaves.getPublic()); //se encripta con la clave publica;
	System.out.println("Clave publica: "+Base64.getEncoder().encodeToString(parejaClaves.getPublic().getEncoded()));
    
    //encrypting the data
    mensajeCifrado = cipher.doFinal(mensaje.getBytes());	 
    System.out.println("Mensaje cifrado: "+new String(Base64.getEncoder().encodeToString(mensajeCifrado)));
	
	} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidKeyException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalBlockSizeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (BadPaddingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
	}
}

public static void descifrarMensaje() {
	
	if (mensajeCifrado==null) System.out.println("Es necesario cifrar primero un mensaje");
	
	else {
		
	System.out.println("Mensaje cifrado: "+new String(Base64.getEncoder().encodeToString(mensajeCifrado)));
    
	try {
		
		Cipher cipher;
		cipher = Cipher.getInstance("RSA");
	
      
    //Initializing a Cipher object
    cipher.init(Cipher.DECRYPT_MODE, parejaClaves.getPrivate()); //se desencripta con la clave privada;
	System.out.println("Clave privada: "+Base64.getEncoder().encodeToString(parejaClaves.getPrivate().getEncoded()));
    
    //encrypting the data
    byte[] bytes = cipher.doFinal(mensajeCifrado);	 
    System.out.println("Mensaje descifrado: "+new String(bytes, StandardCharsets.UTF_8));
	
	} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidKeyException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalBlockSizeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (BadPaddingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
}
	
}