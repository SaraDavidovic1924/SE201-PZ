/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REGISTRACIJA;

import Baza.Konekcija;
import Forma.Main;
import Klase.Korisnik;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 *
 * @author doppe_000
 */
public class Login extends Application {
    // Pocetni prozor sa login i register formama

    public static Korisnik korisnik;


    @Override
    public void start(Stage primaryStage) {
      

        BorderPane bp = new BorderPane();

        VBox vb2 = new VBox();
        VBox vbE = new VBox();
        VBox vbX = new VBox();
        VBox vbY = new VBox();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label username = new Label("Mail : ");
        gridPane.add(username, 0, 1);

        TextField tf1 = new TextField();
        gridPane.add(tf1, 1, 1);

        Label password = new Label("Password: ");
        gridPane.add(password, 0, 2);

        PasswordField tf2 = new PasswordField();
        gridPane.add(tf2, 1, 2);

        Button btn = new Button("Login now");
        vbX.getChildren().add(btn);
        vbX.setAlignment(Pos.CENTER);
        vbX.setPadding(new Insets(0, 0, 30, 0));

        Button btn2 = new Button("Register");
        vbY.getChildren().add(btn2);
        vbY.setAlignment(Pos.CENTER);

        btn.setStyle("-fx-font: 16 arial; -fx-base: #b6e7c9;");
        btn2.setStyle("-fx-font: 14 arial; -fx-base: #b6e2a5;");

        vbE.getChildren().addAll(vbX, vbY);
        vbE.setAlignment(Pos.CENTER);
        vbE.setPadding(new Insets(-250, 0, 0, 0));

        bp.setTop(vb2);
        bp.setCenter(gridPane);
        bp.setBottom(vbE);

        Scene scene = new Scene(bp, 1080, 600);

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        if(Login.korisnik != null){
            tf1.setText(Login.korisnik.getMail());
            tf2.setText(Login.korisnik.getLozinka());
        }
        btn.setOnAction(e -> {
            
            try {
                // Pritisak na dugme register prebacuje na novi prozor
                login(primaryStage, tf1.getText(), tf2.getText());
                 
            } catch (ParseException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn2.setOnAction((ActionEvent t) -> {
            Registracija.start(primaryStage);
            
        });

    }

    public static void login(Stage primaryStage, String email, String pass) throws ParseException {

        String query = "Select * from Korisnik where mail='" + email + "' and lozinka='" + pass + "'";
        korisnik = Konekcija.Login(query);

        if (korisnik == null) {
            JOptionPane.showMessageDialog(null, "Pogresno ste uneli podatke!");
        } else {
            

            Main m = new Main();
            m.start(primaryStage);
        }
    }
    
  

}
