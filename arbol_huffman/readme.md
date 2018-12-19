<h1>Algoritmo de Huffman</h1>
Esta es una respuesta al post <a href="https://www.lawebdelprogramador.com/foros/Java/1678920-Ayuda-Trabajo-final-Algoritme-de-huffman.html">Ayuda Trabajo final Algoritme de huffman</a> de Fabrizzio

<h2>El enunciado:</h2>
Como ultimo trabajo de cierto ramo, se me ha pedido que utilizando un algoritmo de huffman ( sobre compresion) realize las siguientes operaciones, este trabajo es mi ultimo trabajo de un ramo que me ha costado mucho pasar y seria de muchisima ayuda que alguien me pudiese ayudar :/.

El algoritmo debe ser capaz de contar las letras y generar una compresion, mostrando a traves de un OUTPUT el diccionario utilizado para la compresion.

Luego ese mismo output, debe ser puesto en una descompresion y debe generar el texto anterior.

El texto a utilizar es un Lorem Ipsum de la pagina https://es.lipsum.com/

El programa tiene las siguientes consideraciones,

Mi profesor me ha pedido que a mi codigo ( que pondre mas abajo) le agregue las siguientes caracteristicas:

-Ingresar el archivo con el texto desde una ubicacion
-Debe reconocer los espacios y las comas.
-generar una salida con el diccionario y los datos comprimidos como 11010011, etc
-una vez comprimido, debe ser ingresado en un Descompresor el output anterior
-Debe mostrar el texto re-Generado

<h2>La solución</h2>
Hola, vi el enunciado y solo me queda una pregunta y es la cantidad de bits o dígitos debe tener.
Investigando sobre el código de Huffman por aquí y por allá me topé con este video que explica el algorítmo de Huffman
[url]https://youtu.be/8Gf8wutvS1w[/url]

Entonces siguiendo lo los pasos hice el código. Luego me surgió la duda por la cantidad de dígitos que debe llevar cada caracter. Para solucionar eso lo que hice completar con ceros a la izquierda.

Por ejemplo para la frase: <i><b>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi sed. </b></i>; El diccionario que genera es:
<br><img src="https://github.com/billyjoel01/lwp/blob/master/arbol_huffman/diccionario.jpg" alt="diccionario" />

Si no relleno con estos ceros como podría diferencia de una <b>m</b> de una <b>e</b>; entonces lo que hice fue rellenar con ceros a la izquierda.

<h2>El programa</h2>
La clase principal es <a href="https://github.com/billyjoel01/lwp/blob/master/arbol_huffman/ArbolHuffman.java">ArbolHuffman.java</a> En ella he modulado la lógica del programa para que sea mas entendible lo que está pasando.
La clase <a href="https://github.com/billyjoel01/lwp/blob/master/arbol_huffman/Simbolo.java">Simbolo.java</a> representa cada caracter de la frase, además de contar con las propiedades frecuencia y codificación.
La clase <a href="https://github.com/billyjoel01/lwp/blob/master/arbol_huffman/Nodo.java">Nodo.java</a> representa un nodo del arbol binario
<br>
Como uno de los requerimientos es cargar el contenido de un archivo y codificarlo, en caso de no tener el contenido del archivo entonces queda la asignación de la variable frase que al final será codificada.
