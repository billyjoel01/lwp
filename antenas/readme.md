<h1>Antenas</h1>
Este proyecto lo pide Juan en el post <a href="https://www.lawebdelprogramador.com/foros/Java/1678383-Ayuda-con-arreglos.html">Ayuda-con-arreglos</a>
Se trata de un proyecto de matrices y áreas.

Para ver el enunciado del proyecto ver el <a href="https://github.com/billyjoel01/lwp/blob/master/antenas/PF%5B553%5D.pdf">PDF</a>

<h2>Paso a paso</h2>
<ul>
 <li>Se pide el número de filas y columnas, además del largo o diametro de accióin de de cada antena.</li>
 <li>Se pide el número de antenas colocadas y sus coordenadas.</li>
 <li>Se inicializa la matriz y se colocan las antenas existentes dentro de ella.</li>
 <li>Se inicia un bluce que evalúa cual antena se debe colocar.
  <ul>
    <li>Se obtiene una lista (arreglo) de antenas sugeridas, que tienen cierta cantidad de puntos de cobertura.</li>
    <li>Se determina cual de las antenas sugeridas tiene el mayor número de puntos de cobertura.</li>
    <li>Se setea la antena con mayor número de puntos de cobertura dentro de la matriz, además de su área de cobertura.</li>
    <li>El proceso se repite hasta que no queden puntos sin cobertura dentro de la matriz.</li>
  </ul>
 </li>
 <li>Se imprimen los resultados.</li>
 </ul>
