# GnomesBrastlewark
La aplicación es muy sencilla, cuenta con una pantalla para visualizar el listado de gnomos en un grid, con su imagen y nombre. Si se pulsa en una celda, se accede al detalle con toda la información del gnomo.

Se ha desarrollado con arquitectura MVVM y se han añadido test unitarios para comprobar que se mapea bien la información y se calculan los parámetros para mostrar correctamente el sexo y color de pelo de los gnomos. Y test de integración con pruebas automatizadas con espresso para comprobar el funcionamiento básico de la app, descarga, filtrado y vista detalle.

### Librerías
A parte de las librerías propias, de Android y testing se han añadido:

| Plugin | README |
| ------ | ------ |
| Retrofit | Para poder consumir el json de forma eficiente y obtener los datos.|
| Glide | Para la descarga y gestión de caché de las imágenes.|

### Curiosidades
* Se ha hecho un método para determinar el sexo de los gnomos en función a las vocales de sus nombres.
> La 'A' y la 'I' suman 2 puntos y 1 respectivamente para determinar que sea mujer
> La 'O' y la 'U' suman 2 puntos y 1 respectivamente para determinar que sea varón
> Y en caso de empate se considera hermafrodita.

* Se ha realizado el filtrado de los gnomos en función del nombre.
