package proyecto1.graficador;

import java.util.*;

/**
* Clase que genera las Graficas en SVG.
*/
public class GraficaSVG{

	private LinkedList<Double> valoresY;
	private double x1;
	private double x2;
	private double y1;
	private double y2;
	private int ancho;
	private int alto;
	private double proporcionX;
    private double proporcionY;
	private final static String[] colores = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

    /**
    * Metodo Constructor para generar la grafica SVG.
    *@param ancho - ancho del canvas.
    *@param alto - alto del canvas.
    *@param proporcionX - La proporcion de pixeles por unidad.
    *@param proporcionY - La proporcion de pixeles por unidad.
    *@param x1 - Valores para los intervalos.
    *@param x2 - Valores para los intervalos.
    *@param y1 - Valores para los intervalos.
    *@param y2 - Valores para los intervalos.
    *@param valoresY - Lista de resultados de la evaluacion.
    */
	public GraficaSVG(int ancho, int alto,double proporcionX ,double proporcionY,double x1, double x2,double y1 , double y2, LinkedList<Double> valoresY){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.valoresY = valoresY;
		this.proporcionX = proporcionX;
        this.proporcionY = proporcionY;
		this.ancho = ancho;
		this.alto = alto;
	}

    private String path(double x1, double x2){
        return "M " + x1 + " " + x2 + " l 0 0 ";
    }

    private String pathEje(double x1, double y1, double x2, double y2){
        return "M " + x1 + " " + y1 + " l " + x2 + " " + y2 + " ";
    }

	private String linea(double x1, double y1, double x2, double y2, String color){
		return "<line x1='" + x1 + "' y1='" + y1 + "' x2='" + x2 + "' y2='" + y2 + "' stroke='" + color + "' stroke-width='3' />";
    }

    private String eje(int x1,int y1,int x2,int y2){
    	return "<line x1='" + x1 + "' y1='" + y1 + "' x2='" + x2 + "' y2='" + y2 + "' stroke='black' stroke-width='2' />";
    }

    private String dimensiones(int alto, int ancho){
    	return "<svg width='" + ancho + "' height='" + alto + "'>";
    }

    private String cierraSvg(){
    	return "</svg>";
    }

    private int coordenadaX(double x){
    	x = x - x1;
    	return (int) (x*proporcionX);
    }

    private int coordenadaY(double y){
    	y = y2 - y;
    	return (int) (y*proporcionY);
    }

    private double setProporcion(int altoAncho, double x1, double x2){
        double prop = altoAncho/(x2-x1);
        return prop;
    }

    public LinkedList<String> pintaGrafica(){
        LinkedList<String> salida = new LinkedList<String>();
    	salida.add(dimensiones(alto,ancho));
    	if(x1 < 0 && x2 > 0){//eje y
    		salida.add(eje(coordenadaX(0),0,coordenadaX(0),alto));
    	}
    	if(y1 < 0 && y2 > 0){//eje x
    		salida.add(eje(0,coordenadaY(0),ancho,coordenadaY(0)));
    	}
    	double valX = 0;
		String color = "#";
			for(int j = 0; j < 6;j++)
				color += colores[(int)(Math.random()*colores.length)];
            double anterior = 0;
		for(Double val: valoresY){
            if(val == Double.NaN || (val < 0 && anterior > 0) || (val > 0 && anterior < 0)){
                anterior = val;
                continue;
            }
    		salida.add(linea(valX-1,coordenadaY(anterior),valX,coordenadaY(val),color));
    		valX++;
            anterior = val;
		}
	    salida.add(cierraSvg());
        return salida;
    }

    public LinkedList<String> graficaCanvas(){
        LinkedList<String> salida1 = new LinkedList<String>();
        if(x1 < 0 && x2 > 0){
            salida1.add(pathEje(coordenadaX(0),0,0,alto));
        }
        if(y1 < 0 && y2 > 0){
            salida1.add(pathEje(0,coordenadaY(0),ancho,0));
        }
        double valX = 0;
        double anterior = 0;
        for(Double val1: valoresY){
            if(val1 == Double.NaN || (val1 < 0 && anterior > 0) || (val1 > 0 && anterior < 0)){
                anterior = val1;
                continue;
            }
            salida1.add(pathEje(valX,coordenadaY(val1),1,coordenadaY(val1)-coordenadaY(anterior)));
            valX++;
            anterior = val1;
        }
        return salida1;
    }
}