import java.io.*;
import java.net.Socket;
import java.nio.file.Files;;

/**
 * FileClient es el cliente para la transferencia de ficheros para el servidor FileServer.
 * Permite transferir un fichero de cualquier tipo a la ubicación de ejecución del servidor FileServer.
 * Solamente se necesita conocer la ip de destino (ip del servidor), puesto utilizado y el fichero que vas a trasnferir.
 * Nota: El cliente solamente puede enviar ficheros que el sevidor recibira
 */
public class FileClient {
    private final String host;
    private final int port;

    public FileClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void sendFile(File file) {
        //Get data of file
        final String name = file.getName();
        final long size = file.length();

        try {
            Socket socket = new Socket(host, port);

            //Obtenemos los fluhos de entrada y salida del socjet
            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            PrintWriter writer = new PrintWriter(bufferedOutputStream);

            //Se envia el nombre del fichero
            System.out.println("Fichero que se enviara: " + name);
            writer.println(name);
            writer.flush();

            //Se envia el tamaño en bytes del fichero, de esta manera el servidor sabe la cantidad
            // de datos a esperar.
            System.out.println("Tamano del fichero: " + size + " bytes");
            writer.println(size);
            writer.flush();


            //Se obtiene todos los bytes del fichero y se envian al servidor
            byte[] imageBytes = Files.readAllBytes(file.toPath());
            bufferedOutputStream.write(imageBytes);
            bufferedOutputStream.flush();

            //Se cierra todos los fluhos de datos
            bufferedOutputStream.close();
            outputStream.close();
            socket.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
