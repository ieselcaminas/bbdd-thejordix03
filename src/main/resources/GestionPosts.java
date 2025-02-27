import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class GestionPosts {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != -1) {
            System.out.print("1 - Nuevo Post | ");
            System.out.print("2 - Listar Posts | ");
            System.out.println("-1 - Salir");

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine(); // Consumir salto de línea

                if (opcion == 1) {
                    newPost();
                } else if (opcion == 2) {
                    listarTodosLosPosts();
                }
            } else {
                System.out.println("Por favor, introduce una opción válida.");
                sc.next(); // Limpiar entrada incorrecta
            }
        }
    }

    private static void listarTodosLosPosts() throws SQLException {
        Connection con = Main.connection;
        PreparedStatement st = con.prepareStatement("SELECT * FROM posts");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            printPost(rs);
        }
    }

    private static void printPost(ResultSet rs) throws SQLException {
        System.out.println("ID: " + rs.getInt("id"));
        System.out.println("Texto: " + rs.getString("texto"));
        System.out.println("Likes: " + rs.getInt("likes"));
        System.out.println("Fecha: " + rs.getDate("fecha"));
        System.out.println("Usuario ID: " + rs.getInt("id_usuario"));
        System.out.println("-----------------------------------");
    }

    public static void newPost() throws SQLException {
        if (Main.id_usuario == -1) {
            System.out.println("No estás logeado. Inicia sesión primero.");
            GestionUsuarios.gestionMenu();
            if (Main.id_usuario == -1) return; // Si sigue sin estar logeado, salir
        }

        Connection con = Main.connection;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el texto del post:");
        String texto = sc.nextLine();
        java.sql.Date fecha = new java.sql.Date(new Date().getTime());

        PreparedStatement st = con.prepareStatement("INSERT INTO posts (texto, likes, fecha, id_usuario) VALUES (?, ?, ?, ?)");
        st.setString(1, texto);
        st.setInt(2, 0);
        st.setDate(3, fecha);
        st.setInt(4, Main.id_usuario);
        st.executeUpdate();

        System.out.println("Post publicado con éxito.");
    }
}
