package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.example.demo.dto.OrderItemsDto;
import com.example.demo.dto.OrdersDto;
import com.example.demo.entity.OrderItems;
import com.example.demo.entity.Orders;

@Component
public class MyAppMapper {

    private final ModelMapper mapper;

    public MyAppMapper() {
        this.mapper = new ModelMapper();

        // Custom mapping: OrderItems -> OrderItemsDto
        mapper.addMappings(new PropertyMap<OrderItems, OrderItemsDto>() {
            @Override
            protected void configure() {
                map().setOrderId(source.getOrders().getOrderId());
            }
        });

        // If needed, you can also add OrderItemsDto -> OrderItems mappings here.
    }

    public OrdersDto convertToOrderDto(Orders orders) {
        if (orders == null) return null;
        return mapper.map(orders, OrdersDto.class);
    }

    public Orders convertToOrder(OrdersDto orderDto) {
        if (orderDto == null) return null;
        return mapper.map(orderDto, Orders.class);
    }

    public OrderItemsDto convertToOrderItemsDto(OrderItems orderItems) {
        if (orderItems == null) return null;
        return mapper.map(orderItems, OrderItemsDto.class);
    }

    public OrderItems convertToOrderItems(OrderItemsDto orderItemsDto) {
        if (orderItemsDto == null) return null;

        OrderItems orderItems = mapper.map(orderItemsDto, OrderItems.class);

        // Manually set Orders object using orderId
        if (orderItemsDto.getOrderId() != null) {
            Orders orders = new Orders();
            orders.setOrderId(orderItemsDto.getOrderId());
            orderItems.setOrders(orders);
        }

        return orderItems;
    }
}
