public class test{
public static void main(String args[]){

String url = "jdbc:mysql://localhost:3306/chat";
String username = "root";
String password = "";

System.out.println("Connecting database...");

try (Connection connection = DriverManager.getConnection(url, username, password)) {
    System.out.println("Database connected!");
} catch (SQLException e) {
    throw new IllegalStateException("Cannot connect the database!", e);
}
}
}