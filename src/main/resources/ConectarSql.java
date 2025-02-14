import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public class ConectarSql {

    static java.sql.Connection connection;

    public static java.sql.Connection getConnection(){
        String host = "jdbc:sqlite:src/main/resources/network.sqlite";
        if (connection == null) {
            try {
                connection = java.sql.DriverManager.getConnection(host);
            }catch (SQLException sql){
                System.out.println(sql.getMessage());
                System.exit(0);
            }
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        java.sql.Connection con = getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from usuarios");
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println("\t" + rs.getString(2));
            System.out.println("\t" + rs.getString(3));


        }
        stmt.close();
        //insertUser();

    }
    public static void insertUser() throws SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate("INSERT INTO usuarios (nombre,apellidos) VALUES ('Janet', 'Espinosa')");
    }



}