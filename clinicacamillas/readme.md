<h1>El problema</h1>
El usuario Ricardo el <a href="https://www.lawebdelprogramador.com/foros/Java/1696349-Ayuda-ejercicio-java.html#i1696349">post</a> escribe lo siguiente
<br>
<code>
Se requiere un programa que permita gestionar la información de camillas en una clínica. El programa debe permitir efectuar las siguientes opciones:
1. Inicializar el estado de todas las camillas en estado disponible
2. Consultar el estado de una camilla en particular y si la camilla está ocupada, retornar el nombre del paciente que la ocupa.
3. Registrar los datos de un paciente (nombre y cédula) y asociarlos a una camilla en particular
4. Dar salida a un paciente que se encuentra en una camilla específica
</code>
<br><br>
<h1>La solución</h1>
<p>
Se crean 2 beans
<ul>
<li><b>Paciente</b> que tiene los datos del paciente (nombre y cedula)</li>
<li><b>Camilla</b> que representa a una camilla (id, paciente, disponible)</li>
</ul>
</p>

<h2>La clase principal</h2>
<p>
La clase principal de esta solución es <b><a href="https://github.com/billyjoel01/lwp/blob/master/clinicacamillas/ClinicaCamillas.java">ClinicaCamilla</a></b>. En esta clase se hace uso de <code>java.util.Map</code> para 
identificar las camillas y utilizar las propiedades de éstas
</p>

