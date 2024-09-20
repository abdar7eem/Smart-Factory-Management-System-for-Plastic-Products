import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

class dataBase {
    private static String dbURL;
    private static String dbUsername = "root"; // database username
    private static String dbPassword = "1212"; // database password
    private static String URL = "127.0.0.1"; // server location
    private static String port = "3306"; // port that mysql uses
    private static String dbName = "factory"; // database on mysql to connect to
    private static Connection con;

    public static Connection connectDb() {
        try {
            dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
            Properties p = new Properties();
            p.setProperty("user", dbUsername);
            p.setProperty("password", dbPassword);
            p.setProperty("useSSL", "false");
            p.setProperty("autoReconnect", "true");
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection(dbURL, p);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
}