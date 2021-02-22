# Programación 3 - TPO

## Integrantes

- Alonso, Juan Cruz - LU: 1024826
- Georgescu, Franco - LU: 1084087

## Consigna

Implementar 3 de los siguientes algoritmos:

- Algoritmo Depth-First Search (DFS)
- Algoritmo Breadth-First Search (BFS)
- Algoritmo de Prim
- Algoritmo de Kruskal
- Algoritmo de Dijsktra
- Algoritmo de Floyd

> Se implementa: Algoritmo Depth-First Search (DFS), Kruskal y Dijkstra

## Log level

Se puede cambiar el log level para tener un mayor o menor feedback de lo que ocurre mientras se ejecuta el algoritmo.
> Para cambiar el log level modificar las líneas 2 y 3 del archivo logging.properties en `src/main/resources`

Los niveles que recomendamos tener en cuenta son:

- INFO: Muestra mensajes importantes y warnings
- FINE: Muestra detalles de debuggeo, como cuando se inician un método o se termina el mismo, se inicia un loop, etc)
- FINER: Muestra mucho más detalle. Esto se incluirá en loops que sean de una profundidad mayor (for dentro de un for, por ejemplo)
- FINEST: Muestra TODO. **Usar bajo su propio riesgo...**

### Ejemplo de FINEST

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

## GraphFractory

Si bien no es una implementación del patrón que indica, GraphFactory es una clase que engloba todos los grafos que instanciamos. 

Dentro de esta clase, la nomenclatura de los métodos es ${algoritmo}GraphN(). Donde n es un valor numérico incremental.

En caso de querer crear un nuevo grafo, se puede agregar a esta clase y llamarlo en la implementación del algoritmo en cuestión.

## Estructura general del proyecto
Este proyecto utiliza la **consola** como interfaz del usuario. Al correr este mismo, se va a mostrar un menú con las distintas opciones de algoritmos a probar.


Si bien intentamos unificar el estilo de los algoritmos para facilitar su lectura, se detalla más abajo (en cada uno de los subtitulos correspondientes) las particularidades a tener en cuenta para cada uno de ellos, como la interpretación de la salida.

A nivel general, cada algoritmo consta de una clase llamada como el algoritmo en cuestion, y tiene un método púbilco `.execute()`, el cual contiene la lógica inicial del mismo. Esto puede referirse a:
- Selección del grafo a usar
- Definición de parámetros o variables para trabajar
- Instanciamiento de las clases necesarias

A continuación detallamos la información relevante para los tres algoritmos que seleccionamos. Estos son DFS, Kruskal y Dijkstra

--- 
## DFS

### Premisas
Este algoritmo funciona para grafos dirigidos, conexos o no, con peso o no explorando
recursivamente sus sucesores. Como parte del proceso declararemos una lógica trivaluada (Blanco, Gris y Negro).

Al ser un **DFS** este algoritmo podrá presentar un mismo grafo con distintos árboles de recorrido asociados

> Con estas caracteristicas, soportamos la generación de grafos aleatorios 

### Implementación de GrafoTDA

Para esta implementación, usamos GráficoEstático. La misma se basa en una matriz de adyacencia para registrar los vértices entre los nodos.

A continuación dejamos un ejemplo de una de estas matrices, como se vería en consola. 

>Observación: Las aristas se representan con numeros distintos a 0. Si bien esto puede representar un peso, como no es necesario para este algoritmo, **tomamos que un valor != 0 es una arista existente**, indistintamente del valor.

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

### Salida

Mostramos un ejemplo de 10 vértices, identificados por los valores 0-9. En los elementos de respuesta, la posición i en el vector indica el vertice al que hacemos referencia.
Una vez recorridos todos los NODOS podremos conocer los predecesores, y en caso de que no sea conexo nos permite detectar componentes conexos.
```shell
[2021-02-22 00:05:31] [INFO   ] Origen: 0 
[2021-02-22 00:05:31] [INFO   ] 0 -> 1 
[2021-02-22 00:05:31] [INFO   ] 3 -> 2 
[2021-02-22 00:05:31] [INFO   ] 1 -> 3 
[2021-02-22 00:05:31] [INFO   ] 5 -> 4 
[2021-02-22 00:05:31] [INFO   ] 1 -> 5 
[2021-02-22 00:05:31] [INFO   ] 2 -> 6 
[2021-02-22 00:05:31] [INFO   ] 4 -> 7 
[2021-02-22 00:05:31] [INFO   ] 4 -> 8 
[2021-02-22 00:05:31] [INFO   ] 6 -> 9 
[2021-02-22 00:05:31] [INFO   ] Tiempos iniciales de procesamiento de un nodo = [1, 2, 4, 3, 12, 11, 5, 13, 15, 6] 
[2021-02-22 00:05:31] [INFO   ] Tiempos de final de procesamiento de un nodo = [20, 19, 9, 10, 17, 18, 8, 14, 16, 7] 
```
- Predecesores: Se indica el origen y cada una de las aristas por medio del origen y el destino. 
- d: Tiempo final de la iteración. Nos sirve para validar la recursividad. 
- t: Tiempo final de la iteración. Nos sirve para validar la recursividad

