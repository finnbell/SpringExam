package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;

interface PaymentPort {
    Order getOrder(Long aLong);

    void pay(Payment payment);

    void save(Payment payment);
}
