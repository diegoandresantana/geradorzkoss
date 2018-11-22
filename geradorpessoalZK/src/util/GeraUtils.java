package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GeraUtils {

	private static ResourceBundle resource = ResourceBundle.getBundle(
			"geradorzk", Locale.getDefault());

	public static String getValuePropeties(String key) {
		return resource.getString(key);
	}

	/**
	 * Grava Arquivo em Diretorio
	 * 
	 * @param nomeArquivo
	 *            Nome do arquivo sem a axtensao
	 * @param nomePasta
	 *            Caminho completo da pasta
	 * @param conteudo
	 *            A String com o conteudo gerado por template
	 * @param extensao
	 *            A Extensao do arquivo
	 */
	public static void gravaArquivo(String nomeArquivo, String nomePasta,
			String conteudo, String extensao) {

		try {

			File outputFile = new File(nomePasta + "/" + nomeArquivo + "."
					+ extensao);
			FileWriter out = new FileWriter(outputFile);
			out.write(conteudo);
			out.close();
			System.out.println("INFO: Gerando " + outputFile.getName() + "...");
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

	}

	/**
	 * Metodo serve para gravar os formularios
	 * 
	 * @param nomearquivo
	 * @param template
	 * @param extensao
	 */
	public static void gravaforms(String nomearquivo, String template,
			String extensao) {

		String[] arr = nomearquivo.split("_");

		// sempre pegando a ultima posicao

		String arquivo = nomearquivo.toLowerCase();
		
        try {
			template=new String (template.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// um arquivo

		try {
			File diretorio = new File(GeraConfig.WEBCONTENT);
			// cria diretorio se nao existir
			if (!diretorio.exists()) {
				diretorio.mkdir();
			}
			File outputFile = new File(GeraConfig.WEBCONTENT + "/" + arquivo
					+ "." + extensao);
			FileWriter out = new FileWriter(outputFile);
			
			out.write(template);
			out.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

	}

	/**
	 * @param string2
	 * @param string
	 * @param args
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */

	public static void gravaarquivo(String nomeBean, String conteudo,
			String extensao) {

		// um arquivo

		try {
			
			FileWriter fw = new FileWriter(GeraConfig.WEBCONTENT
					+ nomeBean.toLowerCase() + "." + extensao);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(conteudo);
			bw.flush();
			bw.close();
			System.out.println("INFO: Criando o aquivo "
					+ nomeBean.toLowerCase() + "." + extensao);

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

	}

	/**
	 * dá uppercase na primeira letra
	 * 
	 * @param s
	 * @return
	 */
	public static String capitalizeFirst(String s) {
		char[] chars = s.toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);
		s = new String(chars);
		return s;
	}

	/**
	 * dá lowercase na primeira letra
	 * 
	 * @param s
	 * @return
	 */
	public static String uncapitalizeFirst(String s) {
		char[] chars = s.toCharArray();
		chars[0] = Character.toLowerCase(chars[0]);
		s = new String(chars);
		return s;
	}

	public static List<Field> getAllFields(Class<?> clazz, List<Field> lisfields) {
		if (lisfields == null) {
			lisfields = new ArrayList<Field>();
		}
		Field[] arrFields = clazz.getDeclaredFields();
		for (Field f : arrFields) {
			lisfields.add(f);
		}
		clazz = clazz.getSuperclass();
		if (clazz != null) {
			// Chamando recursivamente o metodo agora com o nome da SuperClasse
			getAllFields(clazz, lisfields);
		}

		return lisfields;

	}
	public static List<Field> capturaField(Class<?> clazz,Class<?> annotation){
		List<Field> lista=new ArrayList<Field>();
		for(Field f:getAllFields(clazz, null)){
			if(f.isAnnotationPresent((Class<? extends Annotation>) annotation)){
			   lista.add(f);
			}
		}
		return lista;
	}
	public static String capturaTemplate(String nomearquivo){
		FileInputStream stream = null;
		
			InputStream arquivo=new Caminho().getClass().getResourceAsStream("/util/template/"+nomearquivo);
			
		InputStreamReader streamReader = new InputStreamReader(arquivo);  
		BufferedReader reader = new BufferedReader(streamReader); 
		StringBuilder texto=new StringBuilder();
		String linha;
		 try {
			while( (linha=reader.readLine() ) != null ) {  
				  
				      // Como exemplo, vamos transformar todos   
				       // as letras minúsculas em maiúsculas  
				        texto.append(linha+" \n ");
			}
			
			streamReader.close();
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		return texto.toString();
		
	}
}
