package NuevaApp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionReseñas {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != -1) {
            System.out.print("1 - Añadir Reseña | 2 - Listar Reseñas | -1 - Salir: ");
            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) agregarReseña();
            else if (opcion == 2) listarReseñas();
        }
    }

    public static void agregarReseña() throws SQLException {
        if (Main.id_usuario == -1) {
            System.out.println("⚠️ Inicia sesión primero.");
            GestionUsuarios.gestionMenu();
            return;
        }

        Connection con = Main.connection;
        Scanner sc = new Scanner(System.in);

        System.out.print("📖 Título del libro: ");
        String titulo = sc.nextLine();

        // Verificar si el libro existe
        PreparedStatement checkLibro = con.prepareStatement("SELECT id FROM libros WHERE titulo = ?");
        checkLibro.setString(1, titulo);
        ResultSet rs = checkLibro.executeQuery();

        if (!rs.next()) {
            System.out.println("❌ El libro no existe.");
            return;
        }
        int idLibro = rs.getInt("id");

        System.out.print("📝 Tu reseña: ");
        String texto = sc.nextLine();

        // Insertar la reseña
        PreparedStatement st = con.prepareStatement("INSERT INTO reseñas (texto, id_usuario, id_libro) VALUES (?, ?, ?)");
        st.setString(1, texto);
        st.setInt(2, Main.id_usuario);
        st.setInt(3, idLibro);
        st.executeUpdate();

        System.out.println("✅ Reseña añadida.");
    }

    public static void listarReseñas() throws SQLException {
        Connection con = Main.connection;
        Scanner sc = new Scanner(System.in);

        System.out.print("📖 Introduce el título del libro: ");
        String titulo = sc.nextLine();

        PreparedStatement st = con.prepareStatement(
                "SELECT r.texto, u.nombre FROM reseñas r " +
                        "JOIN usuarios u ON r.id_usuario = u.id " +
                        "JOIN libros l ON r.id_libro = l.id " +
                        "WHERE l.titulo = ?"
        );
        st.setString(1, titulo);
        ResultSet rs = st.executeQuery();

        System.out.println("\n📝 Reseñas para \"" + titulo + "\":");
        boolean hayReseñas = false;

        while (rs.next()) {
            hayReseñas = true;
            System.out.println("👤 " + rs.getString("nombre") + ": " + rs.getString("texto"));
        }

        if (!hayReseñas) {
            System.out.println("❌ No hay reseñas para este libro.");
        }
    }
}
