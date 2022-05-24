package hello.core;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;

public class WebEx1 extends JFrame {

    public static void initAndLoadWebView(final JFXPanel fxPanel) {
        Group group = new Group();
        Scene scene = new Scene(group);
        fxPanel.setScene(scene);

        WebView webView = new WebView();

        group.getChildren().add(webView);
        webView.setMinSize(500, 500);
        webView.setMaxSize(800, 600);

        WebEngine webEngine = webView.getEngine();

        webEngine.load("http://www.naver.com");

    }

}
