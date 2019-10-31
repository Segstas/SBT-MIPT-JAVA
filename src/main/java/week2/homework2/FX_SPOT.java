package week2.homework2;

public class FX_SPOT implements TradeType {
    int price;

    public FX_SPOT(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "FX_SPOT{" +
                "price=" + price +
                '}';
    }
}
