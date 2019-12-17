package week9.homework;

import week9.homework.classesforserialize.Adress;
import week9.homework.classesforserialize.Person;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Serializer jsonSerializerStrategy = new JSONSerializer();
        Serializer xmlSerializerStrategy = new XMLSerializer();


        SerializerImpl serializerXML = new SerializerImpl();
        SerializerImpl serializerJSON = new SerializerImpl();

        serializerJSON.setStrategy(jsonSerializerStrategy);
        serializerXML.setStrategy(xmlSerializerStrategy);

        Adress adress = new Adress("Moscow", 121212);
        Person person = new Person("Bob", adress, new String[]{"12121213", "0099900", "77777"});
        System.out.println(serializerJSON.serialize(person));
        System.out.println(serializerXML.serialize(person));

    }
}
