/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1.graficador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

/**
 *
 * @author davif
 */
public class GraficadorController implements Initializable {
    
    @FXML 
    private TextField x1text;

    @FXML
    private TextField x2text;
    @FXML 
    private TextField y1text;
    @FXML 
    private TextField y2text;
    @FXML 
    private TextField anchotext;
    @FXML 
    private TextField altotext;
    @FXML 
    private TextField funciontext;
    @FXML 
    private Canvas canvas;
    @FXML 
    private Button botonGraficar;
    @FXML 
    private Button botonSVG;
    
    
    
    @FXML
    private void handleBotonGraficar(ActionEvent event) {
        String x1 = x1text.getCharacters().toString();
        String x2 = x2text.getCharacters().toString();
        String y1 = y1text.getCharacters().toString();
        String y2 = y2text.getCharacters().toString();
        String alto = altotext.getCharacters().toString();
        String ancho = anchotext.getCharacters().toString();
        String funcion = funciontext.getCharacters().toString();
        System.out.println(alto); 
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
