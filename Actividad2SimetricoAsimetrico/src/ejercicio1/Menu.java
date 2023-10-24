package ejercicio1;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Menu { //incluye metodos cifrar y descifrar simetricos
	
	static Scanner sc = new Scanner(System.in);
	static Key clave;
	static String claveString;
	static String textoACifrar="";
	static byte[] textoCifrado;
	static String resultado;
	
public static void mostrarMenu() {
	
	int opcion=0; //menu para elegir si se aporta una clave o se genera una
	
	
	
	System.out.println("\n---------------------------------------------\n"
			+ "1. Introducir clave.\n"
			+ "2. Obtener clave.\n\n"
			+ "Elija opcion.");
	
	try {
		opcion = sc.nextInt();
		
	} catch (InputMismatchException e) {
		System.out.println("Por favor, introduzca un numero.");
	}
	
	switch(opcion) {
	case 1: //opcion 1, el usuario da la clave
		System.out.println("Introduzca la clave (16, 24 o 32 caracteres)."); //limitacion del algoritmo AES
		claveString = sc.next();
		byte[] claveBytes = claveString.getBytes(StandardCharsets.UTF_8); //se pasa el String del usuario a array de Bytes para pasarlo a la clase Key
        clave = new SecretKeySpec(claveBytes, "AES"); //se usa el algoritmo AES y se crea la key
		break;
	
	case 2: //opcion 2, genera el programa la clave
		try {
			KeyGenerator generadorClave = KeyGenerator.getInstance("AES"); 
			SecureRandom secRandom = new SecureRandom();
		    generadorClave.init(secRandom);
		    clave = generadorClave.generateKey();
		    
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}	
		break;
	
	default: System.out.println("Opcion incorrecta.");
		break;
	}
	//se pasa el array de bytes a String para mostrar la clave completa por pantalla
	byte[] rawData = clave.getEncoded();
	//String claveString = new String(rawData, StandardCharsets.UTF_8);
    String claveString = Base64.getEncoder().encodeToString(rawData); 
    
    //se usa la linea no comentado para mostrar en caracteres los datos binarios del array de bytes
    //la linea comentada daria los datos de texto, que en este caso son ilegibles; mas abajo se usaran para mostrar el mensaje descifrado
    
    
	System.out.println("Clave de cifrado simetrico: "+claveString);
	System.out.println("Introduzca el texto a cifrar:");
	sc.nextLine();
	textoACifrar=sc.nextLine();
	
	cifrarSimetrico(clave, textoACifrar);
	descifrarSimetrico(clave, textoCifrado);
	
	
}

public static void cifrarSimetrico(Key secretKey,String textoSinCifrar) {
	
	//se usa la clase cipher.
	//el ejercicio pedia recibir la clave como String, sin embargo+
	//el metodo keyGenerator genera una Key y la clase cipher trabaja con una Key
	//ademas de un paso innecesario la conversion Key -> String -> Key, no he encontrado como poder hacerlo.
	
	Cipher cipher;
	try {
		cipher = Cipher.getInstance("AES");
		cipher.init(cipher.ENCRYPT_MODE, secretKey); //uso del encrypt mode
	    textoCifrado = cipher.doFinal(textoSinCifrar.getBytes());//el string sin cifrar se pasa a un array de bytes cifrado  
	  
	    String textoCifradoString = Base64.getEncoder().encodeToString(textoCifrado); //hacemos lo mismo que comentado arriba para poder mostrar el texto cifrado
	    System.out.println("Mensaje cifrado: "+textoCifradoString);
	
	} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvalidKeyException e) {
		System.out.println("Clave incorrecta. Introduzca una clave con 16, 24 o 32 caracteres.");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}   
}

public static void descifrarSimetrico(Key secretKey, byte[] textoCifrado) {
		
		//uso la clase cipher
		Cipher cipher;
		try {
			byte[] rawData = secretKey.getEncoded();
		    String claveString = Base64.getEncoder().encodeToString(rawData);
		    
			System.out.println("Clave de descifrado simetrico: "+claveString); //vuelvo a pasar a string la clave simplemente para ver+
															//por pantalla que efectivamente es la misma y que el cifrado es simetrico
			cipher = Cipher.getInstance("AES");
			cipher.init(cipher.DECRYPT_MODE, secretKey); //uso del decrypt mode
		    byte[] bytes = cipher.doFinal(textoCifrado);
		    resultado = new String(bytes, StandardCharsets.UTF_8); //ahora si uso la forma que pasa a String los datos de texto y no los datos binarios
		    System.out.println("Mensaje descifrado: '"+resultado+"'");
		
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	
}

}
