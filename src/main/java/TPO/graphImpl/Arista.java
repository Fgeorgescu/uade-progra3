package TPO.graphImpl;

public class Arista implements Comparable<Arista> {
    public int origen;
    public int destino;
    public int peso;

    public Arista(int origen, int destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    @Override
    public int compareTo(Arista o) {
        return Integer.compare(peso, o.peso);
    }

    @Override
    public String toString() {
        return String.format("(%d:%d) - %d", origen, destino, peso);
    }
}
