package springcore.hello.scan;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springcore.hello.AutoAppConfig;
import springcore.hello.member.MemberService;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {


    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

    }

}
