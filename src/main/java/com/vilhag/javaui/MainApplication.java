package com.vilhag.javaui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    public static boolean isDarkTheme = false;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader calcLoader = new FXMLLoader(MainApplication.class.getResource("calc.fxml"));
        System.out.println(getParameters().getRaw());
        for (String param : getParameters().getRaw()) {
            if (param.equals("dark")) {
                isDarkTheme = true;
                break;
            }
        }
        Scene scene = new Scene(calcLoader.load(), 400, 620);
        scene.getStylesheets().add(Objects.requireNonNull(MainApplication.class.getResource(isDarkTheme ? "calcDark.css" : "calc.css")).toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.setResizable(false);
        stage.show();
    }
}
