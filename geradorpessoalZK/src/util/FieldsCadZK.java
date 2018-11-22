package util;

import java.lang.reflect.Field;

import annotations.ZKField;

/**
 * Classe responsavel por fazer os campos do formulario .zul
 * 
 * @author diego
 * 
 */
public class FieldsCadZK {
	/**
	 * Metodo que escolhe qual a melhor opção de campo para construir e retornar
	 * 
	 * @param field
	 *            -campo a ser gerado
	 * @param idtela
	 *            -id usado no zul para os campos
	 * @return campo para o Zull
	 */
	public static String contruirField(Field field, String idtela) {

		if (field.getType().getName().equals("java.lang.Integer")) {
			return getIntBox(field, idtela);
		}
		if (field.getType().getName().equals("java.lang.Double")) {
			return getDoubleBox(field, idtela);
		}
		if (field.getType().getName().equals("java.util.Date")) {
			return getDateBox(field, idtela);
		}
		if (field.getType().getName().equals("java.math.BigDecimal")) {
			return getBigDecimal(field, idtela);
		}
		if (field.getType().getName().equals("java.lang.Long")) {
			return getLongBox(field, idtela);
		}
		if (field.getType().getName().equals("java.lang.String")) {
			return getTexBox(field, idtela);
		}

		return "";

	}

