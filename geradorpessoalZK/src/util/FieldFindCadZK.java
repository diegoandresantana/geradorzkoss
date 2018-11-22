package util;

import java.lang.reflect.Field;

import annotations.ZKField;
import annotations.ZKFieldFind;
import annotations.ZKId;

public class FieldFindCadZK {
	/**
	 * Este metodo gera o campo FieldFind
	 * 
	 * @author diego
	 * @param field
	 * @param idtela
	 * @return campo completo
	 */
	public static String getFieldFind(Field field, String idtela) {
		String campo = "";
		Field campoid = null;
		for (Field f : field.getType().getDeclaredFields()) {
			if (f.getAnnotation(ZKId.class) != null) {
				campoid = f;
				break;
			}
		}

		campo += "\n<hbox>\n 	"
				+ contruirField(campoid, idtela
						+ "."
						+ field.getName() + "." + campoid.getName(),field.getType(),idtela);
		
		campo += "\n <textbox value=\"@{"
				+ idtela
				+ "."
				+ field.getName()
				+ "."
				+ field.getAnnotation(ZKFieldFind.class).propDisplayFieldFind()
				+ "}\" "
				+ " readonly=\"true\" width=\"250px\" />"
				+ "\n<image src=\"/images/imgBuscar.png\" tooltiptext=\"Pesquisar "
				+ field.getAnnotation(ZKFieldFind.class).label() + "\" "
				+ "	   onClick=\"" + idtela + ".pesquisar"
				+ field.getType().getSimpleName() + "()\"/>" ;
				if(field.getAnnotation(ZKFieldFind.class).addbutton())
					campo += "<image src=\"/images/btnIncluir.png\" onClick=\""+idtela+".add"+field.getType().getSimpleName()+"()\"/>";
		
		campo +=	"\n</hbox> ";
		return campo;
	}

