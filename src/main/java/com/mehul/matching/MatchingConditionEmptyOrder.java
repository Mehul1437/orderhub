package com.mehul.matching;

import com.mehul.model.Order;
import com.mehul.model.OrderBook;
import com.mehul.model.Report;
import com.mehul.model.ReportStatus;
import com.mehul.model.ReportType;

public class MatchingConditionEmptyOrder implements MatchingCondition {
    @Override
    public Report process(OrderBook orderBook, Order orderCandidate, Order order) {
        if (orderCandidate == null) {
            System.out.println("[SERVER] Cannot match! MatchingConditionEmptyOrder. orderCandidate: null, order: " + order);
            return new Report(ReportType.exe_report,
                    orderBook.getBuyOrders().size(),
                    null,
                    null,
                    order.getAccountId(),
                    ReportStatus.REJECTED);
        }
        return null;
    }
}