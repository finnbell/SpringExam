package springcore.hello.discount;

import springcore.hello.member.Grade;
import springcore.hello.member.Member;

public class FixDiscountPolicy implements DisocuntPolicy {

    private int discountFixAmount = 1000;  //1000원 할인

    @Override
    public int disocunt(Member member, int price) {
        if( member.getGrade() == Grade.VIP)
            return discountFixAmount;
        else {
            return 0;
        }
    }
}
