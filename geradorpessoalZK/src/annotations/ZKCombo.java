package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation para Identificar Combos
 * 
 * @author diego
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ZKCombo {
	/**
	 * Label referente ao combo
	 * 
	 * @return
	 */
	public String label() default "";

	/**
	 * Propriedade a ser exibida no display do Combo
	 * 
	 * @return
	 */
	public String propDisplayCombo() default "";

	/**
	 * Mostra se é possivel nulo ou não
	 * 
	 * @return
	 */
	public boolean nullable() default true;
	/**
	 * adiciona botao para facilitar a usabilidade, para que seja possivel adicionar item em determinado combo e field find
	 */
	public boolean addbutton() default true;
	/**
	 * Tamanho do campo em pixel
	 * 
	 * @author diego
	 * @return
	 */
	public int width() default 150;
	/**
	 * Tooltip
	 * 
	 * @author diego
	 * @return
	 */
	public String tooltip() default "";
}
