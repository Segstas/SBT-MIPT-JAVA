package week6.homework6;

import java.io.FileWriter;
import java.io.IOException;

public interface Serializer {
    public void serialize(Object object, FileWriter writer) throws IOException;
}
