package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    private Order order;

    private String cardNumber;

    public void setId(Long id) {
        this.Id = id;
    }

    public Payment(final Order order, final String cardNumber) {
        Assert.notNull(order,"주문은 필수입니다.");
        Assert.hasText(cardNumber, "카드 번호는 필수입니다");
        this.order = order;
        this.cardNumber = cardNumber;
    }


    public int getPrice() {
        return order.getTotalPrice();
    }


}
