db4o
====
trabajo practico final
Bases de Datos Orientadas a Objetos


Implementar las siguientes clases en Java

Juez: propiedades: Nombre, matricula profesional, trayectoria (texto)
Juzgado: propiedades: Fuero (civil, comercial, laboral, penal), Juez (instancia), domicilio, localidad
Persona: nombre, apellido, dni, sexo, fecha nacimiento
Causa: propiedades: Juzgado, Nro. Expediente, imputados (conjunto de Persona),  testigos (conjunto de Persona), sentencia (texto, nulo si no tiene todavia)


Crear una aplicación Java que genere multiples instancias de cada una, y utilizando el entonrno eclipse, les dé persistencia en el motor de bases de datos Orientado a objetos:  DB4O


Hacer las siguientes consultas con QueryByExample
Mostrar las causas con sentencia que tengan mas de 2 imputados , se deberá mostrar los datos de la causa y los datos de sus imputados
Mostrar los juzgados del fuero civil que tengan al menos una causa con sentencia y una causa sin sentencia

Resolver también las dos consultas utilizando Native Queries y SODA (una con cada método). 

Pautas: 
Se podrá conformar un grupo de hasta 2 alumnos para realizar el trabajo
Se deberá entregar un informe escrito y en formato PDF con el trabajo y la presentación
Se deberá mostrar el trabajo en máquina a la cátedra
Fecha final de presentacion : Lunes 28 de junio 
