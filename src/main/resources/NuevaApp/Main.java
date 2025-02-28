package NuevaApp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static int id_usuario = -1;
    static Connection connection;
    static String usuario = "";

    public static java.sql.Connection getConnection() {
        String host = "jdbc:sqlite:src/main/resources/network.sqlite";
        if (connection == null) {
            try {
                connection = java.sql.DriverManager.getConnection(host);
            } catch (SQLException sql) {
                System.out.println(sql.getMessage());
                System.exit(0);
            }
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        connection = getConnection();

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != -1) {
            System.out.println("\n1 - Usuarios | 2 - Libros | 3 - Reseñas | -1 - Salir");
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine();
                if (opcion == 1) GestionUsuarios.gestionMenu();
                else if (opcion == 2) GestionLibros.gestionMenu();
                else if (opcion == 3) GestionReseñas.gestionMenu();
            } else {
                System.out.println("⚠️ Ingresa una opción válida.");
                sc.next();
            }
        }

    }
}
