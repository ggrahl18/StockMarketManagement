package Stock;

import java.util.ArrayList;

public class GroupOfPeople {
    private static GroupOfPeople groupOfPeople = new GroupOfPeople();
    public ArrayList<Person> personsList;
    public String name;

    private GroupOfPeople() {}

    public GroupOfPeople createPeople(String name) {
        GroupOfPeople people = groupOfPeople.getPeople();
        people.setName(name);
        ArrayList<Person> personsList = new ArrayList<Person>();
        this.personsList = personsList;
        this.name = name;
        System.out.println(this.name + " has been created.");
        return people;
    }

    public Person showAllPersons() {
        for(Person person : personsList) {
            return person;
        }

        return null;
    }

    public void addPerson(Person person) {
        if(personsList.contains(person)) {
            System.out.println(person.getName() + " already is in the list");
        }
        if(person == null) {
            System.out.println("Person is invalid");
        }

        this.personsList.add(person);
    }

    public void removePerson(Person person) {
        if(person == null) {
            System.out.println("Person is invalid");
        }
        if(personsList.contains(person)) {
            System.out.println(person.getName() + " has been removed.");
            personsList.remove(person);
        }
    }

    private int findPerson(Person person) { return this.personsList.indexOf(person); }

    private int findPerson(String name) {
        for(int i=0; i<personsList.size(); i++) {
            Person person = this.personsList.get(i);
            if(person.getName().equalsIgnoreCase(name)) {
                return i;
            }
        }

        return -1;
    }

    public String queryPerson(Person person) {
        if(findPerson(person) >= 0) {
            return person.getName();
        }

        return null;
    }

    public Person queryPerson(String name) {
        int position = findPerson(name);
        if(position >= 0) {
            return this.personsList.get(position);
        }

        return null;
    }

    public static GroupOfPeople getGroupOfPeople() {
        return groupOfPeople;
    }

    public ArrayList<Person> getPersonsList() {
        return personsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPeople(GroupOfPeople groupOfPeople) {
        this.groupOfPeople = groupOfPeople;
    }

    public static GroupOfPeople getPeople() {
        return groupOfPeople;
    }
}
