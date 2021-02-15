# Bitácora de tareas
## Proyecto final de *Modelado y Programación 2021-1*
### Ricardo A. Luévano B.
3er. Semestre

-----------------------------------------------------

### Índice
* [Problemática](#problemática)
* [¿Cómo lo uso?](#cómo-lo-uso)
* [¿Cómo lo ejecuto?](#cómo-lo-ejecuto)
* [Patrones de diseño](#patrones-de-diseño)
* [Diagramas](#diagramas)

-----------------------------------------------------

### Problemática
El proyecto trata de una bitácora de tareas ordenadas por el tiempo restante para que éstas caduquen. El proyecto se presenta como un _mock-up_ de una app a implementar más adelante, con más funcionalidades.

| <img src="/imagenes/muestra.png" width="50%"> |
|:--:|
| *App sin/con tareas pendientes* |    

**[Regresar al índice](#índice)**

-----------------------------------------------------

### ¿Cómo lo uso?

La primera vez, se muestra la siguiente ventana:

| <img src="/imagenes/tutorial01-principal.png" width="50%"> |
|:--:|
| *Menú al inicio* |


Desde aquí se puede editar las **Configuraciones** o crear una **Nueva tarea**. Para crear una tarea nueva, se necesita agregar *categorias*, de lo contrario no será posible crear una nueva tarea:

| <img src="/imagenes/tutorial02-nuevaTareaSin.png" width="80%"> |
|:--:|
| *Nueva tarea sin categorias* |


Para agregar *categorias*, se dirige al icono del engranaje (Configuraciones) en el menú principal y después a **Editar categorias**, aquí se agregan a lo más seis categorias (en esta versión):

| <img src="/imagenes/tutorial03-configuraciones.png" width="80%"> |
|:--:|
| *Configuraciones* |


Al tener al menos una categoría, se puede crear una tarea nueva:

| <img src="/imagenes/tutorial04-nuevaTareaCon.png" width="50%"> |
|:--:|
| *Nueva tarea con categorias* |


Se puede ajustar el avance en cada tarea:

| <img src="/imagenes/tutorial05-elegirAvance.png" width="80%"> |
|:--:|
| *Ajustar el avance* |


Se puede editar la tarea ya creada:

| <img src="/imagenes/tutorial06-editarTareaDatos.png" width="80%"> |
|:--:|
| *Editar tarea* |


Se puede terminar la tarea ya sea modificando el avance o seleccionando el checkbox:

| <img src="/imagenes/tutorial07-concluida.png" width="50%"> |
|:--:|
| *Concluir tarea* |


Se puede eliminar una tarea en particular:

| ![](/imagenes/tutorial08-eliminarUna.png) |
|:--:|
| *Eliminar tarea* |


O todas las tareas de un tipo:

| ![](/imagenes/tutorial09-eliminarMas.png) |
|:--:|
| *Eliminar tareas por tipo* |

**[Regresar al índice](#índice)**

-----------------------------------------------------

### ¿Cómo lo ejecuto?

El repositorio cuenta con dos versiones del proyecto, una para compilar/correr con javac/java y otra usando **Maven**. La diferencia es que con javac/java hay que descargar **JavaFX (15.0.1)** y los comandos son un poco largos, mientras que con Maven no hay que descargar nada (éste descarga las dependencias necesarias antes de compilar) y los comandos son sencillos.

#### Usando javac/java (SinMaven)

En esta carpeta ya se incluye el jar de **GSON/JSON**, falta descargar JavaFX **SDK**  (15.0.1)desde [este enlace](https://gluonhq.com/products/javafx/).
Una vez descargado, se descomprime el zip y la carpeta (no el zip) se agrega a esta misma carpeta (la misma que contiene a **/src**) y, desde la carpeta que contiene a las carpetas **/gson-2.6.2**, **/javafx-sdk-15.0.1**, **/recursos** y **/src**, se ejecutan los siguientes comandos:

```
export JAVAFX=javafx-sdk-15.0.1/lib
```
```
javac -cp "gson-2.6.2/lib/gson-2.6.2.jar" --module-path $JAVAFX --add-modules javafx.controls src/*.java
```
```
java -cp ./src:./gson-2.6.2/lib/gson-2.6.2.jar --module-path $JAVAFX --add-modules javafx.controls Main
```

En el tercer comando se usa **:.** en Unix (dos puntos, punto), pero en Windows se debe usar **;.** (punto y coma, punto).

#### Usando Maven (ConMaven)

Es muy sencillo, se ubica en la misma carpeta donde está el **pom.xml** y escriben las siguientes instrucciones
```
mvn install
```
```
mvn -q exec:java -Dexec.mainClass=Main
```

El único inconveniente de esta versión es que la primera vez que se ejecuta, tarda un poco en descargar las dependencias, pero esto sólo ocurre la primera vez.

Como dato extra, compilé y ejecuté lo anterior con Java 12.0.2.

**[Regresar al índice](#índice)**

-----------------------------------------------------

### Patrones de diseño

Para la implementación se utilizaron los siguientes patrones:

* **MVC** para dividir la estructura en tres partes:
  * La **Vista** se encarga de crear los objetos en pantalla que representan los datos del **Modelo**,
  * el **Controlador**, constituido por varias clases, se encarga de la lógica del programa,
  * el **Modelo** se encarga de leer los datos, convertirlos en objetos, almacenarlos en estructuras y escribirlos en archivos al final del proceso

| ![](/imagenes/diagrama-clases-01-mvc.png) |
|:--:|
| *Clic en imagen para agrandar* |


* **Observer**. El paso del tiempo es un factor clave: cada tanto, el **Modelo** se actualiza, lo que implica un cambio en la base. Con **Observer**, el **Modelo** le puede avisar al **Controlador** (y éste a la Vista) que ocurrieron cambios y se deben actualizar los objetos en pantalla.

| <img src="/imagenes/diagrama-clases-02-observer.png" width="50%"> |
|:--:|
| *Clic en imagen para agrandar* |

* **Proxy**. Es importante mantener la seguridad del **Modelo**. Para evitar que la **Vista** tenga acceso a los archivos en la base (durante la creación de los objetos gráficos) y estos puedan ser modificados directamente por el usuario, **Proxy** cumple el rol de intermediario entre el **Controlador** y el **Modelo**, pues este se encarga de crear objetos temporales (o copias del objeto original) con las cuales se crean los objetos gráficos con los que interactúa el usuario.

| <img src="/imagenes/diagrama-clases-03-proxy.png" width="80%"> |
|:--:|
| *Clic en imagen para agrandar* |


* **Singleton**. En la **Vista**, se usan varios patrones. Uno de estos es una pila **Singleton** la cual contiene todo elemento (uno encima de otro) que se ve en pantalla. Esta se encarga de eliminar los elementos anteriores y contener nuevos elementos cada que se soliciten cambios desde el **Controlador** (vía el **Modelo**). No puede haber más de dos instancias de estas pues todos los elementos gráficos se guardan en la pila y sólo un objeto principal (una raíz) puede presentarse en el escenario (*Stage*) de *JavaFX*.


| <img src="/imagenes/diagrama-clases-04-singleton.png" width="20%"> |
|:--:|
| *Clic en imagen para agrandar* |
| |
| ![](/imagenes/diagrama-clases-04-singleton2.png) |
| *Representación de los elementos en la pila.* |


* **Factory**. Tenemos dos tipos de objetos que se crean constantemente y permiten al usuario interactuar con la app. Estos son las *SubVentanas* (ventanas dentro de la ventana principal) con opciones para hacer modificaciones, y los _Eventos_ (_EventHandlers_ en botones y otros objetos de interacción), que establecen lo que debe pasar dada la elección del usuario. Para ambas familias de objetos usamos **Factory**.

| ![](/imagenes/diagrama-clases-05-factory.png) |
|:--:|
| *Clic en imagen para agrandar* |


* **Strategy**. Tenemos tres tipos de tareas, cada tipo tiene una apariencia distinta. Se usa **Strategy** para que, en tiempo de ejecución, se elija la apariencia de cada tipo.

| ![](/imagenes/diagrama-clases-06-strategyBuilder.png) |
|:--:|
| *Clic en imagen para agrandar* |


* **Builder**. Cada tarea se presenta de manera gráfica como un bloque conformado por secciones. Como estos objetos se construyen con los mismos componente pero con algunas variaciones, se utilizó Builder.

**[Regresar al índice](#índice)**

-----------------------------------------------------

### Diagramas

| ![](/imagenes/diagrama-clases.png) |
|:--:|
| *Diagrama de clases (resumido)* |

| <img src="/imagenes/diagrama-casos-de-uso.png" width="50%"> |
|:--:|
| *Diagrama de casos de uso* |

| <img src="/imagenes/diagrama-actividades.png" width="80%"> |
|:--:|
| *Diagrama de actividades* |

| ![](/imagenes/diagrama-secuencia-nueva-tarea.png) |
|:--:|
| *Diagrama de secuencia: Nueva tarea* |

| ![](/imagenes/diagrama-secuencia-editar-tarea.png) |
|:--:|
| *Diagrama de secuencia: Editar tarea* |

| ![](/imagenes/diagrama-secuencia-eliminar-tarea.png) |
|:--:|
| *Diagrama de secuencia: Eliminar tarea* |

| ![](/imagenes/diagrama-secuencia-ajustar-avance.png) |
|:--:|
| *Diagrama de secuencia: Ajustar avance de tarea* |

| ![](/imagenes/diagrama-secuencia-concluir.png) |
|:--:|
| *Diagrama de secuencia: Concluir tarea* |

| ![](/imagenes/diagrama-secuencia-configurar.png) |
|:--:|
| *Diagrama de secuencia: Configuraciones* |

| ![](/imagenes/diagrama-secuencia-configurar-categorias.png) |
|:--:|
| *Diagrama de secuencia: Editar categorías* |

| ![](/imagenes/diagrama-secuencia-actualizar.png) |
|:--:|
| *Diagrama de secuencia: Actualizar tareas* |

**[Regresar al índice](#índice)**
