package util;

public class IdentaCodigo {

	public static String identaXML(String codigo){		
		int i = 0;  
		
		
		     StringBuffer buffer = new StringBuffer();  
		         char c = '\0';  
		         int cont = 0;  
		         StringBuffer aux = new StringBuffer(codigo);  
		   
		         for (; i < aux.length(); i++) {  
		             c = aux.charAt(i);  
		             buffer.append(c);  
		             if (c == '>' && (aux.charAt(i - 1)!='/')) {  
		                 cont++;  
		                 if (cont >= 0) {  
		                     buffer.append("\n" + insereOuRemoveEspaco(cont * 1));  
		                 }  
		             } else if (c == '/' && aux.charAt(i - 1) == '<') {  
		                 ++i; //coloquei essa inst. para o char '/' nao repetir  
		                 for (; i < aux.length(); i++) {  
		                     c = aux.charAt(i);  
		                     buffer.append(c);  
		                     if (c == '>') {  
		                         cont--;  
		                         if (cont >= 0) {  
		                             buffer.append("\n" + insereOuRemoveEspaco(cont));  
		                             break;  
		                         }  
		   
		                     }  
		                 }  
		             }  else if(c == '>' && aux.charAt(i - 1)=='/'){
		            	 buffer.append("\n" + insereOuRemoveEspaco(cont * 1));  
		             }
		         }  
		         return buffer.toString(); 
			         /**StringBuffer buffer = new StringBuffer();  
			         char c = '\0';  
			   
			         for (int i = 0; i < codigo.length(); i++){  
			             c = codigo.charAt(i);  
			             buffer.append(c);  
			             if (c == '>'){  
			                 buffer.append("\n" + "");  
			             }  
			             if(c == '<'){  
			   
			                 buffer.trimToSize();  
			             }  
			              
			         }  
			         return buffer.toString();  **/
			      
	}
	 private static String insereOuRemoveEspaco(int num) {  
	         StringBuffer branco = new StringBuffer();  
	        for (int j = 0; j < num; j++) {  
	             branco.append("\t");  
	         }  
	         return branco.toString();  
	}  
	public static String  identaJava(String codigo){
		/**int i = 0;  
		
		
	     StringBuffer buffer = new StringBuffer();  
	         char c = '\0';  
	         int cont = 0;  
	         StringBuffer aux = new StringBuffer(codigo);  
	   
	         for (; i < aux.length(); i++) {  
	             c = aux.charAt(i);  
	             buffer.append(c);  
	             if (c == '{') {  
	                 cont++;  
	                 if (cont >= 0) {  
	                     buffer.append("\n\t" );  
	                 }  
	             } 
	             if (c=='}') {  
	                 cont++;  
	                 if (cont >= 0) {  
	                     buffer.append("\r" );  
	                 }  
	             }
	             if (c==';') {  
	                 cont++;  
	                 if (cont >= 0) {  
	                     buffer.append("\n" );  
	                 }  
	             }
	         }  
	         return buffer.toString(); **/
		return codigo;
	}
	
}
