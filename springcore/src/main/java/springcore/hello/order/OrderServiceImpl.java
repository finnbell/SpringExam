package springcore.hello.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import springcore.hello.discount.DiscountPolicy;
import springcore.hello.member.Member;
import springcore.hello.member.MemberRepository;

@Component
public class OrderServiceImpl implements OrderService {

    private  MemberRepository memberRepository;
    private  DiscountPolicy discountPolicy;



    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("rateDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    public OrderServiceImpl() {

    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(@Qualifier("disocuntPolicy") DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }




}
