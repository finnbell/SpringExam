package springcore.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springcore.hello.discount.DiscountPolicy;
import springcore.hello.discount.RateDiscountPolicy;
import springcore.hello.member.MemberRepository;
import springcore.hello.member.MemberService;
import springcore.hello.member.MemberServiceImpl;
import springcore.hello.member.MemoryMemberRepository;
import springcore.hello.order.OrderService;
import springcore.hello.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()


    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), disocuntPolicy());
    }

    @Bean
    public DiscountPolicy disocuntPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
