package com.mehul.server;

import com.mehul.model.Order;
import com.mehul.model.OrderBook;
import com.mehul.model.Report;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<Order> {
    private final OrderBook orderBook;

    public ServerHandler(OrderBook orderBook) {
        this.orderBook = orderBook;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Order order) {
        System.out.println("[SERVER] received order: " + order);
        if (order == null) {
            throw new IllegalArgumentException("Received null order!");
        }
        Report response = orderBook.processOrder(order);
        System.out.println("[SERVER] processed order: " + response);
        System.out.println("[SERVER] orderBook status: " + orderBook);
        ctx.writeAndFlush(response);
    }
}
