package com.mehul.matching;

import com.mehul.model.Order;
import com.mehul.model.OrderBook;
import com.mehul.model.Report;
import com.mehul.model.ReportStatus;
import com.mehul.model.ReportType;

public class BuyMatchingConditionPrice implements MatchingCondition {
    @Override
    public Report process(OrderBook orderBook, Order orderCandidate, Order order) {
        if (orderCandidate.getPrice() > order.getPrice()) {
            System.out.println("[SERVER] Cannot match! Best ASK " + orderCandidate.getPrice() + " is higher than " + order.getPrice());
            return new Report(ReportType.exe_report, 
                    orderBook.getSellOrders().size(), 
                    null, 
                    null, 
                    order.getAccountId(), 
                    ReportStatus.REJECTED);
        }
        return null;
    }
}