	/**
	 * Este metodo gera o campo TexBox.
	 * 
	 * @author diego
	 * @param field
	 *            -campo a ser gerado
	 * @param idtela
	 *            -id usado no zul para os campos
	 * @return campo completo
	 */
	private static String getTexBox(Field field, String idtela) {
		String campo = "";
		campo += "<textbox id=\""
				+ field.getName()
				+ "\" value=\"@{"
				+ idtela
				+ "."
				+ GeraUtils.uncapitalizeFirst(field.getDeclaringClass()
						.getSimpleName()) + "." + field.getName() + "}\" ";
		if (field.getAnnotation(ZKField.class).readonly() == true) {
			campo += " readonly=\""
					+ field.getAnnotation(ZKField.class).readonly() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).disable()== true) {
			campo += " disabled=\""
					+ field.getAnnotation(ZKField.class).disable() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).maxsize() > 0) {
			campo += "maxlength=\""
					+ field.getAnnotation(ZKField.class).maxsize() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).nullable() == false) {
			campo += " constraint=\"no empty:Entre com a "
					+ field.getAnnotation(ZKField.class).label() + "\"";
		}
		if (!field.getAnnotation(ZKField.class).mask().equals("")) {
			campo += " format=\"" + field.getAnnotation(ZKField.class).mask()
					+ "\" ";
		}
		if (!field.getAnnotation(ZKField.class).tooltip().equals("")) {
			campo += " tooltiptext=\"" + field.getAnnotation(ZKField.class).tooltip()
					+ "\" ";
		}
		
			campo += " width=\"" + field.getAnnotation(ZKField.class).width()
					+ "px\" ";
		
		campo += " />";
		return campo;
	}

	/**
	 * Este metodo gera o campo BigDecimal.
	 * 
	 * @author diego
	 * @param field
	 *            -campo a ser gerado
	 * @param idtela
	 *            -id usado no zul para os campos
	 * @return campo completo
	 */
	private static String getBigDecimal(Field field, String idtela) {
		String campo = "";
		campo += "<decimalbox id=\""
				+ field.getName()
				+ "\" value=\"@{"
				+ idtela
				+ "."
				+ GeraUtils.uncapitalizeFirst(field.getDeclaringClass()
						.getSimpleName()) + "." + field.getName() + "}\" ";
		if (field.getAnnotation(ZKField.class).readonly() == true) {
			campo += " readonly=\""
					+ field.getAnnotation(ZKField.class).readonly() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).disable()== true) {
			campo += " disabled=\""
					+ field.getAnnotation(ZKField.class).disable() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).maxsize() > 0) {
			campo += " maxlength=\""
					+ field.getAnnotation(ZKField.class).maxsize() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).nullable() == false) {
			campo += " constraint=\"no empty:Entre com a "
					+ field.getAnnotation(ZKField.class).label() + "\"";
		}
		if (!field.getAnnotation(ZKField.class).mask().equals("")) {
			campo += " format=\"" + field.getAnnotation(ZKField.class).mask()
					+ "\" ";
		}
		if (!field.getAnnotation(ZKField.class).tooltip().equals("")) {
			campo += " tooltiptext=\"" + field.getAnnotation(ZKField.class).tooltip()
					+ "\" ";
		}
		
		campo += " width=\"" + field.getAnnotation(ZKField.class).width()
					+ "px\" ";
		campo += " />";

		return campo;

	}

	/**
	 * Este metodo gera o campo Double.
	 * 
	 * @author diego
	 * @param field
	 *            -campo a ser gerado
	 * @param idtela
	 *            -id usado no zul para os campos
	 * @return campo completo
	 */
	private static String getDoubleBox(Field field, String idtela) {
		String campo = "";
		campo += "<doublebox id=\""
				+ field.getName()
				+ "\" value=\"@{"
				+ idtela
				+ "."
				+ GeraUtils.uncapitalizeFirst(field.getDeclaringClass()
						.getSimpleName()) + "." + field.getName() + "}\" ";
		if (field.getAnnotation(ZKField.class).readonly() == true) {
			campo += " readonly=\""
					+ field.getAnnotation(ZKField.class).readonly() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).disable()== true) {
			campo += " disabled=\""
					+ field.getAnnotation(ZKField.class).disable() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).maxsize() > 0) {
			campo += " maxlength=\""
					+ field.getAnnotation(ZKField.class).maxsize() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).nullable() == false) {
			campo += " constraint=\"no empty:Entre com a "
					+ field.getAnnotation(ZKField.class).label() + "\"";
		}
		if (!field.getAnnotation(ZKField.class).mask().equals("")) {
			campo += " format=\"" + field.getAnnotation(ZKField.class).mask()
					+ "\" ";
		}
		if (!field.getAnnotation(ZKField.class).tooltip().equals("")) {
			campo += " tooltiptext=\"" + field.getAnnotation(ZKField.class).tooltip()
					+ "\" ";
		}
		
			campo += " width=\"" + field.getAnnotation(ZKField.class).width()
					+ "px\" ";
		campo += " />";

		return campo;

	}

	/**
	 * Este metodo gera o campo IntBox.
	 * 
	 * @author diego
	 * @param field
	 *            -campo a ser gerado
	 * @param idtela
	 *            -id usado no zul para os campos
	 * @return campo completo
	 */
	private static String getIntBox(Field field, String idtela) {
		String campo = "";
		campo += " <intbox id=\""
				+ field.getName()
				+ "\" value=\"@{"
				+ idtela
				+ "."
				+ GeraUtils.uncapitalizeFirst(field.getDeclaringClass()
						.getSimpleName()) + "." + field.getName() + "}\" ";
		if (field.getAnnotation(ZKField.class).readonly() == true) {
			campo += " readonly=\""
					+ field.getAnnotation(ZKField.class).readonly() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).disable()== true) {
			campo += " disabled=\""
					+ field.getAnnotation(ZKField.class).disable() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).maxsize() > 0) {
			campo += "maxlength=\""
					+ field.getAnnotation(ZKField.class).maxsize() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).nullable() == false) {
			campo += " constraint=\"no empty:Entre com a "
					+ field.getAnnotation(ZKField.class).label() + "\"";
		}
		if (!field.getAnnotation(ZKField.class).mask().equals("")) {
			campo += " format=\"" + field.getAnnotation(ZKField.class).mask()
					+ "\" ";
		}
		if (!field.getAnnotation(ZKField.class).tooltip().equals("")) {
			campo += " tooltiptext=\"" + field.getAnnotation(ZKField.class).tooltip()
					+ "\" ";
		}
		
			campo += " width=\"" + field.getAnnotation(ZKField.class).width()
					+ "px\" ";
		campo += " />";
		return campo;

	}

	/**
	 * Este metodo gera o campo LongBox.
	 * 
	 * @author diego
	 * @param field
	 *            -campo a ser gerado
	 * @param idtela
	 *            -id usado no zul para os campos
	 * @return campo completo
	 */
	private static String getLongBox(Field field, String idtela) {
		String campo = "";
		campo += " <longbox id=\""
				+ field.getName()
				+ "\" value=\"@{"
				+ idtela
				+ "."
				+ GeraUtils.uncapitalizeFirst(field.getDeclaringClass()
						.getSimpleName()) + "." + field.getName() + "}\" ";

		if (field.getAnnotation(ZKField.class).readonly() == true) {
			campo += " readonly=\""
					+ field.getAnnotation(ZKField.class).readonly() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).disable()== true) {
			campo += " disabled=\""
					+ field.getAnnotation(ZKField.class).disable() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).maxsize() > 0) {
			campo += "maxlength=\""
					+ field.getAnnotation(ZKField.class).maxsize() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).nullable() == false) {
			campo += " constraint=\"no empty:Entre com a "
					+ field.getAnnotation(ZKField.class).label() + "\"";
		}
		if (!field.getAnnotation(ZKField.class).mask().equals("")) {
			campo += " format=\"" + field.getAnnotation(ZKField.class).mask()
					+ "\" ";
		}
		if (!field.getAnnotation(ZKField.class).tooltip().equals("")) {
			campo += " tooltiptext=\"" + field.getAnnotation(ZKField.class).tooltip()
					+ "\" ";
		}
		
			campo += " width=\"" + field.getAnnotation(ZKField.class).width()
					+ "px\" ";
		campo += " />";
		return campo;

	}

	/**
	 * Este metodo gera o campo DateBox.
	 * 
	 * @author diego
	 * @param field
	 *            -campo a ser gerado
	 * @param idtela
	 *            -id usado no zul para os campos
	 * @return campo completo
	 */
	private static String getDateBox(Field field, String idtela) {
		String campo = "";
		campo += " <datebox id=\""
				+ field.getName()
				+ "\" value=\"@{"
				+ idtela
				+ "."
				+ GeraUtils.uncapitalizeFirst(field.getDeclaringClass()
						.getSimpleName()) + "." + field.getName() + "}\" ";
		if (field.getAnnotation(ZKField.class).readonly() == true) {
			campo += " readonly=\""
					+ field.getAnnotation(ZKField.class).readonly() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).disable()== true) {
			campo += " disabled=\""
					+ field.getAnnotation(ZKField.class).disable() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).maxsize() > 0) {
			campo += " maxlength=\""
					+ field.getAnnotation(ZKField.class).maxsize() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).nullable() == false) {
			campo += " constraint=\"no empty:Entre com a "
					+ field.getAnnotation(ZKField.class).label() + "\"";
		}
		if (!field.getAnnotation(ZKField.class).mask().equals("")) {
			campo += " format=\"" + field.getAnnotation(ZKField.class).mask()
					+ "\" ";
		} else {
			campo += " format=\"dd/MM/yyyy\" ";
		}
		if (!field.getAnnotation(ZKField.class).tooltip().equals("")) {
			campo += " tooltiptext=\"" + field.getAnnotation(ZKField.class).tooltip()
					+ "\" ";
		}
		
			campo += " width=\"" + field.getAnnotation(ZKField.class).width()
					+ "px\" ";
		campo += " />";
		return campo;

	}

	/**
	 * Este metodo gera o campo TimeBox.
	 * 
	 * @author diego
	 * @param field
	 *            -campo a ser gerado
	 * @param idtela
	 *            -id usado no zul para os campos
	 * @return campo inteiro
	 */
	private String getTimeBox(Field field, String idtela) {
		String campo = "";
		campo += " <timebox id=\""
				+ field.getName()
				+ "\" mold=\"rounded\" value=\"@{"
				+ idtela
				+ "."
				+ GeraUtils.uncapitalizeFirst(field.getDeclaringClass()
						.getSimpleName()) + "." + field.getName() + "}\" ";
		if (field.getAnnotation(ZKField.class).readonly() == true) {
			campo += " readonly=\""
					+ field.getAnnotation(ZKField.class).readonly() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).disable()== true) {
			campo += " disabled=\""
					+ field.getAnnotation(ZKField.class).disable() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).maxsize() > 0) {
			campo += " maxlength=\""
					+ field.getAnnotation(ZKField.class).maxsize() + "\" ";
		}
		if (field.getAnnotation(ZKField.class).nullable() == false) {
			campo += " constraint=\"no empty:Entre com a "
					+ field.getAnnotation(ZKField.class).label() + "\"";
		}
		if (!field.getAnnotation(ZKField.class).mask().equals("")) {
			campo += " format=\"" + field.getAnnotation(ZKField.class).mask()
					+ "\" ";
		} else {
			campo += " format=\"a hh:mm:ss\" ";
		}
		if (!field.getAnnotation(ZKField.class).tooltip().equals("")) {
			campo += " tooltiptext=\"" + field.getAnnotation(ZKField.class).tooltip()
					+ "\" ";
		}
		
			campo += " width=\"" + field.getAnnotation(ZKField.class).width()
					+ "px\" ";
		campo += " />";
		return campo;
	}

	/*
	 * private String getCheckBox(Field field,String idtela) { String campo;
	 * <radiogroup selectedItem="@{wNfi.nfi.aetnfi}"> <radio label="Sim"
	 * value="S"/> <radio label="Não" value="N"/> <radio
	 * label="Aguardando Conferência" value="A"/> </radiogroup> return campo;
	 * 
	 * }
	 */
	/*
	 * private String getCheckBox(Field field,String idtela) {
	 * 
	 * <checkbox checked="false" onCheck="bd.autodrop = self.checked"
	 * label="auto drop popup when typing" /> }
	 */
}
