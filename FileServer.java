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
    private final int sizeBuffer = (int) 10e6; //Buffer size 10MB
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

           InputStream inputStream = socket.getInputStream();
           BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, sizeBuffer);
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

           String name = bufferedReader.readLine(); //File name
           System.out.println("Nombre del fichero que se recibira: " + name);
           String size = bufferedReader.readLine(); //File size
           System.out.println("Tamano del fichero: " + size + " bytes");

           //Create and get file outputStream
           BufferedOutputStream bufferFile = createFile(name);

           //Write data to File
           if (bufferFile != null) {
               int data;
               while (true) {
                   data = bufferedInputStream.read();
                   //End data writing
                   if (data == -1) {
                       bufferFile.flush();
                       break;
                   }
                   else
                       bufferFile.write(data);
               }
           }

           //Close all streams
           bufferedInputStream.close();
           bufferedReader.close();
           inputStream.close();
           socket.close();
           serverSocket.close();
           System.out.println("El servidor ha finalizado");
       }
       catch (Exception exception) {
           exception.printStackTrace();
       }
    }

    /**
     * Create and return file outputStream
     * @param fileName Name of file
     * @return file outputStream
     */
    private BufferedOutputStream createFile(final String fileName) {
        String path = System.getProperty("user.dir");
        File file = new File(path, fileName);
        if(!file.exists()) {
            try {
                boolean fileState = file.createNewFile();
                if (fileState) {
                    System.out.println("Fichero creado con exito");

                    //Get file outputStream
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    return new BufferedOutputStream(fileOutputStream, sizeBuffer);
                }
                else
                    System.out.println("¡Error al crear el archivo!");

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        //Todo: Check this
        return null;
    }
}