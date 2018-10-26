package com.hyd.aerofx.demo;

import com.hyd.aerofx.AeroFX;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ScrollPaneDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AeroFX.style();

        BorderPane content = new BorderPane(new Button("BUTTON"));
        content.setPadding(new Insets(30));

        TitledPane titledPane = new TitledPane("TITLE", content);
        Pane pane = new Pane(titledPane);
        pane.setPrefSize(300, 200);

        BorderPane borderPane = new BorderPane(pane);
        borderPane.setPrefWidth(1000);
        borderPane.setPrefHeight(800);
        borderPane.setPadding(new Insets(100));

        AeroFX.styleAllAsGroupBox(borderPane);

        ScrollPane scrollPane = new ScrollPane(borderPane);
        scrollPane.setPannable(true);
        primaryStage.setScene(new Scene(scrollPane, 400, 300));
        primaryStage.show();
    }
}
