package com.mehul.matching;

import com.mehul.model.Order;
import com.mehul.model.OrderBook;
import com.mehul.model.Report;

import static com.mehul.model.ReportFactory.createFilledReport;

public class SellMatchingConditionQuantity implements MatchingCondition {
    @Override
    public Report process(OrderBook orderBook, Order orderCandidate, Order order) {
        if (orderCandidate.getQuantity() >= order.getQuantity()) {
            orderCandidate.setQuantity(orderCandidate.getQuantity() - order.getQuantity());
            if (orderCandidate.getQuantity() == 0) {
                orderBook.getBuyOrders().poll(); // Remove fully matched order
            }
            System.out.println("[SERVER] SELL FILLED at $" + orderCandidate.getPrice() + " for " + order.getQuantity() + " units");
            return createFilledReport(
                    orderBook.getBuyOrders().size(),
                    orderCandidate.getPrice(),
                    orderCandidate.getQuantity(),
                    order.getAccountId());
        }
        return null;
    }
}