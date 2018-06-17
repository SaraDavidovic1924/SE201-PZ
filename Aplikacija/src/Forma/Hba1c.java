/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forma;

import Baza.Konekcija;
import REGISTRACIJA.Login;
import java.text.DecimalFormat;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//test klasa proverava se u JUnit klasi
public class Hba1c {

    public static void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

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
            DnevniUnosMerenja.start(primaryStage);
        });

        mesecniDnevnik.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        mesecniDnevnik.setOnAction((ActionEvent t) -> {

        });

        dnevnik.getItems().addAll(dnevniUnos, mesecniDnevnik
        );

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
DecimalFormat df = new DecimalFormat("#.##"); 

        double hb1c =Math.rint(Konekcija.vratiHB1C(Login.korisnik));
        Label labela = new Label(Double.valueOf(df.format(hb1c)) + "");
        labela.setFont(new Font("Cambria", 232));
        labela.setTextFill(Color.web("#0076a3"));

        labela.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                labela.setScaleX(1.5);
                labela.setScaleY(1.5);
            }
        });

        labela.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                labela.setScaleX(1);
                labela.setScaleY(1);
            }
        });

       
        root.setCenter(labela);
        Scene scene = new Scene(root, 1080, 600, Color.WHITE);

        primaryStage.setTitle("HB1C");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
