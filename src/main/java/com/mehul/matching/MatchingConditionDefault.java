package com.mehul.matching;

import com.mehul.model.Order;
import com.mehul.model.OrderBook;
import com.mehul.model.Report;
import com.mehul.model.ReportStatus;
import com.mehul.model.ReportType;

public class MatchingConditionDefault implements MatchingCondition {
    @Override
    public Report process(OrderBook orderBook, Order orderCandidate, Order order) {
        System.out.println("[SERVER] Cannot match! Default Condition. orderCandidate: " + orderCandidate + ", order: " + order);
        return new Report(ReportType.exe_report,
                orderBook.getBuyOrders().size(),
                null,
                null,
                order.getAccountId(),
                ReportStatus.REJECTED);
    }
}