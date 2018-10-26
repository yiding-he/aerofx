/*
 * Copyright (c) 2014, Matthias Meidinger
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */

package com.hyd.aerofx.demo;

import com.hyd.aerofx.AeroFX;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class SystemSettingsDemo extends Application {

    /**
     * @param primaryStage The primary Stage
     *
     * @throws Exception ...
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SystemSettingsDemo.fxml"));
        loader.setController(new SystemSettingsController());

        Parent root = loader.load();
        primaryStage.setTitle("系统属性 (JavaFX)");
        primaryStage.setResizable(false);
        Scene myScene = new Scene(root, 402, 446);

        AeroFX.style();
        AeroFX.styleAllAsGroupBox(root);

        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public static void main(String... args) {
        launch(args);
    }

    public static class SystemSettingsController {

        @FXML
        TableView<DriverItem> tblDrivers;

        @FXML
        TableColumn<DriverItem, String> colDriverName;

        @FXML
        TableColumn<DriverItem, String> colProtection;

        public void initialize() {
            colDriverName.setCellValueFactory(f -> f.getValue().driverName);
            colProtection.setCellValueFactory(f -> f.getValue().protection);

            tblDrivers.getItems().addAll(
                    new DriverItem("本地磁盘(C:)", "启用"),
                    new DriverItem("本地磁盘(D:)", "启用"),
                    new DriverItem("本地磁盘(E:)", "启用"),
                    new DriverItem("本地磁盘(F:)", "启用"),
                    new DriverItem("本地磁盘(G:)", "启用"),
                    new DriverItem("本地磁盘(H:)", "启用"),
                    new DriverItem("本地磁盘(I:)", "启用"),
                    new DriverItem("本地磁盘(J:)", "启用")
            );
        }
    }

    public static class DriverItem {

        public StringProperty driverName = new SimpleStringProperty();

        public StringProperty protection = new SimpleStringProperty();

        public DriverItem(String driverName, String protection) {
            this.driverName.setValue(driverName);
            this.protection.setValue(protection);
        }
    }
}
