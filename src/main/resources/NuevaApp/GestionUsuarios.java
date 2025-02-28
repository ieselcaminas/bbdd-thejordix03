package NuevaApp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionUsuarios {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (opcion != -1) {
            System.out.print("\n1 - Login | 2 - Nuevo usuario | -1 - Salir: ");
            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {
                if (existeUsuario()) {
                    System.out.println("✅ Sesión iniciada.");
                    break;
                } else {
                    System.out.println("❌ Usuario o contraseña incorrectos.");
                }
            } else if (opcion == 2) {
                insertarUsuario();
            }
        }
    }

    public static boolean existeUsuario() throws SQLException {
        Connection con = Main.connection;
        Scanner sc = new Scanner(System.in);

        System.out.print("👤 Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("🔑 Contraseña: ");
        String contrasenya = sc.nextLine();

        PreparedStatement st = con.prepareStatement("SELECT * FROM usuarios WHERE nombre = ? AND contrasenya = ?");
        st.setString(1, usuario);
        st.setString(2, contrasenya);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            Main.id_usuario = rs.getInt("id");
            Main.usuario = rs.getString("nombre");
            return true;
        }
        return false;
    }

    public static void insertarUsuario() throws SQLException {
        Connection con = Main.connection;
        Scanner sc = new Scanner(System.in);

        System.out.print("👤 Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("📛 Apellidos: ");
        String apellidos = sc.nextLine();
        System.out.print("🔑 Contraseña: ");
        String contrasenya = sc.nextLine();

        PreparedStatement st = con.prepareStatement("INSERT INTO usuarios (nombre, apellidos, contrasenya) VALUES (?, ?, ?)");
        st.setString(1, nombre);
        st.setString(2, apellidos);
        st.setString(3, contrasenya);
        st.executeUpdate();

        System.out.println("✅ Usuario registrado.");
    }
}
