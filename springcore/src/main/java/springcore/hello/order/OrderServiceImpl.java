package springcore.hello.order;

import springcore.hello.discount.DisocuntPolicy;
import springcore.hello.discount.FixDiscountPolicy;
import springcore.hello.member.Member;
import springcore.hello.member.MemberRepository;
import springcore.hello.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DisocuntPolicy disocuntPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = disocuntPolicy.disocunt(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
