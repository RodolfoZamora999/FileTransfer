import java.util.Scanner;
import java.io.File;

/**
 * Punto de acceso del programa
 */
public class Main {
    public static void main(String... args) {
        //Mensaje de bienvenida
        System.out.println("Bienvenido a FileTransfer 0.1");
    
        if(args.length > 0) {
            String context = args[0];

            //Contexto para el cliente
            if(context.equals("--client")) {
                String host;
                int port;
                String patch;
                
                //Si proporciono el parámetro host@port@file
                if(args.length > 1) {
                    String parameter = args[1];
                    String[] tokens = parameter.split("@");

                    if(tokens.length < 3) {
                        System.out.println("Sintaxis incorrecta");
                        System.out.println("Sintaxos de parametro esperado: host@port@patch" );
                        System.out.println("Ejemplo: localhost@1921@c:/users/user/desktop/image.jpg" );
                        return;
                    }

                    host = tokens[0];
                    port = Integer.parseInt(tokens[1]);
                    patch = tokens[2];
                }

                //En caso contrario a lo anterior, pedir los valores
                else {
                    Scanner scanner = new Scanner(System.in);

                    System.out.print("Insertar host: ");
                    host = scanner.nextLine();

                    System.out.print("Insertar puerto: ");
                    port = Integer.parseInt(scanner.nextLine());

                    System.out.print("Insertar fichero  a enviar: ");
                    patch = scanner.nextLine();

                    scanner.close();
                }

                //Iniciar el cliente
                File file = new File(patch);
                FileClient fileClient = new FileClient(host, port);
                fileClient.sendFile(file);
            }

            //Contexto para el servidor
            else if(context.equals("--server")) {
                int port = -1;

                if(args.length > 1) {
                    port = Integer.parseInt(args[1]);
                }
                else {
                    Scanner scanner = new Scanner(System.in);
                    port = Integer.parseInt(scanner.nextLine());
                    scanner.close();
                }

                //Iniciar el servidor
                FileServer fileServer = new FileServer(port);
                fileServer.startServerSocket();
            }
            else {
                //Todo: Trabajar en esta parte tambien
                System.out.println("Error de lectura");
            }
        }
        else {
            //Todo: Modificar esto, por miestras se queda
            System.out.println("Error, intenta de nuevo....");
        }
    }
}
