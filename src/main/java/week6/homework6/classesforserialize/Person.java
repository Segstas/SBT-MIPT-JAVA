package week6.homework6.classesforserialize;

public class Person {
    public String firstName;
    public Adress adress;
    public String[] phoneNumbers;

    public Person(String firstName, Adress adress, String[] phoneNumbers) {
        this.firstName = firstName;
        this.adress = adress;
        this.phoneNumbers = phoneNumbers;
    }
}
