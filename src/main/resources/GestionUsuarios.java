import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionUsuarios {
    public static void gestionMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        String usuario;

        while (opcion != -1) {
            System.out.print("1 - Login | ");
            System.out.print("2 - Nuevo usuario | ");
            System.out.println("-1 - Salir");

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea

                if (opcion == 1) {
                    usuario = existeUsuario();
                    if (!usuario.isEmpty()) {
                        Main.usuario = usuario;
                        System.out.println("Bienvenido, " + usuario);
                        break;
                    } else {
                        System.out.println("Usuario no encontrado, inténtalo de nuevo.");
                    }
                } else if (opcion == 2) {
                    usuario = insertarUsuario();
                    if (!usuario.isEmpty()) {
                        Main.usuario = usuario;
                        System.out.println("Usuario creado exitosamente. Bienvenido, " + usuario);
                        break;
                    } else {
                        System.out.println("Error al crear el usuario.");
                    }
                }
            } else {
                System.out.println("Por favor, introduce una opción válida.");
                sc.next(); // Limpiar entrada incorrecta
            }
        }
    }

    /**
     * Comprueba si el usuario existe en la base de datos.
     * Ahora se verifica con nombre, apellido y contraseña.
     */
    public static String existeUsuario() throws SQLException {
        Connection con = Main.connection;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce tu nombre:");
        String nombre = sc.nextLine();
        System.out.println("Introduce tu apellido:");
        String apellido = sc.nextLine();
        System.out.println("Introduce tu contraseña:");
        String contrasenya = sc.nextLine();

        PreparedStatement st = con.prepareStatement("SELECT id, nombre FROM usuarios WHERE nombre = ? AND apellidos = ? AND contrasenya = ?");
        st.setString(1, nombre);
        st.setString(2, apellido);
        st.setString(3, contrasenya);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            Main.id_usuario = rs.getInt("id"); // Guardar ID del usuario en Main
            return rs.getString("nombre") + " " + apellido; // Devolver el nombre completo
        }
        return "";
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     */
    public static String insertarUsuario() throws SQLException {
        Connection con = Main.connection;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce tu nombre:");
        String nombre = sc.nextLine();
        System.out.println("Introduce tu apellido:");
        String apellido = sc.nextLine();
        System.out.println("Introduce tu contraseña:");
        String contrasenya = sc.nextLine();

        PreparedStatement st = con.prepareStatement("INSERT INTO usuarios (nombre, apellidos, contrasenya) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        st.setString(1, nombre);
        st.setString(2, apellido);
        st.setString(3, contrasenya);
        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            Main.id_usuario = rs.getInt(1); // Obtener el ID generado
            return nombre + " " + apellido;
        }
        return "";
    }
}