--- 

### Kruskal

### Premisas
Este algoritmo funciona para grafos no dirigidos, conexos y ponderados.
Utiliza la técnica de Greedy, ya que busca ordenar de cara a seleccionar la arista de mínimo costo que conecta
dos árboles distintos.

### Implementación de GrafoTDA
Para esta implementación, usamos GráficoEstático como en el caso DFS.

La misma se basa en una matriz de adyacencia para registrar los vértices entre los nodos.

En este caso, si bien importa el peso, *no importa la dirección*. Por este motivo, para aprovechar la implementación que dan en clase, solamente sumamos la arista de un vértice al otro.
Por este motivo, cuando se dan de alta aristas, se hace de un **vertice de menor valor a uno de mayor** (Siendo el valor el int que los representa).


### Registro de nodos unidos

Para llevar un registro de que nodos ya se unieron, vimos que podemos tener conjuntos con únicos elementos (los nodos) al iniciar.
A medida que una arista una dos nodos, estos conjuntos que los representan se unen.
Este comportamiento está modelado en la clase *CONJUNTOS*. Sus funcionalidades son
- Agregar un elemento: Se suma como un conjunto con un único elemento. No se agrega si el elemento ya existe en algun conjunto.
- Conjunto único: Nos dice si tenemos un único conjunto (Los nodos se encuentran unidos)
- Mismo conjunto: Nos dice si dos elementos pertenecen o no al mismo conjunto
- Unir elementos: Une ambos conjuntos y elimina el innecesario para evitar duplicidades


### Salida

La salida por consola nos muestra un mensaje como el siguiente:
```shell
[2021-02-22 00:44:42] [INFO   ] Las aristas que conforman la solución son: [(1:2) - 1, (3:4) - 1, (0:5) - 2, (6:8) - 2, (0:1) - 3, (2:9) - 3, (4:5) - 3, (8:9) - 3, (5:7) - 5] 
```

Podemos ver cada una de las aristas vinculadas con su peso. Todas estas aristas existen en nuestro grafo original, pero deben conformar un grafo de recorrido mínimo, sin ciclos y que cubra todos los nodos.

--- 
### Dijsktra

#### Librería JGraphT

Para esta implementación, utilizamos la librería [JGraphT](!https://jgrapht.org/). Esta ofrece muchas funcionalidades, desde la creación de muchos tipos de grafos y maneras de recorrerlos, hasta la visualización de los mismos por medio de un adapter de Swing. 

> Por cuestiones de tiempo, no podemos implementar la visualización del grafo de una manera elegante, por lo que queda por fuera del scope del proyecto

Features principales de la librería:
- Instanciamiento de grafos
- Agregado de vértices y aristas de distintos tipos (Por ejemplo vértices etiquetados por un String y aristas dirigidas con peso)
- Recorrido BFS gracias a un iterator. Este se usa en el problema de Dijkstra

Para la implementación de Dijkstra, usaremos `SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>`, el cual representa un grafo dirigido y con peso en sus aristas. Nos aseguramos que sea conexo cuando lo definimos.

#### Premisas

De la formulación tenemos que `Sea G= (V,E) grafo dirigido y ponderado con costos no negativos en las aristas`. Asimismo, el grafo debe ser **conexo**.

#### Variaciones

El algoritmo soporta cambiar el nodo desde el cuales se comienza a calcular el peso de los caminos. Ya sea para modificar ese valor, o la estructura en general del grafo que quieran probar (ya hay 2 en código para usar), se puede modificar la clase Dijkstra.

> El vértice de origen `source` debe existir en el grafo que usamos.


#### Salida

La salida se representa de esta manera

```shell
[2021-02-21 23:24:15] [INFO   ] Distancias acumuladas partiendo de v1: {v6=7.0, v7=5.0, v8=2.0, v1=0.0, v2=2.0, v3=5.0, v4=1.0, v5=8.0} 
[2021-02-21 23:24:15] [INFO   ] v3 -> v6 
[2021-02-21 23:24:15] [INFO   ] v2 -> v7 
[2021-02-21 23:24:15] [INFO   ] v4 -> v8 
[2021-02-21 23:24:15] [INFO   ] Origen: v1 
[2021-02-21 23:24:15] [INFO   ] v1 -> v2 
[2021-02-21 23:24:15] [INFO   ] v1 -> v3 
[2021-02-21 23:24:15] [INFO   ] v1 -> v4 
[2021-02-21 23:24:15] [INFO   ] v3 -> v5 
```

En la primera linea, vemos nuestro origen `v1` y los costos para llegar a los otros n vértices. Estos valores son el costo de recorrer todo el camino desde el origen hasta `vn`

Luego, si bien está desordenado, podemos reconstruir el camino a recorrer. Siguiendo el ejemplo, vemos que nuestro origen es `v1`. Luego, vemos que de `v1` debemos ir hacia `v2, v3 y v4`. Los costos para llegar a esos nodos lo tenemos en el mapa anterior.
Posterior a eso, podemos ver que del nodo `v3` se avanza hacia los nodos `v5 y v6`, etc.
