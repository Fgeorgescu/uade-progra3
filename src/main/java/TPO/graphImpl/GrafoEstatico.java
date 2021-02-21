package TPO.graphImpl;

import TPO.utils.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class GrafoEstatico implements GrafosTDA {
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


	int indice ;
	int[][] matrizAdy;
	int dim ; 
	int [] etiquetas;
	
	public void inicializarGrafo(int dim) {
		this.dim = dim ;
		indice = 0;
		matrizAdy = new int[dim][dim];		
		etiquetas = new int[dim];
		
	}
	
	public void agregarVertice(int v) {
		if(indice < dim) {
			etiquetas[indice] = v;			
			for(int i = 0 ;i < dim; i++ ) {
				matrizAdy[i][indice] = 0;
				matrizAdy[indice][i] = 0;
			}
			indice ++;
		}else {
			System.out.println("No se puden agregar mas nodos");
		}		
	}
	
	public int posicionDeNodo(int v) {
		for(int i = 0;  i < indice ; i++) {
			if(etiquetas[i] == v) {
				return i;
			}
		}
		return -1;
		
	}
	public void eliminarVertice(int v) {
		int aux = this.posicionDeNodo(v);
		if(aux != -1) {
			for(int i = 0; i < indice; i++) {
				matrizAdy[aux][i] = matrizAdy[indice-1][i];
				matrizAdy[i][aux] = matrizAdy[i][indice - 1];				
			}
		etiquetas[this.posicionDeNodo(v)] = etiquetas[indice - 1];
		indice-- ;
		}else {
			System.out.println("No se encontro el vertice");
		}	
	}

	public void agregarArista(int v1, int v2, int peso) {
		if(this.posicionDeNodo(v1) != -1 && this.posicionDeNodo(v2) != -1) {
			matrizAdy[this.posicionDeNodo(v1)][this.posicionDeNodo(v2)] = peso;
		}else {
			System.out.println("Alguno de los nodos no existe");
		}
	}
	
	public int[] vertices() {
		int[] aux = new int[indice];
		for(int i = 0; i < indice; i++) {
			aux[i] = etiquetas[i];
			//System.out.print(aux[i] + "\t");
		}
		//System.out.println();
		return aux;
	}

	public void imprimirVertices() {
		int[] aux = new int[indice];
		for(int i = 0; i < indice; i++) {
			aux[i] = etiquetas[i];
			System.out.print(aux[i] + "\t");
		}
		System.out.println();
	}
	
	
	public void eliminarArista(int v1, int v2) {
		
		if(this.posicionDeNodo(v1)>=0 && this.posicionDeNodo(v2)>=0) {
			matrizAdy[this.posicionDeNodo(v1)][this.posicionDeNodo(v2)] = 0;	
		}else {
			System.out.println("No existe alguno de los nodos");
		}
	}
	



	public boolean existeArista(int v1, int v2) {

		return false;
	}

	@Override
	public int pesoArista(int v1, int v2) {
	
		return 0;
	}
	
	public void mostrarMatriz() {
		System.out.print("\t");
		this.imprimirVertices();
		System.out.println();
		for(int i = 0; i < indice; i++) {
			System.out.print(etiquetas[i] + "\t");
			for(int j = 0; j < indice; j++) {
				System.out.print(this.matrizAdy[i][j] + "\t");
			}
			System.out.println("\n");
		}
	}
	public boolean pertenece(int x) {
		return 	(this.posicionDeNodo(x) != -1) ;
	}
	
	public int mayorArista(int v) {
		int fila = this.posicionDeNodo(v);
		if(v != -1) {
			int aux = 0;
			for(int i = 0; i < indice; i++) {
				if(aux  < matrizAdy[fila][i]) {
					aux = matrizAdy[fila][i];
				}
			}
		
			return aux ;
		}else {
			System.out.println("El nodo no se encuentra");
			return v;
		}
	}
	
	public int[] conjuntoAislados() {
		int[] aislados = new int [indice];
		int flag = 0;
		for(int i = 0 ; i < indice ; i ++) {
			flag = 0;
			for(int j = 0 ; j < indice ; j++) {
				if( matrizAdy[i][j] != 0 || matrizAdy[j][i] !=0)  {
					flag = 1;
					break;
				}				
			}
			if(flag == 0) {
				aislados[i]= etiquetas[i];
				System.out.print(aislados[i] + "\t");
			}
		}
		return aislados;
			
	}
	
	public void imparAristas() {
		int contar ;
		for(int i = 0; i < indice; i ++) {
			contar = 0;
			for(int j = 0; j < indice; j++) {
				if(matrizAdy[i][j] != 0) {
					contar++;
				}
			}
			if(contar % 2 != 0) {
				System.out.print(etiquetas[i] + "\t");
			}
		}
	}

	@Override
	public int[] dephtFirstSearch() {
		return null;
	}

	@Override
	public int[] adyacentes(int v) {
		int[] adyacentes = new int[dim];
		Arrays.fill(adyacentes, -1);
		int i = 0;

		for (int candidate = 0 ; candidate < matrizAdy.length; candidate++) {
			if (matrizAdy[v][candidate] != 0) {
				adyacentes[i] = candidate;
				i++;
			}
		}
		return adyacentes;
	}

	public List<Arista> aristas() { // O(n^2)
		List<Arista> aristas = new LinkedList<>();

		for (int i = 0; i < matrizAdy.length ; i ++) {
			for (int j = 0; j < matrizAdy[i].length ; j ++) {
				if (matrizAdy[i][j] != 0) {
					aristas.add(new Arista(i, j, matrizAdy[i][j]));
				}
			}

		}

		return aristas;
	}
}
