package springcore.hello.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import springcore.hello.annotation.MainDiscountPolicy;
import springcore.hello.member.Grade;
import springcore.hello.member.Member;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if( member.getGrade() == Grade.VIP ) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
