package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation para identificar campo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ZKFieldFind {
	/**
	 * Label Referente ao campo
	 * 
	 * @author diego
	 * @return
	 */
	public String label() default "";

	/**
	 * Mostra se é possivel nulo ou não
	 * 
	 * @author diego
	 * @return
	 */
	public boolean nullable() default true;

	
	/**
	 * Propriedade a ser exibida no display do FieldFind
	 * 
	 * @return
	 */
	public String propDisplayFieldFind() default "";
	/**
	 * adiciona botao para facilitar a usabilidade, para que seja possivel adicionar item em determinado combo e field find
	 */
	public boolean addbutton() default true;

	/**
	 * Tooltip
	 * 
	 * @author diego
	 * @return
	 */
	public String tooltip() default "";
	
	
}
