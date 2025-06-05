package com.mehul.engine;

import com.mehul.matching.BuyMatchingConditionPrice;
import com.mehul.matching.BuyMatchingConditionQuantity;
import com.mehul.matching.MatchingCondition;
import com.mehul.matching.MatchingConditionDefault;
import com.mehul.matching.MatchingConditionEmptyOrder;
import com.mehul.model.Order;
import com.mehul.model.OrderBook;
import com.mehul.model.Report;

import java.util.List;

public class OrderMatchingBuy implements OrderMatching {
    private final List<MatchingCondition> conditions = List.of(
            new MatchingConditionEmptyOrder(),
            new BuyMatchingConditionPrice(),
            new BuyMatchingConditionQuantity()
    );

    @Override
    public Report match(OrderBook orderBook, Order order) {
        Order bestBuy = orderBook.getSellOrders().peek(); // Get highest ASK price
        for (MatchingCondition condition : conditions) { // Iterate over all conditions and return the first valid Report
            Report result = condition.process(orderBook, bestBuy, order);
            if (result != null) {
                return result;
            }
        }
        return new MatchingConditionDefault().process(orderBook, bestBuy, order);
    }
}
