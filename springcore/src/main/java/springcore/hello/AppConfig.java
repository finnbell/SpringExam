package springcore.hello;

import springcore.hello.discount.DisocuntPolicy;
import springcore.hello.discount.FixDiscountPolicy;
import springcore.hello.member.MemberRepository;
import springcore.hello.member.MemberService;
import springcore.hello.member.MemberServiceImpl;
import springcore.hello.member.MemoryMemberRepository;
import springcore.hello.order.OrderService;
import springcore.hello.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), disocuntPolicy());
    }

    private DisocuntPolicy disocuntPolicy() {
        return new FixDiscountPolicy();
    }

}
