package util;

import java.lang.reflect.Field;

import annotations.ZKComboFix;
import annotations.ZKCombo;

public class CombosLisZK {
	/**
	 * Este metodo gera o campo ComboBox
	 * 
	 * @author diego
	 * @param field
	 * @param idtela
	 * @param nomeEntidade 
	 * @return campo completo
	 */
	public static String getComboBox(Field field, String idtela, String nomeEntidade) {
		String campo = "";

		campo += "\n <combobox id=\"" + field.getName() + "\" " + "model=\"@{"
				+ idtela + ".lm" + field.getName()
				+ "}\" name=\"" + field.getName() + "\" selectedItem=\"@{"
				+ idtela + "." + field.getName() + "}\"";
		if (!field.getAnnotation(ZKCombo.class).tooltip().equals("")) {
			campo += " tooltiptext=\"" + field.getAnnotation(ZKCombo.class).tooltip()
					+ "\" ";
		}
		
		campo += " width=\"" + field.getAnnotation(ZKCombo.class).width()
					+ "px\" ";
		
		campo+=">";
				
		campo+= "	<comboitem self=\"@{each=" + field.getName().toLowerCase()
				+ "}\" value=\"@{" + field.getName().toLowerCase()
				+ "}\" label=\"@{" + field.getName().toLowerCase() + "."
				+ field.getAnnotation(ZKCombo.class).propDisplayCombo()
				+ "}\"/>";
		campo += "</combobox>";

		return campo;
	}

	/**
	 * Este metodo gera o campo ComboBox Valores Fixos
	 * 
	 * @author diego
	 * @param field
	 * @param idtela
	 * @param nomeEntidade 
	 * @return campo completo
	 */
	public static String getComboBoxFix(Field field, String idtela, String nomeEntidade) {
		String campo = "";

		campo += "\n <combobox id=\""
				+ field.getName()
				+ "\"  name=\""
				+ field.getName()
				+ "\" selectedItem=\"@{"
				+ idtela
				+ "."
				+ GeraUtils.uncapitalizeFirst(field.getDeclaringClass()
						.getSimpleName()) + "." + field.getName() + "}\"";
		if (!field.getAnnotation(ZKComboFix.class).tooltip().equals("")) {
			campo += " tooltiptext=\"" + field.getAnnotation(ZKComboFix.class).tooltip()
					+ "\" ";
		}
		
		campo += " width=\"" + field.getAnnotation(ZKComboFix.class).width()
					+ "px\" ";
		
		campo+=">";
		String[] propriedades = {}, valores = {};
		valores = field.getAnnotation(ZKComboFix.class).values();
		propriedades = field.getAnnotation(ZKComboFix.class).properties();
		if (propriedades.length == valores.length) {
			for (int i = 0; i < valores.length; i++) {
				campo += "\n	<comboitem  value=\"" + valores[i] + "\" label=\""
						+ propriedades[i] + "\"/>";
			}

		}

		campo += "\n</combobox>";

		return campo;
	}
}
