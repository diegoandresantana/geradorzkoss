package backview;

import java.lang.reflect.Field;

import util.CapturaTemplate;
import util.GeraConfig;
import util.GeraUtils;
import util.Tags;
import annotations.ZKId;
import annotations.ZKTelaForte;

/**
 * Classe BackView responsavel por ter os dois metodos que fazem gerar o
 * BackView nome+lis.java ou nome+cad.java
 */
public class BackView {
	/**
	 * 
	 * Metodo responsavel por gerar a classe BackView .java ex: stacad.java
	 * 
	 * @param clazz
	 * @see BackViewCad
	 */

	public static void backViewCad(Class<?> clazz) {

		String idclasse = "";
		Field idclazz = null;
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKId.class) != null) {
				idclasse = f.getName();
				idclazz = f;
			}
		}
		String sb = "";

		sb = CapturaTemplate.VIEWCAD;
		sb = sb.replace(Tags.pacote(), "\npackage " + GeraConfig.PACOTEVIEW
				+ ";");

		String nomecompleto = clazz.getSimpleName();
		String nome = clazz.getSimpleName().replace("VO", "");
		String tipoid = "";
		String nomeminus = clazz.getSimpleName().replace("VO", "")
				.toLowerCase();
		String nomecompleteuncaptalize = GeraUtils.uncapitalizeFirst(clazz
				.getSimpleName());
		for (Field f : GeraUtils.getAllFields(clazz, null)) {
			if (f.isAnnotationPresent(ZKId.class)) {

				tipoid = f.getType().getSimpleName();
				break;
			}
		}
		sb = sb.replace(Tags.nome(), nome);
		sb = sb.replace(Tags.nomecompleto(), nomecompleto);
		sb = sb.replace(Tags.nomeCompletoUncapitalizeFirst(),
				nomecompleteuncaptalize);
		sb = sb.replace(Tags.nomeMinusculo(), nomeminus);
		sb = sb.replace(Tags.tipoid(), tipoid);
		sb = sb.replace(Tags.imports(), BackViewCad.metodoImports(clazz,
				idclazz, idclasse));
		sb = sb.replace(Tags.declare(), BackViewCad
				.metodoPropriedadesDeclaradas(clazz, idclazz, idclasse));

		sb = sb.replace(Tags.initComponentes(), BackViewCad
				.metodoInitComponentes(clazz, idclazz, idclasse));
		sb = sb.replace(Tags.incluir(), BackViewCad.metodoIncluir(clazz,
				idclazz, idclasse));
		sb = sb.replace(Tags.salvar(), BackViewCad.metodoSalvar(clazz, idclazz,
				idclasse));

		sb = sb.replace(Tags.apagar(), BackViewCad.metodoApagar(clazz, idclazz,
				idclasse));
		sb = sb.replace(Tags.limpar(), BackViewCad.metodoLimpar(clazz, idclazz,
				idclasse));
		// BackViewCad.metodoPesquisar(clazz, idclazz, idclasse);
		sb = sb.replace(Tags.retorno(), BackViewCad.metodoRetorno(clazz,
				idclazz, idclasse));

		sb = sb.replace(Tags.trata(), BackViewCad.metodoTrataVO(clazz, idclazz,
				idclasse));

		sb = sb.replace(Tags.getterSetter(), BackViewCad
				.metodoGetterSetterPropriedadesDeclaradas(clazz, idclazz,
						idclasse));
		sb = sb.replace(Tags.camposPesquisar(), BackViewCad
				.metodoCamposPesquisar(clazz, idclazz, idclasse));
		sb = sb.replace(Tags.addbutton(), BackViewCad.metodoAddButton(clazz,
				idclazz, idclasse));
		// sb=sb.replace(Tags.buscaCodigo(),BackViewCad.metodoFieldFindOnChange(clazz,
		// idclazz, idclasse));
		sb = sb.replace(Tags.buscaCodigo(), "");
		sb = sb
				.replace(Tags.idCaptalize(), GeraUtils
						.capitalizeFirst(idclasse));
		System.out.println(sb.toString());
		GeraUtils.gravaArquivo(clazz.getSimpleName().replace("VO", "") + "cad",
				GeraConfig.SOURCE + GeraConfig.DIRVIEW, sb.toString(), "java");
	}

	/**
	 * 
	 * Metodo responsavel por gerar a classe BackView .java ex: stalis.java
	 * 
	 * @param clazz
	 * @see BackViewLis
	 */
	public static void backViewLis(Class<?> clazz) {
		String idclasse = "";
		Field idclazz = null;
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKId.class) != null) {
				idclasse = f.getName();
				idclazz = f;
			}
		}
		String sb = "";

		sb = CapturaTemplate.VIEWLIS;
		sb = sb.replace(Tags.pacote(), "\npackage " + GeraConfig.PACOTEVIEW
				+ ";");

		String nomecompleto = clazz.getSimpleName();
		String nome = clazz.getSimpleName().replace("VO", "");
		String tipoid = "";
		String nomeminus = clazz.getSimpleName().replace("VO", "")
				.toLowerCase();
		String nomecompleteuncaptalize = GeraUtils.uncapitalizeFirst(clazz
				.getSimpleName());
		for (Field f : GeraUtils.getAllFields(clazz, null)) {
			if (f.isAnnotationPresent(ZKId.class)) {

				tipoid = f.getType().getSimpleName();
				break;
			}
		}
		sb = sb.replace(Tags.nome(), nome);
		sb = sb.replace(Tags.nomecompleto(), nomecompleto);
		sb = sb.replace(Tags.nomeCompletoUncapitalizeFirst(),
				nomecompleteuncaptalize);
		sb = sb.replace(Tags.nomeMinusculo(), nomeminus);
		sb = sb.replace(Tags.tipoid(), tipoid);
		sb = sb.replace(Tags.imports(), BackViewLis.metodoImports(clazz,
				idclazz, idclasse));
		sb = sb.replace(Tags.declare(), BackViewLis
				.metodoPropriedadesDeclaradas(clazz, idclazz, idclasse));

		sb = sb.replace(Tags.initComponentes(), BackViewCad
				.metodoInitComponentes(clazz, idclazz, idclasse));

		sb = sb.replace(Tags.limpar(), BackViewLis.metodoLimpar(clazz, idclazz,
				idclasse));
		sb = sb.replace(Tags.getterSetter(), BackViewLis
				.metodoGetterSetterPropriedadesDeclaradas(clazz, idclazz,
						idclasse));
		sb = sb.replace(Tags.camposPesquisar(), BackViewLis
				.metodoCamposPesquisar(clazz, idclazz, idclasse));
		sb = sb.replace(Tags.buscaCodigo(), BackViewLis
				.metodoFieldFindOnChange(clazz, idclazz, idclasse));
		sb = sb.replace(Tags.pesquisar(), BackViewLis.metodoPesquisar(clazz,
				idclazz, idclasse));

		System.out.println(sb.toString());
		GeraUtils.gravaArquivo(clazz.getSimpleName().replace("VO", "") + "lis",
				GeraConfig.SOURCE + GeraConfig.DIRVIEW, sb.toString(), "java");
	}
	public static void backViewLisPaginadoDinamico(Class<?> clazz) {
		String idclasse = "";
		Field idclazz = null;
		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKId.class) != null) {
				idclasse = f.getName();
				idclazz = f;
			}
		}
		String sb = "";

		sb = CapturaTemplate.VIEWLISDYNAMIC;
		sb = sb.replace(Tags.pacote(), "\npackage " + GeraConfig.PACOTEVIEW
				+ ";");

		String nomecompleto = clazz.getSimpleName();
		String nome = clazz.getSimpleName().replace("VO", "");
		String tipoid = "";
		String nomeminus = clazz.getSimpleName().replace("VO", "")
				.toLowerCase();
		String nomecompleteuncaptalize = GeraUtils.uncapitalizeFirst(clazz
				.getSimpleName());
		for (Field f : GeraUtils.getAllFields(clazz, null)) {
			if (f.isAnnotationPresent(ZKId.class)) {

				tipoid = f.getType().getSimpleName();
				break;
			}
		}
		sb = sb.replace(Tags.nome(), nome);
		sb = sb.replace(Tags.nomecompleto(), nomecompleto);
		sb = sb.replace(Tags.nomeCompletoUncapitalizeFirst(),
				nomecompleteuncaptalize);
		sb = sb.replace(Tags.nomeMinusculo(), nomeminus);
		sb = sb.replace(Tags.tipoid(), tipoid);
		sb = sb.replace(Tags.imports(), BackViewLis.metodoImports(clazz,
				idclazz, idclasse));
		sb = sb.replace(Tags.declare(), BackViewLis
				.metodoPropriedadesDeclaradas(clazz, idclazz, idclasse));

		sb = sb.replace(Tags.initComponentes(), BackViewCad
				.metodoInitComponentes(clazz, idclazz, idclasse));

		sb = sb.replace(Tags.limpar(), BackViewLis.metodoLimpar(clazz, idclazz,
				idclasse));
		sb = sb.replace(Tags.getterSetter(), BackViewLis
				.metodoGetterSetterPropriedadesDeclaradas(clazz, idclazz,
						idclasse));
		sb = sb.replace(Tags.camposPesquisar(), BackViewLis
				.metodoCamposPesquisar(clazz, idclazz, idclasse));
		sb = sb.replace(Tags.buscaCodigo(), BackViewLis
				.metodoFieldFindOnChange(clazz, idclazz, idclasse));
		sb = sb.replace(Tags.pesquisar(), BackViewLis.metodoExclusivoParaLisDinamico(clazz, idclazz, idclasse));

		System.out.println(sb.toString());
		GeraUtils.gravaArquivo(clazz.getSimpleName().replace("VO", "") + "lis",
				GeraConfig.SOURCE + GeraConfig.DIRVIEW, sb.toString(), "java");
	}
	/**
	 * 
	 * Metodo responsavel por gerar a classe BackView .java ex: stacad.java
	 * 
	 * @param clazz
	 * @see BackViewCad
	 */
	public static void backViewCadDupla(Class<?> clazz) {
		StringBuilder sb = new StringBuilder();

		String idclassepai = "";
		Field idclazzpai = null;

		String idclasse = "";
		Field idclazz = null;

		for (Field f : clazz.getDeclaredFields()) {
			if (f.getAnnotation(ZKId.class) != null) {
				idclasse = f.getName();
				idclazz = f;
			}
		}
		Class clazzpai = GeraUtils.capturaField(clazz, ZKTelaForte.class)
				.get(0).getType();
		for (Field f : GeraUtils.getAllFields(clazzpai, null)) {
			if (f.getAnnotation(ZKId.class) != null) {
				idclassepai = f.getName();
				idclazzpai = f;
			}
		}

		sb.append(" package " + GeraConfig.PACOTEVIEW + ";");
		sb.append("\n import java.util.Date;");
		sb.append("\n import java.util.HashMap;");
		sb.append("\nimport java.util.List;");
		sb.append("\nimport java.util.ArrayList;");
		sb.append("\n import java.util.Map;");
		sb.append("\nimport org.zkoss.zul.ListModelList;");

		sb.append("\n import org.zkoss.zul.Messagebox;");
		sb.append("\n import pmcg.framework.ui.WindowCrud;");
		sb.append("\n import pmcg.framework.util.WinUtils;	");
		sb.append("\nimport org.zkoss.zk.ui.event.Events;	");
		sb.append("\nimport org.zkoss.zk.ui.Executions;");

		BackViewCadDupla.metodoImports(sb, clazz, idclazz, idclasse);
		BackViewCadDupla.metodoImports(sb, clazzpai, idclazzpai, idclassepai);
		sb.append("\npublic class "
				+ clazzpai.getSimpleName().replace("VO", "")
				+ "cad extends WindowCrud {");

		BackViewCadDupla.metodoPropriedadesDeclaradas(sb, clazz, idclazz,
				idclasse, clazzpai);

		BackViewCadDupla.metodoConstrutorClasse(sb, clazzpai, idclazzpai,
				idclassepai);

		BackViewCadDupla.metodoInitComponentes(sb, clazz, idclazz, idclasse,
				clazzpai);
		BackViewCadDupla.metodoAddRemoveItem(sb, clazz, idclazz, idclasse,
				clazzpai);
		BackViewCadDupla.metodoIncluir(sb, clazz, idclazz, idclasse, clazzpai,
				idclassepai, idclazzpai);
		BackViewCadDupla.metodoSalvar(sb, clazz, idclazz, idclasse, clazzpai,
				idclazzpai, idclassepai);

		BackViewCadDupla.metodoApagar(sb, clazz, idclazz, idclasse);
		BackViewCadDupla.metodoLimpar(sb, clazzpai, idclazzpai, idclassepai,
				clazz);
		BackViewCadDupla.metodoLimparCamposItem(sb, clazz, idclazz, idclasse);
		BackViewCadDupla.metodoPesquisar(sb, clazz, idclazz, idclasse);

		BackViewCadDupla.metodoRetorno(sb, clazz, idclazz, idclasse, clazzpai,
				idclazzpai, idclassepai);
		BackViewCadDupla.metodoImprimir(sb, clazz, idclazz, idclasse);

		BackViewCadDupla.metodoTrataVO(sb, clazzpai, idclazzpai, idclassepai);
		BackViewCadDupla.metodoTrataFilhoVO(sb, clazz, idclazz, idclasse);

		BackViewCadDupla.metodoSair(sb, clazz, idclazz, idclasse);
		BackViewCadDupla.metodoGetterSetterPropriedadesDeclaradas(sb, clazz,
				idclazz, idclasse);
		BackViewCadDupla.metodoGetterSetterPropriedadesDeclaradas(sb, clazzpai,
				idclazzpai, idclassepai);

		BackViewCadDupla.metodoCamposPesquisar(sb, clazz, idclazz, idclasse);
		BackViewCadDupla.metodoCamposPesquisar(sb, clazzpai, idclazzpai,
				idclassepai);
		BackViewCadDupla.metodoAddButton(sb, clazz, idclazz, idclasse);
		BackViewCadDupla.metodoAddButton(sb, clazzpai, idclazzpai, idclassepai);

		BackViewCadDupla.metodoFieldFindOnChange(sb, clazz, idclazz, idclasse);

		BackViewCadDupla.metodoFieldFindOnChange(sb, clazzpai, idclazzpai,
				idclassepai);
		sb.append(" }");

		System.out.println(sb.toString());
		GeraUtils.gravaArquivo(clazzpai.getSimpleName().replace("VO", "")
				+ "cad", GeraConfig.SOURCE + GeraConfig.DIRVIEW, sb.toString(),
				"java");
	}
}
