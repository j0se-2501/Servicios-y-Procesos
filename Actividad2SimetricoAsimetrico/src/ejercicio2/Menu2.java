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
	
	int opcion=-1; //menu para que el usuario elija una opcion
	
	while(opcion!=0) {
	
	System.out.println("\n---------------------------------------------\n\n"
			+ "Clave publica: "+Base64.getEncoder().encodeToString(parejaClaves.getPublic().getEncoded())+"\n" //se pasa de Key a String para mostrarse correctamente por pantalla
			+ "Clave privada: "+Base64.getEncoder().encodeToString(parejaClaves.getPrivate().getEncoded())+"\n\n"
			+ "1. Cifrar Mensaje.\n"
			+ "2. Descifrar mensaje.\n"
			+ "3. Regenerar claves.\n\n"
			+ "0. Salir\n\n"
			+ "Elija opcion.");
	
	opcion = sc.nextInt();
	
	try { 
	
	switch(opcion) { //switch con las opciones del menu
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
	
	} catch (InputMismatchException e) { //catch para el caso en que no meta un entero
		
	}
	
	
	
	}
}
	
public static void generarKeysAsimetricas() {
	
	try {
	
	keyPairGen = KeyPairGenerator.getInstance("RSA"); //primero llamo el KeyPairGenerator, le meto el algoritmo RSA
	
    keyPairGen.initialize(512); //lo inicializo con un tamaño de 512 bits; es el que menor me permite, para que las claves no ocupen demasiado en pantalla
    
    parejaClaves = keyPairGen.generateKeyPair(); //aqui se genera la pareja de claves
    
    System.out.println("Claves generadas.");
	
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
	
}

public static void cifrarMensaje() {
	
	if (parejaClaves == null) System.out.println("\nEs necesario generar primero la pareja de claves."); //en caso de que no haya claves generadas, aunque luego cambie el codigo y aqui es imposible llegar en teoria, pero por si acaso lo dejo
	
	else {
	
	System.out.println("Introduzca un mensaje para cifrar:");
	
	sc.nextLine();
	mensaje=sc.nextLine(); //mensaje del usuario para cifrar
    
	try {
		
		Cipher cipher; //creo un objeto de la clase Cipher y le meto el algoritmo RSA
		cipher = Cipher.getInstance("RSA");
	
    cipher.init(Cipher.ENCRYPT_MODE, parejaClaves.getPublic()); //inicializo el objeto cipher y se encripta con la clave publica;
	System.out.println("Clave publica: "+Base64.getEncoder().encodeToString(parejaClaves.getPublic().getEncoded())); //muestro la clave publica por pantalla
    
    mensajeCifrado = cipher.doFinal(mensaje.getBytes()); //aquí se encripta a un array de bytes	 
    System.out.println("Mensaje cifrado: "+new String(Base64.getEncoder().encodeToString(mensajeCifrado))); //muestro el array de bytes encriptado por pantalla
	
	} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
		e.printStackTrace();
	} catch (InvalidKeyException e) {
		e.printStackTrace();
	} catch (IllegalBlockSizeException e) {
		e.printStackTrace();
	} catch (BadPaddingException e) {
		e.printStackTrace();
	}
    
	}
}

public static void descifrarMensaje() {
	
	//no dejo introducir un mensaje cualquiera al usuario ya que se necesita haber cifrado antes las claves;
	//se recoge el mensaje cifrado del metodo cifrarMensaje();
	//por ende, el usuario necesitaria primero cifrar un mensaje, y copiar y pegar luego el mensaje cifrado y las claves que salieran por pantalla.
	//para evitar complicaciones y por facilidad de uso, prefiero dejarlo asi.
	
	if (mensajeCifrado==null) System.out.println("Es necesario cifrar primero un mensaje"); //if en caso de que no se haya cifrado un mensaje.
																						
	else {
		
	System.out.println("Mensaje cifrado: "+new String(Base64.getEncoder().encodeToString(mensajeCifrado))); //muestro por pantalla el mensaje cifrado para confirmar que es el mismo
    
	try {
		
		Cipher cipher;
		cipher = Cipher.getInstance("RSA"); //genero un objeto cipher y le paso el argumento rsa
	
    cipher.init(Cipher.DECRYPT_MODE, parejaClaves.getPrivate()); //se inicializa y se desencripta con la clave privada;
	System.out.println("Clave privada: "+Base64.getEncoder().encodeToString(parejaClaves.getPrivate().getEncoded())); //muestro la clave privada por pantalla
    
    byte[] bytes = cipher.doFinal(mensajeCifrado);//descifro el mensaje a un array de bytes
    System.out.println("Mensaje descifrado: "+new String(bytes, StandardCharsets.UTF_8)); //y lo paso a string para mostrarlo pon pantalla
	
	} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
		e.printStackTrace();
	} catch (InvalidKeyException e) {
		e.printStackTrace();
	} catch (IllegalBlockSizeException e) {
		e.printStackTrace();
	} catch (BadPaddingException e) {
		e.printStackTrace();
	}
	
	}
	
}
	
}