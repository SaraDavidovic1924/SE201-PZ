/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forma;

import Baza.Konekcija;
import Klase.IstorijaMerenja;
import REGISTRACIJA.Login;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author doppe_000
 */
public class DnevniUnosMerenja {

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

        Label tipUnosa = new Label("Tip unosa: ");
        final ComboBox tipUnosaComboBox = new ComboBox();
        tipUnosaComboBox.getItems().addAll(
                "Glikemija", "Insulin"
        );

        Label tipInsulina = new Label("Vrsta insulina: ");
        final ComboBox tipComboBox = new ComboBox();
        tipComboBox.getItems().addAll(
                "brzo delujuci", "sporo delujuci"
        );

        tipUnosaComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                String s = tipUnosaComboBox.getValue().toString();
                if (s.equals("Glikemija")) {
                    tipInsulina.setVisible(false);
                    tipComboBox.setVisible(false);

                } else {
                    tipInsulina.setVisible(true);
                    tipComboBox.setVisible(true);
                }
            }

        });

        Label datum = new Label("Datum: ");
        TextField tf1 = new TextField();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        tf1.setText(LocalDateTime.now().format(dtf));

        Label vreme = new Label("Vreme: ");
        TextField tf2 = new TextField();
        dtf = DateTimeFormatter.ofPattern("HH:mm");
        tf2.setText(LocalDateTime.now().format(dtf));

        Label glikemija = new Label("Vrednost merenja: ");
        TextField tf3 = new TextField();

        Button btn = new Button("Dodaj stavku");
        Button btn2 = new Button("Obrisi stavku");
        Button btn3 = new Button("Sacuvaj dnevni unos");
        btn.setOnAction((ActionEvent t) -> {
            UnosMerenjaToList(listView, tf1, tf2, tf3, tipComboBox, tipUnosaComboBox);

        });
        btn2.setOnAction((ActionEvent t) -> {
            BrisanjeMerenjaFromList(listView);

        });
        btn3.setOnAction((ActionEvent t) -> {
            SacuvajListu(listView);

        });

        vbox.getChildren().addAll(tipUnosa, tipUnosaComboBox, datum, tf1, vreme, tf2, glikemija, tf3, tipInsulina, tipComboBox);
        hbox.getChildren().addAll(btn, btn2);

        HBox hbox1 = new HBox(listView);
        mainBox.getChildren().add(hbox1);
        mainBox.getChildren().add(btn3);

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setResizable(true);
        primaryStage.show();

    }

    static void UnosMerenjaToList(ListView<IstorijaMerenja> listaUnosa, TextField tfDatum, TextField tfVreme, TextField tfGlikemija, ComboBox cmbTipIns, ComboBox cmbtipUnosa) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        LocalDateTime datum;
        double glikemija;
        String tipUnosa = "";
        String tipIns = "";
        try {
            datum = LocalDateTime.parse(tfDatum.getText() + " " + tfVreme.getText(), formatter);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Nije ispravno unet datum i vreme!").showAndWait();
            return;
        }

        try {
            glikemija = Double.parseDouble(tfGlikemija.getText());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Nije ispravno uneta glikemija!").showAndWait();
            return;
        }

        try {
            tipUnosa = cmbtipUnosa.getValue().toString();
            tipIns = cmbTipIns.getValue().toString();

        } catch (Exception e) {
        }

        IstorijaMerenja unos = new IstorijaMerenja(0, Login.korisnik, datum, glikemija, tipUnosa, tipIns);

        listaUnosa.getItems().add(unos);
    }

    static void BrisanjeMerenjaFromList(ListView<IstorijaMerenja> listaUnosa) {
        listaUnosa.getItems().remove(listaUnosa.getSelectionModel().getSelectedItem());
    }

    static void SacuvajListu(ListView<IstorijaMerenja> listaUnosa) {
        List<IstorijaMerenja> lista = listaUnosa.getItems();

        int rez = Konekcija.sacuvajIstorijuMerenja(lista);

        if (rez == 0) {
            new Alert(Alert.AlertType.ERROR, "Nije sacuvamo!").showAndWait();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Sacuvano!").showAndWait();
            listaUnosa.getItems().clear();
        }
    }
}
