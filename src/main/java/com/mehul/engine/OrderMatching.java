package com.mehul.engine;

import com.mehul.model.Order;
import com.mehul.model.OrderBook;
import com.mehul.model.Report;

public interface OrderMatching {
    Report match(OrderBook orderBook, Order order);
}
