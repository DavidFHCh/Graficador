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
		String union, s1, s2;
		String regexnum = "^[0-9]";
		String regexlet = "^[a-zA-Z]";
		for(int i = 0;i < c.length; i++){
			union = ""; // tenemos que reiniciar la variable
			s1 = String.valueOf(c[i]); 
			if(c[i] == 'x' || c[i] == 'X'){
				l.add(s1);
				continue;
			}
			if(s1.matches(regexlet)){
				union = s1;
				j = i+1;
				s2 = String.valueOf(c[j]);
				while(s2.matches(regexlet)){
					union += s2;
					j++;
					if(j >= c.length)
						break;
					s2 = String.valueOf(c[j]);
				}
				l.add(union);
				i = j-1;
				continue;
					
			}
			if(s1.matches(regexnum)){
				union = s1;
				j = i+1;
				s2 = String.valueOf(c[j]);
				while(s2.matches(regexnum)){
					union += s2;
					j++;
					if(j >= c.length)
						break;
					s2 = String.valueOf(c[j]);
				}
				l.add(union);
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