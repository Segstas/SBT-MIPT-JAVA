package week10.classwork.Client;

import week10.classwork.Order;
import week10.classwork.OrderConsumer;

public class ShopClient implements OrderConsumer {
    @Override
    public Boolean buyOrder(Order order) {
        return false;
    }

    @Override
    public Integer checkOrderCount(Order order) {
        return 0;
    }
}
