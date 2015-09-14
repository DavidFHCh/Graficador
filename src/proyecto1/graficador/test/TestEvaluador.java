package proyecto1.graficador.test;

import proyecto1.graficador.Analizador;
import proyecto1.graficador.Evaluador;
import proyecto1.graficador.Fichador;
import proyecto1.graficador.Ficha;
import proyecto1.graficador.ExcepcionCadenaInvalida;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

public class TestEvaluador{

	private Evaluador e1,e2;
	private Fichador f1,f2,f3;

	public TestEvaluador(){
		f1 = new Fichador();
		f2 = new Fichador();
		f3 = new Fichador();
		e1 = new Evaluador();
		e2 = new Evaluador();
		f1.hazFichas("(3)^4");
		f2.hazFichas("x");
		f3.hazFichas("x^4+2*x+1");
	}

	@Test public void testEvalua(){
		e1.analizar(f1.getFichas());
		double s = e1.evalua(e1.getSalida(),0,true);
		Assert.assertTrue(s == 81);
		e2.analizar(f2.getFichas());
		for(int i = 0; i < 1000; i++){
			s = e2.evalua(e2.getSalida(),i,true);
			Assert.assertTrue(s == i);
		}
		e1 = new Evaluador();
		e1.analizar(f3.getFichas());
		for(Ficha f: e1.getSalida())
			System.out.println(f.entrada + "   " +f.ficha);
		for(int j = 0; j < 1000; j++){
			s = e1.evalua(e1.getSalida(),j,true);
			double s1 = Math.pow(j,4)+(2*j)+1;
			Assert.assertTrue(s == s1);
		}
	}
}