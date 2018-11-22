package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de Configuração das constantes para geracao
 * 
 * @author DIEGO
 * 
 */
public class GeraConfig {

	
   //METODOS	
	public final static String METODOBYCOD = GeraUtils.getValuePropeties("METODOBYCOD").trim();
	public final static String METODOINSERT = GeraUtils.getValuePropeties("METODOINSERT").trim();
	public final static String METODODELETE = GeraUtils.getValuePropeties("METODODELETE").trim();
	public final static String METODOUPDATE = GeraUtils.getValuePropeties("METODOUPDATE").trim();
	public final static String METODOBUSCA = GeraUtils.getValuePropeties("METODOBUSCA").trim();
	public final static String METODOBUSCALIMIT = GeraUtils.getValuePropeties("METODOBUSCALIMIT").trim();
	
	public final static String METODOBUSCAPAGINADACOUNT = GeraUtils.getValuePropeties("METODOBUSCAPAGINADACOUNT").trim();
	public final static String METODOBUSCAPAGINADA = GeraUtils.getValuePropeties("METODOBUSCAPAGINADA").trim();
	
	// Logicas e Dao
	public final static String SOURCE = GeraUtils.getValuePropeties("SOURCE").trim();
	public final static String DIRENTI = GeraUtils.getValuePropeties("DIRENTI").trim();
	public final static String DIRDAO = GeraUtils.getValuePropeties("DIRDAO").trim();
	public final static String DIRDAOINTER = GeraUtils.getValuePropeties("DIRDAOINTER").trim();
	public final static String DIRLOGIC = GeraUtils.getValuePropeties("DIRLOGIC").trim();
	public final static String DIRVIEW = GeraUtils.getValuePropeties("DIRVIEW").trim();
	
	public final static String PACOTEENTIDADE = GeraUtils.getValuePropeties("PACOTEENTIDADE").trim();	
	public final static String PACOTEDAO = GeraUtils.getValuePropeties("PACOTEDAO").trim();
	public final static String PACOTEDAOINTER = GeraUtils.getValuePropeties("PACOTEDAOINTER").trim();
	public final static String PACOTELOGIC = GeraUtils.getValuePropeties("PACOTELOGIC").trim();	
	public final static String PACOTEVIEW = GeraUtils.getValuePropeties("PACOTEVIEW").trim();
	public final static String WEBCONTENT = GeraUtils.getValuePropeties("WEBCONTENT").trim();

	
	public final static String[] CHAVESENTIDADES = GeraUtils.getValuePropeties(
			"CHAVESENTIDADES").trim().split(",");
	
	/**
	 * Metodo captura todas as propriedades referente as entidades e clia um
	 * array dessas classes.
	 * 
	 * @return
	 */
	public static List<Class<?>> getClassEntidades() {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (String chave : CHAVESENTIDADES) {
			try {
				classes.add(Class.forName(PACOTEENTIDADE + "." + chave));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Não Achou a Entidade =" + chave);
				e.printStackTrace();
			}
		}
		ValidaAnnotation.valida(classes);
		return classes;

	}
}
