package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotattion para identificar a Entidade
 * 
 * @author diego
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ZKTelaForte {
	/**
	 * Label referente ao Nome da Entidade
	 * 
	 * @return
	 */
	public String label() default "";
}
