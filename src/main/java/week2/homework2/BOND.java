package week2.homework2;

public class BOND implements TradeType {
    int price;


    public BOND(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BOND{" +
                "price=" + price +
                '}';
    }
}
