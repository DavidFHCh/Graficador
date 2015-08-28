package proyecto1.graficador.test;

import proyecto1.graficador.Fichero;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

/**
 * Clase para pruebas unitarias de la clase Fichero.java
 */
public class TestFichero{

	private Fichero as; 

	/**
	* Crea un objeto de tipo Fichero para las pruebas.
	*/
	public TestFichero(){
		as = new Fichero("52*x^2+8*(x+10)");
	}

	@Test public void testhazFichas(){
		LinkedList<String> l1, l2;
		l1 = new LinkedList<String>(); // Lista con la que compararemos.
		l1.add("52");
		l1.add("*");				
		l1.add("x");
		l1.add("^");
		l1.add("2");
		l1.add("+");
		l1.add("8");
		l1.add("*");
		l1.add("(");
		l1.add("x");			
		l1.add("+");
		l1.add("10");
		l1.add(")");
		l2 = as.hazFichas();
		Assert.assertFalse(l2 == null);
		Assert.assertTrue(l1.size() == l2.size());
	}
}