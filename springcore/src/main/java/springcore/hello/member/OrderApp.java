package springcore.hello.member;

import springcore.hello.AppConfig;
import springcore.hello.order.Order;
import springcore.hello.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {


        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order );
    }
}
