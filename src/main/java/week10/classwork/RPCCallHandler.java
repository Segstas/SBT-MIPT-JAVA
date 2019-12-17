package week10.classwork;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RPCCallHandler implements InvocationHandler {
    private final Order original;

    public RPCCallHandler(Order original) {
        this.original = original;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws IllegalArgumentException, IOException, ClassNotFoundException {


        System.out.println("Client start");
        Socket socket = new Socket("localhost", 14996);
        System.out.println("Client connected");

        InputStream inputStream = socket.getInputStream();
        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());


        Class[] parameterTypes = method.getParameterTypes();
        Class returnType = method.getReturnType();
        String name = method.getName();


        ObjectOutput out;
        /// try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
        out = os;
        out.writeObject(name);
        out.writeObject(returnType);
        out.writeObject(parameterTypes);
        out.writeObject(original);
        out.flush();
        ////byte[] yourBytes = bos.toByteArray();
        ///}

        System.out.println("send to serv...");


        ObjectInputStream is = new ObjectInputStream(inputStream);


        Object ansver = (Object) is.readObject();
        System.out.println("return Order is=" + ansver);



        return ansver;
    }

}
