package backview;

import java.lang.reflect.Field;

import util.GeraConfig;
import util.GeraUtils;
import annotations.ZKCombo;
import annotations.ZKComboFix;
import annotations.ZKEntity;
import annotations.ZKFieldFind;
import annotations.ZKId;
import annotations.ZKTelaForte;

/**
 * 
 * @author diego Essa classe contem todos os metodos necessarios para a montagem
 *         de BackView de cadastro nome+cad.java
 */
public class BackViewCadDupla {
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
	public static void metodoImports(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse) {

		sb.append("\nimport " + GeraConfig.PACOTEDAO + "."
				+ clazz.getSimpleName().replace("VO", "") + "Logic;");
		sb.append("\nimport " + GeraConfig.PACOTEENTIDADE + "."
				+ clazz.getSimpleName() + ";");

		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
				sb.append("\nimport " + f.getType().getName() + ";");
				sb.append("\nimport " + GeraConfig.PACOTEDAO + "."
						+ f.getType().getSimpleName().replace("VO", "")
						+ "Logic;");
			}
			if (f.getAnnotation(ZKFieldFind.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
				sb.append("\nimport " + f.getType().getName() + ";");
			}
		}

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
	public static void metodoPropriedadesDeclaradas(StringBuilder sb,
			Class<?> clazz, Field idclazz, String idclasse, Class<?> clazzpai) {
		sb.append("\npublic " + clazz.getSimpleName() + " "
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
				+ " = new " + clazz.getSimpleName() + "();");
		sb.append("\npublic " + clazzpai.getSimpleName() + " "
				+ GeraUtils.uncapitalizeFirst(clazzpai.getSimpleName())
				+ " = new " + clazzpai.getSimpleName() + "();");

		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
				sb.append("\n public ListModelList lm"
						+ f.getType().getSimpleName() + ";");

			}
		}

		for (Field f : clazzpai.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
				sb.append("\n public ListModelList lm"
						+ f.getType().getSimpleName() + ";");

			}
		}
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null
					|| f.getAnnotation(ZKFieldFind.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
				sb.append("\n private " + f.getType().getSimpleName() + " "
						+ f.getName() + "= new " + f.getType().getSimpleName()
						+ "();");

			}
		}
		for (Field f : clazzpai.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null
					|| f.getAnnotation(ZKFieldFind.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
				sb.append("\n private " + f.getType().getSimpleName() + " "
						+ f.getName() + "= new " + f.getType().getSimpleName()
						+ "();");

			}
		}
		sb.append("\npublic List<" + clazz.getSimpleName() + "> list"
				+ clazz.getSimpleName() + "=new ArrayList<"
				+ clazz.getSimpleName() + ">();");
		sb.append("\n public String itensRemovidos=\"\";");
		sb.append("\n Boolean abertoPeloMenu = Boolean.TRUE;");
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
	public static void metodoConstrutorClasse(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse) {
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

	}

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
	public static void metodoTrataVO(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse) {

		sb.append(" \n public boolean trataVO() {");
		sb.append(" \n	if (this."
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
				+ " != null) {");

		// sb.append(" \n			this."+
		// GeraUtils.uncapitalizeFirst(clazz.getSimpleName())+
		// ".setUsualt(String.format(\"%10s\", this.getLogin()));");
		// sb.append(" \n			this."+
		// GeraUtils.uncapitalizeFirst(clazz.getSimpleName()) +
		// ".setDatalt(new Date());");
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
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
			if (f.getAnnotation(ZKFieldFind.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
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
			if (f.getAnnotation(ZKComboFix.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {

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

		sb.append(" \n			return true;");
		sb.append(" \n		}");
		sb.append(" \n		return false;");
		sb.append(" \n		}");

	}

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
	public static void metodoTrataFilhoVO(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse) {

		sb.append(" \n public boolean trata" + clazz.getSimpleName() + "() {");
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
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
			if (f.getAnnotation(ZKFieldFind.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
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
			if (f.getAnnotation(ZKComboFix.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {

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

		sb.append(" \n			return true;");
		sb.append(" \n		}");
	

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
	public static void metodoInitComponentes(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse, Class<?> clazzpai) {
		sb.append(" \npublic void initComponentes() {");
		sb.append(" \nthis.getCrdBar().getBotao(1).setDisabled(true);");
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
				sb.append(" \n lm" + f.getType().getSimpleName()
						+ "=new ListModelList(new "
						+ f.getType().getSimpleName().replace("VO", "")
						+ "Logic().getRegByExample(null));");
			}

		}
		for (Field f : clazzpai.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
				sb.append(" \n lm" + f.getType().getSimpleName()
						+ "=new ListModelList(new "
						+ f.getType().getSimpleName().replace("VO", "")
						+ "Logic().getRegByExample(null));");
			}

		}
		sb.append(" \n	this.vincular();");
		sb.append(" \n}");

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
	public static void metodoIncluir(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclass, Class<?> clazzpai,
			String idclassepai, Field idclazzpai) {

		sb.append("\n@Override");
		sb.append("\npublic void incluir() {");

		sb.append("\n	try {");
		sb.append("\n		if (this.validarForm() && this.trataVO()");
		sb.append(" && !this.getCrdBar().getBotao(0).isDisabled()) {");
		
		
		sb.append("this."+GeraUtils.uncapitalizeFirst(clazzpai.getSimpleName())+".set"+clazz.getSimpleName()+"List(list"
				+ clazz.getSimpleName()+");");
		sb.append("this.fornecedor = new FornecedorLogic().insertReg(this.fornecedor);");
		
		sb.append(" \n if (this."+GeraUtils.uncapitalizeFirst(clazzpai.getSimpleName())+".get"+ GeraUtils.capitalizeFirst(idclassepai)+"() > 0) {");

		sb.append("\n	if (this.abertoPeloMenu) {");
		sb.append("\n			Messagebox.show(\"Registro incluído com sucesso!\");");
		sb.append("\n	}");
		sb.append("\n	else {");
		sb.append("\n	Events.postEvent(\"onClose\", this, this."
				+ GeraUtils.uncapitalizeFirst(clazzpai.getSimpleName()) + ");");
		sb.append("\n	}");

		sb.append("\n				this.getCrdBar().getBotao(0).setDisabled(true);");
		sb.append("\n				this.getCrdBar().getBotao(1).setDisabled(false);");
		sb.append("\n				this.vincular();");
		sb.append("\n} else {");
		sb
				.append("\n	Messagebox.show(\"O registro não pode ser incluído!\",\"Erro:\", Messagebox.OK, Messagebox.ERROR);");
		sb.append("\n			}");
		sb.append("\n		}");
		sb.append("\n	} catch (Exception e) {");
		sb.append("\n		try {");
		sb
				.append("\n			Messagebox.show(\"O registro não pode ser incluído: \"	+ e.getMessage(), \"Erro:\", Messagebox.OK,	Messagebox.ERROR);");
		sb.append("\n		} catch (InterruptedException e1) {");
		// TODO Auto-generated catch block
		sb.append("\n			e1.printStackTrace();");
		sb.append("\n		}");
		sb.append("\n		e.printStackTrace();");
		sb.append("\n	}");
		sb.append("\n}");

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
	public static void metodoSalvar(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse, Class<?> clazzpai,
			Field idclazzpai, String idclassepai) {
		sb.append("\n@Override");
		sb.append("\npublic void salvar() {");

		sb.append("\n	try {");
		sb.append("\n		if (this.validarForm() && this.trataVO() ");
		sb.append("	&& this."
				+ GeraUtils.uncapitalizeFirst(clazzpai.getSimpleName())
				+ ".get" + GeraUtils.capitalizeFirst(idclassepai)
				+ "() != null");
		sb.append("	&& !this.getCrdBar().getBotao(1).isDisabled()) {");
		sb.append("try {");
			sb.append(" \nnew "+clazz.getSimpleName()+"Logic().delByCodigos(itensRemovidos);");
			sb.append(" \nthis."+GeraUtils.uncapitalizeFirst(clazzpai.getSimpleName())+".set"+clazz.getSimpleName()+"List(list"+clazz.getSimpleName()+"); ");
			sb.append(" \nthis."+GeraUtils.uncapitalizeFirst(clazzpai.getSimpleName())+" = new "+clazzpai.getSimpleName()+"Logic().updateReg(this."+GeraUtils.uncapitalizeFirst(clazzpai.getSimpleName())+");");
			
			sb.append(" \nif (this.abertoPeloMenu) {");
				sb.append(" \n	Messagebox.show(\"Registro Salvo com sucesso!\");");
				sb.append(" \n} else {");
				sb.append(" \n		Events.postEvent(\"onClose\", this, this."+GeraUtils.uncapitalizeFirst(clazzpai.getSimpleName())+");");
				sb.append(" \n	}");
			sb.append(" \n} catch (LogicException e) {");
			sb.append(" \n	Messagebox.show(e.getMessage(), \"Erro:\", Messagebox.OK,	Messagebox.ERROR);");
			sb.append(" \n	e.printStackTrace();");
			sb.append(" \n}");

		sb.append(" \n				this.vincular();");
		sb.append(" \n			} else {");
		sb
				.append(" \n				Messagebox.show(\"O registro não pode ser salvo!\",\"Erro:\", Messagebox.OK, Messagebox.ERROR);");
		sb.append(" \n			}");
	
		sb.append(" \n	} catch (Exception e) {");
		sb.append(" \n		e.printStackTrace();");
		sb.append(" \n	}");
		sb.append(" \n	this.limpar();");
		sb.append(" \n}");

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
	public static void metodoApagar(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse) {

		sb.append("\n@Override");
		sb.append("\npublic void apagar() {");

		sb.append("\ntry {");
		sb
				.append("\nif (Messagebox.show(\"Deseja realmente excluir ?\", \"Aviso!!!\", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES){");

		sb.append("\n			try {");
		sb.append("\n				new " + clazz.getSimpleName()
				+ "Logic().deleteReg(this."
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName()) + ");");
		sb.append("\n} catch (LogicException e) {");
		sb.append("\nthis.vincular();");
		sb.append("\nMessagebox.show(\"Registro excluído com sucesso!\");");
		sb.append("\n}");

		sb.append("\n}");
		sb.append("\n} catch (InterruptedException e) {");
		sb.append("\n	try {");
		sb.append("\n			Messagebox.show(\"Registro não pode ser excluído!\");");
		sb.append("\n	} catch (InterruptedException e1) {");
		sb.append("\n		e1.printStackTrace();");
		sb.append("\n	}");
		sb.append("\n	e.printStackTrace();");
		sb.append("\n	}");
		sb.append("\n}");

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
	 */
	public static void metodoPesquisar(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse) {
		sb.append("\n@Override");
		sb.append("\npublic void pesquisar() {");
		sb
				.append("\nMap<String, String> param = new HashMap<String, String>();");
		sb.append("\nnew WinUtils().abrirLis(\"/forms/"
				+ clazz.getSimpleName().replace("VO", "").toLowerCase()
				+ "lis.zul\", param, this,\"ret" + clazz.getSimpleName()
				+ "\");");
		sb.append("\n}");

	}

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
	public static void metodoRetorno(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse, Class<?> clazzpai,
			Field idclazzpai, String idclassepai) {

		sb.append("\n public void ret" + clazzpai.getSimpleName()
				+ "(Object ret) {");
		sb.append("\n		if (ret != null) {");
		sb.append("\n this."
				+ GeraUtils.uncapitalizeFirst(clazzpai.getSimpleName())
				+ " = (" + clazzpai.getSimpleName() + ") ret;");
		for (Field f : clazzpai.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null
					|| f.getAnnotation(ZKFieldFind.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
				sb.append("\nthis." + f.getName() + "=this."
						+ GeraUtils.uncapitalizeFirst(clazzpai.getSimpleName())
						+ ".get" + GeraUtils.capitalizeFirst(f.getName())
						+ "();");
			}
		}
	
		sb.append("\n	this.list" + clazz.getSimpleName() + "=this."+GeraUtils.uncapitalizeFirst(clazzpai.getSimpleName())+".get"+clazz.getSimpleName()+"List();");

		sb.append("\n			this.getCrdBar().getBotao(0).setDisabled(true);");
		sb.append("\n			this.getCrdBar().getBotao(1).setDisabled(false);");
		sb.append("\n			this.vincular();");
		sb.append("\n		}");
		sb.append("\n	}");
	}

	/**
	 * 
	 * Metodo Responsavel por gerar o Sair
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
	public static void metodoSair(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse) {
		sb.append("\n@Override");
		sb.append("\npublic void sair() {");
		sb.append("\nif (this.abertoPeloMenu) {");
		sb.append("\n	this.detach();");
		sb.append("\n} else {");
		sb.append("\nEvents.postEvent(\"onClose\", this, this."
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName()) + ");");
		sb.append("\n}");

		sb.append("\n}");
	}

	/**
	 * 
	 * Metodo Responsavel por gerar o Imprimir
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
	public static void metodoImprimir(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse) {
		sb.append("\n@Override");
		sb.append("\npublic void imprimir() {");
		// TODO Auto-generated method stub

		sb.append("\n}");

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
	public static void metodoLimpar(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse, Class<?> clazzfilho) {
		sb.append("\n	@Override");
		sb.append("\npublic void limpar() {");
		sb.append("\nthis."
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
				+ " = new " + clazz.getSimpleName() + "();");
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null
					|| f.getAnnotation(ZKFieldFind.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
				sb.append("\n this." + f.getName() + "=new "
						+ f.getType().getSimpleName() + "();");

			}
		}

		sb.append("\n	this.getCrdBar().getBotao(0).setDisabled(false);");
		sb.append("\n	this.getCrdBar().getBotao(1).setDisabled(true);");
		sb.append("\nlimpar" + clazzfilho.getSimpleName() + "();");

		sb.append("\n}");

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
	public static void metodoLimparCamposItem(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse) {

		sb.append("\npublic void limpar" + clazz.getSimpleName() + "() {");
		sb.append("\nthis."
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
				+ " = new " + clazz.getSimpleName() + "();");
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKCombo.class) != null
					|| f.getAnnotation(ZKFieldFind.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
				sb.append("\n this." + f.getName() + "=new "
						+ f.getType().getSimpleName() + "();");

			}
		}

		sb.append("\n	this.vincular();");
		sb.append("\n}");

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
	public static void metodoGetterSetterPropriedadesDeclaradas(
			StringBuilder sb, Class<?> clazz, Field idclazz, String idclasse) {
		
		// metodos getters
		for (Field f : GeraUtils.getAllFields(clazz, null)) {
			if (f.isAnnotationPresent(ZKCombo.class)
					|| f.isAnnotationPresent(ZKFieldFind.class) && f.getAnnotation(ZKTelaForte.class)==null) {
				sb.append("\n public " + f.getType().getSimpleName() + " get"
						+ GeraUtils.capitalizeFirst(f.getName()) + "() {");
				sb.append("return " + f.getName() + ";");
				sb.append("}");

			}
		}
		// metodos setters
		for (Field f : GeraUtils.getAllFields(clazz, null)) {
			if (f.isAnnotationPresent(ZKCombo.class)
					|| f.isAnnotationPresent(ZKFieldFind.class)&& f.getAnnotation(ZKTelaForte.class)==null) {

				sb.append("\n public void set"
						+ GeraUtils.capitalizeFirst(f.getName()) + "("
						+ f.getType().getSimpleName() + " " + f.getName()
						+ ") {");

				for (Field fc : GeraUtils.getAllFields(f.getType(), null)) {

					if (fc.isAnnotationPresent(ZKId.class)) {

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
		
	}

	/**
	 * Metodo que cria os metodos necessarios para os campos Find
	 * 
	 * @param sb
	 * @param clazz
	 * @param idclazz
	 * @param idclasse
	 */
	public static void metodoCamposPesquisar(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse) {
		for (Field f : clazz.getDeclaredFields() ) {
			if (f.getAnnotation(ZKFieldFind.class) != null && f.getAnnotation(ZKTelaForte.class)==null) {
				sb.append("\npublic void pesquisar"
						+ f.getType().getSimpleName() + "() {"
						+ "\n	new WinUtils().abrirLis(\"forms/"
						+ f.getName().toLowerCase()
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
			if (f.isAnnotationPresent(ZKCombo.class) && f.getAnnotation(ZKTelaForte.class)==null) {
				sb.append("\npublic void recebe" + f.getType().getSimpleName()
						+ "(Object obj) {");
				for (Field fc : f.getType().getDeclaredFields()) {

					if (fc.getAnnotation(ZKId.class) != null) {

						for (Field fs : clazz.getDeclaredFields()) {
							if (fs.getAnnotation(ZKCombo.class) != null) {
								sb.append(" \n lm"
										+ fs.getType().getSimpleName()
										+ "=new ListModelList(new "
										+ fs.getType().getSimpleName()
										+ "Logic().getRegByExample(new "
										+ fs.getType().getSimpleName()
										+ "()));");
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
		
	}

	/**
	 * adiciona os metodos referente adcionar mais item ao um combo ou campo
	 * field find
	 */
	public static void metodoAddButton(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse) {
		for (Field f : GeraUtils.getAllFields(clazz, null)) {
			if (f.isAnnotationPresent(ZKCombo.class) && f.getAnnotation(ZKTelaForte.class)==null) {
				if (f.getAnnotation(ZKCombo.class).addbutton()) {
					sb.append("\npublic void add" + f.getType().getSimpleName()
							+ "() {");
					sb
							.append("\n	Map<String, String> map = new HashMap<String, String>();");
					sb.append("\n	map.put(\"pageName\", \""
							+ clazz.getSimpleName().replace("VO", "")
									.toLowerCase() + "cad.zul\");");
					sb.append("\n	String url = \"forms/"
							+ f.getType().getSimpleName().replace("VO", "")
									.toLowerCase() + "cad.zul\";");
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
							+ clazz.getSimpleName().toLowerCase().replace("VO",
									"") + "cad.zul\");");
					sb.append("\n	String url = \"forms/"
							+ f.getType().getSimpleName().toLowerCase()
									.replace("VO", "") + "cad.zul\";");
					sb
							.append("\n	new WinUtils().abrirLis(url, map, this, \"recebe"
									+ f.getType().getSimpleName() + "\");");
					sb.append("\n}");
				}
			}

		}
	}

	public static void metodoFieldFindOnChange(StringBuilder sb,
			Class<?> clazz, Field idclazz, String idclasse) {

		for (Field f : GeraUtils.getAllFields(clazz, null)) {
			if (f.isAnnotationPresent(ZKFieldFind.class)) {
				Field id = null;
				for (Field field : GeraUtils.getAllFields(f.getType(), null)) {
					if (field.isAnnotationPresent(ZKId.class)) {
						id = field;
					}
				}
				sb.append("\npublic void buscaPorCod"
						+ f.getType().getSimpleName() + "() {");
				sb.append("if(this."
						+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
						+ ".get" + GeraUtils.capitalizeFirst(f.getName())
						+ "()!=null){");
				sb.append("recebe" + f.getType().getSimpleName() + "(new "
						+ f.getType().getSimpleName().replace("VO", "")
						+ "Logic().getRegByCod(this."
						+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
						+ ".get" + GeraUtils.capitalizeFirst(id.getName())
						+ "()));");
				sb.append("}");
				sb.append("\n}");
			}

		}
	}

	public static void metodoAddRemoveItem(StringBuilder sb, Class<?> clazz,
			Field idclazz, String idclasse,Class<?> clazzpai) {
		sb.append("\npublic void addItem(){");
		sb.append("\n	if(trata" + clazz.getSimpleName() + "()){");
		sb.append("\n  	if(list" + clazz.getSimpleName() + ".contains("
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName()) + ")){");
		sb.append("\n    		try {");
		sb.append("\n				Messagebox.show(\"Ja exite "
				+ (clazz.getAnnotation(ZKEntity.class)).label()
				+ " na Lista!\");");
		sb.append("\n			} catch (InterruptedException e) {");

		sb.append("\n			e.printStackTrace();");
		sb.append("\n		}");
		sb.append("\n  	}else{");
		sb.append("\n this."+GeraUtils.uncapitalizeFirst(clazz.getSimpleName())+".set"+GeraUtils.capitalizeFirst(GeraUtils.capturaField(clazz, ZKTelaForte.class).get(0).getName())+"(this."+GeraUtils.uncapitalizeFirst(clazzpai.getSimpleName())+");");
		sb.append("\n 	   list" + clazz.getSimpleName() + ".add("
				+ GeraUtils.uncapitalizeFirst(clazz.getSimpleName()) + ");");
		sb.append("\n " + GeraUtils.uncapitalizeFirst(clazz.getSimpleName())
				+ "=new " + clazz.getSimpleName() + "();");
		sb.append("\n  	}");
		sb.append("\n  	}");
		sb.append("\n  	this.vincular();");
		sb.append("\n   }");

		sb.append("\n    public void removerItem(" + clazz.getSimpleName()
				+ " item){");
		sb.append("\n   	try {");
		sb
				.append("\n			if (Messagebox.show(\"Deseja realmente excluir a mensagem?\", \"Aviso!!!\", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES){");
		sb.append("\n	if(item!=null){");
		sb.append("\n						if(item.get" + GeraUtils.capitalizeFirst(idclasse)
				+ "()!=null){");
		sb.append("\n		if(!itensRemovidos.equals(\"\")){");
		sb.append("\n				itensRemovidos+=\",\"+item.get"
				+ GeraUtils.capitalizeFirst(idclasse) + "();");
		sb.append("\n		}else{");
		sb.append("\n								itensRemovidos+=\"\"+item.get"
				+ GeraUtils.capitalizeFirst(idclasse) + "();");
		sb.append("\n		}");
		sb.append("\n	}");
		sb.append("\n	}");
		sb.append("\n		list" + clazz.getSimpleName() + ".remove(item);");
		sb.append("\n				}");
		sb.append("\n			} catch (InterruptedException e) {");

		sb.append("\n				e.printStackTrace();");
		sb.append("\n			}");
		sb.append("\n			this.vincular();");
		sb.append("\n	    }");
	}
}
