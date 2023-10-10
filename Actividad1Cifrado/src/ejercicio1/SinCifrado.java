package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SinCifrado {
	
	private String user;
	private String pass;
	Scanner sc = new Scanner (System.in);
	File archivo;
	FileReader fr;
	BufferedReader br;
	FileWriter fw;
	BufferedWriter bw;
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public SinCifrado(String user, String pass){
		
		this.user=user;
		this.pass=pass;
		
	}
	

	public SinCifrado(){
		
	}
	
	public void pedirDatos() {
		System.out.println("Usuario:");
		user = sc.next();
		System.out.println("Contrasenna:");
		pass = sc.next();
	}
	
	public void annadirTxt() {
		
		try {
			
			archivo = new File("userList.txt");
			fw = new FileWriter(archivo);
			bw = new BufferedWriter (fw);
			bw.write("User: "+user+", ");
			bw.write("Pass: "+pass+";\n");
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void comprobarDatos() {
		
		try {
			
			System.out.println("Comprobacion de datos:");
			pedirDatos();
			archivo = new File("userList.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String dato;
			while ((dato = br.readLine()) != null) {
				 
					if (dato.equals("User: "+user+", Pass: "+pass+";")) { 
						System.out.println("Datos correctos"); 
					} else
						System.out.println("Datos incorrectos");
			}
		
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
