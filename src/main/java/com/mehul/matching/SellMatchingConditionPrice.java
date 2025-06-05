package com.mehul.matching;

import com.mehul.model.Order;
import com.mehul.model.OrderBook;
import com.mehul.model.Report;

import static com.mehul.model.ReportFactory.createRejectReport;

public class SellMatchingConditionPrice implements MatchingCondition {
    @Override
    public Report process(OrderBook orderBook, Order orderCandidate, Order order) {
        if (orderCandidate.getPrice() < order.getPrice()) {
            System.out.println("[SERVER] Cannot match! Best BID " + orderCandidate.getPrice() + " is lower than " + order.getPrice());
            return createRejectReport(orderBook.getBuyOrders().size(), order.getAccountId());
        }
        return null;
    }
}