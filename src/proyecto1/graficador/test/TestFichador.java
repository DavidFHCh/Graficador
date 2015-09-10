package proyecto1.graficador.test;

import proyecto1.graficador.Fichador;
import proyecto1.graficador.Ficha;
import proyecto1.graficador.ExcepcionCadenaInvalida;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

/**
 * Clase para pruebas unitarias de la clase Fichador.java
 */
public class TestFichador{

	private Fichador as, as1, as3; 

	/**
	* Crea un objeto de tipo Fichador para las pruebas.
	*/
	public TestFichador(){
		as = new Fichador();
		as1 = new Fichador();
		as3 = new Fichador();
	}

	/**
     * Prueba unitaria para {@link Fichador#getFichas}.
     */
	@Test public void testgetFichas(){
		as3.hazFichas("x^2+89+x");
		Assert.assertTrue(as3.getFichas() != null);
		Assert.assertTrue(as3.getFichas().size() == 7);
	}

	/**
     * Prueba unitaria para {@link Fichador#hazFichas}.
     */
	@Test public void testhazFichas(){
		LinkedList<Ficha> l1, l2, l3;
		l1 = new LinkedList<Ficha>(); // Lista con la que compararemos.
		l2 = null;
		l3 = null;
		l1.add(new Ficha(7,"52"));
		l1.add(new Ficha(5,"*"));				
		l1.add(new Ficha(8,"x"));
		l1.add(new Ficha(6,"^"));
		l1.add(new Ficha(7,"4"));
		l1.add(new Ficha(4,"+"));
		l1.add(new Ficha(7,"8"));
		l1.add(new Ficha(5,"*"));
		l1.add(new Ficha(2,"("));
		l1.add(new Ficha(8,"x"));			
		l1.add(new Ficha(4,"+"));
		l1.add(new Ficha(7,"10"));
		l1.add(new Ficha(3,")"));
		l1.add(new Ficha(4,"+"));
		l1.add(new Ficha(1,"sin"));
		l1.add(new Ficha(2,"("));
		l1.add(new Ficha(8,"x"));
		l1.add(new Ficha(4,"+"));
		l1.add(new Ficha(7,"2"));
		l1.add(new Ficha(3,")"));		
		try{
			as1.hazFichas("p(x");
			l3 = as1.getFichas(); 
			Assert.fail();
		}catch(ExcepcionCadenaInvalida eci){}
		try{
			as.hazFichas("52*x^4+8*(x+10)+sin(x+2)");
			l2 = as.getFichas();
		} catch(ExcepcionCadenaInvalida eci){
			System.err.println(eci);
			Assert.fail();
		}	
		Assert.assertFalse(l2 == null);
		for(Ficha f : l1){
		}
		for(Ficha f : l2){
		}	
		Assert.assertTrue(l1.size() == l2.size());
		Assert.assertTrue(l1.equals(l2));
	}
}
