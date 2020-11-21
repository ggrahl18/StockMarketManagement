package Stock;

public class PeopleFactory {

    public GroupOfPeople getPeople(String people) {
        if(people == null) {
            return null;
        } else if(people == "new") {
            System.out.println("People list created.");
            return GroupOfPeople.getPeople();
        }
        return null;
    }
}
