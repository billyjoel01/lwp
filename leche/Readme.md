https://www.lawebdelprogramador.com/foros/Java/1675929-Rutina-Productores-de-leche.html
En este problema el usuario Gabriel Monney, necesita una ayuda con un proyecto. El enunciado dice as�:

Como promedio se realizan en el per�odo un total de 60 entregas de leche. Se sabe que, en la zona, hay 12 productores de leche que realizan entregas
El programa llevar� el control del n�mero de productores que han realizado entregas por medio de una variable, denominada cantProductores. El programa llevar� control de la posici�n en la lista de producci�n para una nueva entrega mediante una variable, denominada nuevoIndice.
Se sabe que se tienen las rutinas siguientes: La que calcula la cantidad total de botellas entregadas por un productor (calcularTotalBotellasProductor) dado el �ndice del id del productor, la que devuelve la cantidad de entregas realizadas por un productor (cantidadEntregasProductor) dado el id del productor, la que busca un productor (buscarProductor) dado el id y retorna el �ndice donde se encuentra ese productor (por ello puede usarlas y no necesita modelarlas).
Tambi�n se tiene una variable global denominada PGBE que almacena el promedio global hist�rico de botellas entregadas por productor en la compa��a que es inicializada por el sistema al ejecutarse y puede ser modificada a trav�s de opciones en el men� del programa.
Para cada uno de los siguientes puntos, realizar el diagrama de flujo de la rutina y la codificaci�n Java.
A. Haga una rutina que recibiendo el id del productor, retorne una lista con la cantidad de botellas que realiz� en cada entrega.

B. Haga una rutina que determine si el promedio de botellas de un productor es mayor al PGBE.

C. Haga una rutina que reciba el arreglo con las botellas entregadas por cada productor (el mismo que se recibe al agregar el productor) y retorne el cambio porcentual lineal entre la primera entrega (E0) y la �ltima entrega (E1) usando la siguiente f�rmula: (E1 � E0)/E0

D. Haga una rutina que devuelva un arreglo con el cambio porcentual de todos los productores.

Mi respuesta

He leido el proyecto y la verdad que es complicado y largo, me ha tomado todo el d�a.
El enunciado es complicado de entender pero si se lee con calma habr�a que trabajar con 3 clases.
- La clase principal que tendr� los m�todos que se describen en el enunciado
- Una clase Productor que ser�a una representaci�n de los productores
- Una clase Entrega que ser�a una representaci�n de cada entrega

Las entregas est�n relacionadas a los productores de 1 a N; en otras palabras un productor puede tener N entregas.
Las entregas tendr�an por propiedades lo siguiente:
- id
- Nombre del cliente (Solo por referencia)
- botellas: Cantidad de botellas de la entrega

Los productorers tendr�an por propiedades lo siguiente:
- id
- nombre: Nombre del productor
- cantidadEntregas: Cantidad de entregas que ha hecho el productor
- entregas: Una colecci�n (Mapa) de las entregas que ha realizado. Cada elemento de esta colecci�n ser�a un objeto de la clase entrega
- totalBotellasEntregadas: total de botellas de leche que ha entregado. Si bien esto se puede calcular iterando la colecci�n de entregas y sumando las botellas, por cuestiones de eficiencia, cada vez que se agrega una nueva entrega se le suman las botellas a esta propiedad

Siguiendo el enunciado el programa principal debe contar con un men� as�
- REGISTRAR_PRODUCTOR
- REGISTRAR_ENTREGA
- MOSTRAR_PRODUCTORES
- MOSTRAR_CANTIDAD_BOTELLAS_X_ENTREGA
- AJUSTAR_PGBE
- MOSTRAR_SI_PROMEDIO_MAYOR_PGBE
- MOSTRAR_CAMBIO_PORCENTUAL_LINEAL
- MOSTRAR_CAMBIO_PORCENTUAL_LINEAL_TODOS
- SALIR

Se debe leer la opci�n y dependiendo de la opci�n que escoja el usuario. Yo hice uso de un switch y he hice varios m�todos para encauzar las acciones