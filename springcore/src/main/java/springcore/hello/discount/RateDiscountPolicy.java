package springcore.hello.discount;

import springcore.hello.member.Grade;
import springcore.hello.member.Member;

public class RateDiscountPolicy implements DisocuntPolicy {

    private int discountPercent = 10;

    @Override
    public int disocunt(Member member, int price) {
        if( member.getGrade() == Grade.VIP ) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}