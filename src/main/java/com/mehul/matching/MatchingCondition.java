package com.mehul.matching;

import com.mehul.model.Order;
import com.mehul.model.OrderBook;
import com.mehul.model.Report;

public interface MatchingCondition {
    Report process(OrderBook orderBook, Order orderCandidate, Order order);
}