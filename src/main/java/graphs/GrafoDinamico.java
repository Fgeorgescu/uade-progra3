package graphs;

public class GrafoDinamico implements GrafosTDA {
	NodoGrafo nodo;
	int cantidad;

	public void inicializarGrafo(int dim) {
		nodo = null;
		cantidad = 0;
	}
	public void eliminarVertice(int v) {
		NodoGrafo aux = encontrarNodo(v);
		
		if(aux != null) {
			aux = nodo; 
			if(aux.valor == v) {
				nodo = aux.sig;
				System.out.println("1");
			}
			while(aux != null) {
				this.eliminarArista(aux.valor, v);	
				if(aux.sig == this.encontrarNodo(v) && aux.sig != null) {
					aux.sig = aux.sig.sig;
					cantidad--;
					System.out.println("1");
				}
					aux = aux.sig;
					System.out.println("2");
			}
			
		}else {
			System.out.println("El vertice no existe");
		}

		
	}

	public void agregarVertice(int v) {
		if(this.encontrarNodo(v) == null) {
			
		
			NodoGrafo aux = new NodoGrafo();
			aux.valor = v;
			aux.lista = null;
			aux.sig = nodo;
			aux.Visitado = false;
			aux.marcado = false;
			nodo = aux;
			cantidad++;
		}	
			
	}

	
	public int[] vertices() {
		
		return null;
	}

	public void agregarArista(int v1, int v2, int peso) {
		if(this.encontrarNodo(v1) != null && this.encontrarNodo(v2) != null) {
			NodoArista aux = new NodoArista();
			aux.origen = v1;
			aux.peso = peso;
			aux.Visitado = false;
			aux.apunta = this.encontrarNodo(v2);
			aux.sig = encontrarNodo(v1).lista;
			encontrarNodo(v1).lista = aux; 
		}else {
			System.out.println("No existe alguino de los noods");
		}
	}
	

	
	public void eliminarArista(int v1, int v2) {
		NodoGrafo nodo = encontrarNodo(v1);
		NodoArista arista = nodo.lista;
		if(arista != null) {
			if(arista.apunta.valor == v2) {				
				nodo.lista = arista.sig;
			}
			else {
				while(arista.sig != null && arista.apunta.valor == v2) {
					arista = arista.sig;
				}
				if(arista.sig != null) {
					arista.sig = arista.sig.sig;
				}				
			}			
		}
	}
	
	


	public boolean existeArista(int v1, int v2) {
		NodoGrafo aux = this.encontrarNodo(v1);
		NodoArista arista = aux.lista ;
		while(arista != null) {
			if(arista.apunta.valor ==v2) {
				return true;
			}
			arista = arista.sig;
		}		
		return false;
	}

	public int pesoArista(int v1, int v2) {
		// TODO Auto-generated method stub
		return 0;
	}


	public void mostrarMatriz() {
		NodoGrafo aux = nodo;
		NodoArista arista;
		while(aux != null) {
		System.out.print(aux.valor + "\t" );
		arista = aux.lista;
			while(arista != null) {
				
				System.out.print(arista.peso + " "  + arista.apunta.valor +  "\t");
				arista = arista.sig;
			}
			System.out.println();
			aux = aux.sig;
		}
	}

	public boolean pertenece(int x) {
		if (this.encontrarNodo(x) != null) {
			return true;
		}
		return false;
	}

	public int mayorArista(int v) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int[] conjuntoAislados() {
		int aislados[]= new int [cantidad];
		NodoGrafo aux = nodo;
		
		while(aux != null) {
			
		}
		return null;
	}

	public void imparAristas() {
		// TODO Auto-generated method stub
		
	}
	
	public NodoGrafo encontrarNodo(int v) {
		NodoGrafo aux = nodo;
		while(aux != null) {
			if(aux.valor == v) {
				return aux;
			}
			aux = aux.sig;
		}
		return null;
	}

	public int[] dephtFirstSearch() {
		return null;
	}

	@Override
	public int[] adyacentes(int v) {
		return new int[0];
	}
}
