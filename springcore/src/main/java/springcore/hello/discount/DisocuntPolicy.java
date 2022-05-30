package springcore.hello.discount;

import springcore.hello.member.Member;

public interface DisocuntPolicy {

    /**
     * @return 할인 대상 금액
     */
    int disocunt(Member member, int price);


}
