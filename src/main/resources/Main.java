import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static int id_usuario = -1;
    static java.sql.Connection connection;
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
            System.out.println("\n Menú Principal ");
            System.out.println("1 - Usuarios");
            System.out.println("2 - Posts");
            System.out.println("3 - Comentarios");
            System.out.println("-1 - Salir");

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine(); // Limpiar buffer

                if (opcion == 1) {
                    GestionUsuarios.gestionMenu();
                } else if (opcion == 2) {
                    GestionPosts.gestionMenu();
                } else if (opcion == 3) {
                    GestionComentarios.gestionMenu();
                }
            } else {
                System.out.println(" Introduce una opción válida.");
                sc.next(); // Limpiar entrada incorrecta
            }
        }
        System.out.println(" Programa finalizado.");
    }
}
