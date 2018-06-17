/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forma;

import Baza.Konekcija;
import Klase.Korisnik;
import REGISTRACIJA.Login;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author doppe_000
 */
public class Podesavanje {

    public static void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        VBox vbox = new VBox();
        root.setCenter(vbox);

        HBox hbox = new HBox();
        root.setBottom(hbox);

        Scene scene = new Scene(root, 1080, 600, Color.WHITE);
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);

        // DnevniUnosMerenja-menu bar za dnevnik i mogucnosti unosa
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

        Label jmbg = new Label("JMBG: ");
        TextField tfjmbg = new TextField();

        Label ime = new Label("Ime: ");
        TextField tfime = new TextField();

        Label prezime = new Label("Prezime: ");
        TextField tfprezime = new TextField();

        Label datumRodjenja = new Label("Datum: ");
        TextField tfdatumRodjenja = new TextField();

        Label lbo = new Label("LBO: ");
        TextField tflbo = new TextField();

        Label telefonKorisnika = new Label("Telefon: ");
        TextField tftelefon = new TextField();

        Label mobilniKorisnika = new Label("Mobilni: ");
        TextField tfmob = new TextField();

        Label visina = new Label("Visina: ");
        TextField tfvisina = new TextField();

        Label tezina = new Label("Tezina: ");
        TextField tftezina = new TextField();

        Label pol = new Label("Pol: ");
        final ComboBox polComboBox = new ComboBox();
        polComboBox.getItems().addAll(
                "zenski", "muski", "srednji"
        );

        Label napomena = new Label("Napomena: ");
        TextField tfnapomena = new TextField();

        Label mail = new Label("Mail: ");
        TextField tfmail = new TextField();

        Label lozinka = new Label("Loznika: ");
        TextField tflozinka = new TextField();

        Korisnik k = Login.korisnik;
        Button btn = new Button("Sacuvaj");
        Button btn2 = new Button("Otkazi");
        btn2.setOnAction((ActionEvent t) -> {
        new Main().start(primaryStage);
        });

        btn.setOnAction((ActionEvent t) -> {

            k.setJmbg(tfjmbg.getText());
            k.setIme(tfime.getText());
            k.setPrezime(tfprezime.getText());
            k.setLbo(tflbo.getText());
            k.setTel(tftelefon.getText());
            k.setMobil(tfmob.getText());
            k.setTezina(Double.parseDouble(tftezina.getText()));
            k.setVisina(Double.parseDouble(tfvisina.getText()));
            k.setPol(polComboBox.getValue().toString());
            k.setNapomena(tfnapomena.getText());
            k.setMail(tfmail.getText());
            k.setLozinka(tflozinka.getText());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {
                k.setDatumRodjenja(LocalDate.parse(tfdatumRodjenja.getText(), formatter));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Nije ispravno unet datum i vreme!").showAndWait();
                return;
            }

            int rez = Konekcija.izmeniKorisnika(k);

            if (rez == 0) {
                JOptionPane.showMessageDialog(null, "Sistem ne moze da izmeni korisnika!");
            } else {
                JOptionPane.showMessageDialog(null, "Sistem je izmenio korisnika!");
            }
        });

        // popunjavanje polja postojecim podacima
        tfjmbg.setText(k.getJmbg());
        tfime.setText(k.getIme());
        tfprezime.setText(k.getPrezime());
        try {
            tfdatumRodjenja.setText(k.getDatumRodjenja().toString());
        } catch (Exception e) {
        }
        tflbo.setText(k.getLbo());
        tftelefon.setText(k.getTel());
        tfmob.setText(k.getMobil());
        tfvisina.setText(k.getVisina().toString());
        tftezina.setText(k.getTezina().toString());
        polComboBox.setValue(k.getPol());
        tfnapomena.setText(k.getNapomena());
        tfmail.setText(k.getMail());
        tflozinka.setText(k.getLozinka());

        vbox.getChildren().addAll(jmbg, tfjmbg, ime, tfime, prezime, tfprezime, datumRodjenja, tfdatumRodjenja, lbo, tflbo, telefonKorisnika, tftelefon, mobilniKorisnika, tfmob, visina, tfvisina, tezina, tftezina, pol, polComboBox, napomena, tfnapomena, mail, tfmail, lozinka, tflozinka);
        hbox.getChildren().addAll(btn, btn2);

        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

}
