package uitl;

public class ConnectionTest {
    public static void main(String[] args) {
        try {
            var conn = XJdbc.openConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("Connection successful: " + conn.getMetaData().getURL());
                XJdbc.closeConnection();
            } else {
                System.out.println("Connection failed: connection is null or closed");
            }
        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
