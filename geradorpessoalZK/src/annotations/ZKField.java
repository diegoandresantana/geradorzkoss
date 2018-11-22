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
public @interface ZKField {
	/**
	 * Label Referente ao campo
	 * 
	 * @author diego
	 * @return
	 */
	public String label() default "";

	/**
	 * Mascara do campo
	 * 
	 * @author diego
	 * @return
	 */
	public String mask() default "";

	/**
	 * Tamanho maximo permitido pelo campo
	 * 
	 * @author diego
	 * @return
	 */
	public int maxsize() default 0;

	/**
	 * Mostra se é possivel nulo ou não
	 * 
	 * @author diego
	 * @return
	 */
	public boolean nullable() default true;

	/**
	 * Mostra se é editavel ou nao
	 * 
	 * @author diego
	 * @return
	 */
	public boolean readonly() default false;

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
	/**
	 * Campo desabilitado
	 * 
	 * @author diego
	 * @return
	 */
	public boolean disable() default false;
}