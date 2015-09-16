package proyecto1.graficador;

public class Ficha{

	public int ficha;
	public String entrada;

	public Ficha(){
		ficha = 0;
		entrada = "";
	}

	/**
	* Constructor para generar fichas.
	* @param ficha - identificador, precedencia.
	* @param entrada - la ficha.
	*/
	public Ficha(int ficha, String	entrada){
		this.ficha = ficha;
		this.entrada = entrada;
	}

	/**
	* Metodo que cambia la precedencia de la ficha.
	* @param f - la nueva precendencia.
	*/
	public void setFicha(int f){
		this.ficha = f;
	}

	/**
	* Metodo que ve si dos fichas son iguales.
	* @param o - objeto con el que se comparara.
	*/
	@Override public boolean equals(Object o){
		if(o instanceof Ficha){
			Ficha f1 = (Ficha) o;
			if(this.ficha == f1.ficha && this.entrada.equals(f1.entrada))
				return true;
		}
		return false;
	}
} 