package backview;

import java.lang.reflect.Field;

import util.GeraConfig;
import util.GeraUtils;
import annotations.ZKCombo;
import annotations.ZKComboFix;
import annotations.ZKEntity;
import annotations.ZKFieldFind;
import annotations.ZKId;

/**
 * 
 * @author diego Essa classe contem todos os metodos necessarios para a montagem
 *         de BackView de cadastro nome+cad.java
 */
public class BackViewCad {
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
	public static  String  metodoImports( Class<?> clazz,
			Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();

		sb.append("\nimport " + GeraConfig.PACOTEDAO + "."
				+ clazz.getSimpleName().replace("VO", "") + "DAO;");
		sb.append("\nimport " + GeraConfig.PACOTEENTIDADE + "."
				+ clazz.getSimpleName() + ";");

		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null) {
				sb.append("\nimport " + f.getType().getName() + ";");
				sb.append("\nimport " + GeraConfig.PACOTEDAO + "."
						+ f.getType().getSimpleName().replace("VO", "")
						+ "DAO;");
			}
			if (f.getAnnotation(ZKFieldFind.class) != null) {
				sb.append("\nimport " + f.getType().getName() + ";");
				sb.append("\nimport " + GeraConfig.PACOTEDAO + "."
						+ f.getType().getSimpleName().replace("VO", "")
						+ "DAO;");
			}
		}
     return sb.toString();
	}

	/**
	 * 
	 * Metodo Responsavel por gerar as Propriedades Declaradas em uma classe
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
	public static  String  metodoPropriedadesDeclaradas(
			Class<?> clazz, Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();
		sb.append("\npublic " + clazz.getSimpleName() + " "
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
				+ " = new " + clazz.getSimpleName() + "();");
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
	
	public static  String  metodoConstrutorClasse( Class<?> clazz,
			Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();
		sb.append("\n public " + clazz.getSimpleName().replace("VO", "")
				+ "cad() {");
		sb.append("super(\""
				+ clazz.getSimpleName().replace("VO", "").toLowerCase()
				+ "cad.zul\");");
		sb
				.append("\n Map<String, String> map = Executions.getCurrent().getArg();");
		sb.append("\n if (map.get(\"pageName\") != null) {");
		sb.append("\n 	abertoPeloMenu = Boolean.FALSE;");
		sb.append("\n }");
		sb.append("}");
		return sb.toString();

	} */

	/**
	 * 
	 * Metodo Responsavel por gerar o trataVO
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
	public static  String  metodoTrataVO( Class<?> clazz,
			Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();
	
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null) {
				for (Field fc : f.getType().getDeclaredFields()) {
					if (fc.getAnnotation(ZKId.class) != null) {
						if (f.getAnnotation(ZKCombo.class).nullable() == false) {
							sb.append("\nif (this."
									+ GeraUtils.uncapitalizeFirst(clazz
											.getSimpleName()) + ".get"
									+ GeraUtils.capitalizeFirst(f.getName())
									+ "() == null) {");
							sb.append("\ntry{");
							sb.append("\nMessagebox.show(\"Selecione o "
									+ f.getType().getAnnotation(ZKEntity.class)
											.label() + ".\");");
							sb.append("\n}catch(InterruptedException e){");
							// sb.append("\nthis.getComponente(\"" +
							// fc.getName()+ "\").setFocus(true);");
							sb.append("\ne.printStackTrace();}");
							sb.append("\nreturn false;");
							sb.append("\n} ");
						}
					}
				}

			}
			if (f.getAnnotation(ZKFieldFind.class) != null) {
				for (Field fc : f.getType().getDeclaredFields()) {
					if (fc.getAnnotation(ZKId.class) != null) {
						if (f.getAnnotation(ZKFieldFind.class).nullable() == false) {
							sb.append("\nif (this."
									+ GeraUtils.uncapitalizeFirst(clazz
											.getSimpleName()) + ".get"
									+ GeraUtils.capitalizeFirst(f.getName())
									+ "() == null) {");
							sb.append("\ntry{");
							sb.append("\nMessagebox.show(\"Selecione o "
									+ f.getType().getAnnotation(ZKEntity.class)
											.label() + ".\");");
							sb.append("\n}catch(InterruptedException e){");
							// sb.append("\nthis.getComponente(\"" +
							// fc.getName()+ "\").setFocus(true);");
							sb.append("\ne.printStackTrace();}");
							sb.append("\nreturn false;");
							sb.append("\n} ");
						}
					}
				}
			}
			if (f.getAnnotation(ZKComboFix.class) != null) {

				if (f.getAnnotation(ZKComboFix.class).nullable() == false) {
					sb.append("\nif (this."
							+ GeraUtils
									.uncapitalizeFirst(clazz.getSimpleName())
							+ ".get" + GeraUtils.capitalizeFirst(f.getName())
							+ "() == null) {");
					sb.append("\ntry{");
					sb.append("\nMessagebox.show(\"Selecione o "
							+ f.getAnnotation(ZKComboFix.class).label()
							+ ".\");");
					sb.append("\n}catch(InterruptedException e){");
					// sb.append("\nthis.getComponente(\"" +
					// fc.getName()+ "\").setFocus(true);");
					sb.append("\ne.printStackTrace();}");
					sb.append("\nreturn false;");
					sb.append("\n} ");

				}
			}

		}


		return sb.toString();
	}

	/**
	 * 
	 * Metodo Responsavel por gerar o InitComponentes que serve para inicializar
	 * os objetos da tela
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
	public static  String  metodoInitComponentes( Class<?> clazz,
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
	 * Metodo Responsavel por gerar o Incluir
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
	public static  String  metodoIncluir( Class<?> clazz,
			Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();

		sb.append(" \n" + clazz.getSimpleName().replace("VO", "") + "DAO "
				+ clazz.getSimpleName().replace("VO", "").toLowerCase()
				+ "DAO = new " + clazz.getSimpleName().replace("VO", "")
				+ "DAO();");
		sb.append(" \n" + GeraUtils.uncapitalizeFirst(clazz.getSimpleName()) + " = "
				+  clazz.getSimpleName().replace("VO", "").toLowerCase()
				+ "DAO."+GeraConfig.METODOINSERT+"(this."
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
				+ ");");	
		

		sb.append("\n	if (this.abertoPeloMenu) {");
		sb.append("\n			Messagebox.show(\"Registro incluído com sucesso!\");");
		sb.append("\n	}");
		sb.append("\n	else {");
		sb.append("\n	Events.postEvent(\"onClose\", this, this."
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName()) + ");");
		sb.append("\n	}");

		sb.append("\n				this.getCrdBar().getBotao(0).setDisabled(true);");
		sb.append("\n				this.getCrdBar().getBotao(1).setDisabled(false);");
		sb.append("\n				this.vincular();");
	
		return sb.toString();
	}

	/**
	 * 
	 * Metodo Responsavel por gerar o Salvar
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
	public static  String  metodoSalvar( Class<?> clazz,
			Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();

		sb.append(" \n" + clazz.getSimpleName().replace("VO", "") + "DAO "
				+ clazz.getSimpleName().replace("VO", "").toLowerCase()
				+ "DAO = new " + clazz.getSimpleName().replace("VO", "")
				+ "DAO();");
		sb.append(" \n	"
				+ clazz.getSimpleName().replace("VO", "").toLowerCase()
				+ "DAO."+GeraConfig.METODOUPDATE+"(this."
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
				+ ") ;");
		sb.append(" \n				Messagebox.show(\"Registro Salvo com sucesso!\");");
		sb.append(" \n	if (this.abertoPeloMenu) {");
		sb.append(" \n		Messagebox.show(\"Registro Salvo com sucesso!\");");
		sb.append(" \n	} else {");
		sb.append(" \n	Events.postEvent(\"onClose\", this, this."
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName()) + ");");
		sb.append(" \n	}");

		sb.append(" \n				this.vincular();");
		;
		
		return sb.toString();
	}

	/**
	 * 
	 * Metodo Responsavel por gerar o Apagar
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
	public static  String  metodoApagar( Class<?> clazz,
			Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();
		sb.append("\ntry {");
		sb.append("\n  new " + clazz.getSimpleName().replace("VO", "")
				+ "DAO()."+GeraConfig.METODODELETE+"("
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName()) + ");");
		
		
		sb.append("\n			Messagebox.show(\"Registro excluído com sucesso!\");");
		sb.append("\n	this.limpar();");
		sb.append("\n	} catch (DaoException e) {");
			
		sb.append("\n	Messagebox.show(\"Registro excluído com sucesso!\");");
		sb.append("\n}");
		

		
		return sb.toString();
	}

	/**
	 * 
	 * Metodo Responsavel por gerar o Pesquisar
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
	 
	public static  String  metodoPesquisar( Class<?> clazz,
			Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();
		sb.append("\n@Override");
		sb.append("\npublic void pesquisar() {");
		sb
				.append("\nMap<String, String> param = new HashMap<String, String>();");
		sb.append("\nnew WinUtils().abrirLis(\"/forms/"
				+ clazz.getSimpleName().replace("VO", "").toLowerCase()
				+ "lis.zul\", param, this,\"ret" + clazz.getSimpleName()
				+ "\");");
		sb.append("\n}");
		return sb.toString();
	}*/

	/**
	 * 
	 * Metodo Responsavel por gerar o Retorno
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
	public static  String  metodoRetorno( Class<?> clazz,
			Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();
		
		sb.append("\n this."
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName()) + " = ("
				+ clazz.getSimpleName() + ") ret;");
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null
					|| f.getAnnotation(ZKFieldFind.class) != null) {
				sb.append("\nthis." + f.getName() + "=this."
						+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
						+ ".get" + GeraUtils.capitalizeFirst(f.getName())
						+ "();");
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
	public static  String  metodoLimpar( Class<?> clazz,
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
	public static  String metodoGetterSetterPropriedadesDeclaradas(
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

	/**
	 * Metodo que cria os metodos necessarios para os campos Find
	 * 
	 * @param sb
	 * @param clazz
	 * @param idclazz
	 * @param idclasse
	 */
	public static  String  metodoCamposPesquisar( Class<?> clazz,
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

						sb.append("\n if(obj!=null){");
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
			// excessao: serve para retorno de um botao add
			if (f.isAnnotationPresent(ZKCombo.class)) {
				sb.append("\npublic void recebe" + f.getType().getSimpleName()
						+ "(Object obj) {");

				for (Field fc : f.getType().getDeclaredFields()) {
					if (fc.getAnnotation(ZKId.class) != null) {
						for (Field fs : clazz.getDeclaredFields()) {
							if (fs.getAnnotation(ZKCombo.class) != null) {
								sb.append(" \n lm"
										+ f.getName()
										+ "=new ListModelList(new "
										+ f.getType().getSimpleName().replace(
												"VO", "")
										+ "DAO()."+GeraConfig.METODOBUSCA+"(null));");
							}
						}

						sb.append("\n if(obj!=null){");
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

	/**
	 * adiciona os metodos referente adcionar mais item ao um combo ou campo
	 * field find
	 */
	public static  String  metodoAddButton( Class<?> clazz,
			Field idclazz, String idclasse) {
		StringBuilder sb=new StringBuilder();
		for (Field f : GeraUtils.getAllFields(clazz, null)) {
			if (f.isAnnotationPresent(ZKCombo.class)) {
				if (f.getAnnotation(ZKCombo.class).addbutton()) {
					sb.append("\npublic void add" + f.getType().getSimpleName()
							+ "() {");
					sb
							.append("\n	Map<String, String> map = new HashMap<String, String>();");
					sb.append("\n	map.put(\"pageName\", \""
							+ clazz.getSimpleName().replace("VO", "").toLowerCase()+ "cad.zul\");");
					sb.append("\n	String url = \"forms/"
							+ f.getType().getSimpleName().replace("VO", "").toLowerCase()
									 + "cad.zul\";");
					sb
							.append("\n	new WinUtils().abrirLis(url, map, this, \"recebe"
									+ f.getType().getSimpleName() + "\");");
					sb.append("\n}");
				}
			} else if (f.isAnnotationPresent(ZKFieldFind.class)) {
				if (f.getAnnotation(ZKFieldFind.class).addbutton()) {
					sb.append("\npublic void add" + f.getType().getSimpleName()
							+ "() {");
					sb
							.append("\n	Map<String, String> map = new HashMap<String, String>();");
					sb.append("\n	map.put(\"pageName\", \""
							+ clazz.getSimpleName().replace("VO",
									"").toLowerCase() + "cad.zul\");");
					sb.append("\n	String url = \"forms/"
							+ f.getType().getSimpleName().replace("VO", "").toLowerCase() + "cad.zul\";");
					sb
							.append("\n	new WinUtils().abrirLis(url, map, this, \"recebe"
									+ f.getType().getSimpleName() + "\");");
					sb.append("\n}");
				}
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
					sb.append("\npublic void buscaPorCod" + f.getType().getSimpleName()
							+ "() {");
					sb.append("if(this."+GeraUtils.uncapitalizeFirst(clazz.getSimpleName())+".get"+GeraUtils.capitalizeFirst(f.getName())+"()!=null){");
					      sb.append("recebe"+f.getType().getSimpleName()+"(new " + f.getType().getSimpleName().replace("VO", "")+"DAO()."+GeraConfig.METODOBYCOD+"(this."+GeraUtils.uncapitalizeFirst(clazz.getSimpleName())+".get"+GeraUtils.capitalizeFirst(id.getName())+"()));");
					sb.append("}");					
					sb.append("\n}");				
			}

		}
		return sb.toString();
	}
}