	/**
	 * Metodo que escolhe qual a melhor opção de campo para construir e retornar
	 * 
	 * @param field
	 *            -campo a ser gerado
	 * @param idtela 
	 * @param idtela
	 *            -id usado no zul para os campos
	 * @return campo para o Zull
	 */
	public static String contruirField(Field field, String idtelaecampo,Class clazz, String idtela) {

		if (field.getType().getName().equals("java.lang.Integer")) {
			return getIntBox(field, idtelaecampo,clazz,idtela);
		}
		if (field.getType().getName().equals("java.lang.Double")) {
			return getDoubleBox(field, idtelaecampo,clazz,idtela);
		}
		if (field.getType().getName().equals("java.util.Date")) {
			return getDateBox(field, idtelaecampo,clazz,idtela);
		}
		if (field.getType().getName().equals("java.math.BigDecimal")) {
			return getBigDecimal(field, idtelaecampo,clazz,idtela);
		}
		if (field.getType().getName().equals("java.lang.Long")) {
			return getLongBox(field, idtelaecampo,clazz,idtela);
		}
		if (field.getType().getName().equals("java.lang.String")) {
			return getTexBox(field, idtelaecampo,clazz,idtela);
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
	private static String getTexBox(Field field, String idtela, Class clazz, String idtelacad) {
		String campo = "";
		campo += "\n<textbox id=\"" + field.getName() + "\" value=\"@{"
				+ idtela + "}\" " + " width=\"60px\" ";

		if (field.getAnnotation(ZKField.class).maxsize() > 0) {
			campo += "maxlength=\""
					+ field.getAnnotation(ZKField.class).maxsize() + "\" ";
		}

		if (!field.getAnnotation(ZKField.class).mask().equals("")) {
			campo += " format=\"" + field.getAnnotation(ZKField.class).mask()
					+ "\" ";
		}
		if (!field.getAnnotation(ZKField.class).tooltip().equals("")) {
			campo += " tooltiptext=\"" + field.getAnnotation(ZKField.class).tooltip()
					+ "\" ";
		}
		campo += "  onOK=\"" + idtelacad + ".buscaPorCod"+ clazz.getSimpleName() + "()\" ";
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
	private static String getBigDecimal(Field field, String idtela, Class clazz, String idtelacad) {
		String campo = "";
		campo += "\n<decimalbox id=\"" + field.getName() + "\" value=\"@{"
				+ idtela + "}\" " + " width=\"60px\" ";

		if (field.getAnnotation(ZKField.class).maxsize() > 0) {
			campo += "maxlength=\""
					+ field.getAnnotation(ZKField.class).maxsize() + "\" ";
		}

		if (!field.getAnnotation(ZKField.class).mask().equals("")) {
			campo += " format=\"" + field.getAnnotation(ZKField.class).mask()
					+ "\" ";
		}
		if (!field.getAnnotation(ZKField.class).tooltip().equals("")) {
			campo += " tooltiptext=\"" + field.getAnnotation(ZKField.class).tooltip()
					+ "\" ";
		}
		campo += "  onOK=\"" + idtelacad + ".buscaPorCod"+ clazz.getSimpleName() + "()\" ";
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
	private static String getDoubleBox(Field field, String idtela, Class clazz, String idtelacad) {
		String campo = "";
		campo += "\n<doublebox id=\"" + field.getName() + "\"  value=\"@{"
				+ idtela + "}\" " + " width=\"60px\" ";

		if (field.getAnnotation(ZKField.class).maxsize() > 0) {
			campo += "maxlength=\""
					+ field.getAnnotation(ZKField.class).maxsize() + "\" ";
		}

		if (!field.getAnnotation(ZKField.class).mask().equals("")) {
			campo += " format=\"" + field.getAnnotation(ZKField.class).mask()
					+ "\" ";
		}
		if (!field.getAnnotation(ZKField.class).tooltip().equals("")) {
			campo += " tooltiptext=\"" + field.getAnnotation(ZKField.class).tooltip()
					+ "\" ";
		}
		campo += "  onOK=\"" + idtelacad + ".buscaPorCod"+ clazz.getSimpleName() + "()\" ";
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
	 * @param clazz 
	 * @param idtelacad 
	 * @return campo completo
	 */
	private static String getIntBox(Field field, String idtela, Class clazz, String idtelacad) {
		String campo = "";
		campo += " \n<intbox id=\"" + field.getName() + "\"  value=\"@{"
				+ idtela + "}\" " + " width=\"60px\" ";

		if (field.getAnnotation(ZKField.class).maxsize() > 0) {
			campo += "maxlength=\""
					+ field.getAnnotation(ZKField.class).maxsize() + "\" ";
		}

		if (!field.getAnnotation(ZKField.class).mask().equals("")) {
			campo += " format=\"" + field.getAnnotation(ZKField.class).mask()
					+ "\" ";
		}
		if (!field.getAnnotation(ZKField.class).tooltip().equals("")) {
			campo += " tooltiptext=\"" + field.getAnnotation(ZKField.class).tooltip()
					+ "\" ";
		}
		campo += "  onOK=\"" + idtelacad + ".buscaPorCod"+ clazz.getSimpleName() + "()\" ";
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
	private static String getLongBox(Field field, String idtela, Class clazz, String idtelacad) {
		String campo = "";
		campo += " \n<longbox id=\"" + field.getName() + "\"  value=\"@{"
				+ idtela + "}\" " + " width=\"60px\" ";

		if (field.getAnnotation(ZKField.class).maxsize() > 0) {
			campo += "maxlength=\""
					+ field.getAnnotation(ZKField.class).maxsize() + "\" ";
		}

		if (!field.getAnnotation(ZKField.class).mask().equals("")) {
			campo += " format=\"" + field.getAnnotation(ZKField.class).mask()
					+ "\" ";
		}
		if (!field.getAnnotation(ZKField.class).tooltip().equals("")) {
			campo += " tooltiptext=\"" + field.getAnnotation(ZKField.class).tooltip()
					+ "\" ";
		}
		campo += "  onOK=\"" + idtelacad + ".buscaPorCod"+ clazz.getSimpleName() + "()\" ";
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
	private static String getDateBox(Field field, String idtela, Class clazz, String idtelacad) {
		String campo = "";
		campo += " \n <datebox id=\"" + field.getName() + "\"  value=\"@{"
				+ idtela + "}\" " + " width=\"60px\" ";

		if (field.getAnnotation(ZKField.class).maxsize() > 0) {
			campo += " maxlength=\""
					+ field.getAnnotation(ZKField.class).maxsize() + "\" ";
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
		campo += "  onOK=\"" + idtelacad + ".buscaPorCod"+clazz.getSimpleName() + "()\" ";
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
	private String getTimeBox(Field field, String idtela, Class clazz, String idtelacad) {
		String campo = "";
		campo += " <timebox id=\"" + field.getName()
				+ "\" mold=\"rounded\" value=\"@{" + idtela + "}\" "
				+ " width=\"60px\" ";
		if (field.getAnnotation(ZKField.class).maxsize() > 0) {
			campo += " maxlength=\""
					+ field.getAnnotation(ZKField.class).maxsize() + "\" ";
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
		campo += "  onOK=\"" + idtelacad + ".buscaPorCod"+ clazz.getSimpleName() + "()\" ";
		campo += " />";
		return campo;
	}

}
