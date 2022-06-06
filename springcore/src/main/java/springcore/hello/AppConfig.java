package springcore.hello;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import springcore.hello.discount.DisocuntPolicy;
import springcore.hello.discount.FixDiscountPolicy;
import springcore.hello.discount.RateDiscountPolicy;
import springcore.hello.member.MemberRepository;
import springcore.hello.member.MemberService;
import springcore.hello.member.MemberServiceImpl;
import springcore.hello.member.MemoryMemberRepository;
import springcore.hello.order.OrderService;
import springcore.hello.order.OrderServiceImpl;

@Configurable
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), disocuntPolicy());
    }

    @Bean
    private DisocuntPolicy disocuntPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
