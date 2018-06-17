/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forma;

import Baza.Konekcija;
import Klase.IstorijaMerenja;
import REGISTRACIJA.Login;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author doppe_000
 */
public class Dnevnik {

    public static void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        ListView<IstorijaMerenja> listView = new ListView<IstorijaMerenja>();

        VBox mainBox = new VBox();
        root.setCenter(mainBox);

        Scene scene = new Scene(root, 1080, 600, Color.WHITE);
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);

        VBox vbox = new VBox();
        mainBox.getChildren().add(vbox);

        HBox hbox = new HBox();
        mainBox.getChildren().add(hbox);

        Menu dnevnik = new Menu("Dnevnik");

        MenuItem dnevniUnos = new MenuItem("DnevniUnos");

        dnevniUnos.setOnAction(actionEvent -> {
            DnevniUnosMerenja.start(primaryStage);
        });
        MenuItem mesecniDnevnik = new MenuItem("MesecniDnevnik");
        dnevnik.setOnAction(actionEvent -> {
            Dnevnik.start(primaryStage);
        });
        MenuItem glavnaStr = new MenuItem("Pocetna strana");
        glavnaStr.setOnAction(actionEvent -> {
            new Main().start(primaryStage);
        });

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        dnevnik.getItems().addAll(dnevniUnos, mesecniDnevnik, glavnaStr);

        Menu hba1c = new Menu("HBA1C");
        hba1c.setOnAction(actionEvent -> {
            Hba1c.start(primaryStage);
        });

        CheckMenuItem htmlMenuItem = new CheckMenuItem("ChatPie");
        htmlMenuItem.setSelected(true);
        hba1c.getItems().add(htmlMenuItem);

        CheckMenuItem cssMenuItem = new CheckMenuItem("Hba1c");
        cssMenuItem.setSelected(true);
        hba1c.getItems().add(cssMenuItem);

        Menu korisnik = new Menu("Korisnik");

        MenuItem logout = new MenuItem("Logout");

         logout.setOnAction(actionEvent -> {
            Login.korisnik = null;
            new Login().start(primaryStage);
        });

        MenuItem podesavanja = new MenuItem("Podesavanja");
        podesavanja.setOnAction((ActionEvent t) -> {
            Podesavanje.start(primaryStage);

        });

        korisnik.getItems().addAll(logout, podesavanja);

        menuBar.getMenus().addAll(dnevnik, hba1c, korisnik);

        Label datumOD = new Label("DatumOD: ");
        TextField tfDatumOD = new TextField();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        tfDatumOD.setText(LocalDateTime.now().minusMonths(1).format(dtf));

        Label datumDO = new Label("DatumDO: ");
        TextField tfDatumDO = new TextField();
        tfDatumDO.setText(LocalDateTime.now().format(dtf));

        Button btn = new Button("Prikazi");
        btn.setOnAction((ActionEvent t) -> {
            try {
                prikaziDnevnik(tfDatumOD, tfDatumDO, listView);
            } catch (ParseException ex) {

            }

        });

        vbox.getChildren().addAll(datumOD, tfDatumOD, datumDO, tfDatumDO);
        hbox.getChildren().addAll(btn);

        HBox hbox1 = new HBox(listView);
        mainBox.getChildren().add(hbox1);

        listView.setPrefWidth(1000000);
        listView.setPrefHeight(700000);

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(true);
        primaryStage.show();
         try {
                prikaziDnevnik(tfDatumOD, tfDatumDO, listView);
            } catch (ParseException ex) {

            }

    }

    static void prikaziDnevnik(TextField tfDatumOD, TextField tfDatumDO, ListView listView) throws ParseException {
        listView.getItems().clear();
        LocalDateTime datumOD = LocalDateTime.now().minusMonths(1);
        LocalDateTime datumDO = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        try {
            datumOD = LocalDateTime.parse(tfDatumOD.getText() + " 00:00", formatter);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Nije ispravno unet datum i vreme OD!").showAndWait();
            return;
        }

        try {
            datumDO = LocalDateTime.parse(tfDatumDO.getText() + " 23:59", formatter);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Nije ispravno unet datum i vreme DO!").showAndWait();
            return;
        }

        List<IstorijaMerenja> lista = Konekcija.PrikaziDnevnik(Login.korisnik, datumOD, datumDO);

        if (lista.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Ne postoje podaci za unete parametre!").showAndWait();
            return;
        }

        listView.getItems().addAll(lista);
        
    }

    {

    }

}
