package backview;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import model.entity.hibernate.Cidade;

import org.apache.commons.beanutils.BeanUtils;

import util.GeraConfig;
import util.GeraUtils;
import annotations.ZKCombo;
import annotations.ZKFieldFind;
import annotations.ZKId;

/**
 * 
 * @author diego Essa classe contem todos os metodos necessarios para a montagem
 *         de BackView de listagem nome+lis.java
 */
public class BackViewLis {
	/**
	 * 
	 * Metodo Responsavel por gerar os imports
	 * 
	 * @author diego
	 * @param sb
	 *            =variavel que esta aguardando o a classe
	 * @param clazz
	 *            =classe vigente para fazer a tela
	 * @param idclazz
	 *            = Objeto Field de uma classe, que é o ID da classe
	 * @param idclasse
	 *            = nome do id da classe
	 */
	public static String metodoImports( Class<?> clazz,
			Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();
	

	

		

		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null) {
				sb.append("\nimport " + f.getType().getName() + ";");
				sb.append("\nimport " + GeraConfig.PACOTEDAO + "."
						+ f.getType().getSimpleName().replace("VO", "") + "DAO;");
			}
			if (f.getAnnotation(ZKFieldFind.class) != null) {
				sb.append("\nimport " + f.getType().getName() + ";");
				sb.append("\nimport " + GeraConfig.PACOTEDAO + "."
						+ f.getType().getSimpleName().replace("VO", "") + "DAO;");
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * Metodo Responsavel por gerar as Propriedades declaradas
	 * 
	 * @author diego
	 * @param sb
	 *            =variavel que esta aguardando o a classe
	 * @param clazz
	 *            =classe vigente para fazer a tela
	 * @param idclazz
	 *            = Objeto Field de uma classe, que é o ID da classe
	 * @param idclasse
	 *            = nome do id da classe
	 */
	public static String metodoPropriedadesDeclaradas(
			Class<?> clazz, Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();

		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null) {
				sb.append("\n public ListModelList lm"
						+ f.getName() + ";");

			}
		}
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null
					|| f.getAnnotation(ZKFieldFind.class) != null) {
				sb.append("\n private " + f.getType().getSimpleName() + " "
						+ f.getName() + "= new " + f.getType().getSimpleName()
						+ "();");

			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * Metodo Responsavel por gerar o construtor
	 * 
	 * @author diego
	 * @param sb
	 *            =variavel que esta aguardando o a classe
	 * @param clazz
	 *            =classe vigente para fazer a tela
	 * @param idclazz
	 *            = Objeto Field de uma classe, que é o ID da classe
	 * @param idclasse
	 *            = nome do id da classe
	 */


	/**
	 * 
	 * Metodo Responsavel por gerar o InitComponentes
	 * 
	 * @author diego
	 * @param sb
	 *            =variavel que esta aguardando o a classe
	 * @param clazz
	 *            =classe vigente para fazer a tela
	 * @param idclazz
	 *            = Objeto Field de uma classe, que é o ID da classe
	 * @param idclasse
	 *            = nome do id da classe
	 */
	public static String metodoInitComponentes( Class<?> clazz,
			Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null) {
				sb.append(" \n lm" + f.getName()
						+ "=new ListModelList(new "
						+ f.getType().getSimpleName().replace("VO", "")
						+ "DAO()."+GeraConfig.METODOBUSCA+"(null));");
			}
		}
	
		return sb.toString();
	}

	

	/**
	 * 
	 * Metodo Responsavel por gerar o Limpar
	 * 
	 * @author diego
	 * @param sb
	 *            =variavel que esta aguardando o a classe
	 * @param clazz
	 *            =classe vigente para fazer a tela
	 * @param idclazz
	 *            = Objeto Field de uma classe, que é o ID da classe
	 * @param idclasse
	 *            = nome do id da classe
	 */
	public static String metodoLimpar( Class<?> clazz,
			Field idclazz, String idclasse) {

		StringBuilder sb=new StringBuilder();
		sb.append("\nthis."
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
				+ " = new " + clazz.getSimpleName() + "();");
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null
					|| f.getAnnotation(ZKFieldFind.class) != null) {
				sb.append("\n this." + f.getName() + "=new "
						+ f.getType().getSimpleName() + "();");
			}
		}

		return sb.toString();

	}

	/**
	 * 
	 * Metodo Responsavel por gerar os Getters e Setters
	 * 
	 * @author diego
	 * @param sb
	 *            =variavel que esta aguardando o a classe
	 * @param clazz
	 *            =classe vigente para fazer a tela
	 * @param idclazz
	 *            = Objeto Field de uma classe, que é o ID da classe
	 * @param idclasse
	 *            = nome do id da classe
	 */
	public static String metodoGetterSetterPropriedadesDeclaradas(
			 Class<?> clazz, Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();
		// metodos getters
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null
					|| f.getAnnotation(ZKFieldFind.class) != null) {
				sb.append("\n public " + f.getType().getSimpleName() + " get"
						+ GeraUtils.capitalizeFirst(f.getName()) + "() {");
				sb.append("return " + f.getName() + ";");
				sb.append("}");

			}
		}
		// metodos setters
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null
					|| f.getAnnotation(ZKFieldFind.class) != null) {

				sb.append("\n public void set"
						+ GeraUtils.capitalizeFirst(f.getName()) + "("
						+ f.getType().getSimpleName() + " " + f.getName()
						+ ") {");

				for (Field fc : f.getType().getDeclaredFields()) {

					if (fc.getAnnotation(ZKId.class) != null) {

						sb.append("\n if(" + f.getName() + "!=null){");
						
						sb.append("\nthis."
								+ GeraUtils.uncapitalizeFirst(clazz
										.getSimpleName()) + ".set"
								+ GeraUtils.capitalizeFirst(f.getName()) + "("
								+ f.getName() + ");");
						sb.append("\n }");
					}
				}
				sb.append("this." + f.getName() + "=" + f.getName() + ";");
				sb.append("}");

			}
		}
		return sb.toString();
	}

	public static String metodoPesquisar(Class<?> clazz,Field idclazz, String idclasse){
		StringBuilder sb=new StringBuilder();
		sb.append("hmSis = dao."+GeraConfig.METODOBUSCALIMIT+"("+GeraUtils.uncapitalizeFirst(clazz.getSimpleName().replace("VO","")+");"));
		
		return sb.toString();
	}
	/**
	 * Metodo que cria os metodos necessarios para os campos Find
	 * 
	 * @param sb
	 * @param clazz
	 * @param idclazz
	 * @param idclasse
	 */
	public static String metodoCamposPesquisar( Class<?> clazz,
			Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKFieldFind.class) != null) {

				sb.append("\npublic void pesquisar"
						+ f.getType().getSimpleName() + "() {"
						+ "\n	new WinUtils().abrirLis(\"forms/"
						+ f.getName().replace("VO", "")
						+ "lis.zul\", null, this, \"recebe"
						+ f.getType().getSimpleName() + "\");" + "\n}");
				sb.append("\npublic void recebe" + f.getType().getSimpleName()
						+ "(Object obj) {");
				for (Field fc : f.getType().getDeclaredFields()) {

					if (fc.getAnnotation(ZKId.class) != null) {

						sb.append("\n if(" + f.getName() + "!=null){");
						sb.append("\nthis." + f.getName() + "=("
								+ f.getType().getSimpleName() + ") obj;");
						
						sb.append("\nthis."
								+ GeraUtils.uncapitalizeFirst(clazz
										.getSimpleName()) + ".set"
								+ GeraUtils.capitalizeFirst(f.getName()) + "("
								+ f.getName() + ");");
						sb.append("\n }");
					}
				}

				sb.append("\nthis.vincular();\n}");
			}
		}
		return sb.toString();
	}
	public static String metodoFieldFindOnChange( Class<?> clazz,
			Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();
		for (Field f : GeraUtils.getAllFields(clazz, null)) {
			if (f.isAnnotationPresent(ZKFieldFind.class)) {
					Field id = null;
				    for(Field field:GeraUtils.getAllFields(f.getType(),null)){
						if(field.isAnnotationPresent(ZKId.class)){
							id=field;
						}
					}
					sb.append(" \npublic void buscaPorCod" + f.getType().getSimpleName()
							+ "() {");
					sb.append(" \nif(this."+GeraUtils.uncapitalizeFirst(clazz.getSimpleName())+".get"+GeraUtils.capitalizeFirst(f.getName())+"()!=null){");
					      sb.append("  \nrecebe"+f.getType().getSimpleName()+"(new " + f.getType().getSimpleName().replace("VO", "")+"DAO()."+GeraConfig.METODOBYCOD+"(this."+GeraUtils.uncapitalizeFirst(clazz.getSimpleName())+".get"+GeraUtils.capitalizeFirst(id.getName())+"()));");
					sb.append(" \n}");					
					sb.append("\n}");				
			}

		}
		return sb.toString();
	}
	
	
	
	public static String metodoExclusivoParaLisDinamico(Class<?> clazz,
			Field idclazz, String idclasse){
		StringBuilder sb=new StringBuilder();
		sb.append("\npublic void pesquisar() {");
		
		sb.append("\n	Listbox grid= (Listbox) this.getComponente(\"list"+clazz.getSimpleName().replace("VO","")+"\");");
		sb.append("\n Paging pag =(Paging) this.getComponente(\"pag"+clazz.getSimpleName().replace("VO","")+"\");");
		sb.append("\n int pagativa =pag.getActivePage();");
		sb.append("\n  obj = new "+clazz.getSimpleName()+"();");
		
		

		 sb.append("\ntry {");
				sb.append("\n	obj=("+clazz.getSimpleName()+") BeanUtils.cloneBean("+GeraUtils.uncapitalizeFirst(clazz.getSimpleName())+");");
			
						sb.append("\n} catch (IllegalAccessException e) {");
			// TODO Auto-generated catch block
								sb.append("\n	e.printStackTrace();");
										sb.append("\n} catch (InstantiationException e) {");
			// TODO Auto-generated catch block
										sb.append("\ne.printStackTrace();");
			sb.append("\n} catch (InvocationTargetException e) {");
			// TODO Auto-generated catch block
					sb.append("\n	e.printStackTrace();");
			sb.append("\n} catch (NoSuchMethodException e) {");
			// TODO Auto-generated catch block
					sb.append("\n	e.printStackTrace();");
							sb.append("\n}");
		
			
		
		    //System.out.println(new AtdhdkDAO().getQuantidadeAtdPendente().intValue());
		   //	System.out.println(pagativa);
		sb.append("\n	 pag.setTotalSize(new "+clazz.getSimpleName().replace("VO","")+"DAO()."+GeraConfig.METODOBUSCAPAGINADACOUNT+"(obj).intValue());");
		   
		    
		sb.append("\n   final int PAGE_SIZE = pag.getPageSize();");
		    //System.out.println("tamanho da pagina"+PAGE_SIZE);
		    // Show Listbox with first PAGE_SIZE
		    
			
		sb.append("\n   redraw(obj,0,PAGE_SIZE);");
		    //redraw(0);
		sb.append("\n     pag.setActivePage(0);");
		sb.append("\n    pag.setDetailed(true);");
		sb.append("\n    pag.addEventListener(\"onPaging\", new EventListener() {");
		sb.append("\n       public void onEvent(Event event) {");
		sb.append("\n        	PagingEvent pe = (PagingEvent) event;");
		sb.append("\n       	int pgno = pe.getActivePage();");
		        	
		        	//System.out.println("pagina ativa"+pgno);
		sb.append(" \n        	int ofs = pgno * PAGE_SIZE;    ");
		        	//System.out.println("inicial:"+ofs);
		        	// Redraw current paging
		
		
		    		
		sb.append("\n       	redraw(obj,ofs,PAGE_SIZE);");
				sb.append(" \n      	vincular();");
				sb.append("\n   	      	}");
	sb.append("\n   	   });");
	sb.append("\n   	    vincular();");
	sb.append("\n   	}		");
	
	sb.append("\n	private void redraw("+clazz.getSimpleName()+" obj,Integer inicial,Integer maximoPermitido) {	  	");		
			sb.append("\n	    this.hmSis = new "+clazz.getSimpleName().replace("VO","")+"DAO()."+GeraConfig.METODOBUSCAPAGINADA+"(obj,inicial,maximoPermitido);	 ");
					sb.append("\n	    setListmodel(new ListModelList(hmSis));");
	sb.append("\n	}");
	return sb.toString();
	}
}
