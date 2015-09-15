package proyecto1.graficador.test;

import proyecto1.graficador.Analizador;
import proyecto1.graficador.Fichador;
import proyecto1.graficador.Ficha;
import proyecto1.graficador.ExcepcionCadenaInvalida;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

public class TestAnalizador{

	private Analizador a1;
	private Fichador f1,f2,f3;

	public TestAnalizador(){
		a1 = new Analizador();
		f1 = new Fichador();
		f2 = new Fichador();
		f3 = new Fichador();
		f3.hazFichas("-(5+2+2)");
		f2.hazFichas("4+3*(5+1");
		f1.hazFichas("3+x*-sin(2-1)^(x+2)");
	}

	@Test public void testgetSalida(){
		LinkedList<Ficha> l = a1.analizar(f1.getFichas());;
		Assert.assertTrue(l != null);
		//Assert.assertTrue(l.size() == 19);
	}

	@Test public void testaAnalizar(){
		LinkedList<Ficha> l2 = f2.getFichas() ,l1 = f1.getFichas();
		try{
			a1.analizar(l2);
			Assert.fail();
		}catch(ExcepcionCadenaInvalida eci){}
		a1.setSalida(a1.analizar(l1));
		l1 = a1.getSalida();
		l2.clear();
		l2.add(new Ficha(7,"3"));
		l2.add(new Ficha(8,"x"));
		l2.add(new Ficha(7,"2"));
		l2.add(new Ficha(7, "1"));
		l2.add(new Ficha(4,"-"));
		l2.add(new Ficha(1,"sin"));
		l2.add(new Ficha(0,"-"));
		l2.add(new Ficha(8,"x"));
		l2.add(new Ficha(7,"2"));
		l2.add(new Ficha(4,"+"));
		l2.add(new Ficha(6,"^"));
		l2.add(new Ficha(5,"*"));
		l2.add(new Ficha(4,"+"));
		Assert.assertTrue(l2.equals(a1.getSalida()));
		a1.setSalida(a1.analizar(f3.getFichas()));
		l2 = a1.getSalida();
		Assert.assertTrue(l2.size() == 6);
	}
}