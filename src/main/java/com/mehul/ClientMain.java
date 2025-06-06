package com.mehul;

import com.mehul.client.Client;
import com.mehul.model.Order;
import com.mehul.model.OrderType;

public class ClientMain {
    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        client.sendOrder("localhost", 8080, new Order(OrderType.BUY, 50, 1009, "1233"));
        Thread.sleep(1000);
        client.sendOrder("localhost", 8080, new Order(OrderType.BUY, 50, 1009, "1233"));
        client.sendOrder("localhost", 8080, new Order(OrderType.BUY, 23, 10123, "1233"));

        client.sendOrder("localhost", 8080, new Order(OrderType.SELL, 10, 1000, "1233"));
        client.sendOrder("localhost", 8080, new Order(OrderType.SELL, 40, 1009, "1233"));
    }
}
