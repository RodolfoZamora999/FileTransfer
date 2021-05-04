# FileTransfer
## ¿Qué es FileTransfer?
FileTransfer es un pequeño programa escrito en Java (concretamente java 11) que permite la transferencia de ficheros fácilmente a través de la red.

El objetivo principal de FileTransfer es la posibilidad de poder transferir ficheros de manera muy sencilla a través de la red sin muchas complicaciones, solo basta con especificar la dirección del host y el fichero que se desea transferir.

Para que ocurra la “magia” se necesitan dos piezas claves, el cliente (que es el host que especificara el fichero a transferir) y el servidor (el host que está a la escucha y espera del fichero que se le transferirá) 

### El caso de uso principal a este proyecto es el siguiente: 
La transferencia de ficheros entre dos computadoras dentro de la misma red local, de esta manera dejando de lado la transferencia tediosa a través de USB, configuración de un servidor FTP o tener que subir el fichero a la nube  para su posterior descarga en la computadora de destino. El poder transferir el fichero a través de la misma red local brinda la posibilidad de usar la velocidad máxima posible dentro de tu red LAN, la cual no se ve limitada por el ancho de banda de tu proveedor de Internet (el cual no tiene poder aquí).    

</br>

![make coomand line](documentation/presentation.png)

</br>

## Compilación del proyecto
En la carpeta del proyecto se encuentra un archivo Makefile, para poderlo ejecutar se deberá tener instalado previamente esta herramienta (Makefile está disponible tanto para Linux como para windows).

Solo basta con abrir la terminal en la raíz del proyecto, escribir la palabra “make” y dar enter para que se inicie la compilación y empaquetado del proyecto, dando como resultado un archivo jar complemente funcional. O bien, puedes descargar el archivo compilado y empaquetado del siguiente link: O bien, puedes descargar el archivo compilado y empaquetado del siguiente link:  [ Clic aquí para descargar FileTransfer.jar ](documentation/FileTransfer.jar)


</br>

![make coomand line](documentation/make.PNG)

</br>

## Uso de FileTransfer
Tanto el servidor como el cliente se encuentran “empaquetados” dentro del mismo archivo jar, esto con la finalidad de tener todo integrado en un solo archivo. 

Para iniciar el servidor o cliente solo basta con especificarlo de la siguiente manera desde la terminal:

### Inicio del servidor

```bash
java -jar FileTransfer.jar --server [port]
```

>“port” como su nombre lo indica, hace referencia al número de puerto al que estará escuchando el programa, si no se especifica un puerto se utilizará el *1921* como predeterminado.

</br>

### Inicio del cliente

```bash
java -jar FileTransfer.jar --client host@port@file_path
```

>“host” hace referencia a la dirección del host servidor, por ejemplo: 192.180.168.132, “port” el número de puerto que el servidor está escuchando y “file_path” hace referencia a la dirección del fichero que se quiera transferir, ejemplo: c://users/rodol/desktop/video.mp4

</br>


### Ejemplo práctico

Tenemos la necesidad de hacer la transferencia de un archivo de video llamado “video.mp4” desde mi computadora de escritorio hasta mi laptop, todo esto a través de la red LAN/WLAN.

#### Requisitos:
* Tener FileTransfer.jar en las dos máquinas, en este caso la computadora de escritorio y la laptop.

* Conocer la dirección del host de destino, la cual en mi caso será la laptop, la cual tiene la dirección IPv4 privada 192.168.100.183

* Conocer el nombre del fichero que se transferirá,  en este caso es c://users/user/desktop/video.mp4 (si el fichero se encuentra en el mismo lugar que FileTransfer.jar basta con poner solo el nombre, quedando por ejemplo: video.mp4)

**Paso 1:** Iniciar el servidor FileTransfer en el host de destino, se debe proporcionar un número de puerto de escucha válido, por defecto se utiliza el número 1921. Una vez iniciado correctamente se tendrá algo como lo siguiente:

![make coomand line](documentation/server_example.PNG)

**Paso 2:** Iniciar el cliente FileTransfer en el host cliente, en el cual se encuentra el archivo que se transferirá al host de destino. Se puede pasar el siguiente conjunto de parámetros para una mayor comodidad como se muestra a continuación.

![make coomand line](documentation/client_example.PNG)

Una vez finalizada la transferencia del fichero tanto el servidor como el cliente se detendrán, poniendo fin a la interacción entre ambos. Para transferir cualquier otro archivo se deberá repetir el mismo proceso.

**Nota:** Recuerda habilitar el puerto de escucha en el firewall del host servidor, en caso de no hacerlo podría generar problemas al querer usar dicho puerto por parte de FileTransfer.


</br>
</br>

### <span style="color: red;">Aviso importante</span>

Este proyecto solamente está desarrollado como un mero hobbit y hasta cierto punto con fines educativos, no se recomienda su uso con host fuera de la red local debido a la falta de seguridad en la transferencia de datos.
