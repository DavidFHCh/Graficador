package proyecto1.graficador;

import java.util.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author davif
 */
public class VistaController implements Initializable {
    
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
    private TextArea errorField;
    private final static String[] colores = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    
    @FXML
    private void handleBotonGraficar(ActionEvent event) {
        printTextField("");
        String x1T = x1text.getCharacters().toString();
        String x2T = x2text.getCharacters().toString();
        String y1T = y1text.getCharacters().toString();
        String y2T = y2text.getCharacters().toString();
        String altoT = altotext.getCharacters().toString();
        String anchoT = anchotext.getCharacters().toString();
        String funcion = funciontext.getCharacters().toString();
        int ancho = 0,alto = 0;
        double x1 = 0,x2 = 0,y1 = 0,y2 = 0;
        try{
            ancho = Integer.parseInt(anchoT);
            alto = Integer.parseInt(altoT);
            x1 = Double.valueOf(x1T);
            x2 = Double.valueOf(x2T);
            y1 = Double.valueOf(y1T);
            y2 = Double.valueOf(y2T);
            canvas.setHeight(alto);
            canvas.setWidth(ancho);
            System.out.println(x1+"   "+x2+"  "+y1+"  "+y2);
            Modelo mod = new Modelo(x1,y1,x2,y2,ancho,alto,funcion);
            mod.evalua();
            GraphicsContext gc = canvas.getGraphicsContext2D();
            draw(gc,mod);
        }catch(ExcepcionCadenaInvalida eci){
            printTextField("Checa la funcion, esta mal escrita.\n Recuerda debe ser de la forma f(x)=x.");
        }
        catch(ExcepcionOperacionNoSoportada eons){
            printTextField("La operacion que esta tratando de Realizar no esta soportada.");
        }
        catch(ExcepcionIntervalosInvalidos eii){
            printTextField("Los intervalos son incorrectos, intente con intervalos nuevos. \n Recuerde que x1 y y1 no pueden ser mayores a x2 y y2, respectivamente.");
        }
        catch(NumberFormatException nfe){
            printTextField("Caracteres no validos en los intervalos.");
        }
        catch(RuntimeException re){
            printTextField("Alguno de los campos esta vacio.");
        }
    }

    private void printTextField(String text) {
        errorField.setText(text);
    } 

    private void draw(GraphicsContext gc, Modelo mod){
        LinkedList<String> l = mod.aCanvas();
        gc.setStroke(randomColor());
        for(String s: l){
            gc.appendSVGPath(s);
            //System.out.println(s);
        }
        gc.stroke();
    } 

    private Color randomColor(){
        String color = "#";
        for(int j = 0; j < 6;j++)
            color += colores[(int)(Math.random()*colores.length)];
        return Color.valueOf(color);
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            errorField.setEditable(false);
            canvas.getGraphicsContext2D().setFill(Color.WHITE);
    }    
    
}
