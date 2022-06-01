package springcore.hello.order;

import springcore.hello.discount.DisocuntPolicy;
import springcore.hello.member.Member;
import springcore.hello.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DisocuntPolicy disocuntPolicy;


    public OrderServiceImpl(MemberRepository memberRepository, DisocuntPolicy disocuntPolicy) {
        this.memberRepository = memberRepository;
        this.disocuntPolicy = disocuntPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = disocuntPolicy.disocunt(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
