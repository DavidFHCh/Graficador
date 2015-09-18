/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1.graficador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase Principal.
 * @author davif
 */
public class Graficador extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Vista.fxml"));
        
        Scene scene = new Scene(root);
        

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args de la la linea de comandos.
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
