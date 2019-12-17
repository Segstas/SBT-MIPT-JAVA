package week9.homework;

import week9.homework.classesforserialize.Adress;
import week9.homework.classesforserialize.Person;

public class Main {
    public static void main(String[] args) {
        SerializerImpl serializerXML = new XMLSerializer();
        SerializerImpl serializerJSON = new JSONSerializer();

        Adress adress = new Adress("Moscow", 121212);
        Person person = new Person("Bob", adress, new String[]{"12121213", "0099900", "77777"});
        System.out.println(serializerXML.serialize(person));
        System.out.println(serializerJSON.serialize(person));

    }
}
