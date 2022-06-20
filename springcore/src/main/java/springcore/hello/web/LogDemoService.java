package springcore.hello.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import springcore.hello.common.MyLogger;


@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;

    public void logic(String id) {

//        MyLogger myLogger = myLogger.getObject();
        myLogger.log("service id = " + id);
    }
}
