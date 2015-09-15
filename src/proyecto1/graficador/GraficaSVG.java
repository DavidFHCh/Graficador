package proyecto1.graficador;

import java.util.*;

public class GraficaSVG{

	private LinkedList<String> salida;
	private LinkedList<LinkedList<Double>> valoresY;
	private double x1;
	private double x2;
	private double y1;
	private double y2;
	private int ancho;
	private int alto;
	private int proporcion;
	private final static String[] colores = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

	public GraficaSVG(int ancho, int alto,int proporcion ,double x1, double x2,double y1 , double y2, LinkedList<LinkedList<Double>> valoresY){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.valoresY = valoresY;
		this.proporcion = proporcion;
		this.ancho = ancho;
		this.alto = alto;
		salida = new LinkedList<String>();
	}

	public LinkedList<String> getSalida(){
		return salida;
	}

	private String linea(double x1, double y1,String color){
		return "<line x1='" + x1 + "' y1='" + y1 + "' x2='" + x1 + "' y2='" + y1 + "' stroke='" + color + "' stroke-width='3' />";
    }

    private String eje(int x1,int y1,int x2,int y2){
    	return "<line x1='" + x1 + "' y1='" + y1 + "' x2='" + x2 + "' y2='" + y2 + "' stroke='black' stroke-width='2' />";
    }

    private String dimensiones(int alto, int ancho){
    	return "<svg width='" + ancho + "' height='" + 400 + "'>";
    }

    private String cierraSvg(){
    	return "</svg>";
    }

    private int coordenadaX(double x){
    	x = x - x1;
    	return (int) (x*proporcion);
    }

    private int coordenadaY(double y){
    	y = y2 - y;
    	return (int) (y*proporcion);
    }

    private void pintaGrafica(){
    	salida.add(dimensiones(alto,ancho));
        System.out.println("entro?");
    	if(x1 < 0 && x2 > 0){
    		salida.add(eje(coordenadaX(0),0,coordenadaX(0),alto));
    	}
    	if(y1 < 0 && y2 > 0){
    		salida.add(eje(0,coordenadaY(0),ancho,coordenadaY(0)));
    	}
    	for(LinkedList<Double> l :valoresY){
    		double valX = 0;
			String color = "#";
				for(int j = 0; j < 6;j++)
					color += colores[(int)(Math.random()*colores.length)];
    		for(Double val: l){
    			salida.add(linea(valX,coordenadaY(val),color));
    			valX++;
    		}
    	}
    	salida.add(cierraSvg());
    }
}