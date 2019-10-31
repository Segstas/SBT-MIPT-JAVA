package week2.homework2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parcer {
    static ArrayList<String> words = new ArrayList<String>();


    public static TradeType scan() {
        Scanner fromKeyboard = new Scanner(System.in);
        String fileName = fromKeyboard.next();
        try (FileReader tradeFile = new FileReader(fileName)) {
            Scanner fromFile = new Scanner(tradeFile).useDelimiter("\\}|\\{");
            while (fromFile.hasNext()) {
                words.add(fromFile.next().replaceAll("\\s+", ""));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parce();
    }

    public static TradeType parce() {
        String type = null;
        int price = 0;

        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).equals("type=")) {
                i++;
                type = words.get(i);
            }
            if (words.get(i).equals("price=")) {
                i++;
                price = Integer.parseInt(words.get(i));
            }
        }

        System.out.println(generateObjeceSwitch(type, price));
        //   System.out.println(generateObjeceEnum(type, price));
        generateObjeceSwitch(type, price);
        return generateObjeceEnum(type, price);
    }

    public static TradeType generateObjeceSwitch(String type, int price) {
        TradeType trade = null;

        switch (type) {
            case "BOND":
                trade = new BOND(price);
                break;
            case "COMMODITY_SPOT":
                trade = new COMMODITY_SPOT(price);
                break;
            case "FX_SPOT":
                trade = new FX_SPOT(price);
                break;
            case "IR_SWAP":
                trade = new IR_SWAP(price);
                break;
        }
        return trade;
    }

    public static TradeType generateObjeceEnum(String type, int price) {
        return TradeTypeEnum.valueOf(type).createTrade(type, price);
    }
}