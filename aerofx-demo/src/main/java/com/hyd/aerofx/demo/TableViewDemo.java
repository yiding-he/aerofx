package com.hyd.aerofx.demo;

import com.hyd.aerofx.AeroFX;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Date;
import java.util.stream.IntStream;

public class TableViewDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        AeroFX.style();
        TableView<Book> root = createTableView();

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Test TableView");
        primaryStage.show();
    }

    private TableView<Book> createTableView() {
        TableView<Book> tableView = new TableView<>();
        tableView.getColumns().addAll(
                createColumn("名称", "name"),
                createColumn("作者", "author"),
                createColumn("出版日期", "publishDate")
        );

        tableView.getItems().addAll(createBooks());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        return tableView;
    }

    private Book[] createBooks() {
        String publishDate = String.valueOf(new Date());

        return IntStream.range(1, 101)
                .mapToObj(i -> new Book("book" + i, "author " + i, publishDate))
                .toArray(Book[]::new);
    }

    private TableColumn<Book, String> createColumn(String text, String property) {
        TableColumn<Book, String> column = new TableColumn<>(text);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        return column;
    }
}
