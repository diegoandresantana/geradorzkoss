package view;

import java.lang.reflect.Field;
import java.util.List;

import util.CapturaTemplate;
import util.CodigoTela;
import util.CombosCadZK;
import util.CombosLisZK;
import util.FieldFindCadZK;
import util.FieldFindLisZK;
import util.FieldsCadZK;
import util.FieldsLisZK;
import util.GeraConfig;
import util.GeraUtils;
import util.IdentaCodigo;
import util.Tags;
import annotations.ZKCombo;
import annotations.ZKComboFix;
import annotations.ZKEntity;
import annotations.ZKField;
import annotations.ZKFieldFind;
import annotations.ZKId;
import annotations.ZKTelaForte;

public class Tela {


	public static void telacad(Class<?> clazz) {
		String prefixo = "wCad";
		String nomeEntidade = clazz.getSimpleName();
		String idtela = prefixo + nomeEntidade.replace("VO", "");
		String label=clazz.getAnnotation(ZKEntity.class).label();
		String sb="";
		
		sb=CapturaTemplate.ZULCAD;
		sb=sb.replace(Tags.nomeTela(),label);
		sb=sb.replace(Tags.idTela(),idtela);
		sb=sb.replace(Tags.nome(),nomeEntidade.replace("VO", ""));
		sb=sb.replace(Tags.camposTela(), IdentaCodigo.identaXML(CodigoTela.campoTelaCad(clazz, idtela)));	
		
		System.out.println(sb.toString());
		GeraUtils.gravaforms(clazz.getSimpleName().replace("VO", "") + "cad", sb.toString(), "zul");
	}

