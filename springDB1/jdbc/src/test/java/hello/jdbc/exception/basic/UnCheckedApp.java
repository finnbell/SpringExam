package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

@Slf4j
public class UnCheckedApp {


    @Test
    void unchecked() {
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(() -> controller.request())
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void printExc() {
        Controller controller = new Controller();

        try {
            controller.request();
        }catch(Exception e) {
            log.info("ex",e);
        }
    }

    static class Controller {
        Service service = new Service();

        public void request()  {
            service.logic();
        }
    }

    static class Service {
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic() {
            repository.call();
            networkClient.call();

        }
    }

    static class NetworkClient {
        public void call() {
            throw new RuntimeConnectException("연결 실패");
        }
    }


    static class Repository {
        public void call() {
            try {
                runSQL();
            } catch (SQLException e) {
                //SQLException 발생시 RuntimeException 으로 바꿔서 날린다.
                // 인자로 e 를 꼭 넣도록 하자!  그래야 Caused by ~~ 가 떠서 에러 확인이 쉽다.
                throw new RuntimeSQLException(e);
            }
        }

        public void runSQL() throws SQLException {
            throw new SQLException("ex");
        }
    }


    static class RuntimeConnectException extends RuntimeException {
        public RuntimeConnectException(String message) {
            super(message);
        }
    }

    static class RuntimeSQLException extends  RuntimeException {
        public RuntimeSQLException() {
        }

        public RuntimeSQLException(Throwable cause) {
            super(cause);
        }
    }


}
