package proyecto1.graficador;

import java.util.*;
//import java.lang.String;

/**
 * Clase que analiza sintacticamente las strings recibidas.
 */
public class Fichador{

	private String s; // La String recibida.

	/**
	* Crea un Fichador.
	* @param String s - La String que se analizara.
	*/
	public Fichador(String s){
		this.s = s;
	}

	/**
	* Metodo que crea los "tokens" para despues pasarselos7
	* al arbol Sintactico.
	* @param 
	* @return LinkedList - Una lista con las fichas. 
	*/
	public LinkedList<String> hazFichas() throws ExcepcionCadenaInvalida{
		char[] c = this.s.toCharArray();
		LinkedList<String> l = new LinkedList<String>();
		int j;
		String num;
		String regex = "^[0-9]";
		for(int i = 0;i < c.length; i++){
			num = ""; // tenemos que reiniciar la variable
			String s1 = String.valueOf(c[i]); 
			if(c[i] == 'x' || c[i] == 'X'){
				l.add(s1);
				continue;
			}
			if(s1.matches(regex)){
				num = s1;
				j = i+1;
				String s2 = String.valueOf(c[j]);
				while(s2.matches(regex)){
					num += s2;
					j++;
					s2 = String.valueOf(c[j]);
				}
				l.add(num);
				i = j-1;
				continue;
			}
			if(c[i] == '{' || c[i] == '}' ||
				c[i] == '[' || c[i] == ']' ||
				c[i] == '(' || c[i] == ')' ||
				c[i] == '+' || c[i] == '-' ||
				c[i] == '^' || c[i] == '*'){
				l.add(s1);
		}else {
			throw new ExcepcionCadenaInvalida(s1);
		}
		}
		return l;
	}

}