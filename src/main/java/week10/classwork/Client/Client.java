package week10.classwork.Client;

import week10.classwork.Order;
import week10.classwork.OrderConsumer;
import week10.classwork.RPCCallHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Client implements OrderConsumer {

    public static void main (String[] args) throws IOException, ClassNotFoundException {
        Order original = new Order("Banana", 2);
        RPCCallHandler handler = new RPCCallHandler(original);
        OrderConsumer proxyInstance = (OrderConsumer) Proxy.newProxyInstance(OrderConsumer.class.getClassLoader(),
                new Class[] { OrderConsumer.class },
                handler);
        Object countAnswer = proxyInstance.checkOrderCount(original);
        System.out.println(countAnswer);
    }

    @Override
    public Boolean buyOrder(Order order) {
        return false;
    }

    @Override
    public Integer checkOrderCount(Order order) {
        return 0;
    }
}
