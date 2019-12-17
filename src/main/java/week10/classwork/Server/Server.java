package week10.classwork.Server;

import week10.classwork.Order;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(14996);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Started");

            new Thread(() -> {
                try {
                    try {
                        process(clientSocket);
                    } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    try {
                        clientSocket.close();
                    } catch (IOException ignore) {
                    }
                }
            }).start();
        }
    }

    private static void process(Socket clientSocket) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String name;
        Class returnType;
        Class[] parameterTypes;
        Order original;

        try (ObjectInput in = new ObjectInputStream(clientSocket.getInputStream())) {
            name = (String) in.readObject();
            returnType = (Class) in.readObject();
            parameterTypes = (Class[]) in.readObject();
            original = (Order) in.readObject();
        }


        HashMap<String, Integer> shopStorage = new HashMap<>();
        shopStorage.put("Banana", 50);
        Storage storage = new Storage(shopStorage);
        Method method = storage.getClass().getMethod(name, parameterTypes);
        Integer returnValue = (Integer) method.invoke(storage,original);
        System.out.println(name);
        System.out.println(returnValue);



        ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream());

        ObjectOutput out = null;
        out = os;
        out.writeObject(returnValue);
        out.flush();
    }
}