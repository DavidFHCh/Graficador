package proyecto1.graficador;

public class Ficha{

	public int ficha;
	public final String entrada;

	public Ficha(){
		ficha = 0;
		entrada = "";
	}

	/**
	* Constructor para generar fichas.
	*/
	public Ficha(int ficha, String	entrada){
		this.ficha = ficha;
		this.entrada = entrada;
	}

	public void setFicha(int f){
		this.ficha = f;
	}

	@Override public boolean equals(Object o){
		if(o instanceof Ficha){
			Ficha f1 = (Ficha) o;
			if(this.ficha == f1.ficha && this.entrada.equals(f1.entrada))
				return true;
		}
		return false;
	}
} 