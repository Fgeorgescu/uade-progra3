package TPO.graphImpl;

public class NodoArista implements Comparable<NodoArista>{
	int peso;
	NodoArista sig;
	NodoGrafo apunta;
	boolean Visitado;
	int origen;
	
	public int compareTo(NodoArista aux) {
		if(this.peso < aux.peso) {
			return -1;
		}else if(this.peso > aux.peso) {
			return 1;
		}else {
			return 0;
		}
	}
	
		
	

}
