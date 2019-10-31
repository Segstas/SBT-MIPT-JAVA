package week2.homework2;

public class IR_SWAP implements TradeType {
    int price;

    public IR_SWAP(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "IR_SWAP{" +
                "price=" + price +
                '}';
    }
}
