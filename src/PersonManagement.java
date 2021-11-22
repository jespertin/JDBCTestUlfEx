import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonManagement implements PersonDao {
    private String url = "jdbc:mysql://localhost:3306/personSchema";
    private String userName = "root";
    private String passWord = "root";

    Connection conn;
    PreparedStatement addPerson, removePerson, updateName, updateBirth, getAll, getById;

    public PersonManagement() {
        try {
            conn = DriverManager.getConnection(url, userName, passWord);
            addPerson = conn.prepareStatement("insert into persons (name,birth) values (?,?)");
            removePerson = conn.prepareStatement("delete from persons where id=?");
            updateName = conn.prepareStatement("update persons set name=? where id=?");
            updateBirth = conn.prepareStatement("update persons set birth=? where id=?");
            getAll = conn.prepareStatement("Select * from persons");
            getById = conn.prepareStatement("Select * from persons where id=?");

        } catch (SQLException e) {
            throw new RuntimeException("Problem in Personmanagement" + e);
        }
    }

    @Override
    public boolean addPerson(Person p) {
        String name = p.getName();
        int birth = p.getBirth();

        try {
            addPerson.setInt(2, birth);
            addPerson.setString(1, name);
            addPerson.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Problem in Personmanagement" + e);
        }
    }


    @Override
    public boolean removePerson(int id) {
        try {
            removePerson.setInt(1, id);
            return removePerson.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Problem in removePerson" + e);
        }

    }

    @Override
    public boolean updateName(int id, String name) {
        try {
            updateName.setInt(2, id);
            updateName.setString(1, name);
            return updateName.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Problem in removePerson" + e);
        }
    }

    @Override
    public boolean updateBirth(int id, int birth) {
        try {
            updateBirth.setInt(2, id);
            updateBirth.setInt(1, birth);
            return updateName.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Problem in updateBirth" + e);
        }
    }

    @Override
    public List<Person> getAll() {
        ArrayList<Person> persons = new ArrayList<>();

        try {
            ResultSet rs = getAll.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int birth = rs.getInt("birth");
                persons.add(new Person(id, name, birth));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Problem in getAll" + e);
        }
        return persons;

    }

    @Override
    public Person getById(int id) {
        Person p=null;
        try {
            getById.setInt(1, id);
            ResultSet rs = getById.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int birth = rs.getInt("birth");
                p = new Person(id, name, birth);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Problem in getByID" + e);

        }
        return p;
    }
}
