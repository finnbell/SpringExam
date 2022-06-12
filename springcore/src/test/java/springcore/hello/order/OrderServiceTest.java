package springcore.hello.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springcore.hello.AppConfig;
import springcore.hello.discount.FixDiscountPolicy;
import springcore.hello.member.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    MemberService memberService ;
    OrderService orderService ;



    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);



    }

    @Test
    void fieldInjectionTest() {
        OrderServiceImpl orderService = new OrderServiceImpl();

        orderService.setMemberRepository(new MemoryMemberRepository());
        orderService.setDiscountPolicy(new FixDiscountPolicy());

        orderService.createOrder(1L, "itemA", 10000);
    }




}