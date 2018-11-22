package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation para Identificar Radio
 * 
 * @author diego
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ZKRadio {
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
	public String propDisplayRadio() default "";

	/**
	 * Mostra se é possivel nulo ou não
	 * 
	 * @return
	 */
	public boolean nullable() default true;
}
