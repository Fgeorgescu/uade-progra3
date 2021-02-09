# Programación 3 - TPO

## Integrantes

- Alonso, Juan Cruz - LU: 1024826
- Georgescu, Franco - LU: 1084087

## Consigna

Implementar 2 de los siguientes algoritmos:

- Algoritmo Depth-First Search (DFS)
- Algoritmo Breadth-First Search (BFS)
- Algoritmo de Prim
- Algoritmo de Kruskal
- Algoritmo de Dijsktra
- Algoritmo de Floyd


### Log level

Se puede cambiar el log level para tener un mayor o menor feedback de lo que ocurre mientras se ejecuta el algoritmo.
> Para cambiar el log level modificar las líneas 2 y 3 del archivo logging.properties en `src/main/resources`

Los niveles que recomendamos tener en cuenta son:

- INFO: Muestra mensajes importantes y warnings
- FINE: Muestra detalles de debuggeo, como cuando se inician un método o se termina el mismo, se inicia un loop, etc)
- FINER: Muestra mucho más detalle. Esto se incluirá en loops que sean de una profundidad mayor (for dentro de un for, por ejemplo)
- FINEST: Muestra TODO. **Usar bajo su propio riesgo...**

#### Ejemplo de FINEST

```
[2021-02-08 22:53:05] [FINEST ] Setteando como blanco y con predecesor -1 el nodo 1 
[2021-02-08 22:53:05] [FINEST ] Setteando como blanco y con predecesor -1 el nodo 2 
[2021-02-08 22:53:05] [FINEST ] Setteando como blanco y con predecesor -1 el nodo 3 
[2021-02-08 22:53:05] [FINEST ] Setteando como blanco y con predecesor -1 el nodo 4 
[2021-02-08 22:53:05] [FINEST ] Setteando como blanco y con predecesor -1 el nodo 5 
[2021-02-08 22:53:05] [FINEST ] Setteando como blanco y con predecesor -1 el nodo 6 
[2021-02-08 22:53:05] [FINEST ] Setteando como blanco y con predecesor -1 el nodo 7 
[2021-02-08 22:53:05] [FINEST ] Setteando como blanco y con predecesor -1 el nodo 8 
[2021-02-08 22:53:05] [FINEST ] Setteando como blanco y con predecesor -1 el nodo 9 
[2021-02-08 22:53:05] [FINE   ] Iniciamos la iteración sobre el dfs_forest 
[2021-02-08 22:53:05] [FINEST ] Analizando el nodo 0 en el loop de dfs forest 
[2021-02-08 22:53:05] [FINE   ] El nodo 0 es BLANCO, vamos a profundizar el mismo. Llamando al método DFS 
[2021-02-08 22:53:05] [FINE   ] Se inicia el método dfs recursivo para el nodo 0 
[2021-02-08 22:53:05] [FINE   ] Tiempo de inicio para el nodo 0: 1 
[2021-02-08 22:53:05] [FINEST ] Adyacentes para 0: [1, 3, 5, 8, 9, -1, -1, -1, -1, -1]. Estas son las etiquetas de los nodos adyacentes (excluidos los -1). 
[2021-02-08 22:53:05] [FINER  ] Analizando el nodo 1, adyacente al nodo origen 0 
[2021-02-08 22:53:05] [FINE   ] Se inicia el método dfs recursivo para el nodo 1 
```

## DFS

Para esta implementación, usamos GráficoEstático. La misma se basa en una matriz de adyacencia para registrar los vértices entre los nodos.

A continuación dejamos un ejemplo de una de estas matrices, como se vería en consola. 

>Las aristas se representan con numeros distintos a 0. Si bien esto puede representar un peso, como no es necesario para este algoritmo, **tomamos que un valor != 0 es una arista existente**, indistintamente del valor.

Siguiendo ese razonamiento, el nodo 0 será adyacente consigo 1, 3 y 9. Sin embargo, no existe arista que conecte 9 con 0.
```
	0	1	2	3	4	5	6	7	8	9	

0	0	33	0	31	0	0	0	0	0	61	

1	0	0	12	0	41	1	0	0	87	0	

2	0	0	0	0	0	0	0	32	0	0	

3	0	94	33	8	0	64	87	0	9	92	

4	0	0	48	26	0	0	0	0	0	0	

5	34	37	10	0	11	0	80	29	0	35	

6	39	77	8	63	0	0	0	0	0	35	

7	0	20	36	0	0	0	42	0	0	0	

8	0	55	5	80	66	0	31	52	0	0	

9	0	0	0	40	0	0	0	12	14	0	
```

### Configuración

Desde la clase DFS se pueden modificar constantes para alterar el comportamiento del algoritmo.

Constantes:
- **DIM**: Cantidad de nodos / dimensión de la matriz.
- **MAX_WEIGTH**: Peso máximo de una arista. Si bien no altera el funcionamiento del algoritmo, con numeros más bajos es menos probable que aparezcan aristas en el randomizado.
- **RANDOMIZER_ITERATIONS**: Cantidad de veces que se generan aristas al azar. Un valor alto nos dará un grafo con más aristas entre sus nodos.