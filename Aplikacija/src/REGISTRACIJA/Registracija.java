/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REGISTRACIJA;

import Baza.Konekcija;
import Klase.Korisnik;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author doope
 */
public class Registracija {

    public static void start(Stage primaryStage) {

        BorderPane bp = new BorderPane();

        HBox hb = new HBox();
        VBox vb = new VBox();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(100, 25, 40, 25));

        Label ime = new Label("Ime: ");
        gridPane.add(ime, 0, 1);

        TextField tfIme = new TextField();
        gridPane.add(tfIme, 1, 1);

        Label prezime = new Label("Prezime: ");
        gridPane.add(prezime, 0, 2);

        TextField tfPrezime = new TextField();
        gridPane.add(tfPrezime, 1, 2);

        Label email = new Label("E-mail: ");
        gridPane.add(email, 0, 3);

        TextField tfMail = new TextField();
        gridPane.add(tfMail, 1, 3);

        Label password = new Label("Password: ");
        gridPane.add(password, 0, 4);

        PasswordField tfPass = new PasswordField();
        gridPane.add(tfPass, 1, 4);

        Button btn = new Button("Register now");
        hb.getChildren().add(btn);

        Button btn2 = new Button("Login");
//        btn2.setOnAction((event) -> {
//            Login.
//        })
        vb.getChildren().add(btn2);

//        btn2.setOnAction((ActionEvent t) -> {
//            Login.
//        });
        hb.setPadding(new Insets(0, 0, 0, 150));
        vb.setPadding(new Insets(0, 0, 80, 180));

        btn.setStyle("-fx-font: 16 arial; -fx-base: #b6e7c9;");
        btn2.setStyle("-fx-font: 14 arial; -fx-base: #b6e2a5;");

        bp.setTop(gridPane);
        bp.setCenter(hb);
        bp.setBottom(vb);

        btn.setOnAction((ActionEvent e) -> {
            //Validacija unetih polja korisnika pri registraciji na sistem
            if (tfIme.getText().isEmpty() || tfPrezime.getText().isEmpty() || tfMail.getText().isEmpty() || tfPass.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Molimo unesite sva polja!");
            } else {
                Korisnik k = new Korisnik();
                k.setIme(tfIme.getText());
                k.setPrezime(tfPrezime.getText());
                k.setMail(tfMail.getText());
                k.setLozinka(tfPass.getText());

                int rez = Konekcija.sacuvajKorisnika(k);

                if (rez == 0) {
                    JOptionPane.showMessageDialog(null, "Sistem ne moze da registruje korisnika!");
                } else {
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio korisnika!");
                    Login.korisnik=k;
                    new Login().start(primaryStage);
                    
                }

            }

        });

        Scene scene = new Scene(bp, 1080, 600, Color.WHITE);

        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

    }
}
