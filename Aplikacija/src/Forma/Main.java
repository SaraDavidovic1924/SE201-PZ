/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forma;

import REGISTRACIJA.Login;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

 public  void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
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

        korisnik.getItems().addAll( logout, podesavanja);

        menuBar.getMenus().addAll(dnevnik, hba1c, korisnik);

        
        ImageView img1 = new ImageView();
        Image image1 = new Image("file:img/krug.png");        
        
        
        img1.setImage(image1);
        root.setCenter(img1);
        
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

}
