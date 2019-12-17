package week10.classwork;

public interface OrderConsumer {
    Boolean buyOrder(Order order);
    Integer checkOrderCount(Order order);
}
