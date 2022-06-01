package springcore.hello;

import springcore.hello.discount.FixDiscountPolicy;
import springcore.hello.member.MemberService;
import springcore.hello.member.MemberServiceImpl;
import springcore.hello.member.MemoryMemberRepository;
import springcore.hello.order.OrderService;
import springcore.hello.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }


    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
