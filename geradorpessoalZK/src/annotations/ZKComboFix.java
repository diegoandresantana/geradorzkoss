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
public @interface ZKComboFix {
	/**
	 * Label referente ao combo
	 * 
	 * @return
	 */
	public String label() default "";

	/**
	 * Propriedade ex:{"Em Andamento","Concluido","Cancelado"}
	 * 
	 * @return
	 */
	public String[] properties() default {};

	/**
	 * Propriedade de valores ex:{"0","1","2"}
	 * 
	 * @return
	 */
	public String[] values() default {};

	/**
	 * Mostra se é possivel nulo ou não
	 * 
	 * @return
	 */
	public boolean nullable() default true;
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
