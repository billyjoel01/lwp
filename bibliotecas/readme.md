<h1>Proyecto Biblioteca</h1>

<p>Este proyecto corresponde a un <a href="https://www.lawebdelprogramador.com/foros/Java/1683412-Ayuda-con-este-proyecto.html">post</a> por el usuario <b>Abel Priego Borrego</b></p>

<h2>El enunciado</h2>
<p>
Aplicación compuesta por las siguientes clases:

Libro con los atributos: título, autores (array de String), editorial, año publicación, páginas, número de ejemplares disponibles, ISBN. Todos los atributos son privados y tendrá métodos getter, setter, constructores, y un método que genere un libro aleatorio.

Lector, con los atributos: nombre, dni, número de libros prestados. Todos los atributos son privados y tendrá métodos getter, setter, constructores, y un método que genere un lector aleatorio (el número de libros prestados será 0).

Préstamo. Con los atributos, libro, lector y número de préstamo.

Biblioteca compuesta por un array de libros, un array de lectores y un array de préstamos. El array de libros se inicializará con 300 libros, el de lectores con 50, ambos de forma aleatoria en el constructor. Hay que tener en cuenta que no puede haber ISBN repetidos, ni DNI repetidos. El array de préstamos se inicializará con 150 préstamos todos ellos con número de préstamo igual a 0.

Programa principal con un menú con las siguientes opciones: realizar préstamo, devolver libro, relación de préstamos (número de préstamo, título del libro y nombre del lector).

Un libro sólo se puede prestar si el número de ejemplares disponibles es mayor que 0. Cada vez que se presta, se decrementa este valor en el libro correspondiente de la biblioteca. Un lector sólo puede tener 3 libros prestados. Y el préstamo se coloca en la primera posición que tenga número de préstamo igual a 0.
Al devolver el libro, se incrementa el número de ejemplares disponibles en el array de libros, se decrementa el número de libros prestados en el array de lectores, y se pone a 0 el número de préstamo.
</p>

<h2>La solución</h2>
<h3>Palabras aleatorias</h3>
<p>
Antes de empezar con las soluciones analizamos que se nos piden crear nombres, titulos, autores; en fin palabras aleatorias.
Para darle una solución a esto me fui a <a href="https://es.lipsum.com/">Lorem ipsum generato</a> y generé 5 parrafos, elminé las
comas(,), los puntos(.) y puntos y comas (;). Luego inserté cada palabra en un Array para que luego puedan ser accedidas a traves
de su index con números aleatorios
</p>

<h3>Beans</h3>
<p>Como pide el enunciado se crean 3 beans</p>
<ul><li>Libro</li><li>Lector</li><li>Prestamo</li></ul>

<h3>MyUtils</h3>
<p>Los métodos de generación de Lectores y Libros exigen generación de palabras aleatorias, esta clase MyUtils se encarga de eso. 
Hace uso de la clase <b>PalabrasAleatorias</b> para generar nombres, titulos y autores</p>

<h3>Biblioteca (main)</h3>
<p>Esta es la clase que contiene el método main. En el constructor de la clase se inicializan los libros, lectores y se hacen los prestamos</p>
<p>Contiene un menú con las opciones salir, realizar prestamo, devolver libro y desplegar la relación de prestamos de libros y lectores</p>


