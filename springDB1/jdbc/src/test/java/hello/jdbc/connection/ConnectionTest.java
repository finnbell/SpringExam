package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        log.info("connection={},  class={}", con1, con1.getClass());
        log.info("connection={},  class={}", con2, con2.getClass());
    }

    @Test
    void dataSourceDriverManager() throws SQLException {
        //DriverManagerDataSource -항상 새로운 커넥션을 획득
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);
    }


    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        //커넥션 풀링
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");

        useDataSource(dataSource);
        Thread.sleep(1000);
    }

    private void useDataSource(DataSource datasoure) throws SQLException {
        Connection con1 = datasoure.getConnection();
        Connection con2 = datasoure.getConnection();
        Connection con3 = datasoure.getConnection();
        Connection con4 = datasoure.getConnection();
        Connection con5 = datasoure.getConnection();
        Connection con6 = datasoure.getConnection();
        Connection con7 = datasoure.getConnection();
        Connection con8 = datasoure.getConnection();
        Connection con9 = datasoure.getConnection();
        Connection con10 = datasoure.getConnection();
        Connection con11 = datasoure.getConnection();
        log.info("connection={},  class={}", con1, con1.getClass());
        log.info("connection={},  class={}", con2, con2.getClass());
    }

}
