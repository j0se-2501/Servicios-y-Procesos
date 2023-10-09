package ejercicio;

import java.util.HashMap;

import ejercicio.IteradorVector;

public class HashMap2{ 

public HashMap<Integer, Integer> coleccion = new HashMap<Integer, Integer>();
int valor;
int clave;
int tamanno=10;

public void iniciarHashMapVacio (int tamanno) {
	coleccion.put(0, 0);
	coleccion.put(1, 0);
	coleccion.put(2, 0);
	coleccion.put(3, 0);
	coleccion.put(4, 0);
	coleccion.put(5, 0);
	coleccion.put(6, 0);
	coleccion.put(7, 0);
	coleccion.put(8, 0);
	coleccion.put(9, 0);
}

public int getValorDeClave(int clave){
 return coleccion.get(clave);
}

public void setValorEnClave(int valor){
	clave=valor%tamanno;
	while(coleccion.get(clave)!=0) {
		clave=(valor++)%tamanno;
		if (valor>9) valor=0;
	}
}

public int dimension() {
return coleccion.size();
}

public IteradorVector iterador(){
 return new IteradorVector(this);
}
}
