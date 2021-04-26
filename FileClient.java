import java.io.*;
import java.net.Socket;

/**
 * FileClient es el cliente para la transferencia de ficheros para el servidor FileServer.
 * Permite transferir un fichero de cualquier tipo a la ubicación de ejecución del servidor FileServer.
 * Solamente se necesita conocer la ip de destino (ip del servidor), puesto utilizado y el fichero que vas a trasnferir.
 * Nota: El cliente solamente puede enviar ficheros que el sevidor recibira
 */
public class FileClient {
    private final int sizeBuffer = (int) 10e6; //Buffer size 10MB
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

            //Stream socket
            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, sizeBuffer);
            PrintWriter writer = new PrintWriter(bufferedOutputStream);

            //Stream file
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, sizeBuffer);

            //Send file name
            System.out.println("Fichero que se enviara: " + name);
            writer.println(name);
            writer.flush();

            //Send file size
            System.out.println("Tamano del fichero: " + size + " bytes");
            writer.println(size);
            writer.flush();

            //Read and send data file
            int data;
            while (true) {
                data = bufferedInputStream.read();
                //End data sending
                if (data == -1) {
                    //bufferedOutputStream.write(-1);
                    bufferedOutputStream.flush();
                    break;
                }
                else
                    bufferedOutputStream.write(data);
            }

            //Close all streams
            bufferedInputStream.close();
            fileInputStream.close();
            bufferedOutputStream.close();
            outputStream.close();
            socket.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
