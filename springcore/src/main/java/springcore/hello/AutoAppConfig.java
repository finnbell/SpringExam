package springcore.hello;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import springcore.hello.order.OrderService;
import springcore.hello.order.OrderServiceImpl;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter (type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {


    @Bean
    OrderService orderService() {
        return new OrderServiceImpl();

    }

}
