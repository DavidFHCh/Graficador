package proyecto1.graficador.test;

import proyecto1.graficador.Fichador;
import proyecto1.graficador.ExcepcionCadenaInvalida;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

/**
 * Clase para pruebas unitarias de la clase Fichador.java
 */
public class TestFichador{

	private Fichador as, as1; 

	/**
	* Crea un objeto de tipo Fichador para las pruebas.
	*/
	public TestFichador(){
		as = new Fichador("52*x^4+8*(x+10)+sin(x+2)+aksdbkvb");
		as1 = new Fichador("p?");
	}


	@Test public void testhazFichas(){
		LinkedList<String> l1, l2, l3;
		l1 = new LinkedList<String>(); // Lista con la que compararemos.
		l2 = new LinkedList<String>();
		l1.add("52");
		l1.add("*");				
		l1.add("x");
		l1.add("^");
		l1.add("4");
		l1.add("+");
		l1.add("8");
		l1.add("*");
		l1.add("(");
		l1.add("x");			
		l1.add("+");
		l1.add("10");
		l1.add(")");
		l1.add("+");
		l1.add("sin");
		l1.add("(");
		l1.add("x");
		l1.add("+");
		l1.add("2");
		l1.add(")");		
		try{
			l3 = as1.hazFichas();
			Assert.fail();
		}catch(ExcepcionCadenaInvalida eci){}
		try{
			l2 = as.hazFichas();
		} catch(ExcepcionCadenaInvalida eci){
			System.err.println(eci);
			Assert.fail();
		}	
		Assert.assertFalse(l2 == null);
		System.out.println(l1.size() + "  " + l1.toString());
		System.out.println(l2.size() + "  " + l2.toString());
		Assert.assertTrue(l1.size() == l2.size());
		Assert.assertTrue(l1.equals(l2));
	}
}
