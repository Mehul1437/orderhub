package com.mehul.engine;

import com.mehul.matching.MatchingConditionDefault;
import com.mehul.matching.MatchingConditionEmptyOrder;
import com.mehul.matching.MatchingCondition;
import com.mehul.matching.SellMatchingConditionPrice;
import com.mehul.matching.SellMatchingConditionQuantity;
import com.mehul.model.Order;
import com.mehul.model.OrderBook;
import com.mehul.model.Report;

import java.util.List;

public class OrderMatchingSell implements OrderMatching {
    private final List<MatchingCondition> conditions = List.of(
            new MatchingConditionEmptyOrder(),
            new SellMatchingConditionPrice(),
            new SellMatchingConditionQuantity()
    );

    @Override
    public Report match(OrderBook orderBook, Order order) {
        Order bestBuy = orderBook.getBuyOrders().peek(); // Get highest BID price
        for (MatchingCondition condition : conditions) { // Iterate over all conditions and return the first valid Report
            Report result = condition.process(orderBook, bestBuy, order);
            if (result != null) {
                return result;
            }
        }
        return new MatchingConditionDefault().process(orderBook, bestBuy, order);
    }
}
