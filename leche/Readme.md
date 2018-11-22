https://www.lawebdelprogramador.com/foros/Java/1675929-Rutina-Productores-de-leche.html
En este problema el usuario Gabriel Monney, necesita una ayuda con un proyecto. El enunciado dice así:

Como promedio se realizan en el período un total de 60 entregas de leche. Se sabe que, en la zona, hay 12 productores de leche que realizan entregas
El programa llevará el control del número de productores que han realizado entregas por medio de una variable, denominada cantProductores. El programa llevará control de la posición en la lista de producción para una nueva entrega mediante una variable, denominada nuevoIndice.
Se sabe que se tienen las rutinas siguientes: La que calcula la cantidad total de botellas entregadas por un productor (calcularTotalBotellasProductor) dado el índice del id del productor, la que devuelve la cantidad de entregas realizadas por un productor (cantidadEntregasProductor) dado el id del productor, la que busca un productor (buscarProductor) dado el id y retorna el índice donde se encuentra ese productor (por ello puede usarlas y no necesita modelarlas).
También se tiene una variable global denominada PGBE que almacena el promedio global histórico de botellas entregadas por productor en la compañía que es inicializada por el sistema al ejecutarse y puede ser modificada a través de opciones en el menú del programa.
Para cada uno de los siguientes puntos, realizar el diagrama de flujo de la rutina y la codificación Java.
A. Haga una rutina que recibiendo el id del productor, retorne una lista con la cantidad de botellas que realizó en cada entrega.

B. Haga una rutina que determine si el promedio de botellas de un productor es mayor al PGBE.

C. Haga una rutina que reciba el arreglo con las botellas entregadas por cada productor (el mismo que se recibe al agregar el productor) y retorne el cambio porcentual lineal entre la primera entrega (E0) y la última entrega (E1) usando la siguiente fórmula: (E1 – E0)/E0

D. Haga una rutina que devuelva un arreglo con el cambio porcentual de todos los productores.

Mi respuesta

He leido el proyecto y la verdad que es complicado y largo, me ha tomado todo el día.
El enunciado es complicado de entender pero si se lee con calma habría que trabajar con 3 clases.
- La clase principal que tendrá los métodos que se describen en el enunciado
- Una clase Productor que sería una representación de los productores
- Una clase Entrega que sería una representación de cada entrega

Las entregas están relacionadas a los productores de 1 a N; en otras palabras un productor puede tener N entregas.
Las entregas tendrían por propiedades lo siguiente:
- id
- Nombre del cliente (Solo por referencia)
- botellas: Cantidad de botellas de la entrega

Los productorers tendrían por propiedades lo siguiente:
- id
- nombre: Nombre del productor
- cantidadEntregas: Cantidad de entregas que ha hecho el productor
- entregas: Una colección (Mapa) de las entregas que ha realizado. Cada elemento de esta colección sería un objeto de la clase entrega
- totalBotellasEntregadas: total de botellas de leche que ha entregado. Si bien esto se puede calcular iterando la colección de entregas y sumando las botellas, por cuestiones de eficiencia, cada vez que se agrega una nueva entrega se le suman las botellas a esta propiedad

Siguiendo el enunciado el programa principal debe contar con un menú así
- REGISTRAR_PRODUCTOR
- REGISTRAR_ENTREGA
- MOSTRAR_PRODUCTORES
- MOSTRAR_CANTIDAD_BOTELLAS_X_ENTREGA
- AJUSTAR_PGBE
- MOSTRAR_SI_PROMEDIO_MAYOR_PGBE
- MOSTRAR_CAMBIO_PORCENTUAL_LINEAL
- MOSTRAR_CAMBIO_PORCENTUAL_LINEAL_TODOS
- SALIR

Se debe leer la opción y dependiendo de la opción que escoja el usuario. Yo hice uso de un switch y he hice varios métodos para encauzar las acciones