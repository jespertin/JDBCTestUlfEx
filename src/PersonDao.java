import java.util.List;

public interface PersonDao {

    boolean addPerson(Person p);

    boolean removePerson(int id);

    boolean updateName(int id, String name);

    boolean updateBirth(int id, int birth);

    List<Person> getAll();

    Person getById(int id);




}
