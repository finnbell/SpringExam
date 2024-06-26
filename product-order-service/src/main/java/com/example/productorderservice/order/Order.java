package com.example.productorderservice.order;

import com.example.productorderservice.product.Product;
import org.springframework.util.Assert;

class Order {
    private Long id;
    private final Product product;
    private final int quantity;

    public Order(final Product product, final int quantity) {
        Assert.notNull(product, "상품은 필수입니다.");
        Assert.isTrue(quantity > 0, "수량은1 0보다 커야 합니다.");
        this.product = product;
        this.quantity = quantity;
    }

    public void assignId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
