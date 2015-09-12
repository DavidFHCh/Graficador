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
	private Fichador f1,f2;

	public TestAnalizador(){
		a1 = new Analizador();
		f1 = new Fichador();
		f2 = new Fichador();
		f2.hazFichas("-4+3*(5+1");
		f1.hazFichas("-3*-sin(3.35687+4)*2/-(1-x)^2^3");
	}

	@Test public void testgetSalida(){
		a1.analizar(f1.getFichas());
		LinkedList<Ficha> l = a1.getSalida();
		Assert.assertTrue(l != null);
		//for(Ficha f:l)
		//	System.out.println(f.entrada);
		Assert.assertTrue(l.size() == 15);
	}

	@Test public void testaAnalizar(){
		LinkedList l2 = f2.getFichas() ,l1 = f1.getFichas();
		try{
			a1.analizar(l2);
			Assert.fail();
		}catch(ExcepcionCadenaInvalida eci){}
		a1.analizar(l1);
		l1 = a1.getSalida();
	}
}