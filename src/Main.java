import java.util.List;

public class Main {

    public static void main(String[] args) {
        Person p1 = new Person("Janne",1995);
        Person p2 = new Person("Pelle",1955);
        Person p3 = new Person("Linda",2000);

        PersonManagement pm = new PersonManagement();
        System.out.println(pm.getById(3));

    }
}