	public static void telalis(Class<?> clazz) {
		String prefixo = "wLis";
		String nomeEntidade = clazz.getSimpleName();
		String idtela = prefixo + nomeEntidade.replace("VO", "");
		String label=clazz.getAnnotation(ZKEntity.class).label();
		String sb="";
		
		sb=CapturaTemplate.ZULLIS;
		sb=sb.replace(Tags.nomeTela(),label);
		sb=sb.replace(Tags.idTela(),idtela);
		sb=sb.replace(Tags.nome(),nomeEntidade.replace("VO", ""));
		
		sb=sb.replace(Tags.itemLista(), CodigoTela.lisTela(clazz, idtela,nomeEntidade));

		
		
		System.out.println(sb.toString());
		GeraUtils.gravaforms(clazz.getSimpleName().replace("VO", "") + "lis", IdentaCodigo.identaXML(sb.toString()), "zul");

	}
	public static void telalisdynamic(Class<?> clazz) {
		String prefixo = "wLis";
		String nomeEntidade = clazz.getSimpleName();
		String idtela = prefixo + nomeEntidade.replace("VO", "");
		String label=clazz.getAnnotation(ZKEntity.class).label();
		String sb="";
		
		sb=CapturaTemplate.ZULLISDYNAMIC;
		sb=sb.replace(Tags.nomeTela(),label);
		sb=sb.replace(Tags.idTela(),idtela);
		sb=sb.replace(Tags.nome(),nomeEntidade.replace("VO", ""));
		
		sb=sb.replace(Tags.itemLista(), CodigoTela.lisTela(clazz, idtela,nomeEntidade));

		
		
		System.out.println(sb.toString());
		GeraUtils.gravaforms(clazz.getSimpleName().replace("VO", "") + "lis", IdentaCodigo.identaXML(sb.toString()), "zul");

	}
	public static void telacadDupla(Class<?> clazz) {
		Class clazzpai=GeraUtils.capturaField(clazz,ZKTelaForte.class).get(0).getType();
		String nomeEntidadepai = clazzpai.getSimpleName();
		String prefixo = "wCad";
		String nomeEntidadea = clazz.getSimpleName();
		String idtela = prefixo + nomeEntidadepai.replace("VO", "");
		
		StringBuilder sb = new StringBuilder();

		
		sb
				.append("<?page id=\""+nomeEntidadepai.replace("VO","")+"cadZul\" title=\" New ZUL Title\" cacheable=\"false\""
						+ "language=\"xul/html\" zscriptLanguage=\"Java\" contentType=\"text/html;charset=UTF-8\"?>");

		sb
				.append("<zk xmlns=\"http://www.zkoss.org/2005/zul\" "
						+ " xmlns:h=\"http://www.w3.org/1999/xhtml\" "
						+ " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-infabnce\" "
						+ " xsi:schemaLocation=\"http://www.zkoss.org/2005/zul http://www.zkoss.org/2005/zul/zul.xsd\"> ");

		sb
				.append("<window use=\""
						+ GeraConfig.PACOTEVIEW
						+ "."
						+ nomeEntidadepai.replace("VO", "")
						+ "cad\" id=\""
						+ idtela
						+ "\" title=\"Cadastro de "
						+ ((ZKEntity) clazzpai.getAnnotation(ZKEntity.class)).label()
						+ "\" border=\"normal\" "
						+ "	shadow=\"false\" width=\"550px\" position=\"center\" mode=\"overlapped\" "
						+ "	sizable=\"false\" closable=\"false\" onCreate=\""
						+ idtela + ".initComponentes()\"> ");
		
		sb.append(" <tabbox id=\"tb"+clazzpai.getSimpleName()+"\" height=\"100%\" orient=\"horizontal\">");
		  sb.append("	<tabs width=\"20px\">");
			sb.append("		<tab label=\""+((ZKEntity) clazzpai.getAnnotation(ZKEntity.class)).label()+"\" />");
				sb.append("		<tab label=\""+clazz.getAnnotation(ZKEntity.class).label()+"\" />			");
				sb.append("	</tabs>");
			sb.append("	<tabpanels>");
			sb.append("		<tabpanel>");
				
		sb.append("<separator height=\"10px\"/>");
		sb.append("	<grid width=\"90%\" align=\"center\">");
		sb.append("		<columns>");
		sb.append("			<column label=\"\" align=\"right\"/>");
		sb.append("			<column label=\"\" align=\"left\"/>");
		sb.append("		</columns>");
		sb.append("		<rows>");

		List<Field> fields = GeraUtils.getAllFields(clazzpai,null);
		for (Field f : fields) {
			if (f.getAnnotation(ZKId.class) != null) {
				sb.append("<row>");
				sb.append(f.getAnnotation(ZKField.class).label());
				sb.append(FieldsCadZK.contruirField(f, idtela));
				sb.append("</row>");
			}
		}
		for (Field f : fields) {
			if (f.getAnnotation(ZKId.class) == null) {
				if (f.getAnnotation(ZKField.class) != null) {
					sb.append("<row>");
					sb.append(f.getAnnotation(ZKField.class).label());
					sb.append(FieldsCadZK.contruirField(f, idtela));
					sb.append("</row>");
				} else if (f.getAnnotation(ZKCombo.class) != null) {
					sb.append("<row>");
					sb.append(f.getAnnotation(ZKCombo.class).label());
					sb.append(CombosCadZK.getComboBox(f, idtela));
					sb.append("</row>");
				} else if (f.getAnnotation(ZKFieldFind.class) != null) {
					sb.append("<row>");
					sb.append(f.getAnnotation(ZKFieldFind.class).label());
					sb.append(FieldFindCadZK.getFieldFind(f, idtela));
					sb.append("</row>");
				} else if (f.getAnnotation(ZKComboFix.class) != null) {
					sb.append("<row>");
					sb.append(f.getAnnotation(ZKComboFix.class).label());
					sb.append(CombosCadZK.getComboBoxFix(f, idtela));
					sb.append("</row>");
				}
			}
		}
		sb.append("</rows>");
		sb.append("</grid>");
		sb.append("</tabpanel>");
		sb.append("<tabpanel>");
		sb.append("<separator height=\"10px\"/>");
		sb.append("	<grid width=\"90%\" align=\"center\">");
		sb.append("		<columns>");
		sb.append("			<column label=\"\" align=\"right\"/>");
		sb.append("			<column label=\"\" align=\"left\"/>");
		sb.append("		</columns>");
		sb.append("		<rows>");

		List<Field> fields2 = GeraUtils.getAllFields(clazz,null);
		for (Field f : fields2) {
			if (f.getAnnotation(ZKId.class) != null) {
				sb.append("<row>");
				sb.append(f.getAnnotation(ZKField.class).label());
				sb.append(FieldsCadZK.contruirField(f, idtela));
				sb.append("</row>");
			}
		}
		for (Field f : fields2) {
			if (f.getAnnotation(ZKId.class) == null) {
				if (f.getAnnotation(ZKField.class) != null) {
					sb.append("<row>");
					sb.append(f.getAnnotation(ZKField.class).label());
					sb.append(FieldsCadZK.contruirField(f, idtela));
					sb.append("</row>");
				} else if (f.getAnnotation(ZKCombo.class) != null) {
					sb.append("<row>");
					sb.append(f.getAnnotation(ZKCombo.class).label());
					sb.append(CombosCadZK.getComboBox(f, idtela));
					sb.append("</row>");
				} else if (f.getAnnotation(ZKFieldFind.class) != null) {
					sb.append("<row>");
					sb.append(f.getAnnotation(ZKFieldFind.class).label());
					sb.append(FieldFindCadZK.getFieldFind(f, idtela));
					sb.append("</row>");
				} else if (f.getAnnotation(ZKComboFix.class) != null) {
					sb.append("<row>");
					sb.append(f.getAnnotation(ZKComboFix.class).label());
					sb.append(CombosCadZK.getComboBoxFix(f, idtela));
					sb.append("</row>");
				}
			}
		}
		sb.append("<row spans=\"2\" style=\"text-align:center\" >		");
		sb.append("<button image=\"/images/down.png\" onClick=\""+idtela+".addItem()\" />");
				sb.append("</row>");

		sb.append("</rows>");
		sb.append("</grid>");
		sb.append("<listbox model=\"@{" + idtela
				+ ".list"+clazz.getSimpleName()+"}\" width=\"600px\" height=\"200px\"");
		sb.append("   mold=\"paging\" rows=\"16\"  pageSize=\"10\" pagingPosition=\"top\"    checkmark=\"true\">");
		sb.append(" <listhead> ");

		for (Field f : fields2) {
			if (f.getAnnotation(ZKId.class) != null) {
				sb.append("<listheader label=\""
						+ f.getAnnotation(ZKField.class).label()
						+ "\"   width=\"150px\"/> ");
			}
		}
		for (Field f : fields2) {
			if (f.getAnnotation(ZKId.class) == null) {
				if (f.getAnnotation(ZKField.class) != null) {
					sb.append("<listheader label=\""
							+ f.getAnnotation(ZKField.class).label()
							+ "\"   width=\"150px\"/> ");
				} else if (f.getAnnotation(ZKCombo.class) != null) {
					sb.append("<listheader label=\""
							+ f.getAnnotation(ZKCombo.class).label()
							+ "\"   width=\"150px\"/> ");
				} else if (f.getAnnotation(ZKComboFix.class) != null) {
					sb.append("<listheader label=\""
							+ f.getAnnotation(ZKComboFix.class).label()
							+ "\"   width=\"150px\"/> ");
				} else if (f.getAnnotation(ZKFieldFind.class) != null) {
					sb.append("<listheader label=\""
							+ f.getAnnotation(ZKFieldFind.class).label()
							+ "\"   width=\"150px\"/> ");
				}
			}
		}
		sb.append("<listheader label=\"\" width=\"70px\" /> ");
		sb.append(" </listhead> ");
		String variavelListModel = clazz.getSimpleName().replace("VO", "")
				.toLowerCase();
		sb.append(" <listitem self=\"@{each=" + variavelListModel + "}\">  ");

		for (Field f : fields2) {
			if (f.getAnnotation(ZKId.class) != null) {
				sb.append("  <listcell	  label=\"@{" + variavelListModel
						+ "." + f.getName() + "}\" />  ");
			}
		}
		for (Field f : fields2) {
			if (f.getAnnotation(ZKId.class) == null) {
				if (f.getAnnotation(ZKField.class) != null) {
					sb.append("  <listcell	  label=\"@{" + variavelListModel
							+ "." + f.getName() + "}\" />  ");
				} else if (f.getAnnotation(ZKCombo.class) != null) {
					sb.append("  <listcell	  label=\"@{" + variavelListModel
							+ "." + f.getName() + "."
							+ f.getAnnotation(ZKCombo.class).propDisplayCombo()
							+ "}\" />  ");
				} else if (f.getAnnotation(ZKFieldFind.class) != null) {
					sb.append("  <listcell	  label=\"@{"
							+ variavelListModel
							+ "."
							+ f.getName()
							+ "."
							+ f.getAnnotation(ZKFieldFind.class)
									.propDisplayFieldFind() + "}\" />  ");
				} else if (f.getAnnotation(ZKComboFix.class) != null) {
					sb.append("  <listcell	  label=\"@{" + variavelListModel
							+ "." + f.getName() + "}\" />  ");
				}
			}
		}
		sb.append("  <listcell label=\"\"  >");
		sb.append("  <image src=\"/images/delete.png\" onClick=\""+idtela+".removerItem(self.parent.parent.value)\" />	");
		sb.append(" </listcell>");
		sb.append("  </listitem>");
		sb.append("  </listbox>");
		sb.append("</tabpanel>	");	
		sb.append("</tabpanels>");
		sb.append("</tabbox> ");
		sb.append("<separator height=\"10px\"/>");
		sb.append("</window>");
		sb.append("</zk>");
		System.out.println(sb.toString());
		GeraUtils.gravaforms(clazzpai.getSimpleName().replace("VO", "") + "cad", sb.toString(), "zul");
	}
}
