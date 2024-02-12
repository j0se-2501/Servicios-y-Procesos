package backtrackingDiana;

import java.util.ArrayList;

public class IntentoBueno {
	
	//codigo final tras buscar ejemplos en internet
	//modificacion del codigo encontrado en https://favtutor.com/blogs/combination-sum 
	//partiendo de las ideas planteadas en la clase IntentoInicial

    public static void backtracking(int[] zonasDiana, int tirada, int objetivo, ArrayList<Integer> posibleSolucion, ArrayList<ArrayList<Integer>> resultados, int maximoTiradas) {
        if (objetivo < 0 || posibleSolucion.size() > maximoTiradas) {
            return;
        }
        if (objetivo >= 0 && posibleSolucion.size()<=maximoTiradas) { //entiendo que son 100 puntos o menos, y 5 dardos o menos,
        															  //no 100 puntos y 5 dardos exactos, pues no habría combinaciones posibles
            resultados.add(new ArrayList<>(posibleSolucion));
        }
        for (int i = tirada; i < zonasDiana.length; i++) {
            posibleSolucion.add(zonasDiana[i]);
            backtracking(zonasDiana, i, objetivo - zonasDiana[i], posibleSolucion, resultados, maximoTiradas);
            //si a backtracking en vez de i le pasáramos 0 en el segundo argumento, permitiría permutaciones en las combinaciones
            //con i, se evitan las permutaciones y todas las combinaciones son únicas
            //esto es así porque con i continúa mirando la misma zona de la diana en la que está y las siguientes
            //pero con 0 empezaría cada vez que se llama a mirar de nuevo desde la primera zona de la diana (10 puntos)
            posibleSolucion.remove(posibleSolucion.size() - 1);
            //se elimina la última zona de la diana después de realizar el backtracking para permitir nuevas combinaciones
        }
    }

    public static void main(String args[]) {
        int[] zonasDiana = {10, 25, 50, 75};
        int objetivo = 100;
        int tiradasMaximas = 5;
        int tiro = 0;
        ArrayList<ArrayList<Integer>> resultados = new ArrayList<>();
        backtracking(zonasDiana, tiro, objetivo, new ArrayList<>(), resultados, tiradasMaximas);
        System.out.println("Combinaciones de 5 dardos o menos que obtienen 100 o menos puntos:\n\n");
        for (ArrayList<Integer> combinacion : resultados) {
        	System.out.println(combinacion+" -- "+combinacion.stream().mapToInt(Integer::intValue).sum()+" puntos!");
        }
    }
}

