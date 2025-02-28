package NuevaApp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionLibros {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != -1) {
            System.out.print("1 - Añadir Libro | 2 - Listar Libros | -1 - Salir: ");
            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) agregarLibro();
            else if (opcion == 2) listarLibros();
        }
    }

    public static void agregarLibro() throws SQLException {
        Connection con = Main.connection;
        Scanner sc = new Scanner(System.in);

        System.out.print("📕 Título: ");
        String titulo = sc.nextLine();
        System.out.print("✍ Autor: ");
        String autor = sc.nextLine();

        PreparedStatement st = con.prepareStatement("INSERT INTO libros (titulo, autor) VALUES (?, ?)");
        st.setString(1, titulo);
        st.setString(2, autor);
        st.executeUpdate();

        System.out.println("✅ Libro añadido.");
    }

    public static void listarLibros() throws SQLException {
        Connection con = Main.connection;
        PreparedStatement st = con.prepareStatement("SELECT * FROM libros");
        ResultSet rs = st.executeQuery();

        System.out.println("\n📚 Libros Disponibles:");
        while (rs.next()) {
            System.out.println("📖 " + rs.getString("titulo") + " (Autor: " + rs.getString("autor") + ")");
        }
    }
}
