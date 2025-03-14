# Proyecto 1 Programación I Verano 2021

#### Versión: 1.0

# Análisis
Sokoban es un videojuego clásico de puzzles, creado en 1981 por el japonés Hiroyuki Imabayashi.
Sokoban en japonés significa "encargado de almacén", lo cual va acorde a la dinámica del juego que consiste en colocar
cajas en puntos designados dentro del almacén, el cual esta representado por un tablero compuesto de cuadros, los cuales
a su vez pueden representar piso o paredes.

Para poder oredenar las cajas el jugador puede moverse diagonal u horizontalmente sobre posiciones de piso que se
estén vacías. El jugador puede desplazar mientras no hayan obstáculos de por medio (pared u otra  caja), ya que estos
le impiden desplazar la caja en esa dirección. El jugador para desplazar una caja únicamente puede empujar la caja por
espacios vacíos, es decir no puede jalarla.

# Guía de usuario

Al inicializar el programa se deben ingresar en ese orden:
1. Número de filas
2. Número de columnas
3. Número de cajas

Seguidamente se debe ingresar un tablero con el estado actual del juego, respetando la cantidad de filas, columnas y
cajas ingresadas previamente, para representar cada elemento del tablero se utilizan los siguientes símbolos:

| Símbolo |                        Significado                         |
|---------|:----------------------------------------------------------:|
| #       |                           Pared.                           |
| X       |   Posición donde se debe colocar una caja, con un caja.    |
| *       |       Caja sobre otra posición que no sea la final.        |
| O       | Posición donde se debe colocar una caja, pero sin la caja. |
| @       |            El encargado del almacén / jugador.             |
| .       |                        Piso vacío.                         |

Luego de ingresar los datos el jugador recibirá la siguiente información:
* Si el jugador ganó el juego.
* Las posiciones actuales de las cajas.
* Las posiciones de las cajas bloqueadas.
* Los movimientos válidos que el jugador puede realizar el próximo turno


### Ejemplo de entrada que podría ingresar el usuario
~~~
5 5 2

#####
#*.O#
#X.O#
#..@#
#####
~~~
###Ejemplo de salida
~~~
Victoria: No
 Cajas: r01c01 r02c01* 
 Cajas Bloqueadas: r01c01
Movimientos válidos: N:r02c03 E:- S:- O:r03c02
~~~
En la información de salida se especifican los detos de:

1. Condición de victoria (Victoria): ocurre cuando todas las cajas están sobre los puntos designados.
2. Posiciones actuales de las cajas (Cajas): indica las coordenadas en las que se encuentran todas las cajas.
   La "r" representa la fila (row) y la "c" la columna. Las coordenadas con "*" al final indican que esa caja ya se
   encuentra en el punto designado.
3. Posiciones actuales de las cajas bloqueadas (Cajas Bloqueadas): indica las coordenas de las cajas que no pueden
   desplazarse más y que no se encuentran en un punto designado. La información de las coordenas de las cajas bloqueadas
   debe seguir el mismo formato que el punto anterior.
4. Movimientos válidos (Movimientos válidos): indican las coordenas de las posiciones a las cuales puede desplazarse el
   jugador, y viene indicado con los puntos cardinales: norte (N), este(E), oeste(O) y sur(S). En caso de que haya una
   pared o una posoción bloqueda vendrá un "-".

## Cómo crear el archivo .jar
Para poder generar un archivo .jar ejecutable, se debe abrir en el IDE TnteliJ el archivo build.gradle y colocar al 
final del archivo los siguientes comandos:

~~~
jar{
    manifest{
        attributes "Main-Class": "cr.ac.ucr.Tarea1"
}

    from {
        configurations.runtimeClasspath.collect { File-> it.isDirectory() ? it : zipTree(it) }
    }
}      
~~~
Seguidamente depués de esto dentro de la carpeta "build" se generará la carpeta "libs" la cual contiene el archivo 
ejecutable .jar.

## Cómo ejecutar el archivo .jar junto con los casos de prueba
Dentro de los archivos del programa viene incluida la carpeta "tests", la cual contiene 9 ejemplos de entrada y salida 
que se utilizan para comprobar que el programa funcione correctamente.
Para poder ejecutar los casos de prueba se debe abrir la linea de comandos y dirigirse al directorio en donde se encuentra
la caepeta "libs" , una vez ahí se deben introducir los siguientes comandos:

~~~
java -jar TareaProgramada1-1.0.jat < ../../tests/input.txt | diff - ../../tests/output000.txt && echo Test 000: OK
~~~
Se está solicitando que en caso de que la entrada de prueba de corresponda a la salida de prueba se imprima en la linea 
de comandos el texto, y se obtendría el siguiente resultado:
~~~
Test 000: OK
~~~
# Créditos

Nombre: David Gonzalez Vilanueva

Contacto: david.gonzalezvillanueva@ucr.ac.cr

Carné: C13388

---

Nombre: Anthony Navarro Brenes

Contacto: edgar.navarrobrenes@ucr.ac.cr

Carné: C15479
