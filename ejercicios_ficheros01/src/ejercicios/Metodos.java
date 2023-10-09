package ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Metodos {

public static void contarCaracteres() {
	
	File archivo = null; //inicializo en file y el filereader a null
	FileReader fr = null;

	try {

		String nombreArchivo = "elQuijote.txt"; //guardo el nombre del archivo en una variable porque me resultó más cómodo pero no hace falta
		archivo = new File("files\\", nombreArchivo); //el archivo está en la carpeta files. Files está en la raíz del proyecto
		fr = new FileReader(archivo); //creo el filereader del archivo

		int caracter; //variable para ir analizando caracter a caracter. Es un int porque el metodo read() del filereader trabaja con el codigo ascii
		int contadorCaracteres = 0;//contador de caracteres

		while ((caracter = fr.read()) != -1) { // //-1 porque el metodo read devuelve -1 cuando no encuentra un caracter
			contadorCaracteres++;
		}

		System.out.println("El archivo " + nombreArchivo + " tiene " + contadorCaracteres + " caracteres.");

	} catch (IOException e) { 

		e.printStackTrace();
	} finally {
		try {
			if (null != fr) {
				fr.close(); //con el finally aseguramos que se cierra en canal del filereader
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}

public static void contarPalabras() {

	File archivo = null;
	FileReader fr = null;

	try {

		archivo = new File("files\\", "elQuijote.txt");
		fr = new FileReader(archivo);

		int caracter;
		int contadorPalabras = 0;

		while ((caracter = fr.read()) != -1) {
			if ((caracter == 32) || (caracter == 10)) //como una palabra equivale a un espacio o a un salto de linea, si encuentra el fr uno de los dos, sumo uno al contador de palabras
				contadorPalabras++;
		}

		System.out.println("\nEl archivo " + archivo.getName() + " tiene " + contadorPalabras + " palabras."); //también se puede usar este método getName y no usar una variable extra como antes "nombreArchivo"

	} catch (IOException e) {

		e.printStackTrace();
	} finally {
		try {
			if (null != fr) {
				fr.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}

public static void contarCapitulos() {

	File archivo = null;
	FileReader fr = null;
	BufferedReader br = null; //en este metodo ya uso el bufferedreader

	try {

		String nombreArchivo = "elQuijote.txt";
		archivo = new File("files\\", nombreArchivo);
		fr = new FileReader(archivo);
		br = new BufferedReader(fr);

		String linea;
		int contadorCapitulos = 0;

		while ((linea = br.readLine()) != null) {
			for (int i = 1; i < 100; i++) //este for lo hago por si fuera necesario buscar capitulo junto a un numero, por si diera la casualidad de que una linea empezara por 'capitulo' siendo una palabra suelta en mitad del texto
				if ((linea.startsWith("CAPÍTULO " + i + ": ")) || (linea.startsWith("Capítulo " + i + ": "))) //el metodo startsWith analiza el comienzo del String
					contadorCapitulos++;
		}

		System.out.println("\nEl archivo " + nombreArchivo + " tiene " + contadorCapitulos + " capitulos.");

	} catch (IOException e) {

		e.printStackTrace();
	} finally {
		try {
			if (null != fr) {
				fr.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}

public static void contarMolinos() {

	File archivo = null;
	FileReader fr = null;
	BufferedReader br = null;

	try {

		String nombreArchivo = "elQuijote.txt";
		archivo = new File("files\\", nombreArchivo);
		fr = new FileReader(archivo);
		br = new BufferedReader(fr);

		String linea;
		int contadorMolinos = 0;

		while ((linea = br.readLine()) != null) {
			for (String st : linea.split(" ")) //con este for each y el metodo split separo las palabras dentro de un string
				if ((st.startsWith("molino")) || (st.startsWith("Molino"))) //si cada una de esas unidades del for each empieza por molino o Molinos, entra en el if y suma 1 el contador
					contadorMolinos++;
		}

		System.out.println("\nEn el archivo " + nombreArchivo + " aparecen los molinos " + contadorMolinos + " veces.");

	} catch (IOException e) {

		e.printStackTrace();
	} finally {
		try {
			if (null != fr) {
				fr.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

}

public static void dividirCapitulosEnFicheros() {

	File archivo = null;
	File archivoNuevo = null;
	FileReader fr = null;
	BufferedReader br = null;
	FileWriter fw = null; //aqui ya escribo en el archivo así que necesito el filewriter y el printwriter
	PrintWriter pw = null;

	try {

		String nombreArchivo = "elQuijote.txt";
		String nombreNuevoArchivo = null; //creo otra variable para guardar las copias independientes que voy haciendo de cada capitulo
		archivo = new File("files\\", nombreArchivo);
		fr = new FileReader(archivo);
		br = new BufferedReader(fr);

		String linea;

		int capitulo = 1; //contador para los capitulos; me sirve para identificar los capitulos en el texto y tambien para nombrar los archivos nuevos que se van creando
		while ((linea = br.readLine()) != null) { //primero leo todo el texto

			if ((linea.startsWith("CAPÍTULO " + capitulo + ": "))
					|| (linea.startsWith("Capítulo " + capitulo + ": "))) { //si la linea comienza con capitulo, en mayus o minus, entra en el if para crear un nuevo archivo con ese nuevo capitulo
				if (fw != null) //en cada iteracion del while, al crear un nuevo capitulo, quiero cerrar el filewriter para poder abrir uno nuevo con el nuevo file del nuevo capitulo 
					fw.close();
				nombreNuevoArchivo = "elQuijote_cap" + String.format("%02d", capitulo) + ".txt"; //gracias a la variable capitulo, puedo nombrar los archivos con los distintos numeros; %02d da el formato 01, 02, etc
				archivoNuevo = new File("files\\capitulos\\", nombreNuevoArchivo); //creo el archivo nuevo del capitulo con el nombre creado justo en la linea anterior
				fw = new FileWriter(archivoNuevo);
				pw = new PrintWriter(fw); //abro nuevos fw y pw
				capitulo++; //le sumo 1 a capitulo para que a partir de ahora este if busque ya el proximo capitulo y no este que se acaba de crear
			}
			if (pw != null && fw != null) { //necesito que sean distintos de null porque como el quijote no empieza directamente por "capitulo 1", se salta el if que crea el pw/fw, y si son nulos dan error al imprimirse
				pw.println(linea);
			}
		}

		System.out.println("\nSe han dividido los capitulos de " + nombreArchivo
				+ " en diferentes archivos de texto, en la ruta files\\capitulos.");

	} catch (IOException e) {

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

public static void copiarArchivoEnMayusculas() {

	File archivo = null;
	File archivoNuevo = null;
	FileReader fr = null;
	BufferedReader br = null;
	FileWriter fw = null;
	PrintWriter pw = null;

	try {

		String nombreArchivo = "elQuijote.txt";
		archivo = new File("files\\", nombreArchivo);
		archivoNuevo = new File("files\\copia\\", "copia_elQuijote.txt");
		fr = new FileReader(archivo);
		br = new BufferedReader(fr);
		fw = new FileWriter(archivoNuevo);
		pw = new PrintWriter(fw);

		String linea;
		String lineaMayus = ""; //necesito una segunda variable que vaya almacenando las lineas en las que cambio una palabra por su version en mayusculas

		while ((linea = br.readLine()) != null) {
			for (String st : linea.split(" ")) { //con el for each y el metodo split, separo las palabras del string para poder analizar cuales son
				if (((st.contains("molinos")) || (st.contains("hidalgo")) || (st.contains("Dulcinea")))
						&& (!st.contains("hidalgos"))) { //quiero que no contenga hidalgo en plural porque solo me pide el ejercicio en singular
					lineaMayus += st.toUpperCase() + " "; //voy creando la linea concatenando de nuevo los string que he ido separando en el for each, ya convertidos a mayuscula
				} else
					lineaMayus += st + " "; //si no entra en el if de las palabras clave, el proceso de concatenación es el mismo; podría hacerlo sin el else creo, simplemente usando una concatenacion y haciendo el uppercase antes por separado
			}
			pw.println(lineaMayus); //escribo en el archivo de copia la linea
			lineaMayus = ""; //reinicio la variable que copia la linea para que este vacía y lista para copiar otra

		}

		System.out.println("\nSe ha copiado el archivo " + nombreArchivo
				+ " con las palabras 'Dulcinea', 'molinos' e 'hidalgo' en mayusculas, en la ruta files\\copia\\copia_elQuijote.txt.");

	} catch (IOException e) {

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