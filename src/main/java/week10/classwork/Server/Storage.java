package week10.classwork.Server;

import week10.classwork.Order;
import week10.classwork.OrderConsumer;

import java.util.HashMap;

public class Storage implements OrderConsumer {


    HashMap<String, Integer> shopStorage = new HashMap<>();

    public Storage(HashMap<String, Integer> shopStorage) {
        this.shopStorage = shopStorage;
    }


    @Override
    public Boolean buyOrder(Order order) {
        if (shopStorage.containsKey(order.getName())) {
            int oldCount = shopStorage.get(order.getName());
            if (oldCount > order.getCount()) {
                shopStorage.replace(order.getName(), oldCount - order.getCount());
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer checkOrderCount(Order order) {
        if (shopStorage.containsKey(order.getName())) {
            return shopStorage.get(order.getName());
        }
        return 0;
    }
}
