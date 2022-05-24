package hello.core;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;

@SpringBootApplication
public class CoreApplication implements CommandLineRunner {
    public static void main(String[] args) {

//        SpringApplication.run(CoreApplication.class, args);


        new SpringApplicationBuilder(CoreApplication.class).headless(false).run(args);
    }


    @Override
    public void run(String... args) throws Exception {


        JFrame frame = new JFrame("Spring Boot Swing Web App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024,768);

        JFXPanel panel =new JFXPanel();
        panel.setLayout(null);


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                WebEx1.initAndLoadWebView(panel);
            }
        });

        frame.setContentPane(panel);
        frame.setVisible(true);

    }



}
