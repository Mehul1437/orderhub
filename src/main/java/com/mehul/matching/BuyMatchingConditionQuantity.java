package com.mehul.matching;

import com.mehul.model.Order;
import com.mehul.model.OrderBook;
import com.mehul.model.Report;
import com.mehul.model.ReportStatus;
import com.mehul.model.ReportType;

public class BuyMatchingConditionQuantity implements MatchingCondition {
    @Override
    public Report process(OrderBook orderBook, Order orderCandidate, Order order) {
        if (orderCandidate.getQuantity() >= order.getQuantity()) {
            orderCandidate.setQuantity(orderCandidate.getQuantity() - order.getQuantity());
            if (orderCandidate.getQuantity() == 0) {
                orderBook.getSellOrders().poll(); // Remove fully matched order
            }
            System.out.println("[SERVER] BUY FILLED at $" + orderCandidate.getPrice() + " for " + order.getQuantity() + " units");
            return new Report(ReportType.exe_report, 
                    orderBook.getSellOrders().size(), 
                    order.getPrice(), 
                    order.getQuantity(), 
                    order.getAccountId(),
                    ReportStatus.FILLED);
        }
        return null;
    }
}