import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    public void addPet(Pet pet) {
        String sql = "INSERT INTO pets (name, age, type, breed, has_nice_fur) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pet.getName());
            stmt.setInt(2, pet.getAge());

            if (pet instanceof Dog) {
                stmt.setString(3, "DOG");
                stmt.setString(4, ((Dog) pet).getBreed());
                stmt.setObject(5, null);
            } else {
                stmt.setString(3, "CAT");
                stmt.setObject(4, null);
                stmt.setBoolean(5, ((Cat) pet).hasNiceFur());
            }
            stmt.executeUpdate();
            System.out.println("Success! Saved to DB.");

        } catch (SQLException e) {
            System.out.println("Error saving pet: " + e.getMessage());
        }
    }

    public List<Pet> getAll() {
        List<Pet> list = new ArrayList<>();
        String sql = "SELECT * FROM pets ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String type = rs.getString("type");

                Pet p;
                if ("DOG".equals(type)) {
                    p = new Dog(name, age, rs.getString("breed"));
                } else {
                    p = new Cat(name, age, rs.getBoolean("has_nice_fur"));
                }
                p.setId(id);
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delete(int id) {
        String sql = "DELETE FROM pets WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Pet deleted successfully.");
            else System.out.println("Pet not found.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}