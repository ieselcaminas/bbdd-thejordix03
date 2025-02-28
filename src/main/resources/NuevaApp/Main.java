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

    public static Connection getConnection() {
        String host = "jdbc:sqlite:src/main/resources/network.sqlite";
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(host);
            } catch (SQLException sql) {
                System.out.println("⚠️ Error de conexión: " + sql.getMessage());
                System.exit(0);
            }
        }
        return connection;
    }

    public static void crearTablas() {
        try {
            Statement stmt = connection.createStatement();

            // Tabla de usuarios
            stmt.execute("CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT NOT NULL, " +
                    "apellidos TEXT NOT NULL, " +
                    "contrasenya TEXT NOT NULL)");

            // Tabla de libros
            stmt.execute("CREATE TABLE IF NOT EXISTS libros (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "titulo TEXT NOT NULL, " +
                    "autor TEXT NOT NULL)");

            // Tabla de reseñas
            stmt.execute("CREATE TABLE IF NOT EXISTS reseñas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "texto TEXT NOT NULL, " +
                    "id_usuario INTEGER, " +
                    "id_libro INTEGER, " +
                    "FOREIGN KEY (id_usuario) REFERENCES usuarios(id), " +
                    "FOREIGN KEY (id_libro) REFERENCES libros(id))");

            System.out.println("✅ Tablas verificadas/corregidas.");

        } catch (SQLException e) {
            System.out.println("⚠️ Error al crear las tablas: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        connection = getConnection();
        crearTablas(); // Se crean las tablas al iniciar el programa

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
