import java.sql.*;

public class MySQLCRUD {

    private final String url = "jdbc:mysql://localhost:3306/lab3";
    private final String user = "root";
    private final String pass = "";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    // CREATE
    public void create(Customer c) {
        String sql = "INSERT INTO customer (id, firstName, lastName, email, phone) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getId());
            ps.setString(2, c.getFirstName());
            ps.setString(3, c.getLastName());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getPhone());

            ps.executeUpdate();
            System.out.println("MySQL Inserted: " + c.getId());

        } catch (SQLException e) {
            System.out.println("MySQL Insert Error: " + e.getMessage());
        }
    }

    // READ
    public void read() {
        String sql = "SELECT * FROM customer";

        try (Connection conn = connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n--- MySQL Data ---");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " " +
                                rs.getString("firstName") + " " +
                                rs.getString("lastName") + " " +
                                rs.getString("email") + " " +
                                rs.getString("phone")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void update(Customer c) {
        String sql = "UPDATE customer SET email=? WHERE id=?";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getEmail());
            ps.setInt(2, c.getId());

            ps.executeUpdate();
            System.out.println("MySQL Updated: " + c.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE ONE
    public void delete(int id) {
        String sql = "DELETE FROM customer WHERE id=?";

        try (Connection conn = connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("MySQL Deleted: " + id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE ALL (IMPORTANT FOR TESTING)
    public void deleteAll() {
        String sql = "DELETE FROM customer";

        try (Connection conn = connect();
             Statement st = conn.createStatement()) {

            st.executeUpdate(sql);
            System.out.println("MySQL Table Cleared");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}