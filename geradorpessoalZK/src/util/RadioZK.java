package util;

import java.lang.reflect.Field;

import annotations.ZKRadioFix;

public class RadioZK {

	private String getRadioBox(Field field, String idtela) {
		String campo;
		campo = "";
		campo += "<radiogroup selectedItem=\"@{"
				+ idtela
				+ "."
				+ GeraUtils.uncapitalizeFirst(field.getDeclaringClass()
						.getSimpleName()) + "." + field.getName() + "}\"> ";
		int cont = 0;
		for (String s : field.getAnnotation(ZKRadioFix.class).values()) {
			String valor[] = s.split(":");
			if (cont == 0) {
				campo += "<radio label=\"" + valor[0]
						+ "\"  checked=\"true\" value=\"" + valor[1] + "\"/>";
			} else {
				campo += "<radio label=\"" + valor[0] + "\" value=\""
						+ valor[1] + "\"/>";
			}
			cont++;
		}
		campo += "  </radiogroup> ";

		return campo;

	}

	private String getRadioBoxFix(Field field, String idtela) {
		String campo;
		campo = "";
		campo += "<radiogroup selectedItem=\"@{"
				+ idtela
				+ "."
				+ GeraUtils.uncapitalizeFirst(field.getDeclaringClass()
						.getSimpleName()) + "." + field.getName() + "}\"> ";
		int cont = 0;
		for (String s : field.getAnnotation(ZKRadioFix.class).values()) {
			String valor[] = s.split(":");
			if (cont == 0) {
				campo += "<radio label=\"" + valor[0]
						+ "\"  checked=\"true\" value=\"" + valor[1] + "\"/>";
			} else {
				campo += "<radio label=\"" + valor[0] + "\" value=\""
						+ valor[1] + "\"/>";
			}
			cont++;
		}
		campo += "  </radiogroup> ";

		return campo;

	}
}
