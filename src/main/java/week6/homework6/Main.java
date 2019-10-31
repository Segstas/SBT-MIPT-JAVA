package week6.homework6;

import week6.homework6.classesforserialize.Adress;
import week6.homework6.classesforserialize.Person;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Serializer serializer = new JSONSerializer();
        Adress adress = new Adress("Moscow", 121212);
        Person person = new Person("Bob", adress, new String[]{"12121213"});
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/main/java/week6/homework6/JSONnote.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            serializer.serialize(person, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
