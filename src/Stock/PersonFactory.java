package Stock;

public class PersonFactory {

    public Person getPerson(String person) {
        if(person == null) {
            return null;
        } else if(person == "new") {
            System.out.println("Initial Public Offering initiated.");
            return Person.getPerson();
        }
        return null;
    }
}
