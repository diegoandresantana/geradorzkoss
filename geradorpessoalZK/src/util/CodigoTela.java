package util;

import java.lang.reflect.Field;
import java.util.List;

import annotations.ZKCombo;
import annotations.ZKComboFix;
import annotations.ZKField;
import annotations.ZKFieldFind;
import annotations.ZKId;

public class CodigoTela {

	
	public static String campoTelaCad(Class clazz,String idtela){
		StringBuilder sb=new StringBuilder();

		List<Field> fields = GeraUtils.getAllFields(clazz,null);
		for (Field f : fields) {
			if (f.getAnnotation(ZKId.class) != null) {
				sb.append("<row>");
				
				sb.append("<hbox>");
				sb.append("<label value=\""+f.getAnnotation(ZKField.class).label()+"\" />");
				if(f.getAnnotation(ZKField.class).nullable()==false){
				   sb.append("<label style=\"color:red\" value=\"*\" />");				}
				
				sb.append("</hbox>");
				sb.append(""+FieldsCadZK.contruirField(f, idtela));
				sb.append("</row>");
			}
		}
		for (Field f : fields) {
			if (f.getAnnotation(ZKId.class) == null) {
				if (f.getAnnotation(ZKField.class) != null) {
					sb.append("<row>");
					sb.append("<hbox>");
					sb.append("<label value=\""+f.getAnnotation(ZKField.class).label()+"\" />");
					if(f.getAnnotation(ZKField.class).nullable()==false){
					   sb.append("<label style=\"color:red\" value=\"*\" />");				}
					
					sb.append("</hbox>");
					sb.append(FieldsCadZK.contruirField(f, idtela));
					sb.append("</row>");
				} else if (f.getAnnotation(ZKCombo.class) != null) {
					sb.append("<row>");
					sb.append("<hbox>");
					sb.append("<label value=\""+f.getAnnotation(ZKCombo.class).label()+"\" />");
					if(f.getAnnotation(ZKCombo.class).nullable()==false){
					   sb.append("<label style=\"color:red\" value=\"*\" />");				}
				
					sb.append("</hbox>");
					sb.append(CombosCadZK.getComboBox(f, idtela));
					sb.append("</row>");
				} else if (f.getAnnotation(ZKFieldFind.class) != null) {
					sb.append("<row>");
					sb.append("<hbox>");
					sb.append("<label value=\""+f.getAnnotation(ZKFieldFind.class).label()+"\" />");
					if(f.getAnnotation(ZKFieldFind.class).nullable()==false){
					   sb.append("<label style=\"color:red\" value=\"*\" />");
					   }
					
					sb.append("</hbox>");
					sb.append(FieldFindCadZK.getFieldFind(f, idtela));
					sb.append("</row>");
				} else if (f.getAnnotation(ZKComboFix.class) != null) {
					sb.append("<row>");
					sb.append("<hbox>");
					sb.append("<label value=\""+f.getAnnotation(ZKComboFix.class).label()+"\" />");
					if(f.getAnnotation(ZKComboFix.class).nullable()==false){
					   sb.append("<label style=\"color:red\" value=\"*\" />");				}
					
					sb.append("</hbox>");
					sb.append(CombosCadZK.getComboBoxFix(f, idtela));
					sb.append("</row>");
				}
			}
		}
		return sb.toString();
		
	}

	public static String lisTela(Class clazz,String idtela,String nomeEntidade){
		StringBuilder sb=new StringBuilder();

		
		
		
		sb.append(" <listhead> ");
		List<Field> fields = GeraUtils.getAllFields(clazz, null);
		for (Field f : fields) {
			if (f.getAnnotation(ZKId.class) != null) {
		
				sb.append("<listheader width=\""+(f.getAnnotation(ZKField.class).width()+10)+"px\" >");
				sb.append("<vbox>");
				sb.append("<label value=\""+f.getAnnotation(ZKField.class).label()+"\"/>");
				sb.append(FieldsLisZK.contruirField(f, idtela,nomeEntidade));				
				sb.append("</vbox> ");
				sb.append("</listheader>");
			}
		}
		for (Field f : fields) {
			if (f.getAnnotation(ZKId.class) == null) {
				if (f.getAnnotation(ZKField.class) != null) {
					sb.append("<listheader width=\""+(f.getAnnotation(ZKField.class).width()+10)+"px\" >");
					sb.append("<vbox>");
					sb.append("<label value=\""+f.getAnnotation(ZKField.class).label()+"\"/>");
					sb.append(FieldsLisZK.contruirField(f, idtela,nomeEntidade));				
					sb.append("</vbox> ");
					sb.append("</listheader>");
				} else if (f.getAnnotation(ZKCombo.class) != null) {
					sb.append("<listheader width=\""+(f.getAnnotation(ZKCombo.class).width()+33)+"px\" >");
					sb.append("<vbox>");
					sb.append("<label value=\""+f.getAnnotation(ZKCombo.class).label()+"\"/>");
					sb.append(CombosLisZK.getComboBox(f, idtela,nomeEntidade));
					sb.append("</vbox> ");
					sb.append("</listheader>");
					
				} else if (f.getAnnotation(ZKComboFix.class) != null) {
					sb.append("<listheader width=\""+(f.getAnnotation(ZKComboFix.class).width()+33)+"px\" >");
					sb.append("<vbox>");
					sb.append("<label value=\""+f.getAnnotation(ZKComboFix.class).label()+"\"/>");
					sb.append(CombosLisZK.getComboBoxFix(f, idtela,nomeEntidade));
					sb.append("</vbox> ");
					sb.append("</listheader>");
				} else if (f.getAnnotation(ZKFieldFind.class) != null) {
					sb.append("<listheader width=\"200px\" >");
					sb.append("<vbox>");
					sb.append("<label value=\""+f.getAnnotation(ZKFieldFind.class).label()+"\"/>");
					sb.append(FieldFindLisZK.getFieldFind(f, idtela,nomeEntidade));
					sb.append("</vbox> ");
					sb.append("</listheader>");
				}
			}
		}

		sb.append(" </listhead> ");
		
		
		
		
		String variavelListModel = clazz.getSimpleName().replace("VO", "")
				.toLowerCase();
		sb.append(" <listitem self=\"@{each=" + variavelListModel + "}\">  ");

		for (Field f : fields) {
			if (f.getAnnotation(ZKId.class) != null) {
				sb.append("  <listcell	  label=\"@{" + variavelListModel
						+ "." + f.getName() + "}\" />  ");
			}
		}
		for (Field f : fields) {
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
		sb.append("  </listitem>");
	

		return sb.toString();
		
	}
}
