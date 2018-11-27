https://www.lawebdelprogramador.com/foros/Java/1676196-ayuda-con-un-ejercicio.html#last

En esta ocación se trata de todo un proyecto (por lo extenso)
Se trata de operaciones con base de datos

<h1>El enunciado</h1><br>
1. Crear Base Datos, llamada Taller en JDB (Derby).
2. Crear tabla Vendedor dentro de base de datos Taller
Tabla Vendedor:
Campos: rut varchar(10) not nul primary key
nombre varchar(20) not null,
apellidoPaterno varchar(25) not null,
apellidoMaterno varchar(35) null,
sueldoBase int not null
Inserte a lo menos 3 vendedores.

3. Debe crear la clase Vendedor, con los atributos:
-rut: String,
-nombre : String,
-apepat : String,
-apemat : String,
-sueldo : int.

Debe consider incorporar a la clase todos los atributos que le permitan
conectarse a la base de datos Taller.

En la clase Vendedor debe implementar los metodos:
a. public void leerDatos(): que debe leer los datos del vendedor por teclado,
además debe validar que todos los datos not null hayan sido ingresados..
.
b. private int insertarDatos(rut,nombre, apellidopaterno, apellidomaterno
sueldobase): que debe permitir insertar los datos de un vendedor en la
base de datos taller, debe retornar el numero de vendeores insertados.

_____________________________________________________________________
c. private void buscarDatos(String rut): que debe permitir buscar en la base
de datos y deplegarlos en pantalla.

d. private int eliminarDatos(rut,): que debe permitir eliminar los del
vendedor cuyo rut coincida con el dado como parámetro.

e. public void buscarVendedor(): debe pedir que el usuario ingrese un rut a
buscar, luego debe validar que el rut del Vendedor haya sido ingresados, e
invocar la método buscarDatos(rut), y si existen datos mostrar los datos en
detalle desplegándolos en pantalla
En caso contario debe indicar que no existen datos asociados al rut
ingresado.

f. public void agregarVendedor(): debe pedir que el usuario ingrese primero
un rut luego de validar que el rut no exista en la base de datos, si no existe
debe pedir leer el resto de los datos invocando al método leerDatos() y luego
insertarlos en la tabla vendedor invocando al método
insertarDatos(rut,nombre,apepat,apemat,sueldo.

g. pubic void eliminarVendedor() , primero debe pedir que el usuario ingrese el
rut del vendedor a liminar, luego debe validar el rut del Vendedor haya sido
ingresados.
Después debe buscar los datos del Vendedor por rut y desplegarlos en
pantalla y luego preguntar está seguro de querer borrar los datos del rut
desplegado, si la respuesta es afirmativa debe borrar los datos de la tabla
debe indicar el resultado de esta acción al usuario, usando el método
eliminarDatos(rut).
En caso que el rut no exista en la base de datos debe desplegar un mensaje
indicándolo.

h. pubic void listarVendedores(), debe listar todos los vendedores existentes en
una lista tabalulada.



4. Crear Aplicación Java de consola llamada MantenedorVendedorApp
Cree una aplicación en Java en modo consola y conceptos de (Factorización
y reutilización de Código y Orientación al Objeto);
La clase principal debe desplegar un menú con las siguientes opciones:
? Agregar Vendedor
? Buscar Vendedor x Rut,
? Eliminar Vendedor
? Listar Todos los Vendedores
Y permitir a través de un objetoVendedor del Tipo Vendedor, realizar las operaciones
seleccionadas mediante menú.


<h1>La solución</h1><br>
El proyecto está hecho en Netbeans. 
Se cargó el driver de Java DB.
Para que esto funcione primero debes crear la base de datos. Yo le puse Taller, pero le puedes poner nombre que quieras solo que en caso de poner otro nombre entonces modificar la variable CATALOG que se encuentra en la clase utils.MyConnection
Cuando crees la base de datos recuerda crearla con las siguientes credenciales
usuario: taller_user
password: taller_password
En caso que crees la base de datos con otras credenciales puedes modificarlas en la clase utils.MyConnection en las variables USER y PASSWORD
Para crear la tabla Vendedor puedes hacerlo siguiendo las indicaciones del enunciado; También puedes ejecutar la clase utils.CreateTableVendedor;
Otra alternativa sería ejecutar el comando a nivel de sql:

CREATE TABLE vendedor (<br>
id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),<br>
created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,<br>
rut varchar(10) NOT NULL,<br>
nombre varchar(20) NOT NULL,<br>
apellidoPaterno varchar(25) NOT NULL,<br>
apellidoMaterno varchar(35) DEFAULT NULL,<br>
sueldoBase int NOT NULL,<br>
PRIMARY KEY (rut)<br>
);<br>

La clase principal del proyecto es MantenedorVendedorApp
