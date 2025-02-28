import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class GestionComentarios {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != -1) {
            System.out.print("1 - Comentar en un Post | ");
            System.out.print("2 - Ver comentarios de un Post | ");
            System.out.println("-1 - Salir");

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea

                if (opcion == 1) {
                    nuevoComentario();
                } else if (opcion == 2) {
                    listarComentarios();
                }
            } else {
                System.out.println("Por favor, introduce una opción válida.");
                sc.next(); // Limpiar entrada incorrecta
            }
        }
    }

    /**
     * Permite añadir un nuevo comentario a un post.
     */
    public static void nuevoComentario() throws SQLException {
        if (Main.id_usuario == -1) {
            System.out.println("Debes iniciar sesión para comentar.");
            GestionUsuarios.gestionMenu();
            return;
        }

        Connection con = Main.connection;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el ID del post al que quieres comentar:");
        int postId = sc.nextInt();
        sc.nextLine(); // Consumir el salto de línea

        System.out.println("Escribe tu comentario:");
        String texto = sc.nextLine();

        java.sql.Date fecha = new java.sql.Date(new Date().getTime());
        PreparedStatement st = con.prepareStatement("INSERT INTO comentarios (texto, fecha, id_usuario, id_post) VALUES (?, ?, ?, ?)");
        st.setString(1, texto);
        st.setDate(2, fecha);
        st.setInt(3, Main.id_usuario);
        st.setInt(4, postId);
        st.executeUpdate();

        System.out.println("Comentario añadido con éxito.");
    }

    /**
     * Lista los comentarios de un post específico.
     */
    public static void listarComentarios() throws SQLException {
        Connection con = Main.connection;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el ID del post para ver sus comentarios:");
        int postId = sc.nextInt();

        PreparedStatement st = con.prepareStatement("SELECT usuarios.nombre, comentarios.texto, comentarios.fecha FROM comentarios JOIN usuarios ON comentarios.id_usuario = usuarios.id WHERE id_post = ?");
        st.setInt(1, postId);
        ResultSet rs = st.executeQuery();

        System.out.println("\n--- Comentarios del Post ID " + postId + " ---");
        boolean hayComentarios = false;
        while (rs.next()) {
            hayComentarios = true;
            System.out.println("--- " + rs.getString("nombre") + ": " + rs.getString("texto") + " (" + rs.getDate("fecha") + ")");
        }

        if (!hayComentarios) {
            System.out.println("--- No hay comentarios en este post.");
        }
    }
}
