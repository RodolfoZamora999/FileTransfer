import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * FileServer es el servidor para la trasnferencia de archivos, por defecto los ficheros se almacenaran en la misma
 * ubicación de ejecución que el servidor.
 * Solamente se debe colocar un número de puerto valido para que se inicie.
 * Nota: El cliente solamente puede enviar ficheros que el sevidor recibira
 */
public class FileServer {
    private final int port;

    public FileServer(int port) {
        this.port = port;
        System.out.println("FileServer 0.1");
    }

    public void startServerSocket() {
       try {
           System.out.println("FileServer esta iniciando...");
           System.out.println("Escuchando puerto: " + port);

           ServerSocket serverSocket = new ServerSocket(port);
           Socket socket = serverSocket.accept();

           //Obtener los flujos de entrada y salida del socket
           InputStream inputStream = socket.getInputStream();
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

           //Obtenemos el nombre del fichero
           String name = bufferedReader.readLine();
           System.out.println("Nombre del fichero que se recibira: " + name);
           //Obtenemos el tamaño en bytes del fichero
           String size = bufferedReader.readLine();
           System.out.println("Tamano del fichero: " + size + " bytes");

           //Obtenemos un array de todos los bytes del fichero
           byte[] bytesFile = inputStream.readAllBytes();

           //Método para la creación del fichero
           createFile(name, bytesFile);

           //Cerramos todos los flujos de datos
           bufferedReader.close();
           inputStream.close();
           socket.close();
           System.out.println("El servidor ha finalizado");
       }
       catch (Exception exception) {
           exception.printStackTrace();
       }
    }


    private void createFile(final String fileName, final byte[] bytesFile) {
        String path = System.getProperty("user.dir");
        File file = new File(path, fileName);

        if(!file.exists()) {
            try {
                boolean fileState = file.createNewFile();
                if (fileState) {
                    System.out.println("Fichero creado con exito");

                    //Obtenemos los flujos de entrada y salida del fichero
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

                    //Escribimos el array de bytes en el fichero creado
                    bufferedOutputStream.write(bytesFile);

                    //Cerramos los flujos de datos
                    bufferedOutputStream.close();
                    fileOutputStream.close();

                    System.out.println("¡Escritura del fichero exitosa!");
                }
                else
                    System.out.println("¡Error al crear el archivo!");

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    //Punto de entrada del programa
    public static void main(String... args) throws IOException {
        System.out.print("Numero de puerto: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int port = Integer.parseInt(br.readLine());

        FileServer fileServer = new FileServer(port);
        fileServer.startServerSocket();
        br.close();
    }
}
