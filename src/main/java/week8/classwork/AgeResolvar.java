import java.util.ArrayList;
import java.util.List;

public class AgeResolvar {
    public static void main(String[] args) {
        Person person1 = new Person(11, "asdasd");
        Person person2 = new Person(23, "asdas");
        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);

        printAges(personList);
        printNames(personList);

    }

    private static void printAges(List<Person> people) {
        printFields(people, Person::getAge);
    }

    private static void printNames(List<Person> people) {
        printFields(people, Person::getName);
    }

    private static void printFields(List<Person> people, PersonFieldGetter getter) {
        for (Person person : people) {
            if (person.getAge() >= 18) {
                System.out.println(getter.getField(person));
            }
        }


    }
}
