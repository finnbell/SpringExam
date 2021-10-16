package com.jpabook.jpashop.domain.item;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue

    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)   // ORDINAL 은 순서에 영향을 받아서,  STRING 으로만 사용함
    private DeliveryStatus status; //READY, COMP   [READY, COMP]


}
