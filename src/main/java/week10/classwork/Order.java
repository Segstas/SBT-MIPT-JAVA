package week10.classwork;

import java.io.Serializable;
 public class Order implements Serializable {
     public String getName() {
         return name;
     }

     public int getCount() {
         return count;
     }

     private final String name;
    private final int count;

    public Order(String name, int count) {
        this.name = name;
        this.count = count;
    }
